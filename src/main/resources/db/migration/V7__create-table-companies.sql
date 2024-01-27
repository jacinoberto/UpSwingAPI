CREATE TABLE tb_companies(
    id_company UUID,
    company_name VARCHAR(150) NOT NULL,
    trading_name VARCHAR(150),
    company_code VARCHAR(15) NOT NULL,
    business_area_id UUID NOT NULL,
    description VARCHAR(150),
    address_id UUID NOT NULL,
    website VARCHAR(150),
    main_phone VARCHAR(15) NOT NULL,
    optional_phone VARCHAR(15),
    social_one VARCHAR(150),
    social_two VARCHAR(150),
    social_three VARCHAR(150),
    social_four VARCHAR(150),
    mail VARCHAR(150) NOT NULL,
    password VARCHAR(75) NOT NULL,
    active_profile BOOLEAN NOT NULL,

    PRIMARY KEY(id_company),
    CONSTRAINT fk_business_area_company_id FOREIGN KEY(business_area_id) REFERENCES tb_business_areas(id_business_area),
    CONSTRAINT fk_address_company_id FOREIGN KEY(address_id) REFERENCES tb_addresses(id_address)
);