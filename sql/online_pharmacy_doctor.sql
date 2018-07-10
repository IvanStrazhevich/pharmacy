CREATE TABLE online_pharmacy.doctor
(
    client_cl_id int(11) PRIMARY KEY NOT NULL COMMENT 'corresponds to client_id',
    dc_license_lic_id int(11) COMMENT 'Correspond to license',
    CONSTRAINT fk_doctor_client_id FOREIGN KEY (client_cl_id) REFERENCES client (cl_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_doctor_license_id FOREIGN KEY (dc_license_lic_id) REFERENCES doctor_license (dlic_id) ON DELETE SET NULL ON UPDATE CASCADE
);
CREATE INDEX fk_doctor_license1_idx ON online_pharmacy.doctor (dc_license_lic_id);
INSERT INTO online_pharmacy.doctor (client_cl_id, dc_license_lic_id) VALUES (2, 1);
INSERT INTO online_pharmacy.doctor (client_cl_id, dc_license_lic_id) VALUES (7, 2);