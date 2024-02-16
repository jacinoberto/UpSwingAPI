CREATE TABLE tb_emails(
    id_email UUID,
    email_from VARCHAR(150) NOT NULL,
    email_to VARCHAR(150) NOT NULL,
    subject VARCHAR(150) NOT NULL,
    message TEXT NOT NULL,
    date_of_dispatch DATE NOT NULL,
    student_id VARCHAR(100),
    company_id VARCHAR(100),

    PRIMARY KEY(id_email),
    CONSTRAINT fk_student_email_id FOREIGN KEY(student_id) REFERENCES tb_students(id_student),
    CONSTRAINT fk_company_email_id FOREIGN KEY(company_id) REFERENCES tb_companies(id_company)
);