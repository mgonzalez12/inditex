

1- Descargar dependecias:
-
- Usar Intellij IDEA Community edition https://www.jetbrains.com/idea/download
- Para este pequeño proyecto se esta usando maven como manejador de dependencias
  por lo que una vez descargado el proyecto debes usar el comando:
  ``` mvn clean install ```
- pero antes debe deshabilitar los test, para que no de error la compilacion o correr el proyecto.
  ![maven-img.png](assets/images/maven-img.png)

2- Correr el proyecto:
-
- para ello vas a la clase InditexApplication y le das a las flechas de  ``` run ```
- Esto Creara la tabla y base de datos h2
- Puedes acceder a la base de datos por el navegador en la url http://localhost:8081/h2-console
  ![h2_db.png](assets/images/h2_db.png)
 ```
- Driver Class:  jdbc:h2:mem:testdb
- Usuario: sa
- password:
 ```

3- Prueba de funcionalidad del proyecto:
-
- Puedes hacer las consultas por la url: http://localhost:8081/swagger-ui/index.html
  donde haras uso de Swagger UI una herramienta de openApi para hacer pruebas:
  ![swagger.png](assets/images/swagger.png)
- Hacer consultas usando la data:

```
  brandId: 1
  productId: 35455
  date: 2020-06-14T00:00:00Z
 ```

4- Pruebas en Postman:
- 
- Tenemos una coleccion donde podemos realizar las deferentes pruebas y consultas:
  url: http://localhost:8081/api/prices?brandId=1&productId=35455&date=2020-06-14T00:00:00Z

5- Los Test:
-
- Primero que nada debemos tener el proyecto corriendo, click al boton ``` Toggle Skip Test Mode ```  que se encuentra en el menu de maven del lado izquierdo del IDEA
  habilitamos los test y hacemos un ``` mvn clean install ```
  ![maven-img.png](assets/images/maven-img.png)
  Lo puede hacer por consola o por esos botones de consola que aparecen
- ahora si quieres hacer la prueba individualmente de cada uno de los test puedes ingresar al proyecto, donde estan test unitario, test funcionales y de integracion

6- Code Coverage -[ SonarQube - Jacoco]
-
Lo primero es usar el comando ``` docker-compose up -d  ``` para descargar las imagenes de docker y crear los contenedores

luego usar en el navegador  ``` http://localhost:9000  ``` eso ingresa a la interfaz de sonarQube


Cuando muestra la interfaz el usuario por defecto es  ``` admin  ``` y el password  ``` admin  ``` y luego te pide que ingrese de nuevo la clave para cambiarla por la que desees


Luego hacer las configuraciones de seguridad,deshabilitamos todas.
![config_sonar_1.png](assets/images/config_sonar_1.png)
Luego configuracion de permisos y ya con eso podemos usar sonarqube.
![config_sonar_2.png](assets/images/config_sonar_2.png)

luego puede ejecutar el comando:
``` mvn sonar:sonar -Pcoverage  ```

![sonarQube_3.png](assets/images/sonarQube_3.png)
 ```
- En la url http://localhost:9000/  
- usuarios=  admin
- password=  admin

luego te pide cambio de clave por la que quieras.
 ```
alli puede ver la cobertura el codigo y otras cosas:
![sonarQube_1.png](assets/images/sonarQube_1.png)

### Jacoco
Cuando hacemos el mvn clean install con los test habilitados, esto nos crea una carpeta donde se encuentra jacoco:

Seleccionamos el archivo index.html y abrimos en el navegador

![jacoco.png](assets/images/jacoco.png)
