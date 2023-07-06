/* global bootstrap: false */
(() => {
  'use strict'
  const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(tooltipTriggerEl => {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()
  
function ajax(element){
    event.preventDefault();
    var url = element.getAttribute('href');
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      // if (xhr.readyState === 4 && xhr.status === 200) {
        document.getElementById('container').innerHTML = xhr.responseText;
      // }
    };
    xhr.open('GET', url, true);
    xhr.send();
}
