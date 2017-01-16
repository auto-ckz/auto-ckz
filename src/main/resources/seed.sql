-- Dodawanie kont odbywa się w metodzie initialize klasy AccountService
INSERT INTO address (id, street, city, houseNumber, zipCode) VALUES (1, 'Ikara', 'Gdynia', '2', '82-123');
INSERT INTO address (id, street, city, houseNumber, zipCode) VALUES (2, 'Dedala', 'Gdynia', '4', '81-421');

INSERT INTO client (id, address_id, firstName, lastName, phoneNumber, pesel) VALUES (1, 1, 'Stefan', 'Klient', '943255235', '93435632544');
INSERT INTO client (id, address_id, firstName, lastName, phoneNumber, pesel) VALUES (2, 2, 'Wiesław', 'Klient', '987654321','93256524612');

INSERT INTO car (id, clientid, make, model, year, registrationNumber, vin, dateOfFirstRegistration, vehiclecheckup, vehiclemileage, engineCapacity, enginePower, fuelType) VALUES (1, 1, 'Polonez', 'Caro', 1988, 'GA234f','12345678901234567', '1988-10-05', true, 300103, '1.2', '62 KM', 'benzyna');
INSERT INTO car (id, clientid, make, model, year, registrationNumber, vin, dateOfFirstRegistration, vehiclecheckup, vehiclemileage, engineCapacity, enginePower, fuelType) VALUES (2, 2, 'Fiat', '125p', 1972, 'GDA235', '23465345324234434', '1975-12-04', true, 242433, '1.5', '78 KM', 'gaz');

INSERT INTO mechanic (id, firstName, lastName, phoneNumber) VALUES (1, 'Zbigniew', 'Mechanik', '92042453923');
INSERT INTO mechanic (id, firstName, lastName, phoneNumber) VALUES (2, 'Stas', 'Mechanik', '93024243563');

INSERT INTO memberofcustomerservice (id, firstName, lastName, phoneNumber) VALUES (1, 'Stefan', 'Obsluga', '111222333');

INSERT INTO repairorder (id, carid, clientid, memberofcustomerserviceid, totalCost, description, date) VALUES (1, 1, 1, 1, 1500, 'Naprawa zawieszenia', '2016-12-04');
INSERT INTO repairorder (id, carid, clientid, memberofcustomerserviceid, totalCost, description, date) VALUES (2, 2, 2, 1, 1200, 'Elektryka', '2016-12-17');

INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (1, 1, 1, 'Wymiana amortyzatorów', 'NOT_STARTED');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (2, 1, 1, 'Wymiana wachaczy', 'IN_PROGRESS');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (3, 2, 1, 'Wymiana lamp przednich', 'SUSPENDED');
INSERT INTO repair (id, repairorderid, mechanicid, description, status) VALUES (4, 2, 2, 'Montaż alarmu', 'IN_PROGRESS');
