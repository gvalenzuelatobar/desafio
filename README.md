# Evaluación

Repositorio para obtener el proyecto
https://github.com/gvalenzuelatobar/desafio

En el Archivo application.properties, se encuentra la configuracion de Swagger y la BD H2
En el Archivo schema.sql, se encuentra el script de la creacion de la BD

url swagger , documentacion de los controladores
http://localhost:8080/swagger/swagger-ui/index.html

JDBC 
dbc:h2:mem:test

Credenciales 
username: sa
password: 

url BD H2
http://localhost:8080/h2-console

tablas ocupadas
select * from usuario;
select * from phone;


Segun el menseaje, se analizo que al usuario de entrada se le asigna una lista de telefonos(phones)

Pruebas con Postman

- Para el ingreso del mensaje
POST
http://localhost:8080/usuario/ingreso

    {
        "name": "Juan Rodrwwiguewz",
        "email": "oioioiwe8ee9@0ewuuo.com",
        "password": "8dcb61bc",
        "phones": [
            {
                "number": "1111",
                "citycode": "111",
                "contrycode": "1111"
            },
            {
                "number": "222",
                "citycode": "222",
                "contrycode": "222"
            },
            {
                "number": "333",
                "citycode": "233322",
                "contrycode": "2333322"
            }
        ]
    }

- Para obtener todos los mensajes de la BD
GET
http://localhost:8080/usuario/all

Test
Para el test se creo una pequeña llamada con los datos minimos para el ingreso del mensaje
UsuariosApplicationTests


