//Ajax for buttons
function ajaxB(element){
    event.preventDefault();
  let spinner = document.getElementById('mainSpinner');
  var url = element.getAttribute('href');
  var xhr = new XMLHttpRequest();

  xhr.open('GET', url, true);
  document.getElementById('container').innerHTML = "";
  spinner.style.display = 'flex';

  xhr.onload = function() {
      document.getElementById('container').innerHTML = xhr.responseText;
      spinner.style.display = 'none';
    }
  xhr.send();   
};

//Ajax for functions
function ajaxF(url){
  // event.preventDefault();
  let spinner = document.getElementById('mainSpinner');
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);
  document.getElementById('container').innerHTML = "";
  spinner.style.display = 'flex';
  xhr.onload = function() {
    document.getElementById('container').innerHTML = xhr.responseText;
    spinner.style.display = 'none';
  };
  xhr.send();
}

//Ajax for search
function ajaxForm(formName) {
  // event.preventDefault();
  let spinner = document.getElementById('mainSpinner');
  let form = document.getElementById(formName);
  var url = form.getAttribute('action');
  var csrfToken = document.getElementById('csrf').value;
  var formData = new FormData(form);
  var xhr = new XMLHttpRequest();
  xhr.open('POST', url, true);
  xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
  document.getElementById('container').innerHTML = "";
  spinner.style.display = 'flex';

  xhr.onload = function() {
      document.getElementById('container').innerHTML = xhr.responseText;
      spinner.style.display = 'none';
  }
  xhr.send(formData);   
};