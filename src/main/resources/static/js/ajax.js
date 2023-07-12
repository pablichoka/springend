//Ajax for buttons
function ajaxB(element){
    // event.preventDefault();
    //   var url = element.getAttribute('href');
    //   var xhr = new XMLHttpRequest();
    //   xhr.open('GET', url, true);
    //   xhr.onload = function() {
    //     document.getElementById('container').innerHTML = xhr.responseText;
    //   };
    //   xhr.send();
  
  let spinner = document.getElementById('mainSpinner');
  var url = element.getAttribute('href');
  var xhr = new XMLHttpRequest();

  xhr.open('GET', url, true);

  // Muestra el spinner antes de enviar la petición
  spinner.style.display = 'flex';

  xhr.onload = function() {
        document.getElementById('container').innerHTML = xhr.responseText;
    

      // Oculta el spinner al completarse la petición
      spinner.style.display = 'none';
    }
  xhr.send();
    
};

  


//Ajax for functions
function ajaxF(url){
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);
  xhr.onload = function() {
    document.getElementById('container').innerHTML = xhr.responseText;
  };
  xhr.send();
}
