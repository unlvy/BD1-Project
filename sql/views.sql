/* 	wszystkie punkty (uproszczona wersja) */
CREATE OR REPLACE VIEW projekt.points_view AS 
SELECT
	punkt_id,
	punkt.punkt_typ_id,
	nazwa,
	szerokosc_geograficzna,
	wysokosc_geograficzna,
	punkt_typ.typ
FROM
	projekt.punkt
INNER JOIN projekt.punkt_typ ON
	projekt.punkt.punkt_typ_id = projekt.punkt_typ.punkt_typ_id;

/* wszystkie punkty wraz z szczegolami */
CREATE OR REPLACE VIEW projekt.punkty_szczegolowo AS
SELECT DISTINCT
	punkt.punkt_id,
	punkt.nazwa,
	punkt_typ.typ,
	punkt.szerokosc_geograficzna,
	punkt.wysokosc_geograficzna,
	(SELECT * FROM projekt.lista_sasiadow(punkt.punkt_id)) AS sasiedzi,
	CASE
		WHEN punkt.punkt_typ_id = 2
			THEN 'wysokosc: ' || dolina.wysokosc
		WHEN punkt.punkt_typ_id = 3
			THEN 'wysokosc: ' || przelecz.wysokosc || '\nmasyw gorski: ' || przelecz.masyw_gorski
		WHEN punkt.punkt_typ_id = 4
			THEN 'wysokosc: ' || szczyt.wysokosc || '\nwybitnosc: ' || szczyt.wybitnosc 
		WHEN punkt.punkt_typ_id = 5
			THEN 'udogodnienia:\n ' || (SELECT * FROM projekt.lista_udogodnien(punkt.punkt_id))
		ELSE
			'brak'
	END AS szczegoly
FROM
	projekt.punkt,
	projekt.punkt_typ,
	projekt.dolina,
	projekt.przelecz,
	projekt.szczyt,
	projekt.schronisko
WHERE
	punkt.punkt_typ_id = punkt_typ.punkt_typ_id
	AND (punkt.punkt_typ_id = 1
	OR (punkt.punkt_id = dolina.punkt_id AND punkt.punkt_typ_id = 2)
	OR (punkt.punkt_id = przelecz.punkt_id AND punkt.punkt_typ_id = 3)
	OR (punkt.punkt_id = szczyt.punkt_id AND punkt.punkt_typ_id = 4)
	OR (punkt.punkt_id = schronisko.punkt_id AND punkt.punkt_typ_id = 5));

/* etapy wszystkich wycieczek */
CREATE OR REPLACE VIEW projekt.wycieczka_etap AS 
SELECT
	wycieczka_szlak.wycieczka_id,
	wycieczka_szlak.kolejnosc,
	sp.nazwa AS n1,
	ep.nazwa AS n2,
	droga.dlugosc,
	szlak.czas_przejscia,
	szlak.w_gore,
	szlak.w_dol,
	droga.kolor
FROM
	projekt.wycieczka_szlak,
	projekt.szlak,
	projekt.punkt sp,
	projekt.punkt ep,
	projekt.droga
WHERE
	wycieczka_szlak.szlak_id = szlak.szlak_id
	AND szlak.start_punkt_id = sp.punkt_id
	AND szlak.stop_punkt_id = ep.punkt_id
	AND szlak.droga_id = droga.droga_id
ORDER BY
	wycieczka_szlak.wycieczka_id ASC,
	wycieczka_szlak.kolejnosc ASC;

/* wycieczki krotkie (mniej lub 5 etapow) */
CREATE OR REPLACE VIEW projekt.krotkie_wycieczki AS
SELECT
	wycieczka_id,
	MAX(kolejnosc)
FROM
	projekt.wycieczka_szlak
GROUP BY
	wycieczka_id
HAVING
	MAX(kolejnosc) <= 5;

/* wycieczki dlugie (wiecej niz 5 etapow) */
CREATE OR REPLACE VIEW projekt.dlugie_wycieczki AS
SELECT
	wycieczka_id,
	MAX(kolejnosc)
FROM
	projekt.wycieczka_szlak
GROUP BY
	wycieczka_id
HAVING
	MAX(kolejnosc) >= 5;

/* wszystkie schroniska majace wiecej niz 2 udogodnienia */
CREATE OR REPLACE VIEW projekt.przytulne_schroniska AS
SELECT 
	schronisko.schronisko_id,
	ilosc_lozek,
	COUNT(udogodnienie_id) AS liczba_udogodnien
FROM
	projekt.schronisko
INNER JOIN projekt.schronisko_udogodnienie ON
	schronisko.schronisko_id = schronisko_udogodnienie.schronisko_id
GROUP BY
	schronisko.schronisko_id
HAVING
	COUNT(udogodnienie_id) >= 2;
