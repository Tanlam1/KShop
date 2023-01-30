const addStore = () => {

    let newStore = storeInvalid()

    if (!newStore) {
        return false
    }

    http.post(`${_URL_MAIN}/ShopStores/add`, newStore)
        .then(data => {
            Swal.fire('Success', 'Thêm store thành công', 'success')
                .then(rs => window.location.href = `${_ADMIN_DIR}/vendors`)
        })

}

const editStore = () => {

    let storeEdit = storeInvalid(1)

    if (!storeEdit) {
        return false
    }

    http.put(`${_URL_MAIN}/ShopStores/update`, storeEdit)
        .then(data => {
            Swal.fire('Success', 'Chỉnh sửa store thành công', 'success')
                .then(rs => window.location.href = `${_ADMIN_DIR}/edit/vendors/${data.id}`)
        })
}

function storeInvalid(type = 0) {
    let store_code = _$('#store_code')
    let store_name = _$('#store_name')
    let description = _$('#description')
    let address = _$('#address')
    let phone = _$('#phone')
    let image = _$('#image')


    if (!store_code || store_code.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập store code', 'info')
        return false
    }

    if (!store_name || store_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên store', 'info')
        return false
    }

    if (!address || address.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập địa chỉ', 'info')
        return false
    }

    if (!phone || phone.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập sđt', 'info')
        return false
    }

    if (!image || image.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập hình ảnh chính của sản phẩm', 'info')
        return false
    }


    if (!description || description.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mô tả sản phẩm', 'info')
        return false
    }

    let result = {
        store_code: store_code.value,
        store_name: store_name.value,
        image: image.value,
        description: description.value,
        address: address.value,
        phone: phone.value,
    }

    if (type == 1) { // edit
        result.id = _$('#store_id').value * 1
    }

    return result
}

const onChangeFile = (e) => {
    let output = document.getElementById('image-view');


    uploadToImgur(e, output, e.target.files[0])
}

_$$('input[type="number"]').forEach(e => {
    e.value = parseFloat(e.value)
})