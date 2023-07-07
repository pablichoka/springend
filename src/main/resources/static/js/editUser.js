const targetElement = document.getElementById('container');

const observer = new MutationObserver(function() {
  // let sendButton = document.getElementById('sendButton');
  let userDataForm = document.getElementById('userData');

  if (userDataForm) {
    userDataForm.addEventListener('submit', function(event) {
      event.preventDefault();
      userDataForm.submit();
      setTimeout(function() {
        goBack();
      }, 2000);
    });
  }
  console.log('Se han detectado cambios en el DOM');
});

const observerOptions = {
  childList: true, 
  subtree: true,
};

if (targetElement) {
  observer.observe(targetElement, observerOptions);
}

function goBack() {
  var url = '/userActions/listUser';
  fetch(url)
    .then(function(response) {
      return response.text();
    })
    .then(function(data) {
      document.getElementById('container').innerHTML = data;
    })
    .catch(function(error) {
      console.error('Error en la solicitud:', error);
    });
}
