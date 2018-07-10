CREATE TABLE online_pharmacy.access_level
(
    acc_lvl_name varchar(45) PRIMARY KEY NOT NULL
);
INSERT INTO online_pharmacy.access_level (acc_lvl_name) VALUES ('client');
INSERT INTO online_pharmacy.access_level (acc_lvl_name) VALUES ('doctor');
INSERT INTO online_pharmacy.access_level (acc_lvl_name) VALUES ('pharmacist');