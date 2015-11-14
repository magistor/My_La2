@echo off

pushD %~dp0\loginserver\
start StartAuthServer

pushD %~dp0\gameserver\
start startGameServer

exit