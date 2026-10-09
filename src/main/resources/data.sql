-- ============================================================
-- data.sql - Datos de prueba para RopaStore
-- Se ejecuta DESPUÉS de que Hibernate crea las tablas.
-- ============================================================

-- Limpieza (orden inverso a las dependencias lógicas)
DELETE FROM pedido;
DELETE FROM producto;
DELETE FROM cliente;
DELETE FROM sucursal;
DELETE FROM admin;

-- ============================================================
-- SUCURSALES
-- ============================================================
INSERT INTO sucursal (nombre, direccion)
VALUES ('Sucursal Centro', 'Av. Libertador Bernardo O''Higgins 1234, Santiago');

INSERT INTO sucursal (nombre, direccion)
VALUES ('Sucursal Providencia', 'Av. Providencia 5678, Santiago');

INSERT INTO sucursal (nombre, direccion)
VALUES ('Sucursal Viña', 'Av. San Martín 910, Viña del Mar');

-- ============================================================
-- CLIENTES
-- ============================================================
INSERT INTO cliente (rut, nombre, correo, telefono, direccion)
VALUES ('11111111-1', 'Juan Pérez', 'juan@example.com', 912345678, 'Av. Providencia 123');

INSERT INTO cliente (rut, nombre, correo, telefono, direccion)
VALUES ('22222222-2', 'María García', 'maria@example.com', 923456789, 'Calle Los Leones 456');

INSERT INTO cliente (rut, nombre, correo, telefono, direccion)
VALUES ('33333333-3', 'Pedro Silva', 'pedro@example.com', 934567890, 'Av. Apoquindo 789');

-- ============================================================
-- PRODUCTOS (sucursal_id referencia a sucursal.id)
-- ============================================================
INSERT INTO producto (nombre, precio, categoria, stock, sucursal_id)
VALUES ('Polera Negra', 12990, 'Poleras', 50, 1);

INSERT INTO producto (nombre, precio, categoria, stock, sucursal_id)
VALUES ('Jeans Azul', 29990, 'Pantalones', 30, 1);

INSERT INTO producto (nombre, precio, categoria, stock, sucursal_id)
VALUES ('Chaqueta Cuero', 59990, 'Chaquetas', 10, 2);

INSERT INTO producto (nombre, precio, categoria, stock, sucursal_id)
VALUES ('Zapatillas Running', 45990, 'Calzado', 25, 2);

INSERT INTO producto (nombre, precio, categoria, stock, sucursal_id)
VALUES ('Gorro Lana', 8990, 'Accesorios', 100, 3);

-- ============================================================
-- PEDIDOS (cliente_id referencia a cliente.id)
-- ============================================================
INSERT INTO pedido (cliente_id, fecha_pedido, total, estado)
VALUES (1, SYSDATE, 42980, 'PENDIENTE');

INSERT INTO pedido (cliente_id, fecha_pedido, total, estado)
VALUES (1, SYSDATE, 8990, 'PAGADO');

INSERT INTO pedido (cliente_id, fecha_pedido, total, estado)
VALUES (2, SYSDATE, 59990, 'PAGADO');

INSERT INTO pedido (cliente_id, fecha_pedido, total, estado)
VALUES (3, SYSDATE, 45990, 'ENVIADO');

INSERT INTO pedido (cliente_id, fecha_pedido, total, estado)
VALUES (2, SYSDATE, 29990, 'PENDIENTE');

-- Admins
INSERT INTO admin (email, password)
VALUES ('admin1@ropastore.cl', '$2a$10$65eaoJckSJcExxd7UraO0ORmr7jWLmZ1xWJVXvyTqKFDi7BA/OB/.');

INSERT INTO admin (email, password)
VALUES ('admin2@ropastore.cl', '$2a$10$EEZrgHvNR/0gQ5r9ayXlKeftpmsw2Dnri2zYoJm6H4JVIlIr2Fuw2');

COMMIT;