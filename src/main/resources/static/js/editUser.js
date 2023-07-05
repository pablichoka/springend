// Agrega un evento de clic al botón "Guardar"
document.getElementById('sendButton').addEventListener('click', function() {
    // Realiza una petición AJAX para guardar los cambios del usuario
    // Puedes utilizar fetch o XMLHttpRequest
  
    // Ejemplo con fetch:
    fetch('/userActions/editUser/{id}', {
      method: 'POST',
      body: JSON.stringify({ /* Datos del usuario editado */ }),
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(function(response) {
      loadListUserPage();
    })
    .catch(function(error) {
      // Maneja los errores de la petición
    });
  });
  
  // Agrega un evento de clic al enlace o al botón "Volver a la lista"
document.getElementById('backToList').addEventListener('click', function(event) {
    event.preventDefault();
  
    // Carga la página "listUser" nuevamente en el contenedor principal
    loadListUserPage();
  });
  
  // Función para cargar la página "listUser" en el contenedor principal
  function loadListUserPage() {
    // Realiza una petición AJAX para cargar la página "listUser" en el contenedor principal
    // Puedes utilizar fetch o XMLHttpRequest
  
    // Ejemplo con fetch:
    fetch('/userActions/listUser', {
      method: 'GET'
    })
    .then(function(response) {
      // Procesa la respuesta del servidor
  
      // Actualiza el contenido del contenedor principal con el resultado de la petición AJAX
      document.getElementById('container').innerHTML = response;
    })
    .catch(function(error) {
      // Maneja los errores de la petición
    });
  }
  