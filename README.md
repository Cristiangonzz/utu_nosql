# nosql
Proyecto de Aplicación Web con Java, Angular y MongoDB

Este proyecto es una aplicación web desarrollada con Java y Angular, utilizando MongoDB como base de datos. Incluye soporte para contenedores Docker y un pipeline de integración y despliegue continuo (CI/CD) configurado con Jenkins.

Tabla de Contenidos
*Formato de Intercambio de Datos
*Descripción de URL de los Servicios
*Instalación y Configuración
*Plataformas, Lenguajes y Base de Datos Utilizados
*Justificación de la Base de Datos
*Diseño del Esquema
*Manual de Configuración y Requerimientos Opcionales

http://localhost:8080/
Swagger http://localhost:8080/swagger-ui/index.html

Endpoints:
Agregar a pacientes:
Metodo POST
http://localhost:8080/api/pacientes
Body:
{
  "ci": "string",
  "nombre": "string",
  "apellido": "string",
  "fechaNacimiento": "2024-11-28",
  "sexo": "string"
}

Ejemplo :
{
  "ci": "1111111",
  "nombre": "Julio",
  "apellido": "Rodriguez",
  "fechaNacimiento": "2024-11-28",
  "sexo": "Masculino"
}

Agregar Registro medico para un paciente :
Metodo POST
http://localhost:8080/api/pacientes/{ci}/registros
body:
{
  "fecha": "2024-11-28",
  "tipo": "string",
  "diagnostico": "string",
  "medico": "string",
  "institucion": "string",
  "descripcion": "string",
  "medicacion": "string"
}

Ejemplo :
http://localhost:8080/api/pacientes/1111111/registros
{
  "fecha": "2024-11-28",
  "tipo": "Consulta",
  "diagnostico": "Saludable",
  "medico": "Alfredo",
  "institucion": "Medica",
  "descripcion": "Persona con animo",
  "medicacion": "Ninguna"
}

Buscar registros medicos:
Metodo GET
http://localhost:8080/api/pacientes/{ci}/registros
body:
{
  "page": 0,
  "size": 1,
  "sort": [
    "string"
  ]
}

Ejemplo 
http://localhost:8080/api/pacientes/1111111/registros
body:
{
  "page": 0,
  "size": 1,
}

Instalación y Configuración
Requisitos Previos
Java 21 o superior
Node.js 20 y npm
MongoDB
Docker
Jenkins

En Backend 
  mvn clean install
  java -jar target/backend-0.0.1-SNAPSHOT.jar

Frontend:
  npm install
  ng build.

Ejecutar Docker compose :
docker-compose up --build


Plataformas, Lenguajes y Base de Datos Utilizados
*Backend: Java (Spring Boot)
*Frontend: Angular
*Base de Datos: MongoDB
*CI/CD: Jenkins
*Contenedores: Docker

Justificación de la Base de Datos
Se eligió MongoDB debido a su naturaleza NoSQL, que permite un diseño flexible del esquema de datos. Esto es especialmente útil para aplicaciones con estructuras de datos dinámicas o en evolución.

Manual de Configuración y Requerimientos Opcionales
Configuración Opcional: Docker y Jenkins
Docker

Define un archivo Dockerfile para cada servicio (backend y frontend).
Usa docker-compose.yml para orquestar los contenedores.
Jenkins

Configura un pipeline en Jenkins con los siguientes pasos:
Build: Compila el backend y frontend.
Test: Ejecuta pruebas automáticas.
Deploy: Despliega los contenedores en un entorno de producción.




Proyecto para Utu - - Materia Nosql
