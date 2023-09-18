start wt --title "COMPILER" -d .\ powershell -Command "mvn package -DskipTests"

ping 127.0.0.1 -n 15 > nul

start wt --title "FRONTEND" -d .\src\front powershell -noexit -Command "flutter run" ^
; split-pane -H --title "BACKEND" -d .\target powershell -noexit -Command "java '-Dspring.data.mongodb.uri=%KCAL_MONGO_REMOTE%' -jar .\tool-0.0.1-SNAPSHOT.jar"