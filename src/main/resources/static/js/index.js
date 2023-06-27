let home = document.getElementById('home');
let features = document.getElementById('features');
let contact = document.getElementById('contact');
let container = document.getElementById('container');

let nav = document.querySelector('nav');
let links = Array.from(nav.querySelectorAll('a'));
let sections = document.querySelectorAll('.section');

links.forEach((link, index) => {
    link.addEventListener('click', (event) => {
      event.preventDefault();
  
      sections.forEach((section, sectionIndex) => {
        if (index === sectionIndex) {
            section.classList.add('active');
        } else {
            section.classList.remove('active');
        }
      });
  
      links.forEach((link) => {
        link.classList.remove('active');
      });
  
      link.classList.add('active');
    });
  });

