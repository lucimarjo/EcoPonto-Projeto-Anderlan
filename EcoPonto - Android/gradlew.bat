@echo off
setlocal
set "GRADLE_VERSION=8.13"
set "DIST_ROOT=%USERPROFILE%\.gradle\ecoponto-distributions"
set "GRADLE_HOME=%DIST_ROOT%\gradle-%GRADLE_VERSION%"
if not exist "%GRADLE_HOME%\bin\gradle.bat" (
  echo Baixando e verificando Gradle %GRADLE_VERSION% da distribuicao oficial...
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$ErrorActionPreference='Stop'; $root='%DIST_ROOT%'; New-Item -ItemType Directory -Force -Path $root | Out-Null; $zip=Join-Path $root 'gradle-%GRADLE_VERSION%-bin.zip'; $sum=Join-Path $root 'gradle-%GRADLE_VERSION%-bin.zip.sha256'; Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip' -OutFile $zip; Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip.sha256' -OutFile $sum; $actual=(Get-FileHash $zip -Algorithm SHA256).Hash.ToLower(); $expected=(Get-Content $sum -Raw).Trim().Split(' ')[0].ToLower(); if ($actual -ne $expected) { throw 'Falha na verificacao SHA-256 do Gradle' }; Expand-Archive -Path $zip -DestinationPath $root -Force"
  if errorlevel 1 exit /b 1
)
call "%GRADLE_HOME%\bin\gradle.bat" %*
exit /b %ERRORLEVEL%
