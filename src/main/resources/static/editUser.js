//editUser();

async function editModal(id) {
    let formEdit = document.forms["formEdit"];
    const modal = new bootstrap.Modal(document.querySelector('#edit'));
    await modal.show();
    Modal(formEdit, modal, id);
}

document.addEventListener("DOMContentLoaded", function () {

    let formEdit = document.forms["formEdit"];
    formEdit.addEventListener("submit", ev => {
        ev.preventDefault();
        let roles = [];
        for (let i = 0; i < formEdit.roles.options.length; i++) {
            if (formEdit.roles.options[i].selected) {
                roles.push({
                    id: formEdit.roles.options[i].value,
                    role: "ROLE_" + formEdit.roles.options[i].text
                });
            }
        }
        fetch("admin/user/" + formEdit.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: formEdit.username.value,
                name: formEdit.name.value,
                lastName: formEdit.lastName.value,
                email: formEdit.email.value,
                password: formEdit.password.value,
                roles: roles
            })
        }).then(() => {
            formEdit.reset();
            $('#closeEdit').click();
            getUsersOfTable();
        });
    });
});