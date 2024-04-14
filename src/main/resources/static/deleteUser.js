'use strict';

let deleteForm = document.forms["formDelete"];

async function deleteModal(id) {
    const modal = new bootstrap.Modal(document.querySelector('#delete'));
    await deleteUser(id);
    switch (deleteForm.roles.value) {
        case '1':
            deleteForm.roles.value = 'ADMIN';
            break;
        case '2':
            deleteForm.roles.value = 'USER';
            break;
    }
}

function deleteUser(id) {
    deleteForm.addEventListener("submit", ev => {
        ev.preventDefault();
        const data = { id: deleteForm.id.value, role: deleteForm.roles.value }; // Создаем объект с данными
        fetch("admin/user/" + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data) // Преобразуем объект в JSON
        }).then(() => {
            $('#closeDelete').click();
            getUsersOfTable();
        });
    });
}
