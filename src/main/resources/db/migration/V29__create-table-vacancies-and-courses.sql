CREATE TABLE tb_vacancies_and_courses(
    id_vacancy_course UUID,
    job_offer_id UUID NOT NULL,
    course_id UUID NOT NULL,

    PRIMARY KEY(id_vacancy_course),
    CONSTRAINT fk_job_offer_vacancy_and_course_id FOREIGN KEY(job_offer_id) REFERENCES tb_job_offers(id_job_offer),
    CONSTRAINT fk_course_vacancy_and_course_id FOREIGN KEY(course_id) REFERENCES tb_courses(id_course)
);