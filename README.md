# Coding Assesment  - XCaleConsulting

## Pre-requisitos:
###	-IntelliJ o otro IDE similar.
###	-Docker Desktop
###	-Bash
###	-Postman (Desktop agent instalado)



## Consideraciones:

### Usuarios:
#### - Los usuarios se registran usando su numero de telefono.
#### - No pueden existir numeros de telefono duplicados.


### Grupos:
#### - Para crear un grupo se debe asignar al menos un nombre y los usuarios que lo integraran.
#### - Los usuarios que no esten registrados antes, no seran agregados al grupo.
#### - En caso de no pasar lista de Admins, se asignara solo al creador del grupo.
#### - Solo los admins pueden actualizar los datos del grupo (nombre, lista de admins, lista de participantes).


### Mensajes:
#### - Solo pueden enviar mensajes a un grupo los usuarios registrados y que forman parte de dicho grupo.



## Referencias / Archivos utiles (.\Miguel-Coronel\Refs):

### 	- Diagrama UML (UML Diagram.jpg)
### 	- Postman Template file (whatsapp-api.postman_collection.json)



## Para correr el Proyecto

####	1. Clonar del siguiente link (https://github.com/migcoronel/Miguel-Coronel)

#### 2. Abrir el directorio en IntelliJ y correr el siguiente comando para instalar las dependencias:
####		`mvn install`

#### 3. Ir al directorio docker (.\whatsapp-group-api\src\main\java\com\example\whatsappgroupapi\docker) y correr el siguiente comando:
####		`docker-compose up -d`

####		Este ultimo comando Iniciara dos Containers:
####			-Posqresql
####			-RabbitMQ

#### 4. Para abrir el management de RabbitMQ, abrir el siguiente url en un explorardor (http://localhost:15672/) y nos logueamos con el usuario `guest` y contraseña `guest`.

#### 5. Volvemos a IntelliJ y corremos el siguiente comando para iniciar el proyecto:
####		`mvn clean spring-boot:run`

#### 6. Despues de iniciado el proyecto, debemos ir a Postman para iniciar las interacciones con la API.

#### 7. Aca podemos importar el Postman template file, donde tendremos ya configuradas todas las llamadas que necesitamos hacer a la API



## Para Mejorar:

#### - API documentation.
#### - Autenticación de usuario al registrar por SMS.
#### - Implementar los campos "entregado" y "visto" en los mensajes.
#### - Envio de multimedia en los mensajes.
#### - Limite en el numero de usuarios por grupo.