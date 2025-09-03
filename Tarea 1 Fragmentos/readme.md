### README del Proyecto MyExample

Este documento describe el flujo principal de la app y el propósito de cada Composable del paquete ui/screen. La app está construida con Jetpack Compose y Navigation Compose, y organiza sus pantallas mediante una barra de navegación inferior.

---

### MainActivity.kt

Ruta: app/src/main/java/com/example/myexample/MainActivity.kt

Responsabilidades principales:
- Configura el tema de la app usando MyExampleTheme y habilita edge-to-edge.
- Crea y recuerda un NavController para manejar la navegación con Navigation Compose.
- Define un Scaffold con una NavigationBar (barra inferior) y un NavHost que contiene las rutas.

Detalles clave:
- Destinos y navegación:
  - Usa un enum Destinations que centraliza icono, etiqueta (StringRes) y route (tipo serializable) para cada pantalla.
  - startDestination del NavHost: Calculator.
  - La barra inferior itera Destinations.entries y crea un NavigationBarItem por cada entrada. Al pulsar, navega a destination.route y actualiza el índice seleccionado.
- Estado de selección de la barra inferior:
  - selectedDestination se guarda con rememberSaveable como un Int que coincide con el índice del enum Destinations. Se inicializa con Destinations.CALCULATOR.
- Registro de composables en NavHost:
  - composable<Calculator> -> CalculatorScreen
  - composable<Color> -> ColorScreen
  - composable<Image> -> ImageScreen
  - composable<List> -> ListScreen
  - composable<Button> -> ButtonScreen
- Modificadores:
  - Se propaga Modifier.fillMaxSize() y padding(innerPadding) desde el Scaffold a cada pantalla para respetar insets y layout.

Archivos relacionados:
- Destinations.kt define:
  - enum class Destinations con: CALCULATOR, RADIO_BUTTONS (Color), IMAGE, LIST, BUTTONS.
  - data object serializables: Calculator, Color, Image, List, Button.
  - Íconos Material y etiquetas de strings.xml.

---

### Composables del paquete ui/screen

Ruta base: app/src/main/java/com/example/myexample/ui/screen/

#### CalculatorScreen.kt — CalculatorScreen
Objetivo: Calculadora sencilla entre dos números A y B con operaciones básicas.
- Entradas: Dos TextField (A y B) con KeyboardType.Decimal.
- Operaciones: Se derivan de Operation.entries (ADD, SUB, MUL, DIV, POW, MOD). Cada operación se representa como una Card dentro de una LazyVerticalGrid adaptativa (GridCells.Adaptive(120.dp)).
- Interacción: Al pulsar una Card, convierte A y B a Double (con fallback 0.0) y calcula el resultado según la operación.
- Estado:
  - a, b como String mediante rememberSaveable.
  - result como Double mediante rememberSaveable.
- UI: Muestra las celdas de operaciones con su signo (it.sign) en tipografía titleLarge y, debajo, el resultado como texto.
- Previsualización: Hay un @Preview que envuelve el Composable en un Scaffold respetando el padding.

#### ColorScreen.kt — ColorScreen
Objetivo: Selector de color con RadioButtons y animación del color seleccionado.
- Estado: selectedOption con remember que guarda el Color actual (rojo, verde, azul).
- Animación: animateColorAsState para transicionar al color elegido con tween de 1000 ms y LinearEasing.
- UI:
  - Un círculo (Box con clip CircleShape) que muestra el color animado.
  - Lista de opciones de color; cada fila es selectable con un RadioButton (onClick null por accesibilidad) y un Text con el nombre del color.
- Layout: Column centrada vertical y horizontalmente; separación con Spacer(16.dp).
- Previsualización: Incluye @Preview con Scaffold.

#### ImageScreen.kt — ImageScreen
Objetivo: Mostrar una imagen con un título opcional que aparece/oculta al tocar.
- Estado: expanded con remember para alternar la visibilidad del título.
- UI:
  - Card que contiene una Column clickable.
  - Image(painterResource(R.drawable.jetpack_compose), contentDescription desde strings.xml).
  - AnimatedVisibility que muestra el Text "Jetpack Compose" (tipografía bodyLarge) cuando expanded es true.
- Layout: Box centrado que envuelve la Card.

#### ListScreen.kt — ListScreen
Objetivo: Lista de 30 elementos con control de selección alternando entre Switch y Checkbox.
- Datos: Lista recordada (remember) de pares "Item N" to mutableStateOf(false).
- Renderizado: LazyColumn con items(size). Para cada índice:
  - Determina si es par/impar para alternar el control: índices impares usan Checkbox, pares usan Switch.
  - Muestra el texto con indicador ✅/❌ según el estado.
- Interacción: Cambiar el control actualiza el estado correspondiente del elemento.
- Layout: Row por ítem con padding(16.dp) y Arrangement.SpaceBetween.

#### ButtonScreen.kt — ButtonScreen
Objetivo: Contador simple con botones de incrementar y decrementar.
- Estado: counter como Int con rememberSaveable.
- UI:
  - Row centrada con tres elementos: IconButton(-), Text(counter), IconButton(+).
  - Íconos: Icons.Default.Remove y Icons.Default.Add, con contentDescription desde strings.xml.
- Interacción: onClick decrementa o incrementa el contador.
- Estilo: Texto en headlineMedium y padding horizontal de 24.dp alrededor del valor.

---

### Recursos y cadenas

Ruta: app/src/main/res/values/strings.xml
- Define etiquetas para navegación y accesibilidad: calculator, color, image, list, button.
- Otros: number (con formato), description para la imagen, increment/decrement para botones.

---

### Notas de arquitectura y navegación
- Navigation Compose con destinos tipados: Se utilizan data object @Serializable (Calculator, Color, Image, List, Button) como rutas, haciendo la navegación más segura y menos propensa a errores de cadenas.
- Destinations enum como single source of truth para la barra inferior: ícono, etiqueta y route para construir la UI y navegar.
- Estado preservado: rememberSaveable se usa en pantallas con entrada del usuario (calculadora, contador) para conservar estado en recomposiciones y cambios de configuración.

---

### Requisitos previos y dependencias destacadas
- Jetpack Compose Material3 para componentes visuales (Scaffold, NavigationBar, TextField, etc.).
- Navigation Compose para el NavHost y composables de navegación.
- Kotlinx Serialization para rutas tipadas.

---

### Cómo extender el proyecto
- Agregar una nueva pantalla:
  1) Crear el nuevo Composable en ui/screen.
  2) Añadir una nueva data object @Serializable en ui/route/Destinations.kt y un nuevo valor en el enum con icono y etiqueta.
  3) Registrar composable<NuevoDestino> en el NavHost de MainActivity.
- Añadir un nuevo ítem a la barra inferior: actualizar el enum Destinations y strings.xml con la etiqueta correspondiente.
