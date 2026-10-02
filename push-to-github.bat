@echo off
title Push to GitHub - Hospital Patient Management System
echo ===================================================================
echo     Pushing Project to GitHub: anshika24bec10146-svg
echo ===================================================================
echo.
echo Running: git push -u origin main
echo (If a browser window pops up, click 'Sign in with your browser')
echo.

git push -u origin main

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ===================================================================
    echo  SUCCESS! Project successfully pushed to GitHub!
    echo  View it at: https://github.com/anshika24bec10146-svg/HospitalPatientManagementSystem
    echo ===================================================================
) else (
    echo.
    echo [NOTE] If you saw an authorization error, make sure you are signed in to GitHub.
)

echo.
pause
