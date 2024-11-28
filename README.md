# nosql
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

Proyecto para Utu - - Materia Nosql
