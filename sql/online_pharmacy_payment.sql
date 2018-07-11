CREATE TABLE online_pharmacy.payment
(
    payment_id int(11) PRIMARY KEY NOT NULL COMMENT 'payment id',
    order_order_id int(11) NOT NULL COMMENT 'Corresponds to order',
    pmt_ord_sum decimal(6,2) NOT NULL COMMENT 'Corresponds to sum',
    pmt_confirmed tinyint(4) NOT NULL COMMENT 'Is payment confirmed
',
    CONSTRAINT fk_payment_order_id FOREIGN KEY (order_order_id) REFERENCES `order` (order_id) ON UPDATE CASCADE,
    CONSTRAINT fk_payment_order_sum FOREIGN KEY (pmt_ord_sum) REFERENCES `order` (ord_med_sum) ON UPDATE CASCADE
);
CREATE INDEX fk_payment_order1_idx ON online_pharmacy.payment (order_order_id);
CREATE INDEX fk_payment_order_sum_idx ON online_pharmacy.payment (pmt_ord_sum);
CREATE INDEX fk_payment_confirmed ON online_pharmacy.payment (pmt_confirmed);
INSERT INTO online_pharmacy.payment (payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed) VALUES (1, 1, 58.57, 1);
INSERT INTO online_pharmacy.payment (payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed) VALUES (2, 2, 4.95, 1);
INSERT INTO online_pharmacy.payment (payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed) VALUES (3, 3, 14.55, 0);
INSERT INTO online_pharmacy.payment (payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed) VALUES (4, 4, 86.50, 1);
INSERT INTO online_pharmacy.payment (payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed) VALUES (5, 5, 55.00, 0);
INSERT INTO online_pharmacy.payment (payment_id, pmt_order_id, pmt_ord_sum, pmt_confirmed) VALUES (6, 5, 83.41, 0);