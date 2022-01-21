/* sprawdzenie, czy nazwa punktu jest unikatowa */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowy_punkt()
	RETURNS TRIGGER AS
$$
BEGIN
	IF EXISTS (SELECT 1 FROM projekt.punkt WHERE nazwa = NEW.nazwa) THEN
		RAISE EXCEPTION 'Nazwa nie jest unikatowa!';
		RETURN NULL;
	ELSIF EXISTS (SELECT 1 FROM projekt.punkt WHERE szerokosc_geograficzna = NEW.szerokosc_geograficzna AND wysokosc_geograficzna = NEW.wysokosc_geograficzna) THEN
		RAISE EXCEPTION 'Juz istnieje punkt dla danych wspolrzednych!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowy_punkt_t
    BEFORE INSERT OR UPDATE ON projekt.punkt
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowy_punkt();

/* sprawdzenie, czy nazwa wycieczki jest unikatowa */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowa_wycieczka()
	RETURNS TRIGGER AS
$$
BEGIN
	IF EXISTS (SELECT 1 FROM projekt.wycieczka WHERE nazwa_wycieczki = NEW.nazwa_wycieczki) THEN
		RAISE EXCEPTION 'Nazwa nie jest unikatowa!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowa_wycieczka_t
    BEFORE INSERT OR UPDATE ON projekt.wycieczka
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowa_wycieczka();

/* walidacja nowo dodawanego szlaku */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowy_szlak()
	RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.w_gore < 0 OR NEW.w_dol < 0 OR NEW.czas_przejscia < 0 THEN
		RAISE EXCEPTION 'Przewyzszenia nie moga byc ujemne!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowy_szlak_t
    BEFORE INSERT OR UPDATE ON projekt.szlak
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowy_szlak();


/* walidacja nowo dodawanej drogi */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowa_droga()
	RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.dlugosc <= 0 THEN
		RAISE EXCEPTION 'Dlugosc nie moze byc <= 0';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowa_droga_t
    BEFORE INSERT OR UPDATE ON projekt.droga
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowa_droga();

/* walidacja nowo dodawanej doliny */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowa_dolina()
	RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.wysokosc < 0 THEN
		RAISE EXCEPTION 'Wysokosc nie moze byc ujemna!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowa_dolina_t
    BEFORE INSERT OR UPDATE ON projekt.dolina
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowa_dolina();

/* walidacja nowo dodawanej przeleczy */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowa_przelecz()
	RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.wysokosc < 0 THEN
		RAISE EXCEPTION 'Wysokosc nie moze byc ujemna!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowa_przelecz_t
    BEFORE INSERT OR UPDATE ON projekt.przelecz
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowa_przelecz();

/* walidacja nowo dodawanego szczytu */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowy_szczyt()
	RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.wysokosc < 0 OR NEW.wybitnosc > NEW.wysokosc THEN
		RAISE EXCEPTION 'Wysokosc nie moze byc ujemna! Wybitnosc nie moze byc wieksza niz wysokosc!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowy_szczyt_t
    BEFORE INSERT OR UPDATE ON projekt.szczyt
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowy_szczyt();

/* walidacja nowo dodawanego schroniska */
CREATE OR REPLACE FUNCTION projekt.walidacja_nowe_schronisko()
	RETURNS TRIGGER AS
$$
BEGIN
	IF NEW.ilosc_lozek < 0 THEN
		RAISE EXCEPTION 'Wysokosc nie moze byc ujemna! Wybitnosc nie moze byc wieksza niz wysokosc!';
		RETURN NULL;
	END IF;
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE TRIGGER walidacja_nowe_schronisko_t
    BEFORE INSERT OR UPDATE ON projekt.schronisko
    FOR EACH ROW EXECUTE PROCEDURE projekt.walidacja_nowe_schronisko();
