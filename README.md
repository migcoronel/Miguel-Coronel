# Miguel-Coronel
##Back-end test exercise for XCaleConsulting

###Pre-Requisitos:
	- IntelliJ IDE o similar
	- Docker para Windows
	- Bash for Windows


###Work-Flow:
	-Los usuarios se registran con su numero de telefono
	-Solo puede haber un usuario por numero de telefono
	-Para crear un grupo, los usuarios en el grupo deben estar registrados,
		caso contrario, solo se agregaran los usuarios registrados.
	-Luego de creado el grupo, cualquier usuario perteneciente, puede enviar mensajes.


###Para mejorar:
	-Usuarios admin, con diferentes privilegios
	-Limites de usuarios por grupo
	-Posibilidad de enviar multimedia



##PARA INICIAR EL PROYECTO:
###Clonar el proyecto del siguiente link:
https://github.com/migcoronel/Miguel-Coronel

###En el directorio del projecto, correr el comando:
mvn install

###Ir al directorio "Miguel-Coronel\whatsapp-group-api\src\main\java\com\example\whatsappgroupapi\docker" y correr el siguiente comando:
docker-compose up -d

###El paso anterior correra dos containers:
	1 Postgresql
	2 Rabbit MQ

###Para abrir el management de RabbitMQ:
	ir a la url http://localhost:15672/
	ingresar con usuario "guest" y contraseña "guest"

###Para correr el proyecto correr el comando:
mvn clean spring-boot:run





