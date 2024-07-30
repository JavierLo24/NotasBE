-- -------------------------------------------------------------------------------------------------------------------------
-- Drop tables before
DROP TABLE IF EXISTS teacher CASCADE;
DROP TABLE IF EXISTS course CASCADE;
DROP TABLE IF EXISTS grades CASCADE;
DROP TABLE IF EXISTS usuarios CASCADE;
DROP TABLE IF EXISTS rol CASCADE;
DROP TABLE IF EXISTS student CASCADE;

-- Create userES table.
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(20),
    dni VARCHAR(255),
    cellphone VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS rol (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255)
    );

-- Create students table.
CREATE TABLE IF NOT EXISTS student (
    id SERIAL PRIMARY KEY,
    school_grade VARCHAR(255),
    date_born DATE
    );

-- Create teacher table.
CREATE TABLE IF NOT EXISTS teacher (
    id SERIAL PRIMARY KEY,
    knowledge VARCHAR(255),
    assignment VARCHAR(255)
    );

-- Create course table.
CREATE TABLE IF NOT EXISTS course (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
    );

-- Create grades table.
CREATE TABLE IF NOT EXISTS grades (
    id SERIAL PRIMARY KEY,
    grade DOUBLE PRECISION,
    desc_grade VARCHAR(100)
    );

-- ---------------------------------------------------------------------------------------------------------------------------

------------------------------- CONSTRAINT ABOUT FOREIGN KEY -------------------------------------

-- FOREIGN KEY rol with userE.
ALTER TABLE usuarios
    ADD COLUMN rol_id INT NOT NULL,
    ADD CONSTRAINT fk_rol_id FOREIGN KEY (rol_id) REFERENCES rol (id);

-- FOREIGN KEY userE with student.
ALTER TABLE student
    ADD COLUMN usuarios_id INT NOT NULL,
    ADD CONSTRAINT fk_usuarios_id FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);

-- FOREIGN KEY userE with teacher.
ALTER TABLE teacher
    ADD COLUMN usuarios_id INT NOT NULL,
    ADD CONSTRAINT fk_usuarios_id FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);

-- FOREIGN KEY teacher with course.
ALTER TABLE course
    ADD COLUMN teacher_id INT NOT NULL,
    ADD CONSTRAINT fk_teacher_id FOREIGN KEY (teacher_id) REFERENCES teacher (id);

-- FOREIGN KEY grades with course and student.
ALTER TABLE grades
    ADD COLUMN student_id INT NOT NULL,
    ADD CONSTRAINT fk_student_id FOREIGN KEY (student_id) REFERENCES student (id),
    ADD COLUMN course_id INT NOT NULL,
    ADD CONSTRAINT fk_course_id FOREIGN KEY (course_id) REFERENCES course (id);

-- ----------------------------------------------------------------------------------------------------------------------------------

------------------------------- INSERT VALUES  -------------------------------------

-- Insert data in rol table
INSERT INTO rol (description)
VALUES
    ('STUDENT'),
    ('TEACHER');

-- Insert data in userE table
INSERT INTO usuarios (email, password, first_name, last_name, address, cellphone, dni, rol_id)
VALUES
    ( 'admin@example.com', '1234', 'John', 'Doe', 'colsag', '3158487963', '1234567890', 2),
    ( 'jannedoe@example.com', '4321', 'Janne', 'Doe', 'contento', '3145267894', '9876543210', 2),
    ( 'juan@example.com', '1234', 'Juan', 'Perez', 'el contento', '3165239847', '10052487986', 1),
    ( 'maria@example.com', '1236', 'Maria', 'Gomez', 'barrio blanco', '3125269365', '10042582356', 1);

-- Insert data in teacher table
INSERT INTO teacher (knowledge, assignment, usuarios_id)
VALUES
    ('Matemáticas, Física, Dinámica', 'Décimo y Onces', 1),
    ('Biología, Química, Ciencias', 'Sexto y Septimo', 2);

-- Insert data in students table
INSERT INTO student (date_born, school_grade, usuarios_id)
VALUES
    ('1990-01-15', 'Sexto', 3),
    ('1985-05-10', 'Décimo', 4);

-- Insert data in course table
INSERT INTO course (name, teacher_id)
VALUES
    ('Sexto', 2),
    ('Décimo', 1);

-- Insert data in grades table
INSERT INTO grades (grade, desc_grade, student_id, course_id)
VALUES
    (5, 'Actividad 1', 1, 1),
    (3.5, 'Actividad 2', 1, 1),
    (4.3, 'Actividad 1', 2, 2),
    (2.5, 'Actividad 2', 2, 2);