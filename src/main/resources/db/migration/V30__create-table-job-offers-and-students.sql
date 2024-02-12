CREATE TABLE tb_job_offers_and_students (
    id_vacancy_student UUID,
    job_offer_id UUID NOT NULL,
    student_id UUID NOT NULL,

    PRIMARY KEY (id_vacancy_student),
    CONSTRAINT fk_job_offer_job_offer_and_student_id FOREIGN KEY (job_offer_id) REFERENCES tb_job_offers (id_job_offer),
    CONSTRAINT fk_student_job_offer_and_student_id FOREIGN KEY (student_id) REFERENCES tb_students (id_student)
);