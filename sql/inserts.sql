/* typy punktów */
INSERT INTO projekt.punkt_typ(typ) VALUES('skrzyzowanie szlakow');
INSERT INTO projekt.punkt_typ(typ) VALUES('dolina');
INSERT INTO projekt.punkt_typ(typ) VALUES('przelecz');
INSERT INTO projekt.punkt_typ(typ) VALUES('szczyt');
INSERT INTO projekt.punkt_typ(typ) VALUES('schronisko');

/* udogodnienia schroniskowe */
INSERT INTO projekt.udogodnienie(opis_udogodnienia) VALUES('toalety');
INSERT INTO projekt.udogodnienie(opis_udogodnienia) VALUES('darmowe WiFi');
INSERT INTO projekt.udogodnienie(opis_udogodnienia) VALUES('aneks kuchenny dla gosci');
INSERT INTO projekt.udogodnienie(opis_udogodnienia) VALUES('bar');

/* zagrożenia */
INSERT INTO projekt.zagrozenie_typ(zagrozenie_typ) VALUES('dzikie zwierzeta');
INSERT INTO projekt.zagrozenie_typ(zagrozenie_typ) VALUES('spadajace kamienie');
INSERT INTO projekt.zagrozenie_typ(zagrozenie_typ) VALUES('zagrozenie lawinowe');

/* atrakcje */
INSERT INTO projekt.atrakcja_typ(atrakcja_typ) VALUES('pomnik przyrody');
INSERT INTO projekt.atrakcja_typ(atrakcja_typ) VALUES('punkt widokowy');
