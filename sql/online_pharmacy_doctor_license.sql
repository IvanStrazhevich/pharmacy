CREATE TABLE online_pharmacy.doctor_license
(
    dlic_id int(11) PRIMARY KEY NOT NULL COMMENT 'Doctor”s license id' AUTO_INCREMENT,
    dlic_number varchar(45) NOT NULL COMMENT 'License number',
    dlic_term_till datetime NOT NULL COMMENT 'Term till'
);
INSERT INTO online_pharmacy.doctor_license (dlic_id, dlic_number, dlic_term_till) VALUES (1, 'DL-2017-02-16', '2019-02-16 00:00:00');
INSERT INTO online_pharmacy.doctor_license (dlic_id, dlic_number, dlic_term_till) VALUES (2, 'DL-2018-05-20', '2020-05-20 00:00:00');