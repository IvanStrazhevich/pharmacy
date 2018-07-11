CREATE TABLE online_pharmacy.recipe
(
    rec_id int(11) PRIMARY KEY NOT NULL COMMENT 'unique id' AUTO_INCREMENT,
    doctor_client_cl_id int(11) NOT NULL COMMENT 'corresponds to doctor issued recipe',
    rec_medicine_mdc_id int(11) NOT NULL COMMENT 'corresponds to medicine',
    rec_client_cl_id int(11) NOT NULL COMMENT 'Corresponds to client recipe given to',
    rec_meds_quantity int(11) NOT NULL COMMENT 'quantity of medicine packs',
    rec_dosage decimal(5) NOT NULL COMMENT 'Dosage of medicine',
    rec_date_valid_till datetime NOT NULL,
    CONSTRAINT fk_recipe_doctor_id FOREIGN KEY (doctor_client_cl_id) REFERENCES doctor (user_id) ON UPDATE CASCADE,
    CONSTRAINT fk_recipe_medicine_id FOREIGN KEY (rec_medicine_mdc_id) REFERENCES medicine (mdc_id) ON UPDATE CASCADE,
    CONSTRAINT fk_recipe_client_id FOREIGN KEY (rec_client_cl_id) REFERENCES client (user_id) ON UPDATE CASCADE
);
CREATE INDEX fk_recipe_doctor1_idx ON online_pharmacy.recipe (doctor_client_cl_id);
CREATE INDEX fk_recipe_medicine1_idx ON online_pharmacy.recipe (rec_medicine_mdc_id);
CREATE INDEX fk_recipe_client1_idx ON online_pharmacy.recipe (rec_client_cl_id);
INSERT INTO online_pharmacy.recipe (rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till) VALUES (1, 7, 3, 3, 1, 10, '2018-10-15 00:00:00');
INSERT INTO online_pharmacy.recipe (rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till) VALUES (2, 2, 5, 7, 2, 400, '2018-05-20 00:00:00');
INSERT INTO online_pharmacy.recipe (rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till) VALUES (3, 2, 8, 7, 1, 500, '2018-06-19 00:00:00');
INSERT INTO online_pharmacy.recipe (rec_id, rec_doctor_user_id, rec_medicine_mdc_id, rec_client_user_id, rec_meds_quantity, rec_dosage, rec_date_valid_till) VALUES (4, 7, 3, 2, 2, 10, '2018-07-05 00:00:00');