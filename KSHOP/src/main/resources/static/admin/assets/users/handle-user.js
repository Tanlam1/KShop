let currentCity = _$('#city').getAttribute('data-current')
renderCity('#city', currentCity)

function validInfoUser(type = 0) {

    //password
    if(password.value.replaceAll(' ', '').length > 0 && password.value.replaceAll(' ', '').length < 8) {
        Swal.fire('Message', 'Vui lòng nhập mật khẩu từ 8 ký tự trở lên', 'info')
        return false
    }

    if(password.value.replaceAll(' ', '').length > 0 && password.value.replaceAll(' ', '') !== re_password.value.replaceAll(' ', '')) {
        Swal.fire('Message', 'Nhập lại mật khẩu chưa đúng', 'info')
        return false
    }

    //info
    if(first_name.value.replaceAll(' ', '') == '' || last_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập họ và tên', 'info')
        return false
    }
    if(phone.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số điện thoại', 'info')
        return false
    }
    if(code.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mã CCCD / CMND', 'info')
        return false
    }

    //address
    if(address1.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập địa chỉ của tài khoản', 'info')
        return false
    }
    if(!city || city.options[_$('#city').selectedIndex].text.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn Tỉnh / Thành Phố của bạn', 'info')
        return false
    }
    if(!state || state.options[_$('#state').selectedIndex].text.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn Quận / Huyện của bạn', 'info')
        return false
    }
    if(!ward || ward.options[_$('#ward').selectedIndex].text.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn Phường / Xã của bạn', 'info')
        return false
    }

    let city_ = city.options[_$('#city').selectedIndex].text
    let state_ = state.options[_$('#state').selectedIndex].text
    let ward_ = ward.options[_$('#ward').selectedIndex].text

    let result = {
        last_name: last_name.value,
        first_name: first_name.value,
        password: password.value,
        phone: phone.value,
        code: code.value,
        city: city_, 
        state: state_, 
        ward: ward_,
        address1: address1.value

    }

    //add user
    if(type == 1) {

        if(password.value.replaceAll(' ', '').length < 8) {
            Swal.fire('Message', 'Vui lòng nhập mật khẩu từ 8 ký tự trở lên', 'info')
            return false
        }
    
        if(password.value.replaceAll(' ', '') !== re_password.value.replaceAll(' ', '')) {
            Swal.fire('Message', 'Nhập lại mật khẩu chưa đúng', 'info')
            return false
        }

        if(username.value.replaceAll(' ', '').length < 8) {
            Swal.fire('Message', 'Vui lòng nhập tên tài khoản từ 8 kí tự', 'info')
            return false
        }

        if(email.value.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng nhập email', 'info')
            return false
        }

        result.password = password.value
        result.username = username.value
        result.email = email.value

    } else {
        result.id = user_id.value
    }

    return result
}

function editUser() {
    let data = validInfoUser()

    if(!data) return

    http.post(`${_URL_MAIN}/Users/updateUser`, data)
    .then(data => {
        if(data.status == 200) {
            Swal.fire('Success', data.data, 'success')
            .then(() => window.location.reload())
        } else {
            Swal.fire('Message', data.data, 'info')
        }
    })
}

function addUser() {
    let data = validInfoUser(1)

    if(!data) return

    http.post(`${_URL_MAIN}/Users/adminAddUser`, data)
    .then(data => {
        if(data.status == 200) {
            Swal.fire('Success', data.data, 'success')
            .then(() => window.location.reload())
        } else {
            Swal.fire('Message', data.data, 'info')
        }
    })
}

function handleRoles() {
    let dataArrs = []
    _$$('.radio-section').forEach((e, index) => {
        let arrayInput = e.querySelectorAll('input')
        let status = false
        if(arrayInput[0].checked) {
            status = true
        }
        
        dataArrs.push(status)
    })

    let data = {
        list: dataArrs,
        user_id: user_id.value
    }

    http.post(`${_URL_MAIN}/Users/setRoles`, data)
    .then(data => {
        if(data.status == 200) {
            Swal.fire('Success', data.data, 'success')
            .then(() => window.location.reload())
        } else {
            Swal.fire('Message', data.data, 'info')
        }
    })
}

