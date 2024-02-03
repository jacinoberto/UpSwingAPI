CREATE TABLE tb_courses(
    id_course UUID,
    course_name VARCHAR(150) NOT NULL,
    business_area_id UUID NOT NULL,
    education_level VARCHAR(75) NOT NULL,
    schedule INT NOT NULL,
    monthly_cost NUMERIC(6,2),
    total_cost NUMERIC(6,2),
    active BOOLEAN NOT NULL,

    PRIMARY KEY(id_course),
    CONSTRAINT fk_business_area_course_id FOREIGN KEY(business_area_id) REFERENCES tb_business_areas(id_business_area)
);