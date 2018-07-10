CREATE TABLE online_pharmacy.pharmacist
(
    client_cl_id int(11) PRIMARY KEY NOT NULL COMMENT 'Corresponds to client_id',
    ph_license_lic_id int(11) COMMENT 'Corresponds to license',
    CONSTRAINT fk_pharmacist_client_id FOREIGN KEY (client_cl_id) REFERENCES client (cl_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_pharmacist_license_id FOREIGN KEY (ph_license_lic_id) REFERENCES pharmacist_license (flic_id) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE INDEX fk_pharmacist_client1_idx ON online_pharmacy.pharmacist (client_cl_id);
CREATE INDEX fk_doctor_license10_idx ON online_pharmacy.pharmacist (ph_license_lic_id);
INSERT INTO online_pharmacy.pharmacist (client_cl_id, ph_license_lic_id) VALUES (4, 1);
INSERT INTO online_pharmacy.pharmacist (client_cl_id, ph_license_lic_id) VALUES (5, 2);