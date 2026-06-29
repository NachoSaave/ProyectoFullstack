CREATE TABLE envios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tiempoEstimado VARCHAR(25),
    rutReceptor VARCHAR(10),
    idDestino BIGINT,
    idTransporte BIGINT
)