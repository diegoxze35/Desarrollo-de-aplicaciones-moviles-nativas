### Practica 1: Navegación entre fragmentos

### Moreno Martínez Diego Alejandro - 7CV2

Instalación de Herramientas para Desarrollo Android 
Android Studio 
Descarga e instalación: He descargado Android Studio desde el sitio oficial (developer.android.com) y completado el proceso de instalación; en la ilustración 1 se muestra la web oficial de Android, con un botón en la parte inferior izquierda, que descarga la última versión del IDE de Android Studio. 
Despues de darcargar el instalador, la instalación es como la de cualquier otro programa, lo importante es demostrar como crear un proyecto y un emulador, lo cual se ilustra en la ilustración 2. 
<img width="975" height="547" alt="image" src="https://github.com/user-attachments/assets/7e588f03-55d4-4985-bfbe-3104a759f53e" />
Ilustración 1: Página oficial de android developers
  
Configuración del IDE: Configuré el SDK de Android, creé un dispositivo virtual (emulador) y verifiqué que funciona correctamente ejecutando una aplicación de prueba. 
 <img width="975" height="658" alt="image" src="https://github.com/user-attachments/assets/675f40c4-1071-49c7-8e14-59f93a270d9e" /> 
Ilustración 2: Menú de creación de proyecto de Android Studio. 
  
En la Ilustración 2, hay varias plantillas con las cuales se puede empezar un proyecto de Android; las más importantes son 2, las demás son variaciones de dichas plantillas. 
Plantilla 1: Empty Activity: Crea una aplicación de Android usando Jetpack Compose como sistema de vistas; por defecto, solo contiene un texto que dice “Hello Android”. 
Plantilla 2: Empty Views Activity: Crea una aplicación de Android usando objetos de la clase View como sistema de vistas (vinculados a archivos XML); por defecto, solo contiene un texto que dice “Hello Android”. 
Para este ejemplo, creamos una aplicación usando la plantilla Empty Activity (Ilustración 3), para posteriormente configurar el emulador y ejecutar la aplicación. 
 
 <img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/467bf896-bc42-4d33-8da8-792973d5e55a" />
Ilustración 3: Configuración del proyecto a crear en Android Studio. 
Después de crear el proyecto, en la interfaz de Android Studio, en la barra lateral izquierda, se encuentra el icono del Device Manager, el cual se encarga de gestionar los dispositivos emulados (Ilustración 4). 

 <img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/3e3b8e06-30a2-4680-bb28-3e1ed6de88e7" />
Ilustración 4: Pantalla principal de Android Studio 
Al dar clic en “Add New Device” se desplegará un menú emergente, en el cual daremos clic en “Create virtual device” (Ilustración 5). Posteriormente, al elegir un dispositivo, se pedirá elegir una API de Android; por defecto, puede descargarse la API con la que funciona el dispositivo en la realidad; sin embargo, puede elegirse a gusto del desarrollador. Para este ejemplo, se continuará con el API 36 (Ilustración 6). 
Hay más configuraciones adicionales, como la cantidad de almacenamiento interno del dispositivo, memoria RAM del dispositivo anfitrión que se puede utilizar, cantidad de almacenamiento externo, entre otras. 

<img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/b554d9d9-a247-4e9f-8680-cd9a97f03d43" />
Ilustración 5: Selección del dispositivo para la emulación. 

<img width="975" height="548" alt="image" src="https://github.com/user-attachments/assets/bdfd0cd4-06ba-4438-a037-5774692dc5ce" />
Ilustración 	6: 	Selección 	del 	nivel 	de 	API  
La imagen del dispositivo se descargará y finalmente tendremos el emulador disponible para ejecutar las aplicaciones que se desarrollen (Ilustración 7). 

<img width="848" height="477" alt="image" src="https://github.com/user-attachments/assets/4ef1b80c-451a-4851-aa1a-b102f3975c53" />
Ilustración 7: Ejecuión de la aplicación Hello Android. 
