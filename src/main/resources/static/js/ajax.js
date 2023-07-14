//Ajax for buttons
function ajaxB(element){
    event.preventDefault();
  let spinner = document.getElementById('mainSpinner');
  var url = element.getAttribute('href');
  var xhr = new XMLHttpRequest();

  xhr.open('GET', url, true);
  spinner.style.display = 'flex';

  xhr.onload = function() {
      document.getElementById('container').innerHTML = xhr.responseText;
      spinner.style.display = 'none';
    }
  xhr.send();   
};

//Ajax for functions
function ajaxF(url){
  event.preventDefault();
  let spinner = document.getElementById('mainSpinner');
  var xhr = new XMLHttpRequest();
  xhr.open('GET', url, true);
  spinner.style.display = 'flex';
  xhr.onload = function() {
    document.getElementById('container').innerHTML = xhr.responseText;
    spinner.style.display = 'none';
  };
  xhr.send();
}

//Ajax for forms
function ajaxForSearch(element) {
  event.preventDefault();
  let spinner = document.getElementById('mainSpinner');
  var url = element.getAttribute('href');
  var query = document.getElementById('query').value;
  url = url+query;
  var xhr = new XMLHttpRequest();

  xhr.open('GET', url, true);
  spinner.style.display = 'flex';

  xhr.onload = function() {
      document.getElementById('container').innerHTML = xhr.responseText;
      spinner.style.display = 'none';
  }
  xhr.send();   
};
