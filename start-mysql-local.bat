@echo off
echo Starting MySQL with local data directory...
echo.

REM Create local data directory
if not exist "mysql-data" mkdir mysql-data

REM Start MySQL with local data directory
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqld.exe" --datadir=mysql-data --console

pause
