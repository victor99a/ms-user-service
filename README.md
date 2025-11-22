ms-user-service – Pastelería Mil Sabores

Microservicio de Usuarios y Autenticación
Spring Boot 3 · Java 17 · JWT · Spring Security

Este microservicio gestionará el registro, login y autenticación mediante JWT para la aplicación Pastelería Mil Sabores. También provee validaciones básicas y endpoints seguros.

Funcionalidades principales

Registro de usuarios

Login con generación de token JWT

Validación de credenciales

Codificación segura de contraseñas (BCrypt)

Estructura basada en controladores, servicios y repositorios

Compatible con API Gateway

Endpoints disponibles
Autenticación (/api/auth)
Método	Endpoint	Descripción
POST	/api/auth/register	Registra un usuario nuevo
POST	/api/auth/login	Retorna un JWT si las credenciales son válidas
GET	/api/protected/hello	Endpoint protegido (testing)
Ejemplos de Request / Response
Registro de usuario

POST /api/auth/register

Request:

{
  "nombre": "Victor",
  "apellido": "Barrera",
  "email": "admin2@pasteleria.cl",
  "password": "123456",
  "rol": "ADMINISTRADOR"
}


Response:

{
  "mensaje": "Usuario registrado correctamente"
}

Login

POST /api/auth/login

Request:

{
  "email": "admin2@pasteleria.cl",
  "password": "123456"
}


Response:

{
  "token": "eyJh...tu_jwt..."
}

Arquitectura del servicio

controller/
Contiene los endpoints de autenticación.

service/
Lógica de negocio, validaciones, creación de usuarios y login.

repository/
Acceso a la base de datos mediante Spring Data JPA.

entity/
Mapeo de la tabla Usuario.

security/
Configuración de JWT, filtros y autenticación.

Configuración de la base de datos

Archivo application.properties:

spring.application.name=ms-user-service
server.port=8083

spring.datasource.url=jdbc:mysql://localhost:3306/db_ms_users
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Ejecución del proyecto

Asegurarse de tener MySQL levantado y la base creada.

Clonar el repositorio:

git clone https://github.com/tu-usuario/ms-user-service.git


Ejecutar el proyecto:

./gradlew bootRun


El servicio quedará disponible en:

http://localhost:8083

Integración con API Gateway

El API Gateway enruta las peticiones hacia este microservicio mediante:

/api/auth/**


El frontend se comunica únicamente con el gateway, no directamente con este microservicio.

Tecnologías utilizadas

Java 17

Spring Boot 3

Spring Security

JWT

Lombok

JPA / Hibernate

MySQL

Notas importantes

Las contraseñas siempre se almacenan en formato encriptado (BCrypt).

Las respuestas del login incluyen exclusivamente el token JWT.

Los endpoints protegidos requieren incluir el header:

Authorization: Bearer TU_TOKEN
