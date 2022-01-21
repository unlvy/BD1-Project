/* dodaje punkt skrzyzowanie szlakow */
CREATE OR REPLACE FUNCTION projekt.nowy_punkt_skrzyzowanie(nowa_nazwa VARCHAR, szerokosc INTEGER, dlugosc INTEGER) 
	RETURNS VOID AS
$$  
BEGIN
	INSERT INTO projekt.punkt(punkt_typ_id, nazwa, szerokosc_geograficzna, wysokosc_geograficzna) VALUES(1, nowa_nazwa, szerokosc, dlugosc);
END;
$$
LANGUAGE plpgsql;

/* dodaje punkt doline */
CREATE OR REPLACE FUNCTION projekt.nowy_punkt_dolina(nowa_nazwa VARCHAR, szerokosc INTEGER, dlugosc INTEGER, wysokosc INTEGER) 
	RETURNS VOID AS
$$
DECLARE
	nowy_punkt_id INTEGER;
BEGIN
	INSERT INTO projekt.punkt(punkt_typ_id, nazwa, szerokosc_geograficzna, wysokosc_geograficzna) VALUES(2, nowa_nazwa, szerokosc, dlugosc) RETURNING punkt_id INTO nowy_punkt_id;
	INSERT INTO projekt.dolina(punkt_id, wysokosc) VALUES(nowy_punkt_id, wysokosc);
END;
$$
LANGUAGE plpgsql;

/* dodaje punkt przelecz */
CREATE OR REPLACE FUNCTION projekt.nowy_punkt_przelecz(nowa_nazwa VARCHAR, szerokosc INTEGER, dlugosc INTEGER, wysokosc INTEGER, masyw VARCHAR) 
	RETURNS VOID AS
$$
DECLARE
	nowy_punkt_id INTEGER;
BEGIN
	INSERT INTO projekt.punkt(punkt_typ_id, nazwa, szerokosc_geograficzna, wysokosc_geograficzna) VALUES(3, nowa_nazwa, szerokosc, dlugosc) RETURNING punkt_id INTO nowy_punkt_id;
	INSERT INTO projekt.przelecz(punkt_id, wysokosc, masyw_gorski) VALUES(nowy_punkt_id, wysokosc, masyw);
END;
$$
LANGUAGE plpgsql;

/* dodaje punkt szczyt */
CREATE OR REPLACE FUNCTION projekt.nowy_punkt_szczyt(nowa_nazwa VARCHAR, szerokosc INTEGER, dlugosc INTEGER, wysokosc INTEGER, wybitnosc INTEGER) 
	RETURNS VOID AS
$$
DECLARE
	nowy_punkt_id INTEGER;
BEGIN
	INSERT INTO projekt.punkt(punkt_typ_id, nazwa, szerokosc_geograficzna, wysokosc_geograficzna) VALUES(4, nowa_nazwa, szerokosc, dlugosc) RETURNING punkt_id INTO nowy_punkt_id;
	INSERT INTO projekt.szczyt(punkt_id, wysokosc, wybitnosc) VALUES(nowy_punkt_id, wysokosc, wybitnosc);
END;
$$
LANGUAGE plpgsql;

/* dodaje punkt schronisko */
CREATE OR REPLACE FUNCTION projekt.nowy_punkt_schronisko(nowa_nazwa VARCHAR, szerokosc INTEGER, dlugosc INTEGER, lozka INTEGER, udogodnienia INTEGER[]) 
	RETURNS VOID AS
$$
DECLARE
	id INTEGER;
	i INTEGER;
BEGIN
	INSERT INTO projekt.punkt(punkt_typ_id, nazwa, szerokosc_geograficzna, wysokosc_geograficzna) VALUES(5, nowa_nazwa, szerokosc, dlugosc) RETURNING punkt_id INTO id;
	INSERT INTO projekt.schronisko(punkt_id, ilosc_lozek) VALUES(id, lozka) RETURNING schronisko_id INTO id;
	IF array_length(udogodnienia, 1) > 0 THEN
		FOR i IN 1 .. array_upper(udogodnienia, 1)
		LOOP
			INSERT INTO projekt.schronisko_udogodnienie VALUES(udogodnienia[i], id);
		END LOOP;
	END IF;
END;
$$
LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION projekt.nowa_droga_nowe_szlaki(punkt_a_id INTEGER, punkt_b_id INTEGER, czas_a_b INTEGER, 
	czas_b_a INTEGER, w_gore_a_b INTEGER, w_dol_a_b INTEGER, nowa_dlugosc INTEGER, nowy_kolor VARCHAR,
	atrakcje_id INTEGER[], atrakcje_opisy VARCHAR[], zagrozenia_id INTEGER[], zagrozenia_opisy VARCHAR[]) 
	RETURNS VOID AS
$$
DECLARE
	id INTEGER;
	i INTEGER;
BEGIN
	/* sprawda czy przekazane dane sa ze soba zgodne */
	IF array_length(atrakcje_id, 1) != array_length(atrakcje_opisy, 1) THEN
		RETURN;
	ELSIF array_length(zagrozenia_id, 1) != array_length(zagrozenia_opisy, 1) THEN
		RETURN;
	END IF;

	/* dodanie drogi */
	INSERT INTO projekt.droga(dlugosc, kolor) VALUES(nowa_dlugosc, nowy_kolor) RETURNING droga_id INTO ID;

	/* atrakcje */
	IF array_length(atrakcje_id, 1) > 0 THEN
		FOR i IN 1 .. array_upper(atrakcje_id, 1)
		LOOP
			INSERT INTO projekt.droga_atrakcja(opis_atrakcji, droga_id, atrakcja_typ_id) VALUES(atrakcje_opisy[i], id, atrakcje_id[i]);
		END LOOP;
	END IF;

	/* zagrozenia */
	IF array_length(zagrozenia_id, 1) > 0 THEN
		FOR i IN 1 .. array_upper(zagrozenia_id, 1)
		LOOP
			INSERT INTO projekt.droga_zagrozenie(zagrozenie_opis, droga_id, zagrozenie_typ_id) VALUES(zagrozenia_opisy[i], id, zagrozenia_id[i]);
		END LOOP;
	END IF;

	/* dodanie szlakow: A->B */
	INSERT INTO projekt.szlak(start_punkt_id, stop_punkt_id, w_gore, w_dol, droga_id, czas_przejscia) VALUES(punkt_a_id, punkt_b_id, w_gore_a_b, w_dol_a_b, id, czas_a_b);
	/* dodanie szlakow: B->A */
	INSERT INTO projekt.szlak(start_punkt_id, stop_punkt_id, w_gore, w_dol, droga_id, czas_przejscia) VALUES(punkt_b_id, punkt_a_id, w_dol_a_b, w_gore_a_b, id, czas_b_a);
END;
$$
LANGUAGE plpgsql;

/* zwraca VARCHAR z wszystkimi udogodnieniami dla danego schroniska */
CREATE OR REPLACE FUNCTION projekt.lista_udogodnien(schronisko_id_in INTEGER) 
	RETURNS VARCHAR AS
$$
DECLARE
	schronisko_id_rel INTEGER;
	rec RECORD;
	result VARCHAR := '';
BEGIN
	SELECT schronisko_id FROM projekt.schronisko WHERE punkt_id = schronisko_id_in INTO schronisko_id_rel;
	FOR rec IN (
		SELECT 
			opis_udogodnienia 
		FROM 
			projekt.schronisko_udogodnienie 
		INNER JOIN projekt.udogodnienie ON 
			udogodnienie.udogodnienie_id = schronisko_udogodnienie.udogodnienie_id 
		WHERE 
			schronisko_id_rel = schronisko_id
		)
	LOOP
		result := result || rec.opis_udogodnienia || '\n';
	END LOOP;
	RETURN result;
END;
$$
LANGUAGE plpgsql;

/* zwraca VARCHAR: liste sasiednich punktow */
CREATE OR REPLACE FUNCTION projekt.lista_sasiadow(punkt_id_in INTEGER) 
	RETURNS VARCHAR AS
$$
DECLARE
	rec RECORD;
	result VARCHAR := '';
BEGIN

	FOR rec IN (
		SELECT 
			DISTINCT nazwa
		FROM
			projekt.punkt
		INNER JOIN projekt.szlak ON 
			szlak.stop_punkt_id = punkt.punkt_id
		WHERE
			szlak.start_punkt_id = punkt_id_in
		)
	LOOP
		result := result || rec.nazwa || '\n';
	END LOOP;
	RETURN result;
END;
$$
LANGUAGE plpgsql;
