CREATE TABLE online_pharmacy.order_has_medicine
(
    order_order_id int(11) NOT NULL COMMENT 'Corresponds to order',
    medicine_mdc_id int(11) NOT NULL COMMENT 'corresponds to medicine',
    ohm_med_quantity int(11) COMMENT 'Quantity of med position',
    ohm_med_sum decimal(6,2) COMMENT 'sum of  med position',
    recipe_rec_id int(11) COMMENT 'recipe if needed',
    CONSTRAINT `PRIMARY` PRIMARY KEY (order_order_id, medicine_mdc_id),
    CONSTRAINT fk_order_has_medicine_order1 FOREIGN KEY (order_order_id) REFERENCES `order` (order_id),
    CONSTRAINT fk_order_has_medicine_medicine1 FOREIGN KEY (medicine_mdc_id) REFERENCES medicine (mdc_id),
    CONSTRAINT fk_order_has_medicine_recipe1 FOREIGN KEY (recipe_rec_id) REFERENCES recipe (rec_id)
);
CREATE INDEX fk_order_has_medicine_order1_idx ON online_pharmacy.order_has_medicine (order_order_id);
CREATE INDEX fk_order_has_medicine_medicine1_idx ON online_pharmacy.order_has_medicine (medicine_mdc_id);
CREATE INDEX fk_order_has_medicine_recipe1_idx ON online_pharmacy.order_has_medicine (recipe_rec_id);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (1, 2, 2, 24.24, null);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (1, 3, 1, 34.43, 1);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (2, 1, 5, 4.95, null);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (3, 7, 1, 14.55, null);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (4, 5, 2, 66.20, 2);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (4, 8, 1, 20.30, 3);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (5, 4, 1, 55.00, 4);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (6, 3, 2, 68.86, 4);
INSERT INTO online_pharmacy.order_has_medicine (order_order_id, medicine_mdc_id, ohm_med_quantity, ohm_med_sum, recipe_rec_id) VALUES (6, 7, 1, 14.55, null);