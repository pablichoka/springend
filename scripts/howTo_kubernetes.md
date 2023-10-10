Kubernetes se basa en la definición de objetos que expresan el estado deseado del sistema. Algunos de los más utilizados son Deployment y Service. Un Deployment asegura que un número específico de réplicas de tu aplicación estén funcionando; un Service expone las aplicaciones a tráfico de red.

Dicho esto, vamos a definir tres objetos Deployment (uno para cada, Spring, Flutter y Redis) y tres objetos Service para exponer cada servicio.

# Deployment y Service para la aplicación Spring
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: spring-app
  template:
    metadata:
      labels:
        app: spring-app
    spec:
      containers:
      - name: spring-app
        image: mi-backend
        ports:
        - containerPort: 8080

---
apiVersion: v1
kind: Service
metadata:
  name: spring-service
spec:
  selector:
    app: spring-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
---

# Deployment y Service para la aplicación Flutter
apiVersion: apps/v1
kind: Deployment
metadata:
  name: flutter-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: flutter-app
  template:
    metadata:
      labels:
        app: flutter-app
    spec:
      containers:
      - name: flutter-app
        image: mi-frontend
        ports:
        - containerPort: 80

---
apiVersion: v1
kind: Service
metadata:
  name: flutter-service
spec:
  selector:
    app: flutter-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
---

# Deployment y Service para Redis
apiVersion: apps/v1
kind: Deployment
metadata:
  name: redis
spec:
  replicas: 1
  selector:
    matchLabels:
      app: redis
  template:
    metadata:
      labels:
        app: redis
    spec:
      containers:
      - name: redis
        image: redis
        ports:
        - containerPort: 6379

---
apiVersion: v1
kind: Service
metadata:
  name: redis-service
spec:
  selector:
    app: redis
  ports:
    - protocol: TCP
      port: 6379
      targetPort: 6379


Con estos archivos puedes dirigirte a la línea de comandos y ejecutar kubectl apply -f archivo.yaml para crear los objetos en la configuración.

¿Podrías usar esto para hacer un despliegue en Rancher? Absolutamente, Rancher es un administrador de clústeres de Kubernetes. Una vez que las imágenes estén en un registro accesible a Rancher, puedes usar estos archivos de configuración para implementar las aplicaciones en un clúster administrado por Rancher.