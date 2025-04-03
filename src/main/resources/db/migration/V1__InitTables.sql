CREATE DOMAIN UUID_STRING AS VARCHAR(36);
CREATE DOMAIN ISBN AS VARCHAR(17);

CREATE TABLE genres
(
    id   SMALLSERIAL,
    name VARCHAR(100) UNIQUE NOT NULL,
    CONSTRAINT pk_genres PRIMARY KEY (id)
);

CREATE TABLE books
(
    isbn             ISBN,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    genre_id         INT,
    published_year   INT,
    total_copies     INT          NOT NULL CHECK (total_copies > 0),
    available_copies INT          NOT NULL CHECK (available_copies >= 0),
    CONSTRAINT pk_books           PRIMARY KEY (isbn),
    CONSTRAINT fk_books_genre     FOREIGN KEY (genre_id) REFERENCES genres (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE clients
(
    id          UUID_STRING,
    name         VARCHAR(255)        NOT NULL,
    email        VARCHAR(255)        UNIQUE NOT NULL,
    phone_number VARCHAR(20)         NOT NULL,
    registration_date TIMESTAMP WITH TIME ZONE NOT NULL,
    active       BOOLEAN             DEFAULT TRUE,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

CREATE TABLE addresses
(
    client_id     UUID_STRING,
    street       VARCHAR(255) NOT NULL,
    number       VARCHAR(10) NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        VARCHAR(100) NOT NULL,
    country      VARCHAR(100) NOT NULL,
    postal_code  VARCHAR(20) NOT NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (client_id),
    CONSTRAINT fk_addresses_clients FOREIGN KEY (client_id) REFERENCES clients (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE loans
(
    id          UUID_STRING,
    client_id   UUID_STRING NOT NULL,
    book_isbn   ISBN NOT NULL,
    loan_date   TIMESTAMP WITH TIME ZONE NOT NULL,
    due_date    TIMESTAMP WITH TIME ZONE NOT NULL,
    return_date TIMESTAMP WITH TIME ZONE,
    loan_price  DECIMAL(10, 2) NOT NULL CHECK (loan_price >= 0),
    daily_fine  DECIMAL(10, 2) NOT NULL CHECK (daily_fine >= 0),
    CONSTRAINT pk_loans PRIMARY KEY (id),
    CONSTRAINT fk_loans_clients FOREIGN KEY (client_id) REFERENCES clients (id)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_loans_books FOREIGN KEY (book_isbn) REFERENCES books (isbn)
        ON DELETE RESTRICT ON UPDATE CASCADE
)