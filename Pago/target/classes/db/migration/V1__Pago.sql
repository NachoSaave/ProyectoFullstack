CREATE TABLE pagos (

    id BIGINT PRIMARY KEY AUTO_INCREMENT,

    sale_id BIGINT,

    monto DECIMAL(10,2),    

    metodo_pago VARCHAR(50),

    estado VARCHAR(50)
);