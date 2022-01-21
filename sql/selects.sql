/* liczba punktow */
SELECT 
	COUNT (*)
FROM 
	projekt.punkt;

/* liczba wycieczek */
SELECT
	COUNT(*)
FROM
	projekt.wycieczka;
	
/* prosta lista wszyskich punktow */
SELECT
	nazwa,
	szerokosc_geograficzna,
	wysokosc_geograficzna,
	typ
FROM 
	projekt.points_view;

/* szczegoly dla doliny (punkt_typ_id = 2) */
SELECT
	wysokosc
FROM
	projekt.dolina
WHERE
	punkt_id = #;

/* szczegoly dla przeleczy (punkt_typ_id = 3) */
SELECT
	wysokosc,
	masyw_gorski
FROM
	projekt.przelecz
WHERE
	punkt_id = #;

/* szczegoly dla szczytu (punkt_typ_id = 4) */
SELECT
	wybitnosc,
	wysokosc
FROM
	projekt.szczyt
WHERE
	punkt_id = #;

/* szczegoly dla schroniska (punkt_typ_id = 5) */
/* 1. ilosc lozek */
SELECT
	ilosc_lozek
FROM
	projekt.schronisko
WHERE
	punkt_id = #;

/* 2. udogodnienia */
SELECT 
	projekt.udogodnienie.opis_udogodnienia
FROM
	projekt.schronisko
	projekt.schronisko_udogodnienie
	projekt.udogodnienie
WHERE
	projekt.schronisko.punkt_id = #
	AND projekt.schronisko.schronisko_id = projekt.schronisko_udogodnienie.schronisko_id
	AND projekt.udogodnienie.udogodnienie_id = projekt.schronisko_udogodnienie.udogodnienie_id

/* wszyscy sasiedzi danego punktu */
SELECT 
	DISTINCT nazwa
FROM
	projekt.punkt
INNER JOIN projekt.szlak ON 
	szlak.stop_punkt_id = punkt.punkt_id
WHERE
	szlak.start_punkt_id = 3;

/* wszystkie szlaki rozpoczynajace sie w danym punkcie */
SELECT 
	szlak.stop_punkt_id,
	szlak.w_gore,
	szlak.w_dol,
	szlak.czas_przejscia,
	droga.dlugosc,
	droga.kolor,
	droga.droga_id,
	szlak.szlak_id
FROM
	projekt.szlak
INNER JOIN projekt.droga ON
	szlak.droga_id = droga.droga_id
WHERE
	szlak.start_punkt_id = #;

/* wszystkie atrakcje dla danego szlaku */
SELECT
	atrakcja_typ,
	opis_atrakcji
FROM
	projekt.droga_atrakcja
INNER JOIN projekt.atrakcja_typ ON
	droga_atrakcja.atrakcja_typ_id = atrakcja_typ.atrakcja_typ_id
WHERE
	droga_id = #;

/* wszystkie zagrozenia dla danego szlaku */
SELECT
	zagrozenie_typ,
	zagrozenie_opis
FROM
	projekt.droga_zagrozenie
INNER JOIN projekt.zagrozenie_typ ON
	droga_zagrozenie.zagrozenie_typ_id = zagrozenie_typ.zagrozenie_typ_id
WHERE
	droga_id = #;

/* wszystkie etapy dla wycieczki (wycieczka_szlak) */
SELECT
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
	wycieczka_szlak.wycieczka_id = #
	AND wycieczka_szlak.szlak_id = szlak.szlak_id
	AND szlak.start_punkt_id = sp.punkt_id
	AND szlak.stop_punkt_id = ep.punkt_id
	AND szlak.droga_id = droga.droga_id
ORDER BY
	wycieczka_szlak.kolejnosc ASC;

/* wszystkie dostepne schroniskowe udogodnienia */
SELECT 
	*
FROM
	projekt.udogodnienie;
