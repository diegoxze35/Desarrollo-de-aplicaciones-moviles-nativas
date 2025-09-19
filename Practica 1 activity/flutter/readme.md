# Navigation App - Flutter

Esta es una aplicación Flutter que replica la funcionalidad de la aplicación Android nativa NavigationActivity. La aplicación demuestra varios componentes de la interfaz de usuario y patrones de navegación usando Flutter.

## Características

- Navegación con Bottom Navigation Bar
- Diferentes tipos de campos de texto (TextField)
- Varios estilos de botones (ElevatedButton, OutlinedButton, TextButton, IconButton, FAB)
- Controles de selección (Checkbox, Radio, Switch)
- Listas y grids (ListView, GridView)
- Elementos de información (Text, Image, ProgressIndicator)
- **Nueva pantalla con animaciones y diálogos**

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
- `AnotherScreen`: Nueva pantalla con navegación inferior para animaciones y diálogos.
- `AnimationsScreen`: Demostración de animaciones (tamaño, visibilidad, rotación).
- `DialogsScreen`: Demostración de diálogos.

## Diferencias con la versión Kotlin

- La navegación se maneja con un IndexedStack en lugar de NavHost.
- Los componentes de UI son los equivalentes de Flutter a los de Jetpack Compose.
- La animación del ProgressIndicator se implementa con AnimationController.
- La navegación a la nueva pantalla se realiza mediante Navigator.push en lugar de startActivity.

## Funcionamiento de la aplicación

[Incluir capturas de pantalla de la aplicación]

## Licencia

Este proyecto está bajo la Licencia MIT.
