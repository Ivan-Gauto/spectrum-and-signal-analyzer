@echo off
setlocal

REM Cambiar la consola a UTF-8 para soportar ñ y caracteres especiales
chcp 65001 > nul

REM Ruta al JDK (ajustá si está en otro lado)
set "JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.5.11-hotspot"
set "PATH=%JAVA_HOME%\bin;%PATH%"

REM Ruta al SDK de JavaFX
set "JAVA_FX_LIB=javafx-sdk-21.0.7\lib"

REM Ejecutar
java --module-path "%JAVA_FX_LIB%" --add-modules javafx.controls,javafx.fxml -jar SignalAnalyzer.jar

pause
