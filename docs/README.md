## Estructura de las carpetas y su uso

---

- **build/**: Archivos generados durante el proceso de compilación, como archivos .class y archivos empaquetados .jar.
- **docs/**: Documentación del proyecto, como manuales de usuario y documentos técnicos.
- **lib/**: Librerías externas si no estás usando un sistema de gestión de dependencias como Maven o Gradle.
- **logs/**: Archivos de log generados por la aplicación.
- **src/main/java/**: Código fuente principal del proyecto.
- **com/yourcompany/yourproject/**: Paquete base para tu proyecto.
- **Main.java**: Clase principal que extiende Application y sobreescribe el método start.
- **controller/**: Paquete para los controladores que manejan la lógica de la interfaz de usuario.
- **dao/**: Clases de acceso a datos (Data Access Object).
- **model/**: Clases que representan los datos del negocio.
- **service/**: Clases que contienen la lógica del negocio.
- **src/main/resources/**: Recursos del proyecto, como archivos FXML, imágenes, archivos de configuración, etc.
- **src/test/**: Código fuente de pruebas.
- **java/**: Clases de prueba.
- **resources/**: Recursos necesarios para las pruebas.
- **pom.xml**: Archivo de configuración de Maven, respectivamente.