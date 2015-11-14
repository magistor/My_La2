@echo off
title Restart Server

taskkill /f /im java.exe
 
pushD %~dp0\LoginServer\
start StartAuthServer

pushD %~dp0\GameServer\
start startGameServer

exit