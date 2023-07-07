const targetElement = document.getElementById('container');

const observer = new MutationObserver(function() {
  let loadSpinner = document.getElementsByClassName('spinner-border');

  if(loadSpinner.length > 0){
    for (let i = 0; i < loadSpinner.length; i++) {
      loadSpinner[i].style.display = 'none';
    }
  }

  let userDataForm = document.getElementById('userData');
  let deleteButton = document.getElementById('deleteButton');

  if (userDataForm) {
    userDataForm.addEventListener('submit', function(event) {
      event.preventDefault();
      loadSpinner[0].style['display']='inherit' ;
      userDataForm.submit();
      setTimeout(function() {
        ajaxF('/admin/listUser');
      }, 2000);
    });
  }
});

const observerOptions = {
  childList: true, 
  subtree: true,
};

if (targetElement) {
  observer.observe(targetElement, observerOptions);
}

/* global bootstrap: false */
(() => {
  'use strict'
  const tooltipTriggerList = Array.from(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  tooltipTriggerList.forEach(tooltipTriggerEl => {
    new bootstrap.Tooltip(tooltipTriggerEl)
  })
})()
  
//Ajax for buttons
function ajaxB(element){
  event.preventDefault();
    var url = element.getAttribute('href');
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      document.getElementById('container').innerHTML = xhr.responseText;
    };
    xhr.open('GET', url, true);
    xhr.send();
}

//Ajax for functions
function ajaxF(url){
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    document.getElementById('container').innerHTML = xhr.responseText;
  };
  xhr.open('GET', url, true);
  xhr.send();
}

