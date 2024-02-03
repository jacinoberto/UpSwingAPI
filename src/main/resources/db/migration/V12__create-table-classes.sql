CREATE TABLE tb_classes(
	id_class UUID,
	code INT NOT NULL,
	course_id UUID NOT NULL,
	mode VARCHAR(50),
	shift VARCHAR(50) NOT NULL,
	start_date DATE NOT NULL,
	closing_date DATE NOT NULL,
	vacancy_number INT NOT NULL,
	active BOOLEAN NOT NULL,

	PRIMARY KEY(id_class),
	CONSTRAINT fk_course_class_id FOREIGN KEY(course_id) REFERENCES tb_courses(id_course)
);