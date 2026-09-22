# Taller 6 — API REST simple con Spring Boot

Laboratorio de Ingeniería del Software II — Periodo 2-2026
Programa de Ingeniería de Sistemas — Universidad del Cauca

## Descripción

Microservicio que expone una API RESTful implementando el CRUD de la
entidad de negocio `Producto`, siguiendo una arquitectura en capas:

```
Controller  ->  Service (interfaz + implementación)  ->  Repository (JPA)  ->  Base de datos
```

- **model**: entidad `Producto` anotada con JPA (`@Entity`).
- **repository**: `ProductoRepository`, extiende `JpaRepository`.
- **service**: `IProductoService` (contrato) y `ProductoServiceImpl` (lógica de negocio).
- **controller**: `ProductoController`, expone los endpoints REST.

> Nota: la entidad de negocio contiene anotaciones JPA, lo cual mezcla la
> responsabilidad de negocio con la de persistencia. Es la forma habitual
> de hacer prototipos rápidos, pero no es la mejor práctica (viola el
> principio de responsabilidad única). Se corregirá en talleres posteriores.

## Tecnologías

- Java 17
- Spring Boot 4.0.8
- Spring Data JPA
- Base de datos H2 (en memoria)
- Maven

## Requisitos

- JDK 17 o superior
- Maven (o usar el wrapper `./mvnw` incluido)

## Cómo ejecutar

```bash
# Desde la raíz del proyecto
mvn spring-boot:run
# o, usando el wrapper de Maven:
./mvnw spring-boot:run
```

La aplicación queda disponible en `http://localhost:8080`.

La consola web de H2 queda disponible en `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:productosdb`, usuario: `sa`, contraseña: en blanco).

Al iniciar, la base de datos se crea automáticamente y se cargan 3
productos de ejemplo (ver `src/main/resources/data.sql`).

## Endpoints de la API

Recurso base: `/api/productos`

| Verbo HTTP | Ruta                  | Descripción                    |
|------------|-----------------------|---------------------------------|
| GET        | `/api/productos`      | Lista todos los productos       |
| GET        | `/api/productos/{id}` | Consulta un producto por id     |
| POST       | `/api/productos`      | Crea un nuevo producto          |
| PUT        | `/api/productos/{id}` | Actualiza un producto existente |
| DELETE     | `/api/productos/{id}` | Elimina un producto             |

### Ejemplo de cuerpo JSON (POST / PUT)

```json
{
  "nombre": "Audífonos",
  "descripcion": "Audífonos bluetooth",
  "precio": 90000.0,
  "cantidad": 15
}
```

## Probar con Postman (o el navegador, para GET)

1. **GET** `http://localhost:8080/api/productos` — lista todos los productos.
2. **GET** `http://localhost:8080/api/productos/1` — consulta el producto con id 1.
3. **POST** `http://localhost:8080/api/productos` con el JSON de ejemplo en el body (raw/JSON).
4. **PUT** `http://localhost:8080/api/productos/1` con un JSON modificado en el body.
5. **DELETE** `http://localhost:8080/api/productos/1`.

## Ejecutar las pruebas

```bash
mvn test
```
