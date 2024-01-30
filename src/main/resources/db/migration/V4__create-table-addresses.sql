CREATE TABLE tb_addresses(
    id_address UUID NOT NULL,
    zip_code_id VARCHAR(8) NOT NULL,
    number INT NOT NULL,
    complement VARCHAR(175),

    PRIMARY KEY(id_address),
    FOREIGN KEY(zip_code_id) REFERENCES tb_zip_codes(zip_code)
);