CREATE TABLE transactions(
                                    manufacturer VARCHAR(100),
                                    retailer_id VARCHAR(100),
                                    product_code VARCHAR(100),
                                    transaction_id VARCHAR(255),
                                    transaction_date DATE,
                                    transaction_quantity DECIMAL(10, 4),
                                    transaction_value DECIMAL(10, 2),
                                    PRIMARY KEY (manufacturer, retailer_id, product_code, transaction_id)
);