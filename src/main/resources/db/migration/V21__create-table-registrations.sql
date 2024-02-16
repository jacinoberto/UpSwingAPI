CREATE TABLE tb_registrations(
    registration INT,
    student_id VARCHAR(100) NOT NULL,
    class_id UUID NOT NULL,

    PRIMARY KEY(registration),
    CONSTRAINT fk_student_registration_id FOREIGN KEY(student_id) REFERENCES tb_students(id_student),
    CONSTRAINT fk_class_registration_id FOREIGN KEY(class_id) REFERENCES tb_classes(id_class)
);