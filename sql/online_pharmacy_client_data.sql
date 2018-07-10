CREATE TABLE online_pharmacy.client_data
(
    cldt_id int(11) PRIMARY KEY NOT NULL COMMENT 'Corresponds to client_id',
    cldt_email varchar(45) COMMENT 'Client email',
    cldt_phone varchar(45) COMMENT 'client phone',
    cldt_address varchar(45) COMMENT 'Client address',
    cldt_delivery_address varchar(45) COMMENT 'delivery address',
    CONSTRAINT client_id FOREIGN KEY (cldt_id) REFERENCES client (cl_id) ON UPDATE CASCADE
);
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (1, 'mikh@gmail.com', '+375294567654', 'Belarus, Minsk, Nezalezhnasti ave. 47-25', 'Belarus, Minsk, Nezalezhnasti ave. 47-25');
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (2, 'farse@gmail.com', '+375448473629', 'Belarus, Minsk, Nezalezhnasti ave. 115-6', 'Belarus, Minsk, Nezalezhnasti ave. 115-6');
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (3, 'gord@gmail.com', '+375333988999', 'Belarus, Minsk, Prityckogo str. 17-23', 'Belarus, Minsk, Prityckogo str. 17-23');
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (4, 'msiiii@gmail.com', '+375255874937', 'Belarus, Minsk, Maksima Tanka str. 12-44', 'Belarus, Minsk, Maksima Tanka str. 12-44');
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (5, 'msuuus@gmail.com', '+375296458377', 'Belarus, Minsk, Partizanski ave. 127-45', 'Belarus, Minsk, Partizanski ave. 127-45');
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (6, 'msdgsd@gmail.com', '+375298374684', 'Belarus, Minsk, Miroshnichenko str. 29-16', 'Belarus, Minsk, Miroshnichenko str. 29-16');
INSERT INTO online_pharmacy.client_data (cldt_id, cldt_email, cldt_phone, cldt_address, cldt_delivery_address) VALUES (7, 'mqqerr@gmail.com', '+375445784392', 'Belarus, Minsk, Gromova str. 3-45', 'Belarus, Minsk,Gromova str. 3-45');