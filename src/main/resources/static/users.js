'use strict';


function getCurrentUser() {
    fetch("user/auth")
        .then(response => response.json())
        .then(user => {
            const roles = user.roles.map(role => role.name).join(',');
            $('#emailUser').append(`<span>${user.email}</span>`)
            $('#roleUser').append(`<span>${roles.replace('ROLE_', '') + ' '}</span>`)
            const u = $(`
        <tr\>
            <td\>${user.id}</td\>
            <td\>${user.username}</td\>
            <td\>${user.name}</td\>
            <td\>${user.lastName}</td\>
            <td\>${user.email}</td\>
            <td\>${roles}</td\>
         </tr\>
`);
            $('#oneUser').append(u)
        })
}

getCurrentUser();