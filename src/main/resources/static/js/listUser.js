function reloadPage() {
    location.reload();
  }

  var currentPage = 0; // Variable para almacenar la página actual
  var pageSize = 1; // Tamaño de la página

function loadNextPage() {    
    currentPage++;
    fetch('/userActions/listUser?page=' + currentPage + '&pageSize=' + pageSize)
        .then(function(response) {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Error al cargar la siguiente página');
        }
        })
        .then(function(data) {
            if(checkIfTableIsEmpty(data) === true){
                alert("There's no more users");
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
        alert ("There's no previous users");
    }else{
        currentPage--;
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
    console.log(tableContent)
    if(/^\s*$/.test(tableContent)){
        return true;
    }else{
        return false;
    }
}