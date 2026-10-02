@echo off
echo ========================================================
echo   Running Automated Validation Tests (HPMS)
echo   Author: Anshika (Reg No: 24BEC10146)
echo ========================================================

if not exist bin mkdir bin

echo Compiling test suite...
javac -d bin -sourcepath src src/model/*.java src/service/*.java src/test/*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilation successful! Executing tests...
    echo.
    java -cp bin test.ValidationTest
) else (
    echo [ERROR] Test compilation failed. Ensure javac is in your PATH.
)
pause
