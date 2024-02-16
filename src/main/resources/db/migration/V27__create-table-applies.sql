CREATE TABLE tb_auto_applies(
    id_auto_apply UUID,
    enable_auto_apply BOOLEAN NOT NULL,
    contract VARCHAR(50),
    offer_location VARCHAR(50),
    student_Id VARCHAR(100) NOT NULL,

    PRIMARY KEY(id_auto_apply),
    CONSTRAINT fk_student_auto_apply_id FOREIGN KEY(student_Id) REFERENCES tb_students(id_student)
);