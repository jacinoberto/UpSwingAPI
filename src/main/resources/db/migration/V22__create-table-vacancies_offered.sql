CREATE TABLE tb_vacancies_offered(
    id_vacancy_offer UUID,
    student_id UUID NOT NULL,
    job_offer_id UUID NOT NULL,

    PRIMARY KEY(id_vacancy_offer),
    CONSTRAINT fk_student_vacancy_offer_id FOREIGN KEY(student_id) REFERENCES tb_students(id_student),
    CONSTRAINT fk_class_vacancy_offer_id FOREIGN KEY(job_offer_id) REFERENCES tb_job_offers(id_job_offer)
);