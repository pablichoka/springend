function reloadPage() {
    location.reload();
  }

  var currentPage = 1; // Variable para almacenar la página actual
  var pageSize = 2; // Tamaño de la página

  function loadNextPage() {    
    // Realizar la solicitud AJAX
    fetch('/userActions/listUser?page=' + currentPage + '&pageSize=' + pageSize)
      .then(function(response) {
        if (response.ok) {
          return response.text();
        } else {
          throw new Error('Error al cargar la siguiente página');
        }
      })
      .then(function(data) {
        var tableContent = extractTableContent(data);
        document.getElementById("users").innerHTML = tableContent;
        currentPage++;
      })
      .catch(function(error) {
        console.error(error);
      });
  }
  
  function extractTableContent(data) {
    // Crear un nuevo elemento HTML para analizar el código
    var parser = new DOMParser();
    var htmlDoc = parser.parseFromString(data, 'text/html');
  
    // Seleccionar solo el contenido de la tabla
    var tableContent = htmlDoc.querySelector('table').outerHTML;
  
    return tableContent;
  }