CREATE TABLE tb_classes(
	id_class UUID,
	code INT NOT NULL,
	course_id UUID NOT NULL,
	learning_mode VARCHAR(50),
	shift VARCHAR(50) NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	vacancy_number INT NOT NULL,

	PRIMARY KEY(id_class),
	CONSTRAINT fk_course_class_id FOREIGN KEY(course_id) REFERENCES tb_courses(id_course)
);