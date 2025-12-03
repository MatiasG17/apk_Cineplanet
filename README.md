# apk_Cineplanet 📽️

## 🔎 ¿Qué es?

apk_Cineplanet es una app Android desarrollada en Java que apunta a gestionar o mostrar información relacionada con una cartelera de cine (tal como lo haría una app de boletos/cartelera). El repositorio contiene todo lo necesario para compilar la app: código fuente, configuración de Gradle y estructura típica de proyecto Android.

## 📁 Estructura del proyecto

- `app/` — código fuente de la aplicación.  
- `gradle/`, `build.gradle.kts`, `settings.gradle.kts`, `gradlew`, etc. — configuración del sistema de construcción (build) con Gradle.  
- `.gitignore` — archivos/ directorios ignorados por Git.  
- Otros archivos de configuración del entorno.

## ✅ Prerrequisitos

Para compilar y probar la app localmente necesitas:

- Java (compatible con la versión usada por el proyecto).  
- Android SDK / Android Studio instalado.  
- Gradle (aunque ya está configurado con los scripts `gradlew` incluidos).  
- Un emulador Android o un dispositivo real para ejecutar la app.  

## 🚀 Cómo ejecutar la app localmente

1. Clona el repositorio:
   ```bash
   git clone https://github.com/MatiasG17/apk_Cineplanet.git
   cd apk_Cineplanet
