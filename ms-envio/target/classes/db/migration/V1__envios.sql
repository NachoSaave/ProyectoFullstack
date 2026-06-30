CREATE TABLE envios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    tiempoEstimado VARCHAR2(25),
    rutReceptor VARCHAR2(10),
    idDestino BIGINT,
    idTransporte BIGINT
)