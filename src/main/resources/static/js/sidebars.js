/* global bootstrap: false */
(() => {
  'use strict'
  const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(tooltipTriggerEl => {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()

$(document).ready(function() {
  // Cuando se hace clic en un enlace del sidebar, se carga el container correspondiente
  $('#sidebar a').click(function(event) {
      event.preventDefault(); // Evita el comportamiento predeterminado del enlace

      var url = $(this).attr('href'); // Obtiene la URL del enlace

      // Realiza la solicitud AJAX para obtener el container del archivo HTML
      $.ajax({
          url: url,
          dataType: 'html',
          success: function(data) {
              $('#container').html(data); // Carga el container en el div #container
          },
          error: function() {
              alert('Error al cargar el container');
          }
      });
  });
});

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