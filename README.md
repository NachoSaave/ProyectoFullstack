# ProyectoFullstack
Este proyecto es sobre una agencia/empresa de venta de motos, basada en arquitectura de datos en microservicios. Va incluído dentro del proyecto lo que es el procesamiento pagos en Venta la cuál extrae datos de cliente, inventario, moto y pago. Envío trabaja con extracción de datos de transporte y destino.

Este proyecto fue creado por Matías Mendoza Tilleria y José Saavedra Pacheco.

Lista puertos en uso:

  http://localhost:8081/api/v1/clientes
  
  http://localhost:8082/api/v1/motos
  
  http://localhost:8083/api/v1/inventory
  
  http://localhost:8084/api/v1/ventas
  
  http://localhost:8085/api/v1/pagos
  
  http://localhost:8086/api/v1/notificaciones
  
  http://localhost:8087/api/v1/facturas
  
  http://localhost:8088/api/v1/transportes
  
  http://localhost:8089/api/v1/destino
  
  http://localhost:8090/api/v1/envios
  

Microservicios con Feign ("getters")

  Venta - Extrae datos de Cliente, Inventario, Moto y Pago.

  Envio - Extrae datos de Transporte y Destino

  Factura - Extrae datos de Cliente
  
  Pago - Extrae datos de Cliente

Procedimiento:
El proyecto se debe ejecutar acorde a su orden de puertos (No es obligatorio, pero se recomienda para su comprensión) con la creación de objetos hecha a través de POSTMAN y su visualización a través de MySQL (Preferentemente MySQL Workbench). (Para comprobar todos los metodos de controller, debe comprobarse con los url declarados a través de POSTMAN)

Ahora los ms están actuando con eureka (puerto automatico), para verlas en eureka, hay que iniciar eureka-server.

Todos los microservicios cuentan con log (Ver folder LOGS-log4j2), eureka (http://localhost:8761) y swanger.

Todos cuentan con YAML