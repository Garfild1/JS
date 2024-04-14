'use strict';
const tbody = $('#allUsers');

getUsersOfTable();





function getUsersOfTable() {
    tbody.empty();
    $('#emailUser').empty();
    $('#roleUser').empty();
    fetch('admin/users')
        .then(res => res.json())
        .then(users => {
            users.forEach(user => {
                const roles = user.roles.map(role => role.name).join(',');
                $('#emailUser').append(`<span>${user.email}</span>`)
                $('#roleUser').append(`<span>${roles.replace('ROLE_', '') + ' '}</span>`)
                const u = $(`
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.name}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                        <td>${roles}</td>
                        <td>
                            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#edit" onClick="editModal(${user.id})">
                                Edit
                            </button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delete" onclick="deleteModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>
                `);
                tbody.append(u);
            });
        });
}
