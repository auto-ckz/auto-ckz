-- Dodawanie kont odbywa się w metodzie initialize klasy AccountService
INSERT INTO client (id, firstName, lastName, phoneNumber) VALUES (1, 'Stefan', 'Klient', '123456789');
INSERT INTO client (id, firstName, lastName, phoneNumber) VALUES (2, 'Wiesław', 'Klient', '987654321');

INSERT INTO car (id, clientid, make, model, registrationNumber,  vin, vehiclecheckup, vehiclemileage, year) VALUES (1, 1, 'Polonez', 'Caro', 'GA234f','12345678901234567', true, 30010, 1985);
INSERT INTO car (id, clientid, make, model, registrationNumber, vin, vehiclecheckup, vehiclemileage, year) VALUES (2, 2, 'Fiat', '125p', 'GDA235', '23465345324234434', true, 242432, 1981);

INSERT INTO mechanic (id, firstName, lastName, phoneNumber) VALUES (1, 'Zbigniew', 'Mechanik', '92042453923');
INSERT INTO mechanic (id, firstName, lastName, phoneNumber) VALUES (2, 'Stas', 'Mechanik', '93024243563');

INSERT INTO memberofcustomerservice (id, firstName, lastName, phoneNumber) VALUES (1, 'Stefan', 'Obsluga', '111222333');

INSERT INTO repairorder (id, carid, clientid, memberofcustomerserviceid, totalCost, description, date) VALUES (1, 1, 1, 1, 1500, 'Naprawa zawieszenia', '2016-12-04');
INSERT INTO repairorder (id, carid, clientid, memberofcustomerserviceid, totalCost, description, date) VALUES (2, 2, 2, 1, 1200, 'Elektryka', '2016-12-17');

INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (1, 1, 1, 'Wymiana amortyzatorów', 'NOT_STARTED');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (2, 1, 1, 'Wymiana wachaczy', 'IN_PROGRESS');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (3, 2, 1, 'Wymiana lamp przednich', 'SUSPENDED');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (4, 2, 2, 'Montaż alarmu', 'IN_PROGRESS');
