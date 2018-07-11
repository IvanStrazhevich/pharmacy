CREATE TABLE online_pharmacy.client_amount
(
    clam_client_id int(11) PRIMARY KEY NOT NULL,
    clam_amount_debet decimal(10),
    clam_amount_credit decimal(10),
    CONSTRAINT client_amount_client_cl_id_fk FOREIGN KEY (clam_client_id) REFERENCES client (user_id)
);
CREATE UNIQUE INDEX client_amount_clam_client_id_uindex ON online_pharmacy.client_amount (clam_client_id);