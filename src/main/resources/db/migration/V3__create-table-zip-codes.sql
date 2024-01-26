CREATE TABLE tb_zip_codes(
    zip_code VARCHAR(8) NOT NULL,
    street VARCHAR(150) NOT NULL,
    area VARCHAR(150) NOT NULL,
    city VARCHAR(150) NOT NULL,
    state VARCHAR(75) NOT NULL,

    PRIMARY KEY(zip_code)
);