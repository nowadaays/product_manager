CREATE TABLE product_category (
    id SERIAL PRIMARY KEY,
    product_id BIGINT,
    category_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);