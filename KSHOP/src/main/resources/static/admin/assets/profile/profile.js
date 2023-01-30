let currentCity = _$('#city').getAttribute('data-current')
renderCity('#city', currentCity)

const onChangeFile = (e, output = '', outputSrc = '') => {
    let outputDOM = output == '' ? false : _$(output);
    let outputSrcDOM = outputSrc == '' ? false : _$(outputSrc);
    
    if(outputDOM) {
        uploadToImbb(e, outputDOM, outputSrcDOM, (response) => {
            if(response.status_code == 200) {

                let imgReview = _$(outputSrc)
                if(imgReview) {
                    imgReview.src = response.image.display_url
                    flash('success', 'Upload hình ảnh thành công.')
                    
                }
            } else {
                flash('error', 'Upload thất bại, vui lòng thử lại.')
            }
        })
    }
}

function settingValidate() {
    let description = _$('.ck-content')
    if(store_image.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn ảnh đại diện', 'info')
        return false
    }
    if(store_background.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn ảnh bìa', 'info')
        return false
    }
    if(store_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên cửa hàng', 'info')
        return false
    }
    if(!description || description.innerText.replaceAll(' ', '') == '' || description.innerText.replaceAll('\n', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mô tả cửa hàng', 'info')
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
    description = description.innerHTML
    store_address.value = `${address1.value}, ${ward_}, ${state_}, ${city_}`

    return {
        stores: {
            id: store_id.value * 1,
            store_code: store_code.value,
            store_name: store_name.value,
            description,
            address: store_address.value,
            phone: store_phone.value,
            image: store_image.value,
            background: store_background.value,
        },
        id: user_id.value,
        last_name: last_name.value,
        first_name: first_name.value,
        phone: phone.value,
        code: code.value,
        // email: email.value,
        city: city_, 
        state: state_, 
        ward: ward_,
        address1: address1.value

    }
}

function update() {
    let result = settingValidate()

    if(!result) 
        return
    
    http.post(`${_URL_MAIN}/Users/updateProfile`, result)
    .then(data => {
        if(data.status == 200) {
            Swal.fire('Success', data.data, 'success')
            .then(() => window.location.reload())
        } else {
            Swal.fire('Message', data.data, 'info')
            .then(() => window.location.reload())
        }
    })
}