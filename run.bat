@echo off
start wt --title "SERVERS" ^
    -d .\ powershell "mvn package -DskipTests" ^
    ; split-pane -d .\target powershell "java '-Dspring.data.mongodb.uri=%KCAL_MONGO_REMOTE%' -jar .\tool-0.0.1-SNAPSHOT.jar"
