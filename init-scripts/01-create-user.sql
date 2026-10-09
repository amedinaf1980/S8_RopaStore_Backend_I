-- 01-create-user.sql
-- Este script se ejecuta como SYS sobre la instancia raíz (CDB).
-- Cambiamos al PDB para crear el usuario ahí.
ALTER SESSION SET CONTAINER = FREEPDB1;

-- Creamos el usuario dedicado para la aplicación.
CREATE USER RopaStore IDENTIFIED BY RopaStore123
  DEFAULT TABLESPACE USERS
  QUOTA UNLIMITED ON USERS;

-- Permisos necesarios para que Hibernate pueda crear y gestionar tablas.
GRANT CONNECT, RESOURCE TO RopaStore;
GRANT CREATE SESSION TO RopaStore;
GRANT CREATE TABLE TO RopaStore;
GRANT CREATE SEQUENCE TO RopaStore;
GRANT CREATE VIEW TO RopaStore;
GRANT UNLIMITED TABLESPACE TO RopaStore;


COMMIT;
