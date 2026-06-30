# Proyecto Fullstack - Agencia de Motos
Sistema integral para la gestión y venta de motocicletas basado en una arquitectura de microservicios.

<Autores: Matías Mendoza Tilleria y José Saavedra Pacheco>

# Características y Tecnologías

Eureka: Los servicios se registran automáticamente usando Eureka Server.

Comunicación entre microservicios: Implementada con OpenFeign.

Configuración: Archivos application.yml en todos los proyectos.

Documentación: APIs documentadas con Swagger.

Trazabilidad: Logs gestionados con Log4j2 y gestionados con logback-spring guardados en una carpeta independiente llamada "logs"

# Microservicios y Endpoints

$

8080 - API Gateway (Puerto padre)
    Con gateway, todos lols microservicios funcionan con localhost:8080/api/v1(microservicio)

8081 - Clientes (/api/v1/clientes)

8082 - Motocicletas (/api/v1/motos)

8083 - Inventario (/api/v1/inventario)

8084 - Ventas (/api/v1/ventas)

8085 - Pagos (/api/v1/pagos)

8086 - Notificaciones (/api/v1/notificaciones)

8087 - Facturas (/api/v1/facturas)

8088 - Transportes (/api/v1/transportes)

8089 - Destino (/api/v1/destinos)

8090 - Envíos (/api/v1/envios)

8761 - Eureka Server (http://localhost:8761/)

$

# Comunicación entre Servicios (FeignClient)
Para completar los procesos, ciertos microservicios consumen información de otros:

Venta: Extrae datos de Cliente, Inventario, Motocicleta y Pago.

Envío: Extrae datos de Transporte y Destino.

Factura: Extrae datos de Cliente.

Pago: Extrae datos de Cliente.

# Procedimiento de Ejecución y Pruebas
Iniciar Eureka Server: Es obligatorio iniciarlo primero para que los demás servicios puedan registrarse.

Iniciar Microservicios: Se recomienda levantarlos en orden según su puerto (8080 al 8090).

Utilizar Postman para interactuar con los endpoints (GET, POST, PUT, DELETE). Las pruebas a los controladores deben hacerse mediante las URLs declaradas.

Validación de Datos: Usar MySQL (preferentemente MySQL Workbench) para visualizar la creación correcta de los objetos.