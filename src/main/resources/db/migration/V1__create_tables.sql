CREATE TABLE department (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(200) NOT NULL,
                            duties VARCHAR(150) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE project (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         description VARCHAR(100) NOT NULL,
                         importance INT NOT NULL CHECK (importance >= 1),
                         department_id BIGINT NOT NULL,
                         CONSTRAINT fk_project_department FOREIGN KEY (department_id) REFERENCES department(id)
) ENGINE=InnoDB;

CREATE TABLE worker (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100) NOT NULL,
                        position VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE worker_details (
                                worker_id BIGINT PRIMARY KEY,
                                address VARCHAR(100) NOT NULL,
                                sex VARCHAR(20) NOT NULL,
                                CONSTRAINT fk_details_worker FOREIGN KEY (worker_id) REFERENCES worker(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE department_worker (
                                   department_id BIGINT NOT NULL,
                                   worker_id BIGINT NOT NULL,
                                   PRIMARY KEY (department_id, worker_id),
                                   CONSTRAINT fk_dw_department FOREIGN KEY (department_id) REFERENCES department(id),
                                   CONSTRAINT fk_dw_worker FOREIGN KEY (worker_id) REFERENCES worker(id)
) ENGINE=InnoDB;