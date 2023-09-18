@echo off
start wt --title "COMPILER" ^
    -d .\ powershell "mvn package -DskipTests" ^
    ; new-tab --title "FRONTEND" ^
    -d .\src\front powershell "flutter run" ^
    ; split-pane --title "BACKEND" ^
    -d .\target powershell "java '-Dspring.data.mongodb.uri=%KCAL_MONGO_REMOTE%' -jar .\tool-0.0.1-SNAPSHOT.jar"
