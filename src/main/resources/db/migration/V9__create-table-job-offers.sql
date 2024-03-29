CREATE TABLE tb_job_offers(
    id_job_offer UUID,
    company_id VARCHAR(100) NOT NULL,
    job_position VARCHAR(75),
    business_area_id UUID NOT NULL,
    education_level VARCHAR(150) NOT NULL,
    contract VARCHAR(150) NOT NULL,
    journey VARCHAR(75) NOT NULL,
    salary NUMERIC(6,2) NOT NULL,
    disable_person BOOLEAN NOT NULL,
    offer_qty INT NOT NULL,
    work_schedule VARCHAR(150) NOT NULL,
    offer_description VARCHAR(150),
    closing_date DATE NOT NULL,
    benefits_mobility BOOLEAN NOT NULL,
    benefits_education BOOLEAN NOT NULL,
    benefits_health_wellness BOOLEAN NOT NULL,
    benefits_childcare BOOLEAN NOT NULL,
    benefits_meal BOOLEAN NOT NULL,
    benefits_cultural BOOLEAN NOT NULL,
    status VARCHAR(15) NOT NULL,

    PRIMARY KEY(id_job_offer),
    CONSTRAINT fk_company_job_offer_id FOREIGN KEY(company_id) REFERENCES tb_companies(id_company),
    CONSTRAINT fk_business_area_job_offer_id FOREIGN KEY(business_area_id) REFERENCES tb_business_areas(id_business_area)
);