$services = @(
    "discovery-service",
    "gateway-service",
    "identity-service",
    "equipement-service"
)

# Base directory where the script is located
$baseDir = $PSScriptRoot

foreach ($service in $services) {
    $servicePath = Join-Path -Path $baseDir -ChildPath $service

    if (Test-Path $servicePath) {
        Write-Host "Starting $service..." -ForegroundColor Green

        # Start the application in a new hidden process or a new window
        if ($service -eq "equipement-service") {
            Start-Process -FilePath "powershell" -ArgumentList "-NoExit", "-Command", "cd '$servicePath'; .\mvnw spring-boot:run -DskipTests | Tee-Object -FilePath 'equipement.log'" -WindowStyle Normal
        } else {
            Start-Process -FilePath "powershell" -ArgumentList "-NoExit", "-Command", "cd '$servicePath'; .\mvnw spring-boot:run -DskipTests" -WindowStyle Normal
        }

        if ($service -eq "discovery-service") {
            Write-Host "Waiting 15 seconds for discovery-service to initialize..." -ForegroundColor Cyan
            Start-Sleep -Seconds 15
        }
    } else {
        Write-Host "Directory $servicePath not found. Skipping $service." -ForegroundColor Yellow
    }
}
