ms-user-service – Pastelería Mil Sabores

Microservicio de Usuarios y Autenticación
Spring Boot 3 · Java 17 · Spring Security · JWT

Este microservicio gestiona el registro, login y autenticación mediante JWT para la aplicación Pastelería Mil Sabores. Ofrece validaciones básicas, codificación segura de contraseñas y endpoints protegidos.

📌 Funcionalidades principales

Registro de usuarios

Login con generación de token JWT

Validación de credenciales

Contraseñas encriptadas con BCrypt

Endpoints protegidos con JWT

Arquitectura basada en capas (Controller, Service, Repository, Security)

Integración con API Gateway

📁 Endpoints disponibles
Autenticación (/api/auth)
Método	Endpoint	Descripción
POST	/api/auth/register	Registra un usuario nuevo
POST	/api/auth/login	Devuelve JWT si las credenciales son válidas
GET	/api/protected/hello	Endpoint protegido (testing)
📝 Ejemplos de Request / Response
Registro de usuario

POST /api/auth/register

Request
{
  "nombre": "Victor",
  "apellido": "Barrera",
  "email": "admin2@pasteleria.cl",
  "password": "123456",
  "rol": "ADMINISTRADOR"
}

Response
{
  "mensaje": "Usuario registrado correctamente"
}

Login

POST /api/auth/login

Request
{
  "email": "admin2@pasteleria.cl",
  "password": "123456"
}

Response
{
  "token": "eyJh...tu_jwt..."
}

🏗️ Arquitectura del servicio
src/
 ├── controller/      → Endpoints REST
 ├── service/         → Lógica de negocio
 ├── repository/      → Acceso a BD con Spring Data JPA
 ├── entity/          → Entidades JPA
 ├── security/        → JWT, filtros y configuración de seguridad
 └── dto/             → Objetos de transferencia de datos

🛢️ Configuración de la base de datos

Archivo: application.properties

spring.application.name=ms-user-service
server.port=8083

spring.datasource.url=jdbc:mysql://localhost:3306/db_ms_users
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

▶️ Ejecución del proyecto
1. Clonar el repositorio
git clone https://github.com/tu-usuario/ms-user-service.git

2. Ejecutar el microservicio
./gradlew bootRun


El servicio quedará disponible en:

http://localhost:8083

🌐 Integración con API Gateway

El Gateway enruta hacia este microservicio mediante:

/api/auth/**


El frontend nunca debe llamar directamente a este servicio, solo al gateway:

http://localhost:8080/api/auth/login
http://localhost:8080/api/auth/register

🧩 Tecnologías utilizadas

Java 17

Spring Boot 3

Spring Security

JWT

BCrypt

JPA / Hibernate

MySQL

Lombok

🔐 Notas importantes

Las contraseñas se almacenan encriptadas con BCrypt, nunca en texto plano.

El login devuelve solo un token JWT, no datos del usuario.

Para acceder a endpoints protegidos, se debe enviar:

Authorization: Bearer TU_TOKEN
