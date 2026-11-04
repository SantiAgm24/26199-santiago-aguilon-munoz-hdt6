# Ejercicio 6 - Polimorfismo vía Interfaces

## Descripción

Prototipo de consola en Java que administra integraciones simuladas de pagos, logística y facturación electrónica FEL.

## Diseño

El programa utiliza MVC, herencia y polimorfismo mediante las interfaces `Transaccional`, `Documentable`, `Auditable` y `ConsultableEnvios`.

Todas las integraciones se almacenan en una única lista polimórfica. La interfaz `Comparable` permite ordenarlas por latencia. Las operaciones no admitidas se omiten de forma controlada.

## Cómo ejecutar

(Usando git bash o la terminal)
javac -d bin src/*.java
java -cp bin Main

## Datos iniciales

El programa carga 10 integraciones ficticias:

- 4 pasarelas de pago: `PAG-01` a `PAG-04`.
- 3 proveedores logísticos: `LOG-01` a `LOG-03`.
- 3 certificadores FEL: `FEL-01` a `FEL-03`.

Toodas las operaciones y APIs son simuladas.

### Información de estudiante

- Santiago Aguilón Muñoz 
- Carné #26199
- Primer año