@echo off
echo [1/2] Building your Docker image...
docker build -t game-system-app .

echo.
echo [2/2] Starting the container...
docker run -d --name running-game-system -p 8080:8080 game-system-app

echo.
echo Setup complete! You can now manage this app inside Docker Desktop.
pause