CREATE TABLE online_pharmacy.`order`
(
    order_id int(11) PRIMARY KEY NOT NULL COMMENT 'Order id',
    ord_client_id int(11) NOT NULL COMMENT 'corresponds to client id',
    ord_payed tinyint(4) NOT NULL COMMENT 'confirmed payment',
    ord_med_sum decimal(6,2) NOT NULL COMMENT 'Order sum 
',
    CONSTRAINT fk_order_client_id FOREIGN KEY (ord_client_id) REFERENCES client (user_id) ON UPDATE CASCADE
);
CREATE INDEX fk_order_client1_idx ON online_pharmacy.`order` (ord_client_id);
CREATE INDEX fk_pmt_ord_sum ON online_pharmacy.`order` (ord_med_sum);
INSERT INTO online_pharmacy.`order` (order_id, ord_user_id, ord_payed, ord_med_sum) VALUES (1, 3, 1, 58.57);
INSERT INTO online_pharmacy.`order` (order_id, ord_user_id, ord_payed, ord_med_sum) VALUES (2, 2, 1, 4.95);
INSERT INTO online_pharmacy.`order` (order_id, ord_user_id, ord_payed, ord_med_sum) VALUES (3, 6, 0, 14.55);
INSERT INTO online_pharmacy.`order` (order_id, ord_user_id, ord_payed, ord_med_sum) VALUES (4, 7, 1, 86.50);
INSERT INTO online_pharmacy.`order` (order_id, ord_user_id, ord_payed, ord_med_sum) VALUES (5, 5, 0, 55.00);
INSERT INTO online_pharmacy.`order` (order_id, ord_user_id, ord_payed, ord_med_sum) VALUES (6, 2, 0, 83.41);