---
icon: lucide/wrench
---

# Instalación y uso

Toda nuestra aplicación se dividen en dos partes: el back-end y el front-end; el primero desarrollado en Spring Boot, el segundo con React Native y Expo. Por el momento no hay binarios distribuidos para instalar directamente en el movil/computador, sino que se requiere compilar y ejecutar el aplicativo desde el código fuente. Para ello, siga los siguientes pasos:

## Requisitos

Es necesario que su equipo cumpla con ciertos requisitos.

**Para el back-end:**

- Tener Java JDK 17+ ([puede descargarse aquí](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))

- Instalar Maven ([puede instalarlo siguiendo este artículo](https://maven.apache.org/install.html))

**Para el front-end:**

- Tener Node v24+ ([puede descargar e instalarse desde aquí](https://nodejs.org/es/download))

- Tener un emulador de Android o un dispositivo físico. Si opta por usar un emulador, vea [este artículo](https://developer.android.com/studio/run/emulator) para saber cómo configurarlo.
Sin embargo, nosotros recomendamos el uso de un dispositivo físico por su simplicidad, en ese caso solo [necesita instalar la app de Expo Go](https://play.google.com/store/apps/details?id=host.exp.exponent&hl=es&pli=1) y un cable USB para la conexión.

## Descargar

Se puede lograr de distintas formas:

### Desde *Releases*

Esta es la opción que recomendamos. Mire el último *release* lanzado por nosotros en el Github: [Steamgram - Releases](https://github.com/ArepaConGofio/steamgram/releases)

### Con Git

Vaya a la carpeta donde desee almacenar la aplicación y, a continuación, ejecute el siguiente comando en una terminal:

```sh
git clone https://github.com/ArepaConGofio/steamgram.git
```

### Descargando el zip desde Github

[Acceda al Github](https://github.com/ArepaConGofio/steamgram) y desde la página principal haga click en "<> code" -> "Download ZIP".

<figure markdown="span">
    ![Descargar zip desde Github](https://raw.githubusercontent.com/ArepaConGofio/steamgram/refs/heads/documentation/assets/how-download.png){ width="500" }
    <figcaption>¿Cómo descargar el zip desde Github?</figcaption>
</figure>

## Iniciar

Para iniciar la aplicación (*back-end* y *front-end*), ejecute alguno de los scripts de inicialización ubicados en la raiz del proyecto.

- `StartAppLinux.sh` si está en Linux.
- `StartAppWindows.bat` si está en Windows.

### ¿Qué hace exactamente el script?

Tranquilo, no estamos metiendo mineros de criptomonedas en tu CPU, solo estamos:

- Abriendo una terminal para *back-end* y otra para *front-end*.
- Instalando dependencias en el *back-end* y *front-end*.
- Compilando el código de ambas partes.
- Iniciándolos.

## Comprobar

Al ejecutar el script de inicialización se abrirán dos terminales: una para el *back-end* y otra para el *front-end*. Para comprobar de que la aplicación ya está en funcionamiento:

1. Podemos chequear si la API está operativa accediendo a [localhost:8080](http://localhost:8080/swagger-ui/index.html).
2. Podemos comprobar que el *front-end* ya está listo para abrirse si vemos esto en la terminal.

<figure markdown="span">
    ![Terminal del front-end](https://raw.githubusercontent.com/ArepaConGofio/steamgram/refs/heads/documentation/assets/front-terminal.png){ width="500" }
    <figcaption>Terminal del *front-end* cuando está iniciado</figcaption>
</figure>

## Detener

Basta con hacer `Ctrl+C` en ambas terminales o cerrarlas directamente.

## Usar la aplicación

Para usar la aplicación debemos tener, o un emulador de Android disponible, o un dispositivo físico conectado con la aplicación de Expo Go. Una vez cumplamos uno de estos requisitos:

- Podemos presionar "A" en la terminal para abrir la aplicación desde la terminal. 
- También podemos escanear el QR con la app de Expo Go en caso de hacerlo en un dispositivo físico. 
- Abrir la url desde la app de Expo Go que nos indica la terminal donde dice:

```sh
> Metro waiting on exp://###.###.#.###:8081 
```

!!! success "¿Ahora qué?"

    Ya una vez accedemos a la aplicación, puede acceder con un usuario de demostración como @admin (*username:* admin, *password*: admin)
    para explorar la aplicación o crear su propia cuenta e iniciar sesión.

    Otros usuarios de demostración disponibles son @jesus, @salas, @user y @gabe, cuya contraseña es igual a sus nombres de usuarios.

    Si quieres ver qué puedes hacer en la aplicación, échale un vistazo al video demostrativo y el manual de usuario.