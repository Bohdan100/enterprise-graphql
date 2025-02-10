CREATE TABLE department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    duties VARCHAR(150) NOT NULL
);

    CREATE TABLE project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100),
    importance INT,
    department_id BIGINT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES department(id)
);

CREATE TABLE worker (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL
);

CREATE TABLE worker_details (
    worker_id BIGINT PRIMARY KEY,
    address VARCHAR(100),
    sex VARCHAR(255) NOT NULL,
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);

CREATE TABLE department_worker (
    department_id BIGINT NOT NULL,
    worker_id BIGINT NOT NULL,
    PRIMARY KEY (department_id, worker_id),
    FOREIGN KEY (department_id) REFERENCES department(id),
    FOREIGN KEY (worker_id) REFERENCES worker(id)
);