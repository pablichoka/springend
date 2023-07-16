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

function checkIncompatibilities(formName) {
    let form = document.getElementById(formName);
    let selects = form.querySelectorAll('select');
    let filter = selects[0];
    let sort = selects[1];
    let filterOptions = filter.querySelectorAll('option');
    let sortOptions = sort.querySelectorAll('option');

    if (filterOptions[1].selected) {
        sortOptions[2].disabled = true;
        sortOptions[3].disabled = true;
    } else if (sortOptions[2].selected || sortOptions[3].selected) {
        filterOptions[1].disabled = true;
    } else {
        filterOptions[1].disabled = false; // Reestablecer la opción desactivada si no se cumple ninguna condición
        sortOptions[2].disabled = false; // Reestablecer las opciones desactivadas si no se cumple ninguna condición
        sortOptions[3].disabled = false;
    }
}
