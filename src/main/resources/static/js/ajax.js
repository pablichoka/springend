//Ajax for buttons
function ajaxB(element){
    event.preventDefault();
      var url = element.getAttribute('href');
      var xhr = new XMLHttpRequest();
      xhr.open('GET', url, true);
      xhr.onload = function() {
        document.getElementById('container').innerHTML = xhr.responseText;
      };
      xhr.send();
}


//Ajax for functions
function ajaxF(url){
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);
  xhr.onload = function() {
    document.getElementById('container').innerHTML = xhr.responseText;
  };
  xhr.send();
}
