CREATE TABLE IF NOT EXISTS customers
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    phone_number TEXT
);
CREATE TABLE IF NOT EXISTS details
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    purchase_price DOUBLE PRECISION,
    retail_price DOUBLE PRECISION
);
CREATE TABLE IF NOT EXISTS cars
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    vin_сode TEXT,
    customer_id INT NOT NULL REFERENCES customers(id),

);
CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    prepayment DOUBLE PRECISION,
    delivered BOOLEAN,
    note TEXT,
    customer_id INT NOT NULL REFERENCES customers(id),
    car_id REFERENCES cars(id)
);
CREATE TABLE IF NOT EXISTS sets
(
    id SERIAL PRIMARY KEY,
    amount INT,
    order_id INT NOT NULL REFERENCES orders(id),
    detail_id INT NOT NULL REFERENCES details(id)
)

