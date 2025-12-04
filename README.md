ArteLabSpa — Aplicación Móvil Android
Proyecto académico — DUOC UC
Desarrollado en Kotlin + Jetpack Compose + MVVM
Luciano Picinini y Jose Matte

 Descripción del proyecto
ArteLabSpa es una aplicación móvil para la gestión de usuarios y productos de un estudio de estética.
Permite:
- Registro y login de usuarios con validación de formularios
- Persistencia de sesión con JWT
- Consumo de APIs externas para autenticación y productos
- Visualización y filtrado dinámico de productos
- Gestión del perfil del usuario (incluye foto/Avatar)
- Navegación fluida basada en Material Design 3

El proyecto implementa arquitectura MVVM, manejo de estado con StateFlow, y comunicación con API mediante Retrofit.

 --Tecnologías utilizadas / Frontend / Android
- Kotlin
- Jetpack Compose
- Material Design 3
- MVVM (Model-View-ViewModel)
- StateFlow / MutableStateFlow
- Coroutines + viewModelScope
- Navigation Compose
- Coil (carga de imágenes)

 Backend
- Microservicios externos
- Autenticación basada en JWT

 Persistencia
- SharedPreferences mediante SessionManager

Testing
- JUnit4
- MockK
- Coroutines Test
- Cobertura sobre ViewModels y validaciones

 Funcionalidades principales
Registro de usuario:

- Validación de email, contraseña y campos requeridos
- Llamada a API con Retrofit
- Manejo de estados de error/success

Login:

- Validaciones previas usando ValidationUtils
- Manejo de errores por:
. Formato inválido
. Credenciales incorrectas
. Fallos de conexión
. Errores 500 inesperados

Guardado del token y datos del usuario en SessionManager

 Productos:

- Consumo de API real
- Filtro por nombre
- Filtro por precio máximo
- Uso de combine() de StateFlow para filtrar en tiempo real

Perfil:
- Mostrar datos del usuario
- Cambiar avatar (API + Compose)

 Persistencia:
- Token y datos guardados de forma local
- La sesión se mantiene después de cerrar la app

Pruebas Unitarias:
El proyecto incluye pruebas reales con MockK sobre:

LoginViewModelTest
Prueba:

Email inválido
Contraseña vacía
Login exitoso
Error enviado por el backend
HttpException 401
IOException (sin conexión)

Error inesperado
Estas pruebas verifican que el ViewModel maneje correctamente todas las rutas posibles del login.

ValidationUtilsTest
Prueba:
Emails inválidos → deben retornar false
Emails válidos → deben retornar true

 ProductosViewModelErrorTest
Prueba:
Que al fallar la API se muestre un mensaje de error
Que la lista de productos quede vacía
Que isLoading termine en false


Instalación y Ejecución
1. Clonar el repositorio
git clone https://github.com/tuusuario/ArteLabSpa.git

2. Abrir en Android Studio

File → Open → Seleccionar carpeta del proyecto


3. Ejecutar
Run → Run ‘app’
📱 APK Release (para entregar al profesor)
Se generó un APK firmado:
Ruta común:
app/release/app-release.apk
Este APK puede instalarse en cualquier dispositivo Android o subirse a un emulador online como Appetize.io o AndroidOnlineEmulator.

 Conclusiones
ArteLabSpa implementa:

Diseño visual moderno con Material 3
Arquitectura MVVM correctamente aplicada
Gestión de estado con StateFlow
Formularios completamente validados
Consumo de API mediante Retrofit
Persistencia de sesión con JWT
Pruebas unitarias reales y significativas
APK Release firmado y listo para distribución
