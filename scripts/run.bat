start wt --title "COMPILER" -d ..\ powershell -Command "mvn package -DskipTests"
powershell -ExecutionPolicy Bypass -File "jwtTokenGen.ps1"

pause

start wt --title "FRONTEND" -d ..\..\kCalControl_flutter powershell -noexit -Command "flutter run -d chrome --verbose" ^
; split-pane -H --title "BACKEND" -d ..\target powershell -noexit -Command "java '-Djwt.base64secret=%JWT_TOKEN%' '-Dspring.data.mongodb.uri=%KCAL_MONGO_REMOTE%' '-Dserver.ssl.key-store-password=%SSL_KEY%' -jar .\tool-0.0.1-SNAPSHOT.jar"
