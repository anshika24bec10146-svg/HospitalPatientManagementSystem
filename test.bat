@echo off
setlocal enabledelayedexpansion
title Automated Validation Tests - HPMS (Anshika 24BEC10146)

echo ===================================================================
echo     RUNNING AUTOMATED VALIDATION SUITE (HPMS)
echo     Student: Anshika  ^|  Registration No: 24BEC10146
echo ===================================================================
echo.

set JAVAC_CMD=javac
set JAVA_CMD=java

where javac >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    set "VSCODE_JDK=C:\Users\DELL\.vscode\extensions\redhat.java-1.56.0-win32-x64\jre\21.0.12.1-win32-x86_64\bin"
    if exist "!VSCODE_JDK!\javac.exe" (
        set "JAVAC_CMD=!VSCODE_JDK!\javac.exe"
        set "JAVA_CMD=!VSCODE_JDK!\java.exe"
    )
)

if not exist bin mkdir bin

echo Compiling test files...
"%JAVAC_CMD%" -d bin -sourcepath src src/model/*.java src/service/*.java src/test/*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Executing tests...
    echo.
    "%JAVA_CMD%" -cp bin test.ValidationTest
) else (
    echo [ERROR] Test compilation failed.
)
echo.
pause
