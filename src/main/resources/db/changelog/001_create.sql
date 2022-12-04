CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    phone_number TEXT
);
CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    vin_code VARCHAR (20),
    customer_id INT NOT NULL REFERENCES customers(id)
);
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    prepayment DOUBLE PRECISION,
    delivered BOOLEAN DEFAULT FALSE,
    card_payment BOOLEAN DEFAULT FALSE,
    note VARCHAR (50),
    car_id INT REFERENCES cars(id),
    customer_id INT NOT NULL REFERENCES customers(id)
);
CREATE TABLE details (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    amount INT,
    purchase_price DOUBLE PRECISION,
    retail_price DOUBLE PRECISION,
    supplier VARCHAR (20),
    bringing BOOLEAN DEFAULT FALSE,
    order_id INT REFERENCES orders(id)
)



