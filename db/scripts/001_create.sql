CREATE TABLE IF NOT EXISTS cars
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    vinCode TEXT
);

CREATE TABLE IF NOT EXISTS customers
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    phoneNumber TEXT,
    cars_id INT NOT NULL REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS details
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    purchasePrice DOUBLE PRECISION ,
    amount INT,
    retailPrice DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    created TIMESTAMP,
    customer_id INT NOT NULL REFERENCES customers(id),
    detail_id INT NOT NULL REFERENCES details(id),
    prepayment DOUBLE PRECISION,
    delivered BOOLEAN
)