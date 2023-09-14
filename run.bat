@echo off
start wt --title "SERVERS" -d .\ powershell "mvn package -DskipTests"; 
cmd --title "BACKEND" timeout /t 5 /k "cd %CD%\target && java -Dspring.data.mongodb.uri=%KCAL_MONGO_REMOTE% --enable-preview -jar tool-0.0.1-SNAPSHOT.jar"
