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

Para construir las imagenes docker del proyecto, biquese en la carpeta raíz del mismo y ejecute los siguientes comandos, lo que debería generar las imagenes en docker desktop

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



## Pruebas






## Diseño



## Construido Con

* [Java 11](https://www.oracle.com/co/java/technologies/javase/jdk11-archive-downloads.html) - Lenguaje de programación y desarrollo
* [Html](https://developer.mozilla.org/es/docs/Web/HTML) - Lenguaje de marcado para la elaboración de páginas web
* [JavaScript](https://developer.mozilla.org/es/docs/Web/CSS) -JavaScript es un lenguaje de programación interpretado
* [Maven](https://maven.apache.org/) - Gestión de dependencias
* [Intellij](https://www.jetbrains.com/es-es/idea/) - Entorno de desarrollo integrado para el desarrollo de programas informáticos
* [Git](https://rometools.github.io/rome/) - Sistema de control de versiones distribuido
* [Docker](https://www.docker.com/) - Docker es una plataforma de código abierto diseñada para facilitar la creación, implementación y ejecución de aplicaciones en contenedores

## Autor

* **Laura García** - [lgar000](https://github.com/lgar000)

