async function getOneUser(id) {
    let response = await fetch("/admin/user/" + id);
    if (response.ok) {
        return await response.json();
    } else {
        throw new Error('Failed to fetch user data');
    }
}



async function Modal(form, modal, id) {
    let user;
    try {
        user = await getOneUser(id);
        form.id.value = user.id;
        form.username.value = user.username;
        form.name.value = user.name;
        form.lastName.value = user.lastName;
        form.email.value = user.email;


        form.roles.value = user.roles;

        modal.show();
    } catch (error) {
        console.error(error);
    }
}
