-- Dodawanie kont odbywa się w metodzie initialize klasy AccountService
INSERT INTO client (id, firstName, lastName, phoneNumber) VALUES (1, 'Stefan', 'Klient', '123456789');
INSERT INTO client (id, firstName, lastName, phoneNumber) VALUES (2, 'Wiesław', 'Klient', '987654321');

INSERT INTO car (id, clientid, make, model, vin, vehiclecheckup, vehiclemileage, year) VALUES (1, 1, 'Polonez', 'Caro', '12345678901234567', true, 30010, 1985);

INSERT INTO mechanic (id, firstName, lastName, phoneNumber) VALUES (1, 'Stefan', 'Mechanik', '987654321');

INSERT INTO memberofcustomerservice (id, firstName, lastName, phoneNumber) VALUES (1, 'Stefan', 'Obsluga', '111222333');

INSERT INTO repairorder (id, carid, clientid, memberofcustomerserviceid, totalCost, description, date) VALUES (1, 1, 1, 1, 1500, 'Naprawa zawieszenia', '2016-12-04');

INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (1, 1, 1, 'Wymiana amortyzatorów', 'DONE');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (2, 1, 1, 'Wymiana wachaczy', 'IN_PROGRESS');
