@echo off
setlocal enabledelayedexpansion
title Hospital Patient Management System - Anshika Jain (24BEC10146)

echo ===================================================================
echo     HOSPITAL PATIENT MANAGEMENT SYSTEM (HPMS)
echo     Course: Programming in Java ^| Flipped Course Project
echo     Student: Anshika Jain  ^|  Registration No: 24BEC10146 (SEEE)
echo     Institution: Vellore Institute of Technology, Bhopal
echo ===================================================================
echo.

set JAVAC_CMD=javac
set JAVA_CMD=java

rem Check if javac is available on system PATH
where javac >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    set "VSCODE_JDK=C:\Users\DELL\.vscode\extensions\redhat.java-1.56.0-win32-x64\jre\21.0.12.1-win32-x86_64\bin"
    if exist "!VSCODE_JDK!\javac.exe" (
        set "JAVAC_CMD=!VSCODE_JDK!\javac.exe"
        set "JAVA_CMD=!VSCODE_JDK!\java.exe"
    )
)

if not exist bin mkdir bin

echo [1/2] Compiling Java classes...
"%JAVAC_CMD%" -d bin -sourcepath src src/model/*.java src/service/*.java src/test/*.java src/Main.java

if %ERRORLEVEL% EQU 0 (
    echo [2/2] Compilation successful!
    echo.
    echo Launching Hospital Patient Management System...
    echo -------------------------------------------------------------------
    "%JAVA_CMD%" -cp bin Main
) else (
    echo.
    echo [ERROR] Compilation failed.
    echo Please make sure JDK is installed or open this project in VS Code / Eclipse / IntelliJ.
    pause
)
