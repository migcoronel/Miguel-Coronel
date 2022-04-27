# Coding Assesment  - XCaleConsulting

## Prerrequisitos:
###	-IntelliJ u otro IDE similar.
###	-Docker Desktop
###	-Bash
###	-Postman (Desktop agent instalado)



## Consideraciones:

### Usuarios:
#### - Los usuarios se registran usando su número de teléfono.
#### - No pueden existir números de teléfono duplicados.


### Grupos:
#### - Para crear un grupo se debe asignar al menos un nombre y los usuarios que lo integraran.
#### - Los usuarios que no estén registrados antes, no serán agregados al grupo.
#### - En caso de no pasar lista de Admins, se asignará solo al creador del grupo.
#### - Únicamente los admins pueden actualizar los datos del grupo (nombre, lista de admins, lista de participantes).


### Mensajes:
#### - Solo pueden enviar mensajes a un grupo, los usuarios registrados y que forman parte de dicho grupo.



## Referencias / Archivos útiles (.\Miguel-Coronel\Refs):

### 	- Diagrama UML (UML Diagram.jpg)
### 	- Postman Template file (whatsapp-api.postman_collection.json)



## Para correr el Proyecto

####	1. Clonar del siguiente link (https://github.com/migcoronel/Miguel-Coronel)

#### 2. Abrir el directorio en IntelliJ y correr el siguiente comando para instalar las dependencias:
####		`mvn install`

#### 3. Ir al directorio docker (.\whatsapp-group-api\src\main\java\com\example\whatsappgroupapi\docker) y correr el siguiente comando:
####		`docker-compose up -d`

####		Este último comando Iniciara dos Containers:
####			-Posqresql
####			-RabbitMQ

#### 4. Para abrir el management de RabbitMQ, abrir el siguiente url en un explorador (http://localhost:15672/) y entramos con el usuario `guest` y contraseña `guest`.

#### 5. Para correr las pruebas unitarias, correr el comando:
####		`mvn test`

#### 6. Volvemos a IntelliJ y corremos el siguiente comando para iniciar el proyecto:
####		`mvn clean spring-boot:run`

#### 7. Después de iniciado el proyecto, debemos ir a Postman para iniciar las interacciones con la API.

#### 8. Acá podemos importar el Postman template file, donde tendremos ya configuradas todas las llamadas que necesitamos hacer a la API.

#### 9. Estamos listos para probar la API usando Postman o similares.



## Para Mejorar:

#### - API documentación.
#### - Autenticación de usuario al registrar por SMS.
#### - Implementar los campos "entregado" y "visto" en los mensajes.
#### - Agregar mas test unitarios.
#### - Envío de multimedia en los mensajes.
#### - Límite en el número de usuarios por grupo.