## TALLER PATRONES ARQUITECTURALES

Para este taller se construyó una aplicación que debe tener un formulario para el ingreso de cadenas, a partir de este se deben mostrar la fecha de introducción de la cadena y el mensaje de la cadena. En este se mostrará las últimas 10 cadenas ingresadas. Está aplicación se desplegó en AWS usando EC2 y Docker.

### Prerrequisitos

- Java
- Maven
- Git
- Docker

### Instalación

Para hacer uso del proyecto clone el repositorio usando el siguiente comando

```
git clone https://github.com/lgar000/patronesArquitecturales.git
```

Ubiquese en la carpeta en la cual clono el repositorio. A continuación
acceda a la carpeta principal del proyecto mediante el siguiente comando

```
cd patronesArquitecturales
```

Para compilar y empaquetar, ejecute

```
mvn clean install
```

Para construir las imagenes docker del proyecto, ubiquese en la carpeta raíz del mismo y ejecute los siguientes comandos, lo que debería generar las imagenes en docker desktop

```
docker build -t logservice:latest -f DockerLogService .
```

```
docker build -t httplogservice:latest -f DockerHttpLogService .
```

En el caso del docker-compose.yml, ejecute el siguiente comando

```
docker-compose up -d
```

Una vez hecho esto podrá verificar la creación de los mismo mediante docker desktop, en donde deberá ver lo siguiente

Contenedores:

![contenedores](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/contenedores.png)

Imagenes:

![imagenes](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/imagenes.png)

## Pruebas

Si desea probar el funcionamiento localmente, debe verificar que en docker desktop, en la parte de contenedores se encuentre en ejecución el contendor patronesarquitecturales, tal y como se mostró en la imagen de contenedores. Sí es así diríjase a un navegador e ingrese la url : http://localhost:4567. Aquí encontrará un formulario que le permite ingresar cadenas y le mostrará las últimas diez cadenas ingresadas:

![imagenes](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/pruebaLocal1.png)

En caso de que usted ya haya ingresado las 10 cadenas e ingrese una nueva, la última cadena dejará de mostrarse y la primera fila será ocupada por la cadena que acaba de ingresar:

![imagenes](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/pruebaLocal2.png)

##Pruebas initarias

Se realizó pruebas unitarias para validar el funcionamiento ciclico de RoundRobin:

![testRr](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/testRoundRobin.png)

y se probo el funcionamiento del método getLogs:

![testGetLogs](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/testGetLogs.png)

Para ejecutarlas haga uso de su IDE o del comando mvn:

```
mvn test
```

![testmvn](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/mvnTest.png)

## Diseño

Contamos con la clase HttpURLConnection que mediante RemoteServicesInvoke maneja solicitudes http get y post. HttpURLConnection contiene una lista de las url o endpoints correspondientes a los LogService definidos en docker-composite.yml y aquí mismo se implementa un método (roundRobin), que es el encargado de balanceo de cargas, este se asegura de que se rote la petición a cada uno de las url de los Log service. Este método es llamado en getLogs y insertLog respectivamente.
Por otro lado se tenemos la clase MongoDatabaseOperations
, que es donde se encuentra la implementación de las funciones relacionadas con la base de datos mongodb. Aquí realizamos la conexión a la base de datos y definimos los métodos getLogs, que obtiene una lista de de documentos que representan los logs almacenados en la colección "arepLogs" de la base de datos "admin". Estos logs están ordenados  descendentemente y solamente muestra los últimos 10 registros. También tenemos el método insertLog, mediante el cual insertamos un nuevo log y finalmente tenemos closeConnection, para cerrar la conexión a la base de datos. Esta clase es usada por LogService, en la que se crea una instancia de MongoDatabaseOperations para interactuar con la base de datos MongoDB.  Esto con la finalidad de que cuando se haya definido una ruta  /" con un método GET, se retornen los logs en formato JSON obtenidos mediante mongoDatabaseOperations.getLogs() y para la ruta "/" con un método POST, que inserte un nuevo log en la base de datos utilizando mongoDatabaseOperations.insertLog(req.body()).

Está es la arquitectura que sigue la aplicación y se desplegó en AWS usando EC2 y Docker y sigue los siguientes principios:

El servicio MongoDB es una instancia de MongoDB corriendo en un container de docker en una máquina virtual de EC2.

LogService es un servicio REST que recibe una cadena, la almacena en la base de datos y responde en un objeto JSON con las 10 ultimas cadenas almacenadas en la base de datos y la fecha en que fueron almacenadas.

La aplicación web APP-LB-RoundRobin está compuesta por un cliente web y al menos un servicio REST. El cliente web tiene un campo y un botón y cada vez que el usuario envía un mensaje, este se lo envía al servicio REST y actualiza la pantalla con la información que este le regresa en formato JSON. El servicio REST recibe la cadena e implementa un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y el retorno de la respuesta a cada una de las tres instancias del servicio LogService.

![arquitectura](https://github.com/lgar000/patronesArquitecturales/blob/main/Imagenes/diagramaArquitectura.png)

## Despliegue en AWS

Para verificar el despliegue del taller en en AWS usando EC2 y Docke, puede revisar el siguiente video:

https://www.youtube.com/watch?v=x7JcbvYOSxk

## Construido Con

* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Lenguaje de programación y desarrollo
* [Html](https://developer.mozilla.org/es/docs/Web/HTML) - Lenguaje de marcado para la elaboración de páginas web
* [JavaScript](https://developer.mozilla.org/es/docs/Web/CSS) -JavaScript es un lenguaje de programación interpretado
* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Intellij](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Git](https://rometools.github.io/rome/) - Sistema de control de versiones distribuido
* [Docker](https://www.docker.com/) - Docker es una plataforma de código abierto diseñada para facilitar la creación, implementación y ejecución de aplicaciones en contenedores

## Autor

* **Laura García** - [lgar000](https://github.com/lgar000)

