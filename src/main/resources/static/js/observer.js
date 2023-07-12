const targetElement = document.getElementById('container');

const observer = new MutationObserver(function() {
  let loadSpinner = document.getElementsByClassName('spinner-border');

  if(loadSpinner.length > 0){
    for (let i = 0; i < loadSpinner.length; i++) {
      loadSpinner[i].style.display = 'none';
    }
  }

  let userData = document.getElementById('userData');
  let personalData = document.getElementById('personalData');
  let password = document.getElementById('passwordForm');
  let addNewAdminUser = document.getElementById('addNewAdminUserForm');

  if (userData && personalData && password) {

    let userDataForm = document.getElementById('userData').querySelector('form');
    let personalDataForm = document.getElementById('personalData').querySelector('form');
    let passwordForm = document.getElementById('passwordForm').querySelector('form');

    userDataForm.addEventListener('submit', function(event) {
      event.preventDefault();
      loadSpinner[0].style['display']='inherit' ;
      let userDataInputs = document.getElementsByClassName('userDataForm');
      for (let i = 0; i < userDataInputs.length; i++) {
        if(userDataInputs[i].value === ''){
          userDataInputs[i].value = userDataInputs[i].placeholder;
        }
      }
      userDataForm.submit();
      setTimeout(function() {
        ajaxF('/admin/listUser');
      }, 2000);
    });

    personalDataForm.addEventListener('submit', function(event) {
      event.preventDefault();
      loadSpinner[1].style['display']='inherit' ;
      let personalDataInputs = document.getElementsByClassName('personalDataForm');
      for (let i = 0; i < personalDataInputs.length; i++) {
        if(personalDataInputs[i].value === '' || personalDataInputs[i].value === null){
          personalDataInputs[i].value = personalDataInputs[i].placeholder;
        }
      }
      personalDataForm.submit();
      setTimeout(function() {
        ajaxF('/admin/listUser');
      }, 2000);
    });

    passwordForm.addEventListener('submit', function(event) {
      event.preventDefault();
      loadSpinner[2].style['display']='inherit' ;
      if(document.getElementById('password').value === ''){
        var alertContainer = document.getElementById('alertContainer');
        var alertDiv = document.createElement('div');
        alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-warning');
        alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                            'You cannot enter an empty password!';
        alertContainer.appendChild(alertDiv);
      }else{
        passwordForm.submit();
      setTimeout(function() {
        ajaxF('/admin/listUser');
      }, 2000);
      }
    });
  }

  if(addNewAdminUser){
    let addNewAdminUserForm = addNewAdminUser.querySelector('form');
    addNewAdminUserForm.addEventListener('submit', function(event) {
      event.preventDefault();
      loadSpinner[0].style['display']='inherit' ;
      let dataInputs = document.getElementsByClassName('addUserData');
      let anyEmptyInput = false;
      for (let i = 0; i < dataInputs.length; i++) {
        if(dataInputs[i].value === null || dataInputs[i].value === ''){
          anyEmptyInput = true;
          break;
        } else{
        anyEmptyInput = false;
        }
      }

      if(anyEmptyInput === true){
        var alertContainer = document.getElementById('alertContainer');
        var alertDiv = document.createElement('div');
        alertDiv.classList.add('alert', 'alert-dismissible', 'fade', 'show', 'alert-danger');
        alertDiv.innerHTML = '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>' +
                            'You have to fill all the fields!';
        alertContainer.appendChild(alertDiv);
        dataInputs = null;
      }else{
        addNewAdminUserForm.submit();
        setTimeout(function() {
        ajaxF('/admin/listUser');
      }, 2000);
      }
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