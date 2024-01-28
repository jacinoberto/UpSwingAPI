CREATE TABLE tb_admins(
    id_admin UUID NOT NULL,
    admin_position VARCHAR(75) NOT NULL,
    name VARCHAR(150) NOT NULL,
    main_phone VARCHAR(15) NOT NULL,
    optional_phone VARCHAR(155),
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(16) NOT NULL,
    active_profile BOOLEAN NOT NULL,

    PRIMARY KEY(id_admin)
);