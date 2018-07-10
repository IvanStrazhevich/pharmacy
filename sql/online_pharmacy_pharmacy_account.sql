CREATE TABLE online_pharmacy.pharmacy_account
(
    phac_account_debet decimal(10),
    phac_account_credit decimal(10),
    phac_client_id int(11) PRIMARY KEY NOT NULL,
    CONSTRAINT pharmacy_account_client_cl_id_fk FOREIGN KEY (phac_client_id) REFERENCES client (cl_id)
);
CREATE UNIQUE INDEX pharmacy_account_phac_client_id_uindex ON online_pharmacy.pharmacy_account (phac_client_id);