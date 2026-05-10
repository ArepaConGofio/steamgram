---
icon: lucide/database
---

# Sobre el back-end

## Spring Boot: ¿qué es y para qué sirve?

Como *framework* para el *back-end* hemos utilizado Java Spring Framework (Spring Framework). Un marco popular, de código abierto, de nivel empresarial para crear aplicaciones de producción de nivel independiente que se ejecutan en la máquina virtual Java (JVM). Spring Boot agiliza y simplifica el desarrollo de Spring Framework a través de tres características principales:

1. Autoconfiguración.
2. Un enfoque subjetivo de la configuración.
3. La capacidad de crear aplicaciones independientes.

Estas características trabajan conjuntamente para proporcionar una herramienta que permita configurar una aplicación basada en Spring con un mínimo de configuración e instalación. Las aplicaciones Spring Boot también se pueden optimizar y ejecutar con el tiempo de ejecución de Open Liberty.

*Fuentes:* [¿Qué es Java Spring Boot? - IBM](https://www.ibm.com/es-es/think/topics/java-spring-boot).

## Estructura de carpetas

```bash title="src/backend/..."
├── controllers             # Controladores API REST
│   └── interfaces                  # Interfaces de controladores      
├── domain                  # Modelos de dominio
│   ├── requests                    # Modelos de peticiones
│   └── responses                   # Modelos de respuestas
├── entities                # Modelos de entidad
├── mappers                 # Mappers de dominio <-> entidad
├── repository              # Repositorios JPA
└── service                 # Servicios
    ├── abst                        # Servicios abstractos
    └── interfaces                  # Interfaces de servicios
```

## Patrón *Controller-Service-Repository*


El patrón de diseño utilizado para organizar y estructurar nuestro proyecto es el *Controller-Service-Repository*, en donde se separa el código en tres capas muy pronunciadas para mantener una lógica y simple separación de responsabilidades que nos beneficia, a su vez, con obtener un mejor mantenimiento y comprensión de nuestro proyecto.

!!! quote

    *It’s a pretty simple separation of concerns. If code is related to storage/retrieval, it should go in the Repository. If its dealing with exposing functionality, it goes in the Controller. Anything unique in the business logic would go in the Service layer. The Repository doesn’t care which component is invoking it; it blindly does what it is asked. The Service layer doesn’t care how it gets accessed, it just does its work, using a Repository where required. And the Controller is just passing the work down to the Service layer, so it can stay nice and lean.*
    
    Tom Collings. In Medium.

Fuentes: [Controller-Service-Repository - Tom Collings](https://tom-collings.medium.com/controller-service-repository-16e29a4684e5)

### Capa del controlador

El controlador es la capa más externa de las tres. Es el que contiene la lógica de negocio expuesta. En el caso de las APIs, donde se establecen los *endpoints* a los que distintas aplicaciones pueden consultar con peticiones HTTP, recibiendo datos de la cabecera o el cuerpo de las mismas y enviándolos a los servicios donde se ejecuta el peso fuerte de la lógica, en algunos casos, validando previamente.

### Capa del servicio

El servicio es la capa intermedia, donde reside la lógica de negocio de la aplicación. Aqui se reciben los datos provenientes de los controladores y se crea/modifica/elimina información, en base a la naturaleza de la petición y el resultado que se desea obtener. Aunque ciertamente aquí es donde se manipula la información, donde se almacena es en la...

### Capa del repositorio

La capa más interna de la aplicación, donde reside la conexión con las fuentes de información externas a la aplicación, y con esto pueden comprenderse ficheros, bases de datos o APIs de terceros. Hay librerías que automatizan el desarrollo de esta capa ya que el comportamiento que se encuentra aqui es muy común y giran en torno a las operaciones CRUD (Create, Read, Update, Delete).

## Nuestra implementación

Para mostrar cómo hemos implementado este patrón, mostraré a continuación de ejemplo el repositorio, servicio y controlador encargado de gestionar las publicaciones de Steamgram.

### PostController

Aqui residen algunos *endpoints* relacionados con acciones ante las publicaciones de la aplicación.

```java
@RestController
@RequestMapping("/api/post")
@Tag(name = "Post", description = "Complete post management")
public class PostController implements IController<PostResponse, PostRequest, Integer> {

    private final IPostService postService;
    private final PostMapper postMapper;

    public PostController(IPostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @Override
    @GetMapping("/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Post found successfully"),
        @ApiResponse(responseCode = "404", description = "Post not found"),
    })
    @Operation(summary = "Find a Post by its Id", description = "Find a Post by its Id")
    public ResponseEntity<PostResponse> findById(@Valid @PathVariable Integer id) {
        com.arepacongofio.steamgram.entities.Post post = postService.findById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(postMapper.toResponse(post));
    }

    // Más endpoints...
}
```

Como se puede ver, el controlador tiene inyectado en su contenido el servicio de publicaciones para ejecutar la búsqueda, así como también un *mapper* para transformar el resultado en un `PostResponse`, más adecuado para las respuestas HTTP donde se puede personalizar más la información que se quiere devolver en una petición.

### PostService

Siguiendo el ejemplo de "dame una publicación por su identificador", vemos a continuación como se ejecuta la lógica para buscar esta publicación desde la capa de servicios.

```java
@Service
public class PostServiceImpl {

    PostJpaRepository postRepository;
    
    public PostServiceImpl(PostJpaRepository postRepository) {
        postRepository = postRepository;
    }

    public Post findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    // Más funciones del servicio...
}
```

!!! note

    Para la documentación mostramos la función `findById()` dentro del servicio, pero en el caso real, los servicios heredan de un servicio abstracto que implementan todas estas operaciones básicas.

La función requerida `findById()` recibe la ID proveniente del controlador y ejecuta la lógica de búsqueda: le pide al repositorio encontrar una publicación por la ID establecida, y si no la encuentra, devolverá un `null`. Esto sirve para, en caso de que no exista la publicación que se desea encontrar, el servidor pueda devolver un mensaje negativo a la aplicación que lo solicita con un código 404 (Not Found). 

### PostRepository

El repositorio es el componente más interno implicado en esta búsqueda de la publicación. Puede estar conectado a distintas fuentes, desde un archivo JSON que contiene todos los datos, una base de datos H2, MySql... solo mirando el repositorio no lo sabemos, y esto es lo interesante: esta separación del repositorio nos permite trabajar con posibles distintas fuentes de información sin que necesariamente debamos preocuparnos de la identidad de esta fuente. 

Como mencionamos al principio, dado que las operaciones CRUD son muy comunes, ya existen librerías que nos pueden ayudar a generar las operaciones básicas. Aqui lo estamos haciendo, usando del paquete de repositorios que nos trae Spring Boot.

```java
@Repository
public interface PostJpaRepository extends JpaRepository<Post,Integer> {}
```

Solo con declarar la interfaz y extenderla de `JpaRepository`, Spring Boot se ocupa de inyectar el repositorio en nuestro servicio con todas las operaciones básicas que necesitemos según la entidad y el tipo de ID que tenga. Es por eso que existe `findById()` en nuestro servicio sin que nosotros personalmente lo hayamos implementado.

!!! important

    La librería utilizada también nos permite añadir *queries* más personalizadas introduciendo bajo cierta convención una nueva función en la interfaz o haciendo uso de la anotación `@query`.