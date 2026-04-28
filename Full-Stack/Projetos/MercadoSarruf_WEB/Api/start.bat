@echo off
title Servidor MercadoSarruf
color 0A
echo ========================================
echo    Servidor MercadoSarruf
echo ========================================
echo.

set URL=http://localhost:3000

echo Iniciando servidor em %URL%
echo.

rem Copia a URL para a área de transferência
echo %URL% | clip

echo A URL do servidor foi copiada para a area de transferrncia.
echo.

echo Pressione CTRL+C para parar o servidor
echo.
node server.js
pause