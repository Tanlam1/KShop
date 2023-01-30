
const login = () => {
    const email = _$('#sign-in #email').value
    const password = _$('#sign-in #password').value

    let data = {
        email, password
    }

    http.post('http://localhost:8080/auth/login', data)
        .then(data => handleLogin(data))
        .catch(err => console.log(err))

    let handleLogin = (data) => {
        if(data.status == 200) {
            window.location.href = '/profile'
        } else {
            Swal.fire("Message", data.error, "error")
        }
    }
}

const register = () => {
    const email = _$('#sign-up #email').value
    const password = _$('#sign-up #password').value
    const firstName = _$('#sign-up #firstName').value
    const lastName = _$('#sign-up #lastName').value
    const agree = _$('#sign-up #agree').checked

    if(email.replaceAll(' ', '') == '') {
        Swal.fire("Message", 'Vui lòng nhập email', "error")
        return
    }

    if(password.replaceAll(' ', '').length < 6) {
        Swal.fire("Message", 'Vui lòng nhập password lớn hơn 6 kí tự', "error")
        return
    }

    if(firstName.replaceAll(' ', '') == '') {
        Swal.fire("Message", 'Vui lòng nhập họ và tên lớn hơn 3 kí tự', "error")
        return
    }

    if(lastName.replaceAll(' ', '') == '') {
        Swal.fire("Message", 'Vui lòng nhập họ và tên lớn hơn 3 kí tự', "error")
        return
    }

    if(!agree) {
        Swal.fire("Message", 'Vui lòng chấp nhật điều khoản chính sách bảo mật', "error")
        return
    }

    let data = {
        email, password, firstName, lastName
    }

    http.post('http://localhost:8080/auth/register', data)
        .then(data => handleRegister(data))
        .catch(err => console.log(err))

    let handleRegister = (data) => {
        if(data.status == 200) {
            Swal.fire("Oke", 'Đăng ký thành công', "success")
            .then(rs => {
                window.location.href = '/profile'
            })
        } else {
            Swal.fire("Message", data.error, "error")
        }
    }
}