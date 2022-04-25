# Desafío Mutantes - Meli 🧬

Este desafío consta de una aplicación desarrollada en java que se encuentra habilitada en la nube y permite mediante el consumo de un servicio post la verificación de cadenas de adn de diferentes humanos con el fin de determinar si estos son mutantes o no.
La secuencia de adn debe ser una matriz de NxN representada en un array de strings, cuyos caracteres solo pueden ser "A", "G", "T" o "C".
Todos los anáisis realizados son almacenados en una base de datos mongo desplegada en la nube.
Por medio del consumo de un servicio Get, la aplicación permite consultar un reporte estadístico sobre el análisis realizado a las diferentes secuencias de ADN.

El proyecto está construido bajo el módulo multi módulo de maven, donde contamos con un módulo que almacena nuestra capa web ( **meli-challenge-web-backend** ) y existe un segundo módulo que se encarga de empaquetar de una manera aislada toda nuestra lógica de negocio ( **meli-challenge-domain** ).

## Instalación 🔧 

### Pre-requisitos ⚙️

- Instalar previamente JDK versión 8
- Instalar IDE java de preferencia
- Configurar lombok en el IDE instalado

### En marcha  🚀

Luego de clonar el proyecto desde la rama main, realize la importación de este en el IDE como un proyecto ya existente de maven. 
Para poner en marcha, ejecute la clase principal como una aplicación java. Este correrá por defecto en el puerto 9099.

Para ejecutar los test unitarios, ejecute el siguiente comando ubicado en el assembly del proyecto:
```sh
mvn test
```

## Tecnologías   💻 

Para el desarrollo de este proyecto se hizo uso de las siguientes tecnologías :

| Tecnología | Descripción |
| ------ | ------ |
| [SpringBoot](https://spring.io/projects/spring-boot) |Módulo del framework spring para el desarrollo ágil de aplicaciones |
| [JUnit](https://junit.org/junit4/) | Biblioteca para la creación de pruebas unitarias |
| [Maven](https://maven.apache.org/) | Gestor de dependencias |
| [Lombok](https://projectlombok.org/) | Librería para reducción de código a través a anotaciones |
| [Swagger](https://swagger.io/) | Herramienta para documentación automatizada de las APIs |
| [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb/)  | Módulo de Spring que simplifica la persistencia de los datos |
| [SpringBoot Caché](https://spring.io/guides/gs/caching/) | Módulo para administrar el caché de la aplicación |
| [SpringBoot Validation](https://spring.io/guides/gs/validating-form-input/)  | Módulo para la validación personalizada de parámetros mediante anotaciones |

## Sobre la solución 💡
El enfoque que se le dió a la solución de este desafío, mas que recorrer cada elemento de la matriz buscando caracteres semejantes a su alrededor, fue implementar una serie de builders que formaran palabras en todas las posibles direcciones de la matriz (Horizontal, vertical, y todas sus oblicuas), las cuales pasan posteriormente por un proceso de verificación de coincidencias con los patrones dados en el enunciado.
Las verificaciones positivas se van totalizando para poder determinar si estamos frente al caso de un mutante, y detener el analisis del programa una vez se cumpla la condición, evitando procesar datos innecesarios.

## Probando la aplicación 🕵🏻‍♀️

La aplicación está desplegada en heroku (PaaS). Para revisar la documentación de los servicios a consumir ingrese [aquí.](https://dz-meli-challenge.herokuapp.com/swagger-ui.html) 

#### Detectar si un humano es mutante

Para esto, partimos de que la secuencia de ADN se recibe como un arreglo de Strings que representan una matriz de (NxX).
Sabremos si un humano es o no mutante si se encuentra mas de una secuencia de 4 letras iguales de forma oblicua, horizontal o vertical, como se puede ver en el siguiente caso:

![N|Solid](https://i.ibb.co/BNzppjh/mutantes-Img.png)

Por postman, consumimos un servicio tipo POST con la url https://dz-meli-challenge.herokuapp.com/mutant/ , y le agregamos a la petición un cuerpo tipo json en el que irá la secuencia de ADN de la siguiente manera : 
* _Caso mutante:_ { "dna": [ 
"ATGCGA",
"CAGTGC",
"TTATGT",
"AGAAGG",
"CCCCTA",
"TCACTG" ] }

* _Caso no mutante:_ { "dna": [ 
"ATGCGA",
"CTGTGC",
"TTATGT",
"AGAAGG",
"CACCTA",
"TCACTG" ] }

En caso de presentar errores en el request, como por ejemplo una matriz M x N , adn nulo o vacío, caracteres diferentes a A,T,C,G , la respuesta será un "Bad Request (400)".

Si la secuencia de ADN corresponde a un mutante, la respuesta será "HTTP OK (200)".

De lo contrario, si no se trata de un mutante, la respuesta será "Forbidden (403)".

#### Consultar estadísiticas

Para esto, se debe consumir el servicio GET con url https://dz-meli-challenge.herokuapp.com/stats/, el cual tiene como respuesta un json con la siguiente estructura:
{
"count_mutant_dna":40,
"count_human_dna":100, 
"ratio":0.4
}
* _count_human_dna_ : Cantidad de adns evaluados
* _count_mutant_dna_ : Cantidad de adns evaluados que han sido mutantes
* _ratio_ : Porcentaje del total equivalente a humanos mutantes 

## Autor ✍️️

* **Carlos David Zapata Rúa** - *Construcción y documentación.* - [David Zapata](https://www.linkedin.com/in/deiivyzapata/) 
