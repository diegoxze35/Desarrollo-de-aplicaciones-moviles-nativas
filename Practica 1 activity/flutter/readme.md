# Navigation App - Flutter

Esta es una aplicación Flutter que replica la funcionalidad de la aplicación Android nativa NavigationActivity. La aplicación demuestra varios componentes de la interfaz de usuario y patrones de navegación usando Flutter.

## Características

- Navegación con Bottom Navigation Bar
- Diferentes tipos de campos de texto (TextField)
- Varios estilos de botones (ElevatedButton, OutlinedButton, TextButton, IconButton, FAB)
- Controles de selección (Checkbox, Radio, Switch)
- Listas y grids (ListView, GridView)
- Elementos de información (Text, Image, ProgressIndicator)

## Tecnologías utilizadas

- Flutter
- Dart
- Android Studio / VS Code
- Material Design

## Configuración

1. Asegúrate de tener Flutter instalado. Si no, sigue las instrucciones en [flutter.dev](https://flutter.dev).
2. Clona el repositorio.
3. Abre el proyecto en tu editor preferido.
4. Ejecuta `flutter pub get` para obtener las dependencias.
5. Ejecuta la aplicación con `flutter run`.

## Estructura del proyecto

- `main.dart`: Punto de entrada de la aplicación.
- `NavigationApp`: Widget principal que configura el MaterialApp.
- `NavigationScreen`: Contiene la Scaffold con AppBar y BottomNavigationBar.
- `HomeScreen`: Pantalla de inicio con descripción de la app.
- `TextFieldsScreen`: Demostración de diferentes TextFields.
- `ButtonsScreen`: Demostración de diferentes botones.
- `SelectionScreen`: Demostración de Checkbox, Radio y Switch.
- `ListsScreen`: Demostración de ListView y GridView.
- `InformationScreen`: Demostración de textos, imágenes e indicadores de progreso.

## Diferencias con la versión Kotlin

- La navegación se maneja con un IndexedStack en lugar de NavHost.
- Los componentes de UI son los equivalentes de Flutter a los de Jetpack Compose.
- La animación del ProgressIndicator se implementa con AnimationController.

## Funcionamiento de la aplicación


https://github.com/user-attachments/assets/858ad71e-c9b2-46fe-a2c9-03f8d75629fe

