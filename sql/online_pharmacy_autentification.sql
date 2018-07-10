CREATE TABLE online_pharmacy.autentification
(
    client_cl_id int(11) PRIMARY KEY NOT NULL,
    au_login varchar(45) NOT NULL COMMENT 'Login',
    au_password varchar(45) NOT NULL COMMENT 'password',
    au_access_level varchar(45) COMMENT 'access level',
    CONSTRAINT fk_autentification_client_id FOREIGN KEY (client_cl_id) REFERENCES client (cl_id) ON UPDATE CASCADE
);
CREATE INDEX fk_au_access_level_idx ON online_pharmacy.autentification (au_access_level);
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (1, 'mikh@gmail.com', '827457162', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (2, 'farse@gmail.com', '4525827162', 'doctor');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (3, 'gord@gmail.com', '357743782', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (4, 'msiiii@gmail.com', '576537748', 'pharmacist');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (5, 'msuuus@gmail.com', '827563690', 'pharmacist');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (6, 'msdgsd@gmail.com', '4856930548', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (7, 'mqqerr@gmail.com', '9476584676', 'doctor');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (8, '9025f6f4ea9e9609ab7cb6fb79e6ae83112be4f8', '9d4e1e23bd5b727046a9e3b4b7db57bd8d6ee684', 'pharmacist');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (9, '26b0794778e2c8d63821175ef4841ca768f292fa', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (10, '64542dbabb81dfd446e0cf4f319567c72ee57c7b', 'c8041eb80195d5e21b196820890cc00072b45dde', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (11, 'a95e85aed56318093b024674e217cae0bd30241d', '1cababc75b100f2ff47f40eecf8b1a2183170d06', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (12, '74c72bf55be8b1f4fcdc80fb1a3a59bb086da285', '58d199cb5d913c5f6048560d4c0c9e9b15353008', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (13, '88373916d5c4806b3fba03880331467dae91703', '8bc3de018bcba38da772feafec612a8fd96a6fa4', null);
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (14, '186cd74009911bf433778c1fafff6ce90dd47b69', '8d9bde9c9772df4fb80845a3b99781d3cde5ae33', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (15, '8de231a5302a6b20cbc549ff750c2e8c9bfe22ce', '88eaef03346587695018108de831fc5f65380f75', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (16, '4b3ea89d0f907f618af5c48ce080d3d94dd87e63', '314642e9f0727bc4dfd81f6a33d9482c1cc67ad0', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (17, '43d8826885f22db74fa990f11f2edd7ebb15ab49', '4d29d0b4784c86f7dd0f3386d70dbd2868779ec2', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (18, 'a75822a1c785c91507fd2f32e4c5c561fe31b2f', 'f3555a396cc58256c39b9b06b783363a2f7c5a84', 'client');
INSERT INTO online_pharmacy.autentification (client_cl_id, au_login, au_password, au_access_level) VALUES (19, '8b5484afc09eacd34d9e0d4a045b1cb0f4021e69', 'bd90b595cdbc7eb0946568d6204a2682b695635a', 'client');