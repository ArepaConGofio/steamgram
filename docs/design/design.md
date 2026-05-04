---
icon: lucide/palette
---

# Decisiones de diseño

Hemos tomado un especial cuidado en el diseño de la aplicación dada la naturaleza del mismo: una red social, así como lo son las más populares, deben ofrecer un entorno amigable, usable y accesible para sus usuarios, los cuales pueden consumir más o menos tiempo explorando y descubriendo publicaciones. 

## Recursos utilizados

Para este trabajo se hizo uso de varios sitios web que nos facilitarían recursos e información necesaria para diseñar Steamgram, entre ellos:

1. [Dribble](https://dribbble.com/): Una red social para que los diseñadores puedan autopromocionar su trabajo, sirviendo como un repositorio de portafolios, encontrar u ofrecer trabajo y encontrar inspiración para el diseño web o de aplicaciones.
2. [Android Design Guidelines](https://developer.android.com/design?hl=es-419): Guias de diseño proporcionadas por Android para lograr una mayor accesibilidad y usabilidad en dispositivos Android.
3. [Material 3 Guidelines](https://m3.material.io/): Guias de diseño y experiencia de usuario proporcionadas por Google. Un producto *Open Source* que va muy de la mano con Figma.
4. [Figma](https://www.figma.com/es-es/): Una herramienta de diseño de interfaces colaborativa que permite crear prototipos y *sketches* de la aplicación.
5. [Web Content Accessibility Guidelines (WCAG) 2.1](https://www.w3.org/TR/WCAG21/): Un amplio documento donde se detallan una gran variedad de recomendaciones y estándares de accesibilidad.
6. [Adobe Color](https://color.adobe.com/es/): Una herramienta de Adobe que nos proporciona la posibilidad de explorar y diseñar paletas de colores.

### Diseño en Figma

A continuación puede echarle un vistazo a un boceto del aplicativo desarrollado en Figma.

- [Steamgram - Figma](https://www.figma.com/design/6fCEATWz4yeyrRqu8JbWro/Steamgram?node-id=10-441&p=f)

## Heurísticas de Nielsen

Los 10 heurísticos definidos por Jakob Nielsen y Rolf Molich son buenas prácticas que se pueden aplicar al diseño de interfaces para evaluar su usabilidad. Se trata de principios basados en la observación de usuarios reales y de los problemas a los que se enfrentan a la hora de utilizar cualquier tipo de interfaz.

*Fuentes:* [Los 10 principios heurísticos de Nielsen - Stefano Serafinelli](https://www.teacuplab.com/es/blog/los-10-principios-heuristicos-de-nielsen-explicados-con-ejemplos/).

Nuestras decisiones de diseño giraron en torno a los recursos mencionados al principio de esta página y las heurísticas de Nielsen, pues juntos tuvimos unos cimientos bastante sólidos sobre lo que es una aplicación usable y accesible. A continuación le mostraré de ejemplo algunas características que hemos implementado...

## Características a destacar

### Uso del "Enter" del teclado

Una de las primeras implementaciones fue el uso de `returnKeyType` y `onSubmitEditing` para los campos de formulario en nuestra aplicación. Gracias a ellos, podemos añadir un comportamiento cuando el usuario le de al "Enter" en su teclado; en el primer `TextInput`, `onSubmitEditing` está pensado para que, cuando el usuario le dé al Enter, automaticamente salte al siguiente campo, mientras que en el segundo `TextInput`, se ejecutará la función de enviar los datos, tal cual como si el usuario hubiese presionado el botón de "registrarse". Además, con `returnKeyType`, podemos cambiar el ícono que se ve en el botón del "Enter" para que esté más acorde con "ir al siguiente campo" y luego "enviar los datos".

```tsx
// Campo para ingresar la contraseña
<TextInput
    style={styles.input}
    placeholder="The password must be minimum 8 characters..."
    secureTextEntry
    returnKeyType="next"
    // Cuando se presione el Enter
    onSubmitEditing={() => { input5ref.current?.focus() }} />

// Campo para ingresar la contraseña a repetir
<TextInput
    ref={input5ref}
    placeholder="Repeat the password!"
    secureTextEntry
    returnKeyType="done"
    onSubmitEditing={callbacks.submit} />
```

<figure>
    ![Teclado Next]()
    <figcaption>Teclado con `returnKeyType="next"`</figcaption>
</figure>

<figure>
    ![Teclado Done]()
    <figcaption>Teclado con `returnKeyType="done"`</figcaption>
</figure>

### Pantallas de carga

Cuando se navega entre las diferentes pantallas, es comun en varias de ellas realizar peticiones al *back-end* para cargar datos. Mientras esto se hace, normalmente se congelaría la UI hasta que esto se complete. Para evitar esto, creamos un pequeño componente reutilizable que mostramos mientras se realiza esta carga de datos.

```tsx
export default function GameDetailsPage() {
    const { gameId } = useLocalSearchParams<{ gameId: string }>();
    const [data, setData] = useState<Game>();
    const [isLoading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string>("");

    // Se inicia con la carga de datos
    useEffect(() => {
        const api = new GamesAPIHandler();
        api.getGameDetails(parseInt(gameId))
            .then(value => setData(value))
            .catch(reason => setError(reason.message))

            // Cuando se carguen los datos o suceda un error, se marcará el "cargando"
            // como finalizado.
            .finally(() => setLoading(false));
    }, []);

    // Mientras esté cargando, se va a mostrar la pantalla de carga
    if (isLoading) return <LoadingIndicator category="Game" />

    // Si ya cargó y hay un error, se muestra un mensaje de error.
    if (data === undefined || error !== "") return <StaticErrorAlert message={error} />;

    // Si no hubo errores, procede con iniciar el componente contenedor.
    return <GameDetailsContainer game={data}/>
}
```

### Diseño minimalista

Y con minimalista no nos referimos a todo blanco y simple, si no a remover lo innecesario. Procuramos mostrar en pantalla aquello estrictamente necesario para el usuario, sin mostrar información de más.

<figure>
    ![Diseño minimalista]()
    <figcaption>Un vistazo al diseño de la pantalla de comunidad</figcaption>
</figure>

### Enlaces visibles

Mostrar los enlaces *clickables* con una linea por debajo del texto es un estándar de navegación en la web. Nosotros lo aplicamos también, y gracias a ello podemos ver, como en este ejemplo, es fácil de identificar cuales elementos son interactuables y cuáles no.

<figure>
    ![Post]()
    <figcaption>Elementos clickables de un post</figcaption>
</figure>

### Advertencias y mensajes de confirmación

Ante diferentes acciones, como todos nos podemos equivocar y se nos puede resbalar el movil, añadimos mensajes de confirmación y advertencias para asegurarnos de que el usuario esté realizando esa acción por voluntad propia y no de la gravedad. A continuación unos ejemplos.

<figure>
    ![Confirmación para subir un post]()
    <figcaption>Elementos clickables de un post</figcaption>
</figure>

<figure>
    ![Advertencia antes de eliminar la cuenta]()
    <figcaption>Advertencia antes de eliminar la cuenta (pensándolo bien, ¿por qué querrías abandonar esta maravillosa aplicación? :c)</figcaption>
</figure>