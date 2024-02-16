CREATE TABLE tb_students(
    id_student VARCHAR(255) NOT NULL,
    name VARCHAR(150) NOT NULL,
    occupation VARCHAR(75),
    birth_date DATE NOT NULL,
    social_security VARCHAR(15),
    main_phone VARCHAR(15) NOT NULL,
    optional_phone VARCHAR(155),
    social_one VARCHAR(150),
    social_two VARCHAR(150),
    social_three VARCHAR(150),
    social_four VARCHAR(150),
    address_id UUID NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    active_profile BOOLEAN NOT NULL,
    role VARCHAR(20) NOT NULL,

    PRIMARY KEY(id_student),
    CONSTRAINT fk_address_student_id FOREIGN KEY(address_id) REFERENCES tb_addresses(id_address)
);