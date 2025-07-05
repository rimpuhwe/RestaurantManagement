ALTER TABLE customer
    ADD restaurant_id BIGINT;

ALTER TABLE customer
    ADD CONSTRAINT FK_CUSTOMER_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);