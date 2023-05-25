function editUser(button){

    const rowId = button.getAttribute('data-row');

    const row = document.getElementById('row-' + rowId);
    const form = document.createElement('form');

    let age = document.createElement('input');
    age.type='text';
    age.name='age';

    form.appendChild(age);

    form.style.display='flex';

    row.parentNode.insertBefore(form, row.nextSibling);

}