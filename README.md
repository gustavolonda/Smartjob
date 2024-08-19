<center>
  <h1> Creacion de usuario </h1>
</center>
<code><p align="center"><img height="400" src="https://github.com/gustavolonda/Smartjob/blob/master/images/diagrama.png" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>

<code><p align="center"><img height="400" src="https://github.com/gustavolonda/Smartjob/blob/master/images/swagger.png" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>

### Introducción

El proyecto está desarrollado en java con spring boot. La base de datos que se utilizó es h2, la cual es una base de datos en memoria.


> [!TIP]
> Link de Swagger: http://localhost:8095/swagger-ui/index.html/

### Herramientas utilizadas
* Java 17
* Spring boot 3.1.4
* Gradle
* H2
* Spring Security
* JWT
* Docker
* Docker Compose
* Github
* Swagger
  
### Ejecutar proyecto
Para ejecutar el proyecto primero se crea el jar, para ello se corre el comando `./gradlew clean build` dentro de la carpeta `/Smartjob/backend/auth-service`. Luego se ejecuta el comando `sudo docker-compose up -d` para crear la imagen y contenedor del proyecto
<code><p align="center"><img height="400" src="https://github.com/gustavolonda/Smartjob/blob/master/images/commanCleanBuild.png" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>

### Prueba Realizada
Dentro de la carpeta postman, se encuentra un archivo json con todo los datos necesarios. El cual debe ser importado en postman para realizar pruebas. 

<code><p align="center"><img height="400" src="https://github.com/gustavolonda/Smartjob/blob/master/images/smartjopTest.gif" title="app" style="display: block;margin-left: auto;margin-right: auto;"></p></code>

