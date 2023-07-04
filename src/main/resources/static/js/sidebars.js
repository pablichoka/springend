/* global bootstrap: false */
(() => {
  'use strict'
  const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(tooltipTriggerEl => {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()

function containerLoad(){
  console.log("me estoy ejecutando");
  // Obtener todos los enlaces de la barra lateral
  var sidebarLinks = document.getElementsByClassName('ajax-link');

  // Agregar un evento de clic a cada enlace
  for (var i = 0; i < sidebarLinks.length; i++) {
    sidebarLinks[i].addEventListener('click', function(event) {
      event.preventDefault(); // Evitar la acción predeterminada del enlace

      var url = this.getAttribute('href'); // Obtener la URL del enlace

      // Realizar una solicitud AJAX para obtener el contenido de la URL
      var xhr = new XMLHttpRequest();
      xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
          document.getElementById('container').innerHTML = xhr.responseText;
        }
      };
      xhr.open('GET', url, true);
      xhr.send();
    });
  }
}

var targetDiv = document.getElementById('container');

// Crea una instancia de MutationObserver y pasa una función de callback
var observer = new MutationObserver(function(mutations) {
  // Ejecuta la función que deseas cada vez que se produzca un cambio en el div
  containerLoad();
});

// Configura las opciones del observador (en este caso, observamos cambios en el contenido)
var observerOptions = {
  childList: true,
  subtree: true
};

// Empieza a observar el div con las opciones definidas
observer.observe(targetDiv, observerOptions);

window.addEventListener('DOMContentLoaded', event => {

  // Toggle the side navigation
  const sidebarToggle = document.body.querySelector('#sidebarToggle');
  if (sidebarToggle) {
      // Uncomment Below to persist sidebar toggle between refreshes
      // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
      //     document.body.classList.toggle('sb-sidenav-toggled');
      // }
      sidebarToggle.addEventListener('click', event => {
          event.preventDefault();
          document.body.classList.toggle('sb-sidenav-toggled');
          localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
      });
  }

});