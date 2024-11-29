# appstra - Spring Boot 3.4.0

Este proyecto es una aplicación desarrollada con **Spring Boot 3.4.0** que proporciona un sistema de administración para gestionar **usuarios**, **personas**, **estados**, **tipos de estados**, **tipos de documento**, **países**, **provincias** y **municipios**.

## Características

- **Gestión de usuarios y roles**: Autenticación y autorización mediante Spring Security.
- **CRUD completo**: Operaciones básicas para las entidades mencionadas.
- **API REST**: Implementación de endpoints para la interacción con clientes externos.
- **Validación de datos**: Uso de validaciones integradas con Java Bean Validation.
- **Documentación de API**: Exploración y prueba de endpoints mediante OpenAPI (Swagger UI).
- **Base de datos PostgreSQL**: Persistencia de datos utilizando JPA y PostgreSQL.

## Tecnologías utilizadas

- **Spring Boot 3.4.0**
- **Java 17**
- **PostgreSQL**
- **Swagger UI (OpenAPI)**

## Dependencias del proyecto

### 1. **JPA (Spring Data JPA)**
   - **Dependencia**: `spring-boot-starter-data-jpa`
   - **Descripción**: Proporciona soporte para la interacción con bases de datos relacionales utilizando Hibernate como implementación predeterminada de JPA.

### 2. **Spring Security**
   - **Dependencia**: `spring-boot-starter-security`
   - **Descripción**: Añade autenticación y autorización a la aplicación para proteger recursos y datos.

### 3. **Spring Web**
   - **Dependencia**: `spring-boot-starter-web`
   - **Descripción**: Permite el desarrollo de aplicaciones web y APIs RESTful con soporte para Spring MVC y servidor embebido.

### 4. **PostgreSQL**
   - **Dependencia**: `postgresql`
   - **Descripción**: Controlador JDBC para la conexión y operación sobre bases de datos PostgreSQL.

### 5. **Lombok**
   - **Dependencia**: `lombok`
   - **Descripción**: Facilita la creación de clases reduciendo código repetitivo mediante anotaciones para generar getters, setters y constructores automáticamente.

### 6. **Tomcat**
   - **Dependencia**: `spring-boot-starter-tomcat`
   - **Descripción**: Servidor embebido para desplegar y ejecutar la aplicación.

### 7. **Testing con Spring Boot**
   - **Dependencia**: `spring-boot-starter-test`
   - **Descripción**: Herramientas de prueba como JUnit y Mockito para realizar pruebas unitarias e integradas.

### 8. **Pruebas de Seguridad**
   - **Dependencia**: `spring-security-test`
   - **Descripción**: Facilita pruebas relacionadas con autenticación y autorización en aplicaciones con Spring Security.

### 9. **OpenAPI (Swagger)**
   - **Dependencia**: `springdoc-openapi-starter-webmvc-ui`
   - **Descripción**: Genera documentación interactiva de la API y proporciona una interfaz web para probar los endpoints.

### 10. **Validación de datos**
   - **Dependencia**: `spring-boot-starter-validation`
   - **Descripción**: Soporte para validar datos de entrada utilizando Java Bean Validation (JSR-380).

### 11. **JWT (JSON Web Tokens)**
   - **Dependencia**: `java-jwt`
   - **Descripción**: Manejo de autenticación basada en tokens seguros y compactos con JSON Web Tokens.

## Requisitos previos

1. **JDK 17+**
2. **Maven 3.8+**
3. **PostgreSQL** instalado y configurado.

## Configuración

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/nombre-del-repositorio.git

