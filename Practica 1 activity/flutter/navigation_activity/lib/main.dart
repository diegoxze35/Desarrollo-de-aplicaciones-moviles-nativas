import 'dart:math' as math;
import 'package:flutter/material.dart';

void main() {
  runApp(const NavigationApp());
}

class NavigationApp extends StatelessWidget {
  const NavigationApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigation Activity',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: const NavigationScreen(),
    );
  }
}

class NavigationScreen extends StatefulWidget {
  const NavigationScreen({super.key});

  @override
  State<NavigationScreen> createState() => _NavigationScreenState();
}

class _NavigationScreenState extends State<NavigationScreen> {
  int _currentIndex = 0;

  final List<Widget> _screens = const [
    HomeScreen(),
    TextFieldsScreen(),
    ButtonsScreen(),
    SelectionScreen(),
    ListsScreen(),
    InformationScreen(),
  ];

  final List<String> _screenTitles = [
    'UI Elements Demo',
    'TextFields',
    'Buttons',
    'Selection Controls',
    'Lists',
    'Information',
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(_screenTitles[_currentIndex]),
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
        actions: [
          // Botón para navegar a la nueva actividad
          TextButton(
            onPressed: () {
              Navigator.of(context).push(
                MaterialPageRoute(builder: (context) => const AnotherScreen()),
              );
            },
            child: const Text(
              'Activity',
              style: TextStyle(color: Colors.white),
            ),
          ),
        ],
      ),
      body: _screens[_currentIndex],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (int index) {
          setState(() {
            _currentIndex = index;
          });
        },
        destinations: const [
          NavigationDestination(icon: Icon(Icons.home), label: 'Home'),
          NavigationDestination(icon: Icon(Icons.edit), label: 'TextFields'),
          NavigationDestination(icon: Icon(Icons.touch_app), label: 'Buttons'),
          NavigationDestination(
            icon: Icon(Icons.check_circle),
            label: 'Selection',
          ),
          NavigationDestination(icon: Icon(Icons.list), label: 'Lists'),
          NavigationDestination(icon: Icon(Icons.info), label: 'Info'),
        ],
      ),
    );
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'Welcome to UI Elements Demo',
            style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: 16),
          Card(
            color: Theme.of(context).colorScheme.primaryContainer,
            child: const Padding(
              padding: EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'This app demonstrates various UI elements and layouts in Jetpack Compose, now converted to Flutter.',
                    style: TextStyle(fontSize: 16),
                  ),
                  SizedBox(height: 8),
                  Text(
                    'Main Features:',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  Text('- Navigation with Bottom Navigation Bar'),
                  Text('- Different types of Buttons'),
                  Text('- Selection controls (Checkbox, Radio, Switch)'),
                  Text('- Lists and Grids'),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            color: Theme.of(context).colorScheme.secondaryContainer,
            child: const Padding(
              padding: EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'In this app you can find:',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  Text('- TextFields: Different types of text input fields.'),
                  Text('- Buttons: Various button styles and an FAB.'),
                  Text('- Selection: Checkbox, Radio buttons, and Switch.'),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            color: Theme.of(context).colorScheme.tertiaryContainer,
            child: const Padding(
              padding: EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    'Navigation',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  Text(
                    'Use the bottom navigation bar to switch between sections.',
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class TextFieldsScreen extends StatefulWidget {
  const TextFieldsScreen({super.key});

  @override
  State<TextFieldsScreen> createState() => _TextFieldsScreenState();
}

class _TextFieldsScreenState extends State<TextFieldsScreen> {
  final TextEditingController _basicController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _numberController = TextEditingController();
  final TextEditingController _multilineController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'This screen demonstrates different types of text input fields.',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 16),
          TextField(
            controller: _basicController,
            decoration: const InputDecoration(
              labelText: 'Basic TextField',
              hintText: 'Enter your text here',
              border: OutlineInputBorder(),
            ),
            onChanged: (value) => setState(() {}),
          ),
          const SizedBox(height: 16),
          TextField(
            controller: _passwordController,
            decoration: const InputDecoration(
              labelText: 'Password',
              hintText: 'Enter your password',
              border: OutlineInputBorder(),
            ),
            obscureText: true,
            onChanged: (value) => setState(() {}),
          ),
          const SizedBox(height: 16),
          TextField(
            controller: _numberController,
            decoration: const InputDecoration(
              labelText: 'Number',
              hintText: 'Enter a number',
              border: OutlineInputBorder(),
            ),
            keyboardType: TextInputType.number,
            onChanged: (value) => setState(() {}),
          ),
          const SizedBox(height: 16),
          TextField(
            controller: _multilineController,
            decoration: const InputDecoration(
              labelText: 'Multiline Text',
              hintText: 'Enter multiple lines of text',
              border: OutlineInputBorder(),
              alignLabelWithHint: true,
            ),
            maxLines: 3,
            onChanged: (value) => setState(() {}),
          ),
          const SizedBox(height: 16),
          Card(
            color: Theme.of(context).colorScheme.surfaceContainerHighest,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Current Values:',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  Text('Basic: ${_basicController.text}'),
                  Text('Password: ${'*' * _passwordController.text.length}'),
                  Text('Number: ${_numberController.text}'),
                  Text(
                    'Multiline: ${_multilineController.text.length > 50 ? '${_multilineController.text.substring(0, 50)}...' : _multilineController.text}',
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _basicController.dispose();
    _passwordController.dispose();
    _numberController.dispose();
    _multilineController.dispose();
    super.dispose();
  }
}

class ButtonsScreen extends StatefulWidget {
  const ButtonsScreen({super.key});

  @override
  State<ButtonsScreen> createState() => _ButtonsScreenState();
}

class _ButtonsScreenState extends State<ButtonsScreen> {
  int _clickCount = 0;
  String _lastPressed = 'None';

  void _handleButtonPress(String buttonName) {
    setState(() {
      _clickCount++;
      _lastPressed = buttonName;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'This screen demonstrates different types of buttons.',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 16),
          ElevatedButton(
            onPressed: () => _handleButtonPress('Filled Button'),
            child: const Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(Icons.star),
                SizedBox(width: 8),
                Text('Filled Button'),
              ],
            ),
          ),
          const SizedBox(height: 16),
          OutlinedButton(
            onPressed: () => _handleButtonPress('Outlined Button'),
            child: const Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(Icons.favorite),
                SizedBox(width: 8),
                Text('Outlined Button'),
              ],
            ),
          ),
          const SizedBox(height: 16),
          TextButton(
            onPressed: () => _handleButtonPress('Text Button'),
            child: const Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(Icons.share),
                SizedBox(width: 8),
                Text('Text Button'),
              ],
            ),
          ),
          const SizedBox(height: 16),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              IconButton(
                onPressed: () => _handleButtonPress('Icon Button 1'),
                icon: const Icon(Icons.home),
                tooltip: 'Home',
              ),
              IconButton(
                onPressed: () => _handleButtonPress('Icon Button 2'),
                icon: const Icon(Icons.settings),
                tooltip: 'Settings',
              ),
              IconButton(
                onPressed: () => _handleButtonPress('Icon Button 3'),
                icon: const Icon(Icons.search),
                tooltip: 'Search',
              ),
            ],
          ),
          const SizedBox(height: 16),
          Center(
            child: FloatingActionButton(
              onPressed: () => _handleButtonPress('FAB'),
              child: const Icon(Icons.add),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            color: Theme.of(context).colorScheme.surfaceContainerHighest,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Current State:',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  Text('Total Clicks: $_clickCount'),
                  Text('Last Pressed: $_lastPressed'),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class SelectionScreen extends StatefulWidget {
  const SelectionScreen({super.key});

  @override
  State<SelectionScreen> createState() => _SelectionScreenState();
}

class _SelectionScreenState extends State<SelectionScreen> {
  bool _checkboxValue = false;
  String _radioValue = 'Option 1';
  bool _switchValue = false;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'This screen demonstrates selection controls.',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Checkbox',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text(
                    'A checkbox allows the user to select one or more options.',
                  ),
                  const SizedBox(height: 8),
                  Row(
                    children: [
                      Checkbox(
                        value: _checkboxValue,
                        onChanged: (value) {
                          setState(() {
                            _checkboxValue = value!;
                          });
                        },
                      ),
                      const Text('Enable option'),
                    ],
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'RadioButton',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text(
                    'Radio buttons allow the user to select one option from a set.',
                  ),
                  const SizedBox(height: 8),
                  Column(
                    children: [
                      _buildRadioOption('Option 1'),
                      _buildRadioOption('Option 2'),
                      _buildRadioOption('Option 3'),
                    ],
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Switch',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text(
                    'A switch is a control that toggles between two states, on and off.',
                  ),
                  const SizedBox(height: 8),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      const Text('Enable notifications'),
                      Switch(
                        value: _switchValue,
                        onChanged: (value) {
                          setState(() {
                            _switchValue = value;
                          });
                        },
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            color: Theme.of(context).colorScheme.surfaceContainerHighest,
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Current State:',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  Text('Checkbox: ${_checkboxValue ? 'Checked' : 'Unchecked'}'),
                  Text('RadioButton: $_radioValue'),
                  Text('Switch: ${_switchValue ? 'Activated' : 'Deactivated'}'),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildRadioOption(String value) {
    return Row(
      children: [
        Radio<String>(
          value: value,
          groupValue: _radioValue,
          onChanged: (String? newValue) {
            setState(() {
              _radioValue = newValue!;
            });
          },
        ),
        Text(value),
      ],
    );
  }
}

class ListsScreen extends StatelessWidget {
  const ListsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final items = List.generate(50, (index) => 'Element ${index + 1}');
    final gridItems = List.generate(20, (index) => 'Item ${index + 1}');

    return Padding(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'This screen demonstrates lists and grids.',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 16),
          Expanded(
            child: Card(
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'ListView',
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const Text(
                      'A vertically scrolling list that only renders the visible items.',
                    ),
                    const SizedBox(height: 8),
                    Expanded(
                      child: ListView.builder(
                        itemCount: items.length,
                        itemBuilder: (context, index) {
                          return Card(
                            color: Theme.of(
                              context,
                            ).colorScheme.primaryContainer,
                            child: ListTile(
                              leading: Icon(
                                Icons.star,
                                color: Theme.of(context).colorScheme.primary,
                              ),
                              title: Text(items[index]),
                            ),
                          );
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          const SizedBox(height: 16),
          Expanded(
            child: Card(
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text(
                      'GridView',
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const Text(
                      'A vertically scrolling grid that only renders the visible items.',
                    ),
                    const SizedBox(height: 8),
                    Expanded(
                      child: GridView.builder(
                        gridDelegate:
                            const SliverGridDelegateWithFixedCrossAxisCount(
                              crossAxisCount: 2,
                              crossAxisSpacing: 8,
                              mainAxisSpacing: 8,
                              childAspectRatio: 1.0,
                            ),
                        itemCount: gridItems.length,
                        itemBuilder: (context, index) {
                          return Card(
                            color: Theme.of(
                              context,
                            ).colorScheme.secondaryContainer,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Icon(
                                  Icons.favorite,
                                  color: Theme.of(
                                    context,
                                  ).colorScheme.secondary,
                                  size: 24,
                                ),
                                const SizedBox(height: 8),
                                Text(
                                  gridItems[index],
                                  textAlign: TextAlign.center,
                                  style: const TextStyle(fontSize: 14),
                                ),
                              ],
                            ),
                          );
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class InformationScreen extends StatefulWidget {
  const InformationScreen({super.key});

  @override
  State<InformationScreen> createState() => _InformationScreenState();
}

class _InformationScreenState extends State<InformationScreen>
    with SingleTickerProviderStateMixin {
  late AnimationController _controller;
  late Animation<double> _animation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      duration: const Duration(seconds: 2),
      vsync: this,
    )..repeat(reverse: true);

    _animation = Tween<double>(begin: 0.0, end: 1.0).animate(_controller);
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text(
            'This screen demonstrates information displays.',
            style: TextStyle(fontSize: 16),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Text',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text('Different styles of text.'),
                  const SizedBox(height: 8),
                  const Text('Normal text'),
                  const Text(
                    'Bold text',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                  const Text('Large text', style: TextStyle(fontSize: 24)),
                  Text(
                    'Colored text',
                    style: TextStyle(
                      color: Theme.of(context).colorScheme.primary,
                    ),
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Images',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text('Icons and images.'),
                  const SizedBox(height: 8),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Card(
                        color: Theme.of(context).colorScheme.primaryContainer,
                        child: Padding(
                          padding: const EdgeInsets.all(16),
                          child: Icon(
                            Icons.star,
                            size: 60,
                            color: Theme.of(context).colorScheme.primary,
                          ),
                        ),
                      ),
                      Card(
                        color: Theme.of(context).colorScheme.secondaryContainer,
                        child: Padding(
                          padding: const EdgeInsets.all(16),
                          child: Icon(
                            Icons.favorite,
                            size: 60,
                            color: Theme.of(context).colorScheme.secondary,
                          ),
                        ),
                      ),
                      Card(
                        color: Theme.of(context).colorScheme.tertiaryContainer,
                        child: Padding(
                          padding: const EdgeInsets.all(16),
                          child: Icon(
                            Icons.settings,
                            size: 60,
                            color: Theme.of(context).colorScheme.tertiary,
                          ),
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text(
                    'Progress Indicators',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text('Linear and circular progress indicators.'),
                  const SizedBox(height: 16),
                  const Text('Linear Progress Indicator'),
                  const SizedBox(height: 8),
                  AnimatedBuilder(
                    animation: _animation,
                    builder: (context, child) {
                      return LinearProgressIndicator(value: _animation.value);
                    },
                  ),
                  const SizedBox(height: 8),
                  AnimatedBuilder(
                    animation: _animation,
                    builder: (context, child) {
                      return Text('${(_animation.value * 100).toInt()}%');
                    },
                  ),
                  const SizedBox(height: 16),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Column(
                        children: [
                          const Text('Circular Progress'),
                          const SizedBox(height: 8),
                          AnimatedBuilder(
                            animation: _animation,
                            builder: (context, child) {
                              return CircularProgressIndicator(
                                value: _animation.value,
                              );
                            },
                          ),
                        ],
                      ),
                      Column(
                        children: [
                          const Text('Indeterminate'),
                          const SizedBox(height: 8),
                          const CircularProgressIndicator(),
                        ],
                      ),
                    ],
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

class AnotherScreen extends StatefulWidget {
  const AnotherScreen({super.key});

  @override
  State<AnotherScreen> createState() => _AnotherScreenState();
}

class _AnotherScreenState extends State<AnotherScreen> {
  int _currentIndex = 0;

  final List<Widget> _screens = const [AnimationsScreen(), DialogsScreen()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Another Activity')),
      body: _screens[_currentIndex],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (int index) {
          setState(() {
            _currentIndex = index;
          });
        },
        destinations: const [
          NavigationDestination(
            icon: Icon(Icons.animation),
            label: 'Animations',
          ),
          NavigationDestination(icon: Icon(Icons.square), label: 'Dialogs'),
        ],
      ),
    );
  }
}

class AnimationsScreen extends StatefulWidget {
  const AnimationsScreen({super.key});

  @override
  State<AnimationsScreen> createState() => _AnimationsScreenState();
}

// Usamos TickerProviderStateMixin para el AnimationController
class _AnimationsScreenState extends State<AnimationsScreen>
    with TickerProviderStateMixin {
  // --- Estado y Controladores ---

  // Para Row 1: Animación del corazón
  late AnimationController _heartController;
  late Animation<double> _heartSizeAnimation;

  // Para Row 2: Visibilidad
  bool _isVisible = false;

  // Para Row 3: Rotación
  bool _isRotated = false;

  @override
  void initState() {
    super.initState();

    // Configuración del controlador del corazón (Row 1)
    _heartController = AnimationController(
      duration: const Duration(milliseconds: 1500),
      vsync: this,
    );

    // Definimos la animación (Tween) de 0.0 a 100.0
    _heartSizeAnimation = Tween<double>(
      begin: 0.0,
      end: 100.0,
    ).animate(_heartController);

    // Iniciamos la animación infinita con reversa
    _heartController.repeat(reverse: true);
  }

  @override
  void dispose() {
    // Siempre desechar los controladores
    _heartController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // --- Row 1: Animación de Tamaño (Corazón) ---
        Expanded(
          flex: 1,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              const Text("Size Animation"),
              // AnimatedBuilder se reconstruye cada vez que el controlador notifica un cambio
              AnimatedBuilder(
                animation: _heartController,
                builder: (context, child) {
                  return Icon(
                    Icons.favorite,
                    // El tamaño se toma del valor actual de la animación
                    size: _heartSizeAnimation.value,
                    color: Colors.red,
                  );
                },
              ),
            ],
          ),
        ),

        // --- Row 2: Animación de Visibilidad (Círculo) ---
        Expanded(
          flex: 1,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () {
                  setState(() {
                    _isVisible = !_isVisible;
                  });
                },
                child: Text(_isVisible ? "Hide" : "Show"),
              ),
              // AnimatedOpacity anima el cambio de opacidad automáticamente
              AnimatedOpacity(
                opacity: _isVisible ? 1.0 : 0.0,
                duration: const Duration(milliseconds: 300),
                // Duración estándar de fade
                child: Container(
                  width: 100.0,
                  height: 100.0,
                  decoration: const BoxDecoration(
                    color: Colors.red,
                    shape: BoxShape.circle,
                  ),
                ),
              ),
            ],
          ),
        ),

        // --- Row 3: Animación de Rotación (Ventilador) ---
        Expanded(
          flex: 1,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              // TweenAnimationBuilder anima un valor cuando el 'tween.end' cambia
              TweenAnimationBuilder<double>(
                // El valor final depende del estado _isRotated
                // 2 * math.pi es 360 grados en radianes
                tween: Tween<double>(
                  begin: 0.0,
                  end: _isRotated ? (2 * math.pi) : 0.0,
                ),
                duration: const Duration(milliseconds: 2500),
                builder: (context, angle, child) {
                  // Aplicamos la rotación usando el 'angle' animado
                  return Transform.rotate(angle: angle, child: child);
                },
                // El 'child' es el widget que no se reconstruye (el ícono)
                child: const Icon(
                  Icons.sync, // Placeholder para R.drawable.toys_fan_24px
                  size: 150.0,
                  color: Colors.blueGrey,
                ),
              ),
              // Equivalente al Modifier.padding(top: 50.dp).width(200.dp)
              Padding(
                padding: const EdgeInsets.only(top: 50.0),
                child: SizedBox(
                  width: 200.0,
                  child: ElevatedButton(
                    onPressed: () {
                      setState(() {
                        _isRotated = !_isRotated;
                      });
                    },
                    child: const Text("Rotate Fan"),
                  ),
                ),
              ),
            ],
          ),
        ),
      ],
    );
  }
}

class DialogsScreen extends StatelessWidget {
  const DialogsScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: ElevatedButton(
        onPressed: () {
          showDialog(
            context: context,
            builder: (BuildContext context) {
              return AlertDialog(
                title: const Text("Dialog Title"),
                content: const Text("Dialog Content"),
                actions: [
                  TextButton(
                    onPressed: () {
                      Navigator.of(context).pop();
                    },
                    child: const Text("OK"),
                  ),
                ],
              );
            },
          );
        },
        child: const Text("Show Dialog"),
      ),
    );
  }
}
