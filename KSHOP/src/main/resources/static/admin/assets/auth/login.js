
const login = () => {
    const email = _$('#email').value
    const password = _$('#password').value

    if(email.replaceAll(' ', '') == '') {
        Swal.fire("Message", 'Vui lòng nhập email', "error")
        return
    }

    if(password.replaceAll(' ', '').length < 3) {
        Swal.fire("Message", 'Vui lòng nhập password lớn hơn 3 kí tự', "error")
        return
    }

    let data = {
        email, password
    }

    http.post('http://localhost:8080/auth/admin/login', data)
        .then(data => handleLogin(data))
        .catch(err => console.log(err))

    let handleLogin = (data) => {
        if(data.status == 200) {
            window.location.href = '/admin-panel/dashboards'
        } else {
            Swal.fire("Message", data.error, "error")
        }
    }
}