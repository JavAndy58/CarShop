CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    phone_number TEXT
);
CREATE TABLE details (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    purchase_price DOUBLE PRECISION,
    retail_price DOUBLE PRECISION
);
CREATE TABLE cars (
    id SERIAL PRIMARY KEY,
    name VARCHAR (100),
    vin_сode VARCHAR (20),
    customer_id INT NOT NULL REFERENCES customers(id)
);
CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    prepayment DOUBLE PRECISION,
    delivered BOOLEAN,
    note VARCHAR (50),
    customer_id INT NOT NULL REFERENCES customers(id),
    car_id INT REFERENCES cars(id)
);
CREATE TABLE sets (
    id SERIAL PRIMARY KEY,
    amount INT,
    order_id INT NOT NULL REFERENCES orders(id),
    detail_id INT NOT NULL REFERENCES details(id)
)

