@echo off
setlocal

REM Cambiar la consola a UTF-8 para soportar ñ y caracteres especiales
chcp 65001 > nul

REM Obtener la ruta donde está el .bat
set "SCRIPT_DIR=%~dp0"

REM Ruta relativa a la carpeta lib de JavaFX (debe estar junto al .bat)
set "JAVA_FX_LIB=%SCRIPT_DIR%javafx-sdk-21.0.7\lib"

REM Ejecutar usando java que esté en el PATH del sistema
java --module-path "%JAVA_FX_LIB%" --add-modules javafx.controls,javafx.fxml -jar "%SCRIPT_DIR%SignalAnalyzer.jar"

pause

