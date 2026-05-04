---
icon: lucide/lock
---

# Sobre autenticación y credenciales

Para que un usuario pueda autenticarse en la aplicación debe enviar sus credenciales a través del *front-end*, hacia el *back-end*, donde alli se resolverá y devolverá una respuesta de confirmación con un *token* de acceso en caso de haber sido autenticado, o una respuesta *Not authenticated* en caso de no haberlo sido.

## Registrando un nuevo usuario

Cuando el usuario ingresa sus credenciales e intenta enviarlas al servicio de autenticación, pasan primeramente por una función de validación ubicada en el *front-end* donde se chequeará que las credenciales cumplan con unos requisitos de validación. Estos son:

1. La contraseña debe tener, como mínimo, 8 carácteres.
2. Debe haber una letra mayúscula.
3. Debe haber un símbolo especial.
4. Debe haber un número.

Además de otros requisitos secundarios como que el nombre de usuario tenga contenido, que los campos de "repita la contraseña" y "contraseña" sean iguales, o como otro más especial, que el nombre de usuario esté disponible, cuya confirmación se basa en una petición al *back-end* para comprobarlo.

Estas comprobaciones son hechas por un servicio llamado **ValidationService**.

```ts title="services/ValidationService.ts"

/**
 * Servicio de validación de credenciales.
 * @author JesusLugo2002
 */
export class ValidationService  {
    /**
     * Comprueba que el nombre de usuario esté válido y disponible.
     * @param username - Nombre de usuario a comprobar.
     * @returns Un ValidationResult
     */
    static async isUsernameAvailable(username: string): Promise<ValidationResult> {
        // Lógica de validación...
    }

    /**
     * Comprueba que el correo electrónico esté válido.
     * @param email - Correo electrónico a comprobar.
     * @returns Un ValidationResult
     */
    static validateEmail(email: string): ValidationResult {
        // Lógica de validación...
    }

    /**
     * Comprueba que una contraseña sea válida bajo los siguientes requisitos:
     * 1. Debe tener un mínimo de carácteres (establecido en MIN_PASSWORD_LENGTH).
     * 2. Debe contener al menos una letra mayúscula.
     * 3. Debe contener al menos un número.
     * 4. Debe contener al menos un caracter especial de los establecidos en SPECIAL_CHARACTERS.
     * 5. La contraseña debe ser igual a la contraseña repetida.
     * @param password - Contraseña a comprobar.
     * @param repeatedPassword - Contraseña repetida.
     * @returns Un ValidationResult
     */
    static validatePassword(password: string, repeatedPassword: string): ValidationResult {
        const MIN_PASSWORD_LENGTH = 8;
        const SPECIAL_CHARACTERS = "!@#$&?¿¡€";
    
        // Lógica de validación...
    }
}
```

Este servicio también hace uso de una clase propia, **ValidationResult**.

```ts title="services/ValidationService.ts"
export type ValidationResult = {
    isValid: boolean; // True si es válido, de lo contrario, False.
    message?: string; // Mensaje opcional.
}
```

Que utilizamos para adjuntar un mensaje al estado de validez, permitiéndonos así enviar un mensaje del por qué es inválido y mostrarlo por medio de, por ejemplo, alertas nativas, como se hace a continuación.

```ts title="containers/RegisterContainer.ts"
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
```

!!! success

    Tras haber sido registrado correctamente, la aplicación procederá con iniciar la sesión automáticamente.


## Iniciando sesión

Una vez tenemos una cuenta registrada en Steamgram, podemos iniciar sesión con nuestro nombre de usuario y la contraseña. Por detrás, al enviar las credenciales, el *back-end* las valida con su base de datos y devuelve una respuesta negativa, en caso de que el usuario no exista o la contraseña no coincida, o positiva, en caso de que logre ser autenticado. Una vez autenticado, junto a esa respuesta positiva se envia un *token* de acceso, utilizado como medida adicional de seguridad a la hora de realizar peticiones de creación/eliminación/modificación a la API de Steamgram para asegurar que estas peticiones provienen de la aplicación y no de terceros indeseados.

!!! note

    El *back-end* maneja las contraseñas hasheadas para lograr una mayor seguridad en su almacenamiento y gestión.


## Almacenamiento y uso del token de acceso

El *token* de acceso proporcionado por el *back-end* es almacenado en el dispositivo haciendo uso del paquete [SecureStore](https://docs.expo.dev/versions/latest/sdk/securestore/) de Expo, una librería que nos proporciona una forma segura de encriptar y almacenar información en formato clave-valor.

### Rutas protegidas

La aplicación, a excepción de las pantallas de inicio de sesión y registro, está totalmente protegidas para *guards* de autenticación. Esto le prohibe la entrada a aquellos usuarios no autenticados en la aplicación. Esto se ha logrado haciendo uso de la estructura de React Native y su forma de gestionar las pantallas por medio del `_layout.tsx` en el directorio `app/`, en donde colocamos una pequeña clausula guarda en la que comprueba que exista un usuario y un *token* de acceso establecido.

## Eliminación de la cuenta

El usuario puede en cualquier momento proceder con la eliminación de su cuenta: esto purga todos sus datos del servidor de Steamgram y de las bases de datos, así como también sus publicaciones y reseñas. Para realizar esta acción, el usuario debe ingresar a su perfil -> *settings* -> *Delete account*. 

Esta opción aprovecha una nueva característica: el uso de la huella. Gracias al paquete de Expo [Local Authentication](https://docs.expo.dev/versions/latest/sdk/local-authentication/), Steamgram aprovecha el uso de *Fingerprint API* que le permite el uso de la autenticación local. Esto nos da esa capa de seguridad extra donde el eliminado de la cuenta solo puede realizarse desde un dispositivo propietario.

!!! note

    Si el dispositivo no es compatible para el uso de la huella o no hay huellas registradas, se tendrá que hacer uso de la clave PIN.

<figure markdown="span">
    ![Ejemplo del uso de huella](https://raw.githubusercontent.com/ArepaConGofio/steamgram/refs/heads/documentation/assets/fingerprint-example.png)
    <figcaption>Huella requerida para eliminar la cuenta.</figcaption>
</figure>