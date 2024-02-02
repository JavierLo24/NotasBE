-- -------------------------------------------------------------------------------------------------------------------------
-- Drop tables before

DROP TABLE IF EXISTS alumno;
DROP TABLE IF EXISTS notas;
DROP TABLE IF EXISTS profesor;
DROP TABLE IF EXISTS curso;

-- Create alumnos table.
CREATE TABLE IF NOT EXISTS alumno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    fecha_nac DATE,
    grado VARCHAR(20),
    seccion VARCHAR(255),
    direccion VARCHAR(50),
    telefono VARCHAR(10),
    email VARCHAR(50),
    password VARCHAR(255)
    );

-- Create profesor table.
CREATE TABLE IF NOT EXISTS profesor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255),
    apellidos VARCHAR(255),
    dni VARCHAR(10),
    direccion VARCHAR(50),
    telefono VARCHAR(10),
    email VARCHAR(50),
    password VARCHAR(255)
    );

-- Create curso table.
CREATE TABLE IF NOT EXISTS curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255)
    );

-- Create notas table.
CREATE TABLE IF NOT EXISTS notas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota DOUBLE,
    desc_nota VARCHAR(100),
    promedio DOUBLE
    );

-- ---------------------------------------------------------------------------------------------------------------------------

------------------------------- CONSTRAINT ABOUT FOREIGN KEY -------------------------------------

-- FOREIGN KEY profesor with curso.
ALTER TABLE curso
    ADD COLUMN profesor_id INT NOT NULL,
    ADD CONSTRAINT fk_profesor_id FOREIGN KEY (profesor_id) REFERENCES profesor (id);

-- FOREIGN KEY notas with curso and alumno.
ALTER TABLE notas
    ADD COLUMN alumno_id INT NOT NULL,
    ADD CONSTRAINT fk_alumno_id FOREIGN KEY (alumno_id) REFERENCES alumno (id),
    ADD COLUMN curso_id INT NOT NULL,
    ADD CONSTRAINT fk_curso_id FOREIGN KEY (curso_id) REFERENCES curso (id);

-- ----------------------------------------------------------------------------------------------------------------------------------

------------------------------- INSERT VALUES  -------------------------------------
-- Insert data in profesor table
INSERT INTO profesor (nombres, apellidos, dni, direccion, telefono, email, password)
VALUES
('John', 'Doe', '1234567890', 'colsag', '3158487963', 'admin@example.com', '1234'),
('Janne', 'Doe', '9876543210', 'contento', '3145267894', 'jannedoe@example.com', '4321');

-- Insert data in alumnos table
INSERT INTO alumno (nombres, apellidos, fecha_nac, grado, seccion, direccion, telefono, email, password)
VALUES
    ('Juan', 'Perez', '1990-01-15', 'sexto', 'primaria', 'el contento', '3165239847', 'juan@example.com', '1234'),
    ('Maria', 'Gomez', '1985-05-10', 'octavo', 'secundaria', 'barrio blanco', '3125269365', 'maria@example.com', '1236');

-- Insert data in curso table
INSERT INTO curso (nombre, profesor_id)
VALUES
    ('mates', 1),
    ('sociales', 2);

-- Insert data in notas table
INSERT INTO notas (alumno_id, curso_id, nota, desc_nota, promedio)
VALUES
    (1, 1, 5, "Actividad 1", 0),
    (1, 2, 3.5, "Actividad 2", 0),
    (2, 1, 4.3, "Actividad 1", 0),
    (2, 2, 2.5, "Actividad 2", 0);