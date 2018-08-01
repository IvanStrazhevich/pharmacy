CREATE TABLE access_level
(
  acc_lvl_name VARCHAR(45) NOT NULL
    PRIMARY KEY
)
  COMMENT 'Access level types'
  ENGINE = InnoDB;

CREATE TABLE client_amount
(
  user_id            INT     NOT NULL
    PRIMARY KEY,
  clam_amount_debet  DECIMAL NULL,
  clam_amount_credit DECIMAL NULL
)
  COMMENT 'shows money amount of the client'
  ENGINE = InnoDB;

CREATE TABLE client_detail
(
  user_id     INT         NOT NULL
  COMMENT 'client id '
    PRIMARY KEY,
  cl_name     VARCHAR(45) NULL
  COMMENT 'client’s name',
  cl_lastname VARCHAR(45) NULL
  COMMENT 'client’s last name',
  cl_email    VARCHAR(45) NULL
  COMMENT 'email',
  cl_phone    VARCHAR(15) NULL
  COMMENT 'phone number',
  cl_postcode VARCHAR(10) NULL
  COMMENT 'postcode',
  cl_country  VARCHAR(45) NULL
  COMMENT 'country',
  cl_city     VARCHAR(45) NULL
  COMMENT 'city',
  cl_address  VARCHAR(45) NULL
  COMMENT 'address'
)
  COMMENT 'Base client data'
  ENGINE = InnoDB;

ALTER TABLE client_amount
  ADD CONSTRAINT client_amount_client_user_id_fk
FOREIGN KEY (user_id) REFERENCES client_detail (user_id)
  ON UPDATE CASCADE;

CREATE TABLE doctor_detail
(
  user_id           INT NOT NULL
  COMMENT 'corresponds to client_id'
    PRIMARY KEY,
  dc_license_lic_id INT NULL
  COMMENT 'Correspond to license',
  CONSTRAINT doctor_client_user_id_fk
  FOREIGN KEY (user_id) REFERENCES client_detail (user_id)
    ON UPDATE CASCADE
)
  COMMENT 'Doctor extends client, license info'
  ENGINE = InnoDB;

CREATE INDEX fk_doctor_license1_idx
  ON doctor_detail (dc_license_lic_id);

CREATE TABLE doctor_license
(
  dlic_id        INT AUTO_INCREMENT
  COMMENT 'Doctor”s license id'
    PRIMARY KEY,
  dlic_number    VARCHAR(45) NOT NULL
  COMMENT 'License number',
  dlic_term_till DATETIME    NOT NULL
  COMMENT 'Term till'
)
  COMMENT 'Doctor license, number, date till'
  ENGINE = InnoDB;

ALTER TABLE doctor_detail
  ADD CONSTRAINT fk_doctor_license_id
FOREIGN KEY (dc_license_lic_id) REFERENCES doctor_license (dlic_id)
  ON UPDATE CASCADE
  ON DELETE SET NULL;

CREATE TABLE medicine
(
  mdc_id              INT AUTO_INCREMENT
  COMMENT 'medicine id'
    PRIMARY KEY,
  mdc_name            VARCHAR(45)   NULL
  COMMENT 'medicine name',
  mdc_description     LONGTEXT      NULL
  COMMENT 'medicine description',
  mdc_dosage          DECIMAL(5)    NULL
  COMMENT 'medicine dosage',
  mdc_recipe_required TINYINT       NULL
  COMMENT 'recipe required',
  mdc_price           DECIMAL(5, 2) NULL,
  mdc_available       TINYINT(1)    NULL,
  mdc_quantity        INT           NULL
)
  COMMENT 'Medicine  name, description, dosage, recipe requirement, price'
  ENGINE = InnoDB;

CREATE TABLE `order`
(
  order_id    INT AUTO_INCREMENT
  COMMENT 'Order id'
    PRIMARY KEY,
  ord_user_id INT           NOT NULL
  COMMENT 'corresponds to client id',
  ord_payed   TINYINT       NOT NULL
  COMMENT 'confirmed payment',
  ord_med_sum DECIMAL(6, 2) NULL
  COMMENT 'Order sum 
	',
  CONSTRAINT order_client_user_id_fk
  FOREIGN KEY (ord_user_id) REFERENCES client_detail (user_id)
    ON UPDATE CASCADE
)
  COMMENT 'Contains order data: client id, medicine id, medicine quantity, payment conformation, medicine sum, recipe id'
  ENGINE = InnoDB;

CREATE INDEX order_client_user_id_fk
  ON `order` (ord_user_id);

CREATE TABLE order_has_medicine
(
  order_order_id   INT           NOT NULL
  COMMENT 'Corresponds to order',
  medicine_mdc_id  INT           NOT NULL
  COMMENT 'corresponds to medicine',
  ohm_med_quantity INT           NULL
  COMMENT 'Quantity of med position',
  ohm_med_sum      DECIMAL(6, 2) NULL
  COMMENT 'sum of  med position',
  recipe_rec_id    INT           NULL
  COMMENT 'recipe if needed',
  PRIMARY KEY (order_order_id, medicine_mdc_id),
  CONSTRAINT order_has_medicine_order_order_id_fk
  FOREIGN KEY (order_order_id) REFERENCES `order` (order_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT fk_order_has_medicine_medicine1
  FOREIGN KEY (medicine_mdc_id) REFERENCES medicine (mdc_id)
)
  COMMENT 'List of medicine for order'
  ENGINE = InnoDB;

CREATE INDEX fk_order_has_medicine_order1_idx
  ON order_has_medicine (order_order_id);

CREATE INDEX fk_order_has_medicine_medicine1_idx
  ON order_has_medicine (medicine_mdc_id);

CREATE INDEX fk_order_has_medicine_recipe1_idx
  ON order_has_medicine (recipe_rec_id);

CREATE TABLE payment
(
  payment_id    INT           NOT NULL
  COMMENT 'payment id'
    PRIMARY KEY,
  pmt_order_id  INT           NOT NULL
  COMMENT 'Corresponds to order',
  pmt_ord_sum   DECIMAL(6, 2) NOT NULL
  COMMENT 'Corresponds to sum',
  pmt_confirmed TINYINT       NOT NULL
  COMMENT 'Is payment confirmed
	',
  CONSTRAINT payment_order_order_id_fk
  FOREIGN KEY (pmt_order_id) REFERENCES `order` (order_id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  COMMENT 'Payment info: id, order id, payment confirmation, order sum'
  ENGINE = InnoDB;

CREATE INDEX fk_payment_order1_idx
  ON payment (pmt_order_id);

CREATE INDEX fk_payment_order_sum_idx
  ON payment (pmt_ord_sum);

CREATE INDEX fk_payment_confirmed
  ON payment (pmt_confirmed);

CREATE TABLE pharmacist_detail
(
  user_id           INT NOT NULL
  COMMENT 'Corresponds to client_id'
    PRIMARY KEY,
  ph_license_lic_id INT NULL
  COMMENT 'Corresponds to license',
  CONSTRAINT pharmacist_client_user_id_fk
  FOREIGN KEY (user_id) REFERENCES client_detail (user_id)
)
  COMMENT 'Pharmacist extends client, license info'
  ENGINE = InnoDB;

CREATE INDEX fk_doctor_license10_idx
  ON pharmacist_detail (ph_license_lic_id);

CREATE TABLE pharmacist_license
(
  flic_id        INT AUTO_INCREMENT
  COMMENT 'Pharmacist license id'
    PRIMARY KEY,
  flic_number    VARCHAR(45) NOT NULL
  COMMENT 'License number',
  flic_term_till DATETIME    NOT NULL
  COMMENT 'Term till'
)
  COMMENT 'Pharmacist license, number, date till'
  ENGINE = InnoDB;

ALTER TABLE pharmacist_detail
  ADD CONSTRAINT fk_pharmacist_license_id
FOREIGN KEY (ph_license_lic_id) REFERENCES pharmacist_license (flic_id)
  ON UPDATE CASCADE
  ON DELETE SET NULL;

CREATE TABLE pharmacy_account
(
  phac_user_id        INT     NOT NULL
    PRIMARY KEY,
  phac_account_debet  DECIMAL NULL,
  phac_account_credit DECIMAL NULL,
  CONSTRAINT pharmacy_account_client_user_id_fk
  FOREIGN KEY (phac_user_id) REFERENCES client_detail (user_id)
    ON UPDATE CASCADE
)
  ENGINE = InnoDB;

CREATE TABLE recipe
(
  rec_id              INT AUTO_INCREMENT
  COMMENT 'unique id'
    PRIMARY KEY,
  rec_doctor_user_id  INT        NOT NULL
  COMMENT 'corresponds to doctor issued recipe',
  rec_medicine_mdc_id INT        NOT NULL
  COMMENT 'corresponds to medicine',
  rec_client_user_id  INT        NOT NULL
  COMMENT 'Corresponds to client recipe given to',
  rec_meds_quantity   INT        NOT NULL
  COMMENT 'quantity of medicine packs',
  rec_dosage          DECIMAL(5) NOT NULL
  COMMENT 'Dosage of medicine',
  rec_date_valid_till DATETIME   NOT NULL,
  res_approved        TINYINT(1) NULL,
  CONSTRAINT recipe_doctor_user_id_fk
  FOREIGN KEY (rec_doctor_user_id) REFERENCES doctor_detail (user_id)
    ON UPDATE CASCADE,
  CONSTRAINT fk_recipe_medicine_id
  FOREIGN KEY (rec_medicine_mdc_id) REFERENCES medicine (mdc_id)
    ON UPDATE CASCADE,
  CONSTRAINT recipe_client_user_id_fk
  FOREIGN KEY (rec_client_user_id) REFERENCES client_detail (user_id)
    ON UPDATE CASCADE
)
  COMMENT 'Recipe on medicine issued by doctor to pharmacy on a name of a client'
  ENGINE = InnoDB;

CREATE INDEX recipe_doctor_user_id_fk
  ON recipe (rec_doctor_user_id);

CREATE INDEX fk_recipe_medicine1_idx
  ON recipe (rec_medicine_mdc_id);

CREATE INDEX recipe_client_user_id_fk
  ON recipe (rec_client_user_id);

ALTER TABLE order_has_medicine
  ADD CONSTRAINT fk_order_has_medicine_recipe1
FOREIGN KEY (recipe_rec_id) REFERENCES recipe (rec_id);

CREATE TABLE user
(
  user_id           INT AUTO_INCREMENT
    PRIMARY KEY,
  user_login        VARCHAR(45) NOT NULL
  COMMENT 'Login',
  user_password     VARCHAR(45) NOT NULL
  COMMENT 'password',
  user_access_level VARCHAR(45) NULL
  COMMENT 'access level',
  CONSTRAINT user_user_login_uindex
  UNIQUE (user_login)
)
  COMMENT 'Contains autentification and access level data'
  ENGINE = InnoDB;

CREATE INDEX fk_au_access_level_idx
  ON user (user_access_level);

ALTER TABLE client_detail
  ADD CONSTRAINT client_user_user_id_fk
FOREIGN KEY (user_id) REFERENCES user (user_id)
  ON UPDATE CASCADE
  ON DELETE CASCADE;


