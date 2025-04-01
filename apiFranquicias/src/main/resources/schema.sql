-- Crear la tabla franquicia
CREATE TABLE IF NOT EXISTS franquicia (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL UNIQUE
);

-- Crear la tabla sucursal con clave foránea a franquicia
CREATE TABLE IF NOT EXISTS sucursal (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    franquicia_id INT NOT NULL,
    CONSTRAINT fk_franquicia FOREIGN KEY (franquicia_id) REFERENCES franquicia(id) ON DELETE CASCADE
);

-- Crear la tabla producto con clave foránea a sucursal
CREATE TABLE IF NOT EXISTS producto (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0),
    sucursal_id INT NOT NULL,
    CONSTRAINT fk_sucursal FOREIGN KEY (sucursal_id) REFERENCES sucursal(id) ON DELETE CASCADE
);

-- Insertar datos de ejemplo solo si no existen
INSERT INTO franquicia (nombre)
SELECT 'Franquicia A' WHERE NOT EXISTS (SELECT 1 FROM franquicia WHERE nombre = 'Franquicia A');

INSERT INTO franquicia (nombre)
SELECT 'Franquicia B' WHERE NOT EXISTS (SELECT 1 FROM franquicia WHERE nombre = 'Franquicia B');

INSERT INTO sucursal (nombre, franquicia_id)
SELECT 'Sucursal 1', 1 WHERE NOT EXISTS (SELECT 1 FROM sucursal WHERE nombre = 'Sucursal 1' AND franquicia_id = 1);

INSERT INTO sucursal (nombre, franquicia_id)
SELECT 'Sucursal 2', 1 WHERE NOT EXISTS (SELECT 1 FROM sucursal WHERE nombre = 'Sucursal 2' AND franquicia_id = 1);

INSERT INTO sucursal (nombre, franquicia_id)
SELECT 'Sucursal 3', 2 WHERE NOT EXISTS (SELECT 1 FROM sucursal WHERE nombre = 'Sucursal 3' AND franquicia_id = 2);

INSERT INTO producto (nombre, stock, sucursal_id)
SELECT 'Producto X', 100, 1 WHERE NOT EXISTS (SELECT 1 FROM producto WHERE nombre = 'Producto X' AND sucursal_id = 1);

INSERT INTO producto (nombre, stock, sucursal_id)
SELECT 'Producto Y', 50, 2 WHERE NOT EXISTS (SELECT 1 FROM producto WHERE nombre = 'Producto Y' AND sucursal_id = 2);

INSERT INTO producto (nombre, stock, sucursal_id)
SELECT 'Producto Z', 75, 3 WHERE NOT EXISTS (SELECT 1 FROM producto WHERE nombre = 'Producto Z' AND sucursal_id = 3);
