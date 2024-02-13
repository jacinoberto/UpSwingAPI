CREATE TABLE tb_completed_subjects(
	id_completed_subject UUID,
	subject_id UUID NOT NULL,
	class_id UUID NOT NULL,
	complete BOOLEAN NOT NULL,

	PRIMARY KEY(id_completed_subject),
	CONSTRAINT fk_subject_completed_subject_id FOREIGN KEY(subject_id) REFERENCES tb_subjects(id_subject),
	CONSTRAINT fk_class_completed_subject_id FOREIGN KEY(class_id) REFERENCES tb_classes(id_class)
);