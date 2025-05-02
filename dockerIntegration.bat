@echo off
setlocal

echo 🔧 Running Maven build...
call mvn clean install

echo 🐳 Building Docker image...
docker build -t my-grpc-app:02_May_1.0 .

echo 🧹 Removing old container (if it exists)...
docker rm -f grpc-app 2>nul

echo 🚀 Starting new container...
docker run --name grpc-app ^
  --network my-network ^
  -p 9090:9090 ^
  -e POSTGRES_URI=jdbc:postgresql://my-postgres:5432/grpc ^
  my-grpc-app:02_May_1.0

endlocal
pause
