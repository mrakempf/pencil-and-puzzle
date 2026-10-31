@echo off
rem Opens Pencil and Puzzle in its own app window in Microsoft Edge, or the default browser if Edge is missing.
start "" msedge --app="%~dp0index.html" 2>nul || start "" "%~dp0index.html"
