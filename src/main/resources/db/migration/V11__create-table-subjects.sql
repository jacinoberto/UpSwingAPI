CREATE TABLE tb_subjects(
	id_subject UUID,
	subject_name VARCHAR(75) NOT NULL,
	description TEXT NOT NULL,
	course_id UUID NOT NULL,

	PRIMARY KEY(id_subject),
	CONSTRAINT fk_course_subject_id FOREIGN KEY(course_id) REFERENCES tb_courses(id_course)
);