CREATE TABLE product
(
    id          SERIAL PRIMARY KEY,
    sku         VARCHAR(50)    NOT NULL UNIQUE,
    price       DECIMAL(10, 2) NOT NULL CHECK (price >= 0),
    description TEXT           NOT NULL,
    category    VARCHAR(100)   NOT NULL
);