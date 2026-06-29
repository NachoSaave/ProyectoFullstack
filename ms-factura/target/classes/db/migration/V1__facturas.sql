CREATE TABLE factura (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,
    descripcion VARCHAR(255)
);