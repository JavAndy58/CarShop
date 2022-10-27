CREATE TABLE IF NOT EXISTS cars
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    vin_сode TEXT
);

CREATE TABLE IF NOT EXISTS customers
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    phone_number TEXT,
    cars_id INT NOT NULL REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS details
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    purchase_price DOUBLE PRECISION ,
    retail_price DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    customer_id INT NOT NULL REFERENCES customers(id),
    prepayment DOUBLE PRECISION,
    delivered BOOLEAN
);

CREATE TABLE IF NOT EXISTS sets
(
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id),
    detail_id INT NOT NULL REFERENCES details(id),
    amount INT
)

