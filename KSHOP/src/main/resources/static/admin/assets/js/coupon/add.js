const addVoucher = () => {
    
    let newVoucher = voucherInvalid()

    if(!newVoucher) {
        return false
    }
    
    http.post(`${_URL_MAIN}/ShopVouchers/add`, newVoucher)
    .then(data => {
        Swal.fire('Success', 'Thêm sản phẩm thành công', 'success')
        .then(rs => window.location.href = `${_ADMIN_DIR}/coupons`)
    })
    
}

const editVoucher = () => {

    let voucherEdit = voucherInvalid(1)
    
    if(!voucherEdit) {
        return false
    }
    
    http.put(`${_URL_MAIN}/ShopVouchers/update`, voucherEdit)
    .then(data => {
        Swal.fire('Success', 'Chỉnh sửa sản phẩm thành công', 'success')
        .then(rs => window.location.reload())
    })
}

function voucherInvalid(type = 0) {
    let voucher_code = _$('#voucher_code')
    let voucher_name = _$('#voucher_name')
    let description = _$('.ck-content')
    let price = _$('#price')
    let min_pro = _$('#min_pro')
    let max_disa = _$('#max_disa')
    let max_uses = _$('#max_uses')
    let max_uses_user = _$('#max_uses_user')
    let start_date = _$('#start_date')
    let end_date = _$('#end_date')
    let _fixed = _$('#is_fixed').value == 0 ? false : true
    let discount_amount = _$('#discount_amount')
    
    

    if(!voucher_code || voucher_code.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên sản phẩm', 'info')
        return false
    }

    if(!voucher_name || voucher_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mã sản phẩm (slug)', 'info')
        return false
    }

    if(!description || description.innerHTML.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mô tả sản phẩm', 'info')
        return false
    }

    if(!price || price.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập giá bán của mã', 'info')
        return false
    }

    if(!min_pro || min_pro.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập giá trị tối thiểu được giảm', 'info')
        return false
    }

    if(!max_disa || max_disa.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số tiền tối đa được giảm', 'info')
        return false
    }

    if(!max_uses || max_uses.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập giá nhập của sản phẩm', 'info')
        return false
    }

    if(!max_uses_user || max_uses_user.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập giá niêm yết sản phẩm', 'info')
        return false
    }

    if(!start_date || start_date.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số lượng trên mỗi đơn vị', 'info')
        return false
    }
    if(!end_date || end_date.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số lượng trên mỗi đơn vị', 'info')
        return false
    }

    if(!discount_amount || discount_amount.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên sản phẩm', 'info')
        return false
    }
    let result = {
        voucher_code: voucher_code.value,
        voucher_name: voucher_name.value,
        description: description.innerHTML,
        price: price.value * 1,
        min_pro: min_pro.value * 1,
        max_disa: max_disa.value * 1,
        max_uses: max_uses.value * 1,
        max_uses_user: max_uses_user.value * 1,
        discount_amount: discount_amount.value * 1,
        start_date: start_date.value,
        end_date: end_date.value,
        _fixed
    }

    if(type == 1) { // edit
        result.id = _$('#voucher_id').value * 1
        // result.is_featured = _$('#is_featured').checked
        // result.is_new = _$('#is_new').checked
    }

    return result
}