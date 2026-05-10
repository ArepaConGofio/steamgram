---
icon: lucide/pyramid
---

# Sobre el front-end

## React Native: ¿qué es y para qué sirve?

Como framework para el *front-end* hemos utilizado [React Native](https://reactnative.dev/). Creado por Meta en 2015, permite construir aplicaciones móviles multiplataforma utilizando JavaScript como lenguaje de programación. Con React Native, puedes desarrollar aplicaciones tanto para iOS como para Android utilizando un único código base.

La principal característica de React Native es su enfoque en la creación de interfaces de usuario nativas. A diferencia de otros frameworks híbridos, React Native utiliza componentes nativos de las plataformas móviles, lo que brinda un rendimiento y apariencia similares a las aplicaciones nativas desarrolladas en Swift/Objective-C para iOS o Java/Kotlin para Android.

React Native utiliza el mismo concepto de componentes y el modelo de programación declarativo que React, otro framework de JavaScript desarrollado por Facebook en 2013. Esto permite reutilizar y combinar componentes para construir interfaces de usuario interactivas y dinámicas.

Una ventaja clave de React Native es que permite un desarrollo más eficiente y rápido al utilizar un único código base para múltiples plataformas. Además, proporciona acceso a las API nativas de los dispositivos, lo que significa que se pueden utilizar las funciones y características específicas de iOS y Android cuando sea necesario.

*Fuentes:* [Qué es React Native y para qué sirve - Digitality](https://www.digitality.es/blog/que-es-react-native-y-para-que-sirve)

## Estructura de carpetas

``` bash title="src/frontend/..."
.
├── app/                 # Pantallas/rutas de la aplicación                    
├── assets/              # Recursos de la aplicación
│   └── images/                 # Imágenes
├── components/          # Componentes de presentación
│   ├── pages/                  # Componentes principales de pantallas
│   ├── ui/                     # Elementos UI generales
│   └── views/                  # Elementos UI específicos
├── containers/          # Contenedores
├── context/             # Contextos de la aplicación (AuthContext, ThemeContext...)
├── models/              # Modelos empleados en la aplicación
├── services/            # Servicios auxiliares (ValidationService...)
│   └── interfaces/
├── styles/              # Estilos globales
└── utils/               # Funciones auxiliares (APIHandlers)
    └── interfaces/
```

## Patrón *Container-Presentation* 

Para el desarrollo de la aplicación, el front-end fue organizado siguiendo los principios y la organización del patrón de diseño [Container-Presentational](https://tsh.io/blog/container-presentational-pattern-react); un patrón de diseño muy utilizado en React y que, personalmente gracias a su separación de responsabilidades, capacidad para la reutilización de lógica/UI y escalabilidad, nos permite coincidir con las mejores prácticas en programación para lograr un producto eficiente y sostenible a largo plazo. Con este patrón de diseño ganamos:

1. **Estandarización**: cuando todo el código se escribe siguiendo un patrón, se vuelve predecible y fácil de leer para todos.
2. **Ahorro de tiempo**: siempre que se conozcan los patrones utilizados, se puede comprender fácilmente toda la aplicación. Esta comprensión es transferible a todas y cada una de las características posteriores creadas de la misma manera.
3. **Mantenimiento**: La separación de responsabilidades y la encapsulación significa que, en caso de errores, se sabe exactamente dónde buscar, incluso cuando la aplicación crece cada vez más.

*Fuentes:* [Do container components and React still go together? The container-presentational pattern in React vs hooks - Bartosz Janiuk](https://tsh.io/blog/container-presentational-pattern-react)

### Componentes del contenedor

Estos componentes crean y mantienen datos, llevándolos aún más a sus componentes secundarios. No contienen elementos de interfaz de usuario. Su objetivo principal es entregar datos y lógica de negocio a los componentes de presentación. Es un componente llamado inteligente porque se encarga de todo el funcionamiento interno de la aplicación en segundo plano. Además de los componentes inteligentes, también tenemos...

### Componentes de presentación

Su trabajo es poner datos de presentación a disposición de la interfaz de usuario. Estos datos son pasados primero por sus componentes principales. Componentes como éste pueden describirse como tonto componentes porque no hacen nada por sí solos. Por lo general, no tienen un estado local a menos que lo necesiten para mostrar la interfaz de usuario de una manera específica.

## Nuestra implementación

A continuación mostraré resumidamente una demostración del patrón *container-presentation* implementado en nuestro código, mostrando la página da registrar un usuario.

### Punto de entrada

Por como funciona React Native, cada pantalla de una aplicación equivale a un archivo `.tsx` dentro del directorio `app/` de un proyecto. Para mantener una convención, estos puntos de entrada le añadimos el sufijo **Page** en su clase. En este caso, la clase **RegisterPage** actua de punto de entrada y es lo primero que carga React Native cuando se navega a la pantalla.

Los puntos de entrada los usamos principalmente para cargar los datos necesarios, haciendo *fetch* a la API para cargar, por ejemplo, las publicaciones a mostrar o los juegos guardados del usuario. En este caso, no hacemos nada de eso sino establecemos una estructura simple.

```tsx title="app/register.tsx"
export default function RegisterPage() {
  return (
    <SafeAreaView style={styles.container}>
      <AuthTitle/>
      <RegisterContainer /> {/* Cargamos el contenedor */}
      <Footer />
    </SafeAreaView>
  );
}
```

### RegisterContainer

Como se mencionó anteriormente, los contenedores son los componentes que poseen la lógica. Aquí implementamos esas funciones que se ejecutarán en la página de registro. Nuevamente, para mantener la convención, añadimos de sufijo la palabra **Container**.

```tsx title="containers/RegisterContainer.tsx"
export default function RegisterContainer() {
  const { register } = useContext(AuthContext);
  const router = useRouter();

  const [username, setUsername] = useState("");
  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [repeatedPassword, setRepeatedPassword] = useState("");

  /**
   * En caso de presionar el botón de "Ir al Login", redirige
   * a la pantalla de inicio de sesión.
   */
  function goToLogin(): void {
    return router.navigate("/login");
  }

  /**
   * Ejecuta el registro del usuario con sus necesarias credenciales. 
   * En caso de error, se muestra en forma de una alerta nativa.
   */
  async function submit(): Promise<void> {
    const result = await register({ username, nickname, password, email, repeatedPassword }) as ValidationResult;
    if (!result.isValid) {
      Alert.alert("Error", result.message);
    }
  }

  return (
    <RegisterView
      getters={{ username, email, password, repeatedPassword, nickname }}
      setters={{ setUsername, setEmail, setPassword, setRepeatedPassword, setNickname }}
      callbacks={{ goToLogin, submit }}
    />
  );
}
```

Como se puede ver, en **RegisterContainer** no hay nada relacionado con el componente visual ni elementos de interfaz, en cambio, establecemos aqui las variables del formulario de registro, la función de enviar datos y la de redirección al *Login* cuando se selecciona la opción de "¿Ya tienes una cuenta? ¡Inicia sesión!".

### RegisterView

Este es el componente principal, visual, que usamos para mostrar una pantalla. Por convención, le añadimos de sufijo **View**. En este caso, **RegisterView** pilla todos los datos provenientes del contenedor y los muestra bajo una estructura y un estilo establecido en el fichero.

```tsx title="components/pages/RegisterView.tsx"
type RegisterGetters = {
  username: string;
  email: string;
  password: string;
  repeatedPassword: string;
  nickname: string;
};

type RegisterSetters = {
  setUsername: React.Dispatch<React.SetStateAction<string>>;
  setEmail: React.Dispatch<React.SetStateAction<string>>;
  setPassword: React.Dispatch<React.SetStateAction<string>>;
  setRepeatedPassword: React.Dispatch<React.SetStateAction<string>>;
  setNickname: React.Dispatch<React.SetStateAction<string>>;
};

type RegisterCallbacks = {
  goToLogin: () => void;
  submit: () => void;
};

type Props = {
  getters: RegisterGetters;
  setters: RegisterSetters;
  callbacks: RegisterCallbacks;
};

export default function RegisterView({ getters, setters, callbacks }: Props) {
  const input2ref = useRef<TextInput>(null);
  const input3ref = useRef<TextInput>(null);
  const input4ref = useRef<TextInput>(null);
  const input5ref = useRef<TextInput>(null);

  return (
    <View style={authFormStyles.formContainer}>
      <Text style={authFormStyles.title}>Register</Text>
      <View style={authFormStyles.form}>

        <Text style={styles.inputLabel}>Username*</Text>
        <TextInput
          style={styles.input}
          value={getters.username}
          onChangeText={setters.setUsername}
          placeholder="Insert your username..."
          returnKeyType="next"
          onSubmitEditing={() => { input2ref.current?.focus() }}
          submitBehavior="submit" />

        <Text style={styles.inputLabel}>Nickname</Text>
        <TextInput
          ref={input2ref}
          style={styles.input}
          value={getters.nickname}
          onChangeText={setters.setNickname}
          placeholder="Insert your nickname..."
          returnKeyType="next"
          onSubmitEditing={() => { input3ref.current?.focus() }} 
          submitBehavior="submit"/>

        <Text style={styles.inputLabel}>Email*</Text>
        <TextInput
          style={styles.input}
          ref={input3ref}
          value={getters.email}
          onChangeText={setters.setEmail}
          placeholder="Insert your email..."
          returnKeyType="next"
          onSubmitEditing={() => { input4ref.current?.focus() }} />

        <Text style={styles.inputLabel}>Password*</Text>
        <TextInput
          style={styles.input}
          ref={input4ref}
          value={getters.password}
          onChangeText={setters.setPassword}
          placeholder="The password must be minimum 8 characters..."
          returnKeyType="next"
          onSubmitEditing={() => { input5ref.current?.focus() }} 
          submitBehavior="submit"/>

        <Text style={styles.inputLabel}>Repeat password*</Text>
        <TextInput
          ref={input5ref}
          style={styles.input}
          value={getters.repeatedPassword}
          onChangeText={setters.setRepeatedPassword}
          placeholder="Repeat the password!"
          returnKeyType="done"
          onSubmitEditing={callbacks.submit} />

        <TouchableOpacity
          style={authFormStyles.formSubmit}
          onPress={callbacks.submit}
        >
          <Text style={authFormStyles.formSubmitLabel}>Register</Text>
        </TouchableOpacity>
      </View>
      <Text onPress={callbacks.goToLogin} style={authFormStyles.toAltPage}>
        Already have an account? Log in!
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  input: {
    borderColor: "#D9C4BF",
    borderWidth: 1,
    borderRadius: 10,
    marginVertical: 5,
    paddingHorizontal: 10,
  },
  inputLabel: {
    fontWeight: "bold",
  },
  inputContainer: {
    marginVertical: 8,
  }
});
```

!!! note

    Nuestra forma de transferir datos entre los contenedores y componentes de presentación siguen también un pequeño patrón para facilitarnos el acceso, dividiendo esta información en `getters` para obtener datos, `setters` para establecerlos/editarlos y `callbacks` para aquellas funciones que proveen un comportamiento, como el `submit` que se ejecuta tras completar el formulario.