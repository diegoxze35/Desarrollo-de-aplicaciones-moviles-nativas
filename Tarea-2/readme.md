# Art Space
Art Space es una aplicación de Android moderna que sirve como un espacio creativo para los usuarios.
Permite explorar una galería de imágenes, tomar fotos con la cámara del dispositivo y ofrece una
funcionalidad de lienzo para crear y guardar dibujos propios. 
La aplicación está construida siguiendo las últimas prácticas de desarrollo de Android,
utilizando Kotlin y Jetpack Compose.
# Características Principales
    1.Galería de Imágenes 
        - Visualización en Cuadrícula: Muestra una colección de imágenes en una LazyVerticalGrid
        adaptable y de alto rendimiento.
        - Carga Paginada: Las imágenes se cargan por páginas para optimizar el uso
        de la memoria.
        - Título Dinámico: La barra de la aplicación (Top App Bar) se actualiza dinámicamente 
        para mostrar el número de imágenes cargadas, utilizando recursos de plurales para
        un soporte de internacionalización correcto.
        -Navegación a Detalle: Permite al usuario tocar una imagen para verla en una pantalla de detalle
    2. Lienzo de Dibujo
        - Creación y Guardado: Ofrece una funcionalidad donde los usuarios pueden dibujar
        en un lienzo y guardar sus creaciones.
        - Lista de Dibujos Guardados: La pantalla DrawingListScreen muestra todos los dibujos
        creados por el usuario en una cuadrícula.
        - Persistencia Local: Los dibujos (ImageBitmap) se cargan desde un almacenamiento local,
        permitiendo a los usuarios ver sus obras de arte en cualquier momento.
        - Estado Vacío: Si no hay dibujos guardados, la aplicación muestra un mensaje informativo
        al usuario.
    3.Integración con la Cámara
        -Captura de Fotos: Permite a los usuarios utilizar la cámara del dispositivo para tomar
        fotos directamente desde la aplicación.

# Estructura del Proyecto
El proyecto sigue una arquitectura limpia y modular, separando las responsabilidades en diferentes capas

# ArtSpace Application

Bienvenido al repositorio de ArtSpace, una aplicación Android diseñada con una arquitectura limpia y modular. Este documento describe la estructura del proyecto y los principios de diseño utilizados.

## 🏛️ Arquitectura del Proyecto

Este proyecto sigue los principios de **Clean Architecture**, separando las responsabilidades del código en tres capas principales: **Data**, **Domain** y **UI**. El objetivo es crear una aplicación escalable, mantenible y fácil de testear, donde la lógica de negocio (dominio) no dependa de los detalles de implementación (como la base de datos o la interfaz de usuario).

Además de la separación por capas, el proyecto está organizado por **funcionalidad (feature)**. Esto significa que dentro de cada capa, el código se agrupa en paquetes que representan una característica específica de la aplicación, como `gallery` o `canvas`.

### Estructura General de Paquetes

```
com.damm.artspace
├── data           // Repositorios
│   ├── canvas
│   └── gallery
│
├── di
│   # Módulos de Inyección de Dependencias (Koin)
│
├── domain         //Modelo de datos
│   ├── canvas
│   └── gallery
│
└── ui
    ├── camera
    ├── canvas
    ├── domain     // datos compartidos entre features
    └── gallery
```

---

### Capas Principales

#### 📦 Capa de Datos (`data`)
Es la capa más externa y se encarga de la obtención y almacenamiento de datos.

* **Responsabilidades:**
    * Implementar los repositorios definidos en la capa de `domain`.
    * Gestionar las fuentes de datos (Data Sources), ya sean remotas (API REST, Firebase) o locales (Base de datos Room, SharedPreferences).
    * Mapear los modelos de datos (DTOs, Entities de base de datos) a los modelos de `domain`.
* **Contenido típico:** Clases de `RepositoryImpl`, `ApiService` (con Retrofit), `AppDatabase` (con Room), Daos, etc.

#### 🧠 Capa de Dominio (`domain`)
Es el corazón de la aplicación. Contiene la lógica de negocio y no tiene dependencias con ninguna otra capa.

* **Responsabilidades:**
    * Definir los modelos de negocio (entidades) que representan los objetos centrales de la app.
    * Contener los **Casos de Uso** (Use Cases o Interactors) que orquestan el flujo de datos entre la UI y los repositorios.
    * Definir las **interfaces de los repositorios** que la capa `data` deberá implementar.
* **Regla clave:** Las clases en esta capa no deben conocer nada sobre Android (nada de `Context`, `View`, etc.).

#### 🎨 Capa de Presentación (`ui`)
Contiene todo lo relacionado con la interfaz de usuario y la interacción con el usuario.

* **Responsabilidades:**
    * Mostrar los datos en la pantalla.
    * Manejar las interacciones del usuario (clics, gestos, etc.).
    * Observar los cambios de estado y actualizar la UI en consecuencia.
---

### Módulos Transversales

####💉 Inyección de Dependencias (`di`)

El paquete `di` es fundamental para conectar las diferentes capas sin generar acoplamiento directo

    •data: Contiene la lógica de acceso a datos. Se encarga de decidir si obtener datos de una API remota o de una base de datos local.
    •di: Proporciona las dependencias necesarias a través de la inyección de dependencias, facilitando las pruebas y el desacoplamiento.
    •ui: Contiene toda la lógica relacionada con la interfaz de usuario, construida con Jetpack Compose.•Se divide en sub-paquetes por funcionalidad (camera, canvas, gallery).
    •composable: Alberga las pantallas y componentes @Composable.
    •domain: Contiene clases de estado o modelos que son útiles para la capa de UI pero no pertenecen a la lógica de negocio principal.Tecnologías y Librerías•Lenguaje: Kotlin
    •UI Toolkit: Jetpack Compose para una UI declarativa y moderna.
    •Diseño: Material 3 para los componentes visuales.
    •Asincronía: Coroutines de Kotlin para gestionar operaciones en segundo plano.•Arquitectura: Patrones de arquitectura limpia (UI, Dominio, Datos) y MVVM/MVI.
    •Navegación: Compose Navigation para la navegación entre pantallas.
    •Cámara: CameraX para una abstracción simplificada y robusta de la API de la cámara.

# Cómo Compilar y Ejecutar
 1.Clona este repositorio: git clone <URL_DEL_REPOSITORIO>
 2.Abre el proyecto en la última versión de Android Studio.
 3.Espera a que la sincronización de Gradle finalice.4.Selecciona un emulador o conecta un dispositivo físico.5.Presiona el botón "Run" (Shift + F10).

# Funcionamiento

https://github.com/user-attachments/assets/00db76a7-0bc7-4929-9ef2-925f48c92370



