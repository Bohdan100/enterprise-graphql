-- Insert Departments
INSERT INTO department (name, duties) VALUES
('Development', 'Software Development and Maintenance'),
('Testing', 'Software Testing and Quality Assurance'),
('DevOps', 'Infrastructure Management and Deployment'),
('Marketing', 'Promoting Software Products');

-- Insert Projects
INSERT INTO project (name, description, importance, department_id) VALUES
('Project Phoenix', 'Revamping Legacy System', 8, 1),
('Project Orion', 'Developing New Mobile App', 9, 1),
('Project Atlas', 'Cloud Infrastructure Migration', 7, 3),
('Project Titan', 'Automating Build and Deployment Processes', 8, 3),
('Project Mercury', 'Creating Marketing Campaigns', 6, 4),
('Project Apollo', 'Improving Website UX', 7, 4),
('Project Artemis', 'Performance Testing of Web Application', 8, 2),
('Project Hera', 'Security Testing of Mobile App', 9, 2),
('Project Zeus', 'Developing API Documentation', 7, 1),
('Project Poseidon', 'Setting up CI/CD Pipeline', 8, 3);

-- Insert Workers
INSERT INTO worker (first_name, last_name, position) VALUES
('John', 'Smith', 'Software Developer'),
('Alice', 'Johnson', 'Software Developer'),
('Bob', 'Williams', 'DevOps Engineer'),
('Eva', 'Brown', 'QA Engineer'),
('Michael', 'Davis', 'Software Developer'),
('Sarah', 'Wilson', 'Marketing Specialist'),
('David', 'Garcia', 'DevOps Engineer'),
('Olivia', 'Rodriguez', 'QA Engineer'),
('James', 'Martinez', 'Software Developer'),
('Emily', 'Anderson', 'Marketing Specialist'),
('Daniel', 'Taylor', 'Software Developer'),
('Sophia', 'Thomas', 'QA Engineer'),
('Joseph', 'Hernandez', 'DevOps Engineer'),
('Mia', 'Moore', 'Software Developer'),
('Benjamin', 'Martin', 'Marketing Specialist');

-- Insert Worker Details
INSERT INTO worker_details (worker_id, address, sex) VALUES
(1, '123 Main St', 'MALE'),
(2, '456 Oak Ave', 'FEMALE'),
(3, '789 Pine Ln', 'MALE'),
(4, '101 Elm St', 'FEMALE'),
(5, '222 Maple Dr', 'MALE'),
(6, '333 Cedar Ct', 'FEMALE'),
(7, '444 Birch Rd', 'MALE'),
(8, '555 Willow Way', 'FEMALE'),
(9, '666 Redwood Pl', 'MALE'),
(10, '777 Oak St', 'FEMALE'),
(11, '888 Pine Ave', 'MALE'),
(12, '999 Elm Ln', 'FEMALE'),
(13, '100 Maple Dr', 'MALE'),
(14, '200 Cedar Ct', 'FEMALE'),
(15, '300 Birch Rd', 'MALE');


-- Insert Department-Worker relationships
INSERT INTO department_worker (department_id, worker_id) VALUES
(1, 1), (1, 2), (1, 5), (1, 9), (1, 11), (1, 14), -- Development
(2, 4), (2, 8), (2, 12), -- Testing
(3, 3), (3, 7), (3, 13), -- DevOps
(4, 6), (4, 10), (4, 15); -- Marketing
