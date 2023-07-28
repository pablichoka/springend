var currentPage = 0; // Variable para almacenar la página actual
var pageSize = 20; // Tamaño de la página

function loadNextPage() {    
  currentPage++;
  fetch('/auth/admin/listUser?page=' + currentPage + '&pageSize=' + pageSize)
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
              let alertDiv = document.createElement('div');
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
      let alertDiv = document.createElement('div');
      alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-info');
      alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                          'There is no previous users!';
      alertContainer.appendChild(alertDiv);
  }else{
      currentPage--;
      fetch('/auth/admin/listUser?page=' + currentPage + '&pageSize=' + pageSize)
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

function loadNextPageIng() {    
    currentPage++;
    fetch('/auth/admin/listIngredients?page=' + currentPage + '&pageSize=' + pageSize)
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
                let alertDiv = document.createElement('div');
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
  
  function loadPreviousPageIng() {    
    
    if(currentPage <= 0){
        var alertContainer = document.getElementById('alertContainer');
        let alertDiv = document.createElement('div');
        alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-info');
        alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                            'There is no previous users!';
        alertContainer.appendChild(alertDiv);
    }else{
        currentPage--;
        fetch('/auth/admin/listIngredients?page=' + currentPage + '&pageSize=' + pageSize)
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

function deleteRowFromSelfUser(id){
    let spinner = document.getElementById('spinnerDel');
    spinner.style['display']='inherit' ;
    setTimeout(function() {
        var url = new URL("/", window.location.origin);
        window.location.href = url.href;
      }, 1000);
    
  }

function showDashboard(){
  url = '/auth/views/dashboard';
  ajaxF(url);
}

function checkQueryFilled() {
    let form = document.getElementById('searchForm');
    let filter = form.querySelector('select');
    let query = form.querySelector('input');
    filterOptions = filter.querySelectorAll('option');

    if(filterOptions[5].selected && query.value === ''){
        var alertContainer = document.getElementById('alertContainer');
        let existingAlert = alertContainer.querySelector('.alert');
        if (existingAlert) {
        existingAlert.remove(); // Eliminar la alerta existente
        }
        let alertDiv = document.createElement('div');
        alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-warning');
        alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                            'Write into the search bar in order to filter by role!';
        alertContainer.appendChild(alertDiv);
        return true;
    }else{
        return false;
    }
}
