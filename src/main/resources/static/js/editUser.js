// Seleccionar el elemento que se desea observar
const targetElement = document.getElementById('container');

// Crear una nueva instancia de MutationObserver
const observer = new MutationObserver(function(mutations) {
  // let sendButton = document.getElementById('sendButton');
  let userDataForm = document.getElementById('userData');

  if (userDataForm) {
    userDataForm.addEventListener('submit', function() {
      // event.preventDefault();
      goBack();
      // userDataForm.submit();
    });
  }
  console.log('Se han detectado cambios en el DOM');
});

// Configurar las opciones del observador
const observerOptions = {
  childList: true, // Observar cambios en los nodos hijos
  subtree: true, // Observar cambios en todo el subárbol
  // Otros opciones disponibles: attributes, characterData, attributeFilter, attributeOldValue, characterDataOldValue
};

// Iniciar la observación del elemento
if (targetElement) {
  observer.observe(targetElement, observerOptions);
}


function goBack(){
  var url = '/userActions/listUser'
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
      document.getElementById('container').innerHTML = xhr.responseText;
  };
  xhr.open('GET', url, true);
  xhr.send();
}


// document.addEventListener('DOMContentLoaded', function() {
//   // Aquí puedes acceder al botón de envío del formulario
//   let sendButton = document.getElementById('sendButton');

//   sendButton.addEventListener('click', function(){
//     event.preventDefault();
//     goBack();
//     document.getElementById('userData').submit();
//   });
    
//   function goBack(){
//     var url = '/userActions/listUser'
//     var xhr = new XMLHttpRequest();
//     xhr.onreadystatechange = function() {
//         document.getElementById('container').innerHTML = xhr.responseText;
//     };
//     xhr.open('GET', url, true);
//     xhr.send();
//   }
// });