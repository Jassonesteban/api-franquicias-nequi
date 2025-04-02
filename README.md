# API FRANQUICIAS - PRUEBA TÉCNICA

## 📌 Descripción
API desarrollada en **Java (Spring Boot)** con **Spring WebFlux** para programación reactiva, utilizando **Spring Security** para autenticación y **PostgreSQL** como base de datos. Se implementó una **arquitectura Hexagonal** para mejorar la mantenibilidad y escalabilidad del código.

## 🚀 Tecnologías Utilizadas
- **Java (Spring Boot)**
- **Spring WebFlux** (Programación Reactiva)
- **Spring Security**
- **PostgreSQL**
- **Docker** (para despliegue en contenedores)
- **Arquitectura Hexagonal**

## ⚙️ Modo de Uso
### 1️⃣ Levantar los contenedores con Docker
Para facilitar el despliegue en un entorno local, se han configurado contenedores Docker.
Ejecuta el siguiente comando:
```bash
docker-compose up --build -d
```
Esto iniciará la API junto con la base de datos PostgreSQL de forma local.
La api estará en localhost:8080 y la base de datos estará en localhost:5432

### 2️⃣ Acceder a la documentación de los Endpoints
La documentación completa de los endpoints está disponible en **Postman** en el siguiente enlace:
🔗 [Documentación Postman](https://documenter.getpostman.com/view/10813705/2sB2cREQdk)

### 3️⃣ Probar la API manualmente
Puedes hacer peticiones con herramientas como **Postman** o **cURL**.
Ejemplo de prueba con `cURL`:
```bash
curl -X GET http://localhost:8080/productos

---

### Nota: la base de datos se iniciara automaticamente desde el contenedor, la data sera almacenada ahi, para ver el script SQL, este estara ubicado en la carpeta src/main/resources/schema.sql

