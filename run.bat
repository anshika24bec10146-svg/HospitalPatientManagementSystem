@echo off
echo ========================================================
echo   Compiling and Running Hospital Patient Management System
echo   Author: Anshika (Reg No: 24BEC10146)
echo ========================================================

if not exist bin mkdir bin

echo Compiling Java source files...
javac -d bin -sourcepath src src/model/*.java src/service/*.java src/test/*.java src/Main.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Launching application...
    echo.
    java -cp bin Main
) else (
    echo.
    echo [ERROR] Compilation failed. Please ensure JDK (Java Development Kit) is installed and javac is in your PATH.
    echo If you are using VS Code or Eclipse, open this folder directly and run src/Main.java.
    pause
)
