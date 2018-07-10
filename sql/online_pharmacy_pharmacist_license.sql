CREATE TABLE online_pharmacy.pharmacist_license
(
    flic_id int(11) PRIMARY KEY NOT NULL COMMENT 'Pharmacist license id' AUTO_INCREMENT,
    flic_number varchar(45) NOT NULL COMMENT 'License number',
    flic_term_till datetime NOT NULL COMMENT 'Term till'
);
INSERT INTO online_pharmacy.pharmacist_license (flic_id, flic_number, flic_term_till) VALUES (1, 'PhL-2016-09-07', '2019-09-07 00:00:00');
INSERT INTO online_pharmacy.pharmacist_license (flic_id, flic_number, flic_term_till) VALUES (2, 'PhL-2018-06-21', '2021-06-21 00:00:00');