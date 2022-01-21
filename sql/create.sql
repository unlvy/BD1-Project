/* skrypt tworzący i wiążacy wszystkie tabele bazy danych */

CREATE SEQUENCE projekt.wycieczka_wycieczka_id_seq;

CREATE TABLE projekt.wycieczka (
                wycieczka_id INTEGER NOT NULL DEFAULT nextval('projekt.wycieczka_wycieczka_id_seq'),
                nazwa_wycieczki VARCHAR NOT NULL,
                czas_przejscia INTEGER NOT NULL,
                w_gore INTEGER NOT NULL,
                w_dol INTEGER NOT NULL,
                opis_wycieczki VARCHAR,
                CONSTRAINT wycieczka_pk PRIMARY KEY (wycieczka_id)
);


ALTER SEQUENCE projekt.wycieczka_wycieczka_id_seq OWNED BY projekt.wycieczka.wycieczka_id;

CREATE SEQUENCE projekt.droga_droga_id_seq;

CREATE TABLE projekt.droga (
                droga_id INTEGER NOT NULL DEFAULT nextval('projekt.droga_droga_id_seq'),
                dlugosc INTEGER NOT NULL,
                kolor VARCHAR NOT NULL,
                CONSTRAINT droga_pk PRIMARY KEY (droga_id)
);


ALTER SEQUENCE projekt.droga_droga_id_seq OWNED BY projekt.droga.droga_id;

CREATE SEQUENCE projekt.zagrozenie_typ_zagrozenie_typ_id_seq;

CREATE TABLE projekt.zagrozenie_typ (
                zagrozenie_typ_id INTEGER NOT NULL DEFAULT nextval('projekt.zagrozenie_typ_zagrozenie_typ_id_seq'),
                zagrozenie_typ VARCHAR NOT NULL,
                CONSTRAINT zagrozenie_typ_pk PRIMARY KEY (zagrozenie_typ_id)
);


ALTER SEQUENCE projekt.zagrozenie_typ_zagrozenie_typ_id_seq OWNED BY projekt.zagrozenie_typ.zagrozenie_typ_id;

CREATE SEQUENCE projekt.droga_zagrozenie_droga_zagrozenie_id_seq;

CREATE TABLE projekt.droga_zagrozenie (
                droga_zagrozenie_id INTEGER NOT NULL DEFAULT nextval('projekt.droga_zagrozenie_droga_zagrozenie_id_seq'),
                droga_id INTEGER NOT NULL,
                zagrozenie_typ_id INTEGER NOT NULL,
                zagrozenie_opis VARCHAR NOT NULL,
                CONSTRAINT droga_zagrozenie_pk PRIMARY KEY (droga_zagrozenie_id)
);


ALTER SEQUENCE projekt.droga_zagrozenie_droga_zagrozenie_id_seq OWNED BY projekt.droga_zagrozenie.droga_zagrozenie_id;

CREATE SEQUENCE projekt.atrakcja_typ_atrakcja_typ_id_seq;

CREATE TABLE projekt.atrakcja_typ (
                atrakcja_typ_id INTEGER NOT NULL DEFAULT nextval('projekt.atrakcja_typ_atrakcja_typ_id_seq'),
                atrakcja_typ VARCHAR NOT NULL,
                CONSTRAINT atrakcja_typ_pk PRIMARY KEY (atrakcja_typ_id)
);


ALTER SEQUENCE projekt.atrakcja_typ_atrakcja_typ_id_seq OWNED BY projekt.atrakcja_typ.atrakcja_typ_id;

CREATE SEQUENCE projekt.droga_atrakcja_droga_atrakcja_id_seq;

CREATE TABLE projekt.droga_atrakcja (
                droga_atrakcja_id INTEGER NOT NULL DEFAULT nextval('projekt.droga_atrakcja_droga_atrakcja_id_seq'),
                opis_atrakcji VARCHAR NOT NULL,
                droga_id INTEGER NOT NULL,
                atrakcja_typ_id INTEGER NOT NULL,
                CONSTRAINT droga_atrakcja_pk PRIMARY KEY (droga_atrakcja_id)
);


ALTER SEQUENCE projekt.droga_atrakcja_droga_atrakcja_id_seq OWNED BY projekt.droga_atrakcja.droga_atrakcja_id;

CREATE SEQUENCE projekt.punkt_typ_punkt_typ_id_seq;

CREATE TABLE projekt.punkt_typ (
                punkt_typ_id INTEGER NOT NULL DEFAULT nextval('projekt.punkt_typ_punkt_typ_id_seq'),
                typ VARCHAR NOT NULL,
                CONSTRAINT punkt_typ_pk PRIMARY KEY (punkt_typ_id)
);


ALTER SEQUENCE projekt.punkt_typ_punkt_typ_id_seq OWNED BY projekt.punkt_typ.punkt_typ_id;

CREATE SEQUENCE projekt.udogodnienie_udogodnienie_id_seq;

CREATE TABLE projekt.udogodnienie (
                udogodnienie_id INTEGER NOT NULL DEFAULT nextval('projekt.udogodnienie_udogodnienie_id_seq'),
                opis_udogodnienia VARCHAR NOT NULL,
                CONSTRAINT udogodnienie_pk PRIMARY KEY (udogodnienie_id)
);


ALTER SEQUENCE projekt.udogodnienie_udogodnienie_id_seq OWNED BY projekt.udogodnienie.udogodnienie_id;

CREATE SEQUENCE projekt.punkt_punkt_id_seq;

CREATE TABLE projekt.punkt (
                punkt_id INTEGER NOT NULL DEFAULT nextval('projekt.punkt_punkt_id_seq'),
                punkt_typ_id INTEGER NOT NULL,
                nazwa VARCHAR NOT NULL,
                szerokosc_geograficzna INTEGER NOT NULL,
                wysokosc_geograficzna INTEGER NOT NULL,
                CONSTRAINT punkt_pk PRIMARY KEY (punkt_id)
);


ALTER SEQUENCE projekt.punkt_punkt_id_seq OWNED BY projekt.punkt.punkt_id;

CREATE SEQUENCE projekt.trail_trail_id_seq;

CREATE TABLE projekt.szlak (
                szlak_id INTEGER NOT NULL DEFAULT nextval('projekt.trail_trail_id_seq'),
                start_punkt_id INTEGER NOT NULL,
                stop_punkt_id INTEGER NOT NULL,
                w_gore INTEGER NOT NULL,
                w_dol INTEGER NOT NULL,
                droga_id INTEGER NOT NULL,
                czas_przejscia INTEGER NOT NULL,
                CONSTRAINT szlak_pk PRIMARY KEY (szlak_id)
);


ALTER SEQUENCE projekt.trail_trail_id_seq OWNED BY projekt.szlak.szlak_id;

CREATE SEQUENCE projekt.wycieczka_szlak_wycieczka_szlak_id_seq;

CREATE TABLE projekt.wycieczka_szlak (
                wycieczka_szlak_id INTEGER NOT NULL DEFAULT nextval('projekt.wycieczka_szlak_wycieczka_szlak_id_seq'),
                kolejnosc INTEGER NOT NULL,
                wycieczka_id INTEGER NOT NULL,
                szlak_id INTEGER NOT NULL,
                CONSTRAINT wycieczka_szlak_pk PRIMARY KEY (wycieczka_szlak_id)
);


ALTER SEQUENCE projekt.wycieczka_szlak_wycieczka_szlak_id_seq OWNED BY projekt.wycieczka_szlak.wycieczka_szlak_id;

CREATE SEQUENCE projekt.dolina_dolina_id_seq;

CREATE TABLE projekt.dolina (
                dolina_id INTEGER NOT NULL DEFAULT nextval('projekt.dolina_dolina_id_seq'),
                punkt_id INTEGER NOT NULL,
                wysokosc INTEGER NOT NULL,
                CONSTRAINT dolina_pk PRIMARY KEY (dolina_id)
);


ALTER SEQUENCE projekt.dolina_dolina_id_seq OWNED BY projekt.dolina.dolina_id;

CREATE SEQUENCE projekt.przelecz_przelecz_id_seq;

CREATE TABLE projekt.przelecz (
                przelecz_id INTEGER NOT NULL DEFAULT nextval('projekt.przelecz_przelecz_id_seq'),
                masyw_gorski VARCHAR NOT NULL,
                wysokosc INTEGER NOT NULL,
                punkt_id INTEGER NOT NULL,
                CONSTRAINT przelecz_pk PRIMARY KEY (przelecz_id)
);


ALTER SEQUENCE projekt.przelecz_przelecz_id_seq OWNED BY projekt.przelecz.przelecz_id;

CREATE SEQUENCE projekt.schronisko_shelter_id_seq;

CREATE TABLE projekt.schronisko (
                schronisko_id INTEGER NOT NULL DEFAULT nextval('projekt.schronisko_shelter_id_seq'),
                punkt_id INTEGER NOT NULL,
                ilosc_lozek INTEGER NOT NULL,
                CONSTRAINT schronisko_pk PRIMARY KEY (schronisko_id)
);


ALTER SEQUENCE projekt.schronisko_shelter_id_seq OWNED BY projekt.schronisko.schronisko_id;

CREATE TABLE projekt.schronisko_udogodnienie (
                udogodnienie_id INTEGER NOT NULL,
                schronisko_id INTEGER NOT NULL,
                CONSTRAINT schronisko_udogodnienie_pk PRIMARY KEY (udogodnienie_id, schronisko_id)
);


CREATE SEQUENCE projekt.szczyt_szczyt_id_seq;

CREATE TABLE projekt.szczyt (
                szczyt_id INTEGER NOT NULL DEFAULT nextval('projekt.szczyt_szczyt_id_seq'),
                wybitnosc INTEGER NOT NULL,
                wysokosc INTEGER NOT NULL,
                punkt_id INTEGER NOT NULL,
                CONSTRAINT szczyt_pk PRIMARY KEY (szczyt_id)
);


ALTER SEQUENCE projekt.szczyt_szczyt_id_seq OWNED BY projekt.szczyt.szczyt_id;

ALTER TABLE projekt.wycieczka_szlak ADD CONSTRAINT wycieczka_wycieczka_szlak_fk
FOREIGN KEY (wycieczka_id)
REFERENCES projekt.wycieczka (wycieczka_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.droga_zagrozenie ADD CONSTRAINT droga_droga_zagrozenie_fk
FOREIGN KEY (droga_id)
REFERENCES projekt.droga (droga_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.szlak ADD CONSTRAINT droga_szlak_fk
FOREIGN KEY (droga_id)
REFERENCES projekt.droga (droga_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.droga_atrakcja ADD CONSTRAINT droga_droga_atrakcja_fk
FOREIGN KEY (droga_id)
REFERENCES projekt.droga (droga_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.droga_zagrozenie ADD CONSTRAINT zagrozenie_typ_szlak_zagrozenie_fk
FOREIGN KEY (zagrozenie_typ_id)
REFERENCES projekt.zagrozenie_typ (zagrozenie_typ_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.droga_atrakcja ADD CONSTRAINT atrakcja_typ_droga_atrakcja_fk
FOREIGN KEY (atrakcja_typ_id)
REFERENCES projekt.atrakcja_typ (atrakcja_typ_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.punkt ADD CONSTRAINT punkt_typ_punkt_fk
FOREIGN KEY (punkt_typ_id)
REFERENCES projekt.punkt_typ (punkt_typ_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.schronisko_udogodnienie ADD CONSTRAINT udogodnienie_schronisko_udogodnienie_fk
FOREIGN KEY (udogodnienie_id)
REFERENCES projekt.udogodnienie (udogodnienie_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.schronisko ADD CONSTRAINT punkt_schronisko_fk
FOREIGN KEY (punkt_id)
REFERENCES projekt.punkt (punkt_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.szczyt ADD CONSTRAINT punkt_szczyt_fk
FOREIGN KEY (punkt_id)
REFERENCES projekt.punkt (punkt_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.przelecz ADD CONSTRAINT punkt_przelecz_fk
FOREIGN KEY (punkt_id)
REFERENCES projekt.punkt (punkt_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.dolina ADD CONSTRAINT punkt_dolina_fk
FOREIGN KEY (punkt_id)
REFERENCES projekt.punkt (punkt_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.szlak ADD CONSTRAINT punkt_szlak_fk
FOREIGN KEY (start_punkt_id)
REFERENCES projekt.punkt (punkt_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.szlak ADD CONSTRAINT punkt_szlak_fk1
FOREIGN KEY (stop_punkt_id)
REFERENCES projekt.punkt (punkt_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.wycieczka_szlak ADD CONSTRAINT szlak_wycieczka_szlak_fk
FOREIGN KEY (szlak_id)
REFERENCES projekt.szlak (szlak_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE projekt.schronisko_udogodnienie ADD CONSTRAINT schronisko_schronisko_udogodnienie_fk
FOREIGN KEY (schronisko_id)
REFERENCES projekt.schronisko (schronisko_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;