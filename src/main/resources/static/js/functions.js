var currentPage = 0; // Variable para almacenar la página actual
var pageSize = 20; // Tamaño de la página

function loadNextPage() {    
  currentPage++;
  fetch('/admin/listUser?page=' + currentPage + '&pageSize=' + pageSize)
      .then(function(response) {
      if (response.ok) {
          return response.text();
      } else {
          throw new Error('Error al cargar la siguiente página');
      }
      })
      .then(function(data) {
          if(checkIfTableIsEmpty(data) === true){
              var alertContainer = document.getElementById('alertContainer');
              var alertDiv = document.createElement('div');
              alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-info');
              alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                                  'There is no more users!';
              alertContainer.appendChild(alertDiv);
              currentPage--;
          }else{
              var tableContent = extractTableContent(data);
              document.getElementById("users").innerHTML = tableContent;
          }
      })
      .catch(function(error) {
      console.error(error);
      });
}

function loadPreviousPage() {    
  
  if(currentPage <= 0){
      var alertContainer = document.getElementById('alertContainer');
      var alertDiv = document.createElement('div');
      alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-info');
      alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                          'There is no previous users!';
      alertContainer.appendChild(alertDiv);
  }else{
      currentPage--;
      fetch('/admin/listUser?page=' + currentPage + '&pageSize=' + pageSize)
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
          })
          .catch(function(error) {
          console.error(error);
          });
  }
}


function extractTableContent(data) {
// Crear un nuevo elemento HTML para analizar el código
  var parser = new DOMParser();
  var htmlDoc = parser.parseFromString(data, 'text/html');

  // Seleccionar solo el contenido de la tabla
  var tableContent = htmlDoc.querySelector('table').outerHTML;

  return tableContent;
}

function checkIfTableIsEmpty(data) {
// Crear un nuevo elemento HTML para analizar el código
  var parser = new DOMParser();
  var htmlDoc = parser.parseFromString(data, 'text/html');

  // Seleccionar solo el contenido de la tabla
  var tableContent = htmlDoc.querySelector('tbody').innerHTML;
  if(/^\s*$/.test(tableContent)){
      return true;
  }else{
      return false;
  }
}

function deleteRow(id){
  let row = document.getElementById('row-' + id);
  let spinner = row.querySelector('.spinner-border');
  spinner.style['display']='inherit' ;
  setTimeout(function() {
      row.remove();;
    }, 1000);   
}

function showDashboard(){
  url = '/views/dashboard';
  ajaxF(url);
}