# ProyectoFullstack
Este proyecto es sobre una agencia/empresa de venta de motos, con proceso de factura, pago y envío incluído.
Este proyecto fue creado por Matías Mendoza Tilleria y José Saavedra Pacheco

Lista puertos en uso:
  http://localhost:8081/api/v1/clientes
  
  http://localhost:8082/api/v1/motos
  
  http://localhost:8083/api/v1/inventory
  
  http://localhost:8084/api/v1/ventas
  
  http://localhost:8085/api/v1/pagos
  
  http://localhost:8086/api/v1/notificaciones
  
  http://localhost:8087/api/v1/facturas
  
  http://localhost:8088/api/v1/transportes
  
  http://localhost:8089/api/v1/destino/{id} //ESTE MS NO SE USA CON FINDALL, ES POR {id}
  
  http://localhost:8090/api/v1/envios

Microservicios con Feign ("getters")
  Envio - Extrae datos de Transporte y Destino
  Factura - Extrae datos de Cliente
  Pago - Extrae datos de Cliente
