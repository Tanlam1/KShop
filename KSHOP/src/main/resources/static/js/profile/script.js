
const genderDetailOrder = (id) => {
    const handleOrderDetail = (data) => {
        let order = data[0].orders
        
        let wrap = _$('#order-details')
        let title = _$('.title_detail')
        let list_table = _$('.list_table')
        let table_foot = _$('.table_foot')
        let listItemHTML = ``, trackingHtml = ``
        let total_money = 0
        let discount = order.discount_amount
        let ship_fee = order.shipping_fee
        let list_tracking = _$('.list_tracking')
        data.forEach(e => {
            let discountItemProduct = e.discount_amout + ((e.discount_percentage/100)*e.unit_price)
            let subtotalItemProduct = (e.unit_price * e.quantity) - discountItemProduct
            listItemHTML += `<tr>
                <td>
                    <a href="/product/${e.products.product_code}-${e.products.id}">${e.order_detail_name != null && e.order_detail_name != '' ? e.order_detail_name : e.products.product_name}</a>&nbsp;<strong>x ${e.quantity}</strong><br>
                    SHOP : <a href="/store/${e.orders.users.stores.id}" target="_blank">${e.orders.users.stores.store_name} - ${e.orders.users.first_name} ${e.orders.users.last_name}</a>
                </td>
                <td>${formatVND(subtotalItemProduct)}</td>
            </tr>`
            trackingHtml += `<tr>
                        <td class="order">${e.id}</td>
                        <td class="date">${getDateCurrStr(e.date_allocated)}</td>
                        <td class="status">
                            <span class="${statusOrder(order.order_status).class}">
                            ${statusOrder(order.order_status).current}
                            </span>
                        </td>
                        <td class="total">${formatVND(subtotalItemProduct)} x ${e.quantity}</td>
                        <td class="action"><a href="${order.id_order_ghn != null ? 'https://donhang.ghn.vn/?order_code=' + order.id_order_ghn : 'javascript:Swal.fire(\'Message\', \'Đơn hàng đang chờ shop xác nhận đơn hàng\', \'info\')'}" class="btn btn-rounded">Theo dõi đơn hàng</a>
                        </td>
                    </tr>`

            total_money += subtotalItemProduct
        })
        let html = /*html */``
        table_foot.innerHTML = `<tr>
                        <th>Tạm tính:</th>
                        <td>${formatVND(total_money)}</td>
                    </tr>
                    <tr>
                        <th>Phí ship:</th>
                        <td>${formatVND(ship_fee)}</td>
                    </tr>
                    <tr>
                        <th>Khuyến mãi:</th>
                        <td>${formatVND(discount)}</td>
                    </tr>
                    <tr>
                        <th>Hình thức thanh toán:</th>
                        <td>${order.paymentTypes.payment_name}</td>
                    </tr>
                    <tr class="total">
                        <th class="border-no">Tổng tiền:</th>
                        <td class="border-no">${formatVND(total_money - discount + ship_fee)}</td>
                </tr>`
        list_table.innerHTML = listItemHTML
        title.innerHTML = `Đơn hàng #${order.id} được tạo vào lúc ${getDateCurrStr(order.created_at)} và đang trong trạng thái ${order.order_status}.`
        list_tracking.innerHTML = trackingHtml
        _$('.customer_address').innerHTML = `${order.ship_address1}, <span class="customer_ward"> ${order.ship_ward} </span>, <span class="customer_state"> ${order.ship_state} </span>, <span class="customer_city"> ${order.ship_city}, <span class="customer_country"> ${order.ship_country} </span>`
        _$('.box-address-checkout__info').innerHTML = `<span class="customer_name">${order.ship_name}</span> | <span>${order.ship_phone}</span>`
    }

    http.get(`${_URL_MAIN}/OrderDetail/get/orderid/${id}`)
        .then(data => handleOrderDetail(data))
}

const orderList = () => {
    const idCustomer = _$('#idCustomer').value

    const handleOrders = (data) => {
        let wrapOrders = _$('.ordersList')
        let html = ``
        dataEach = data.data.orders.reverse()
        dataProductDetailName = data.data.order_detail_product.reverse()
        dataEach.forEach((e, i)=> {
            let htmlCancelOrder = ``
            let htmlReOrder = ``
            if(e.order_status.toLocaleLowerCase() === 'new') {

                htmlCancelOrder = `<a href="javascript:void(0)" onclick="cancelOrder(${e.id})" class="cancel__order-btn link-to-tab btn btn-outline btn-default btn-block btn-sm btn-rounded">Hủy đơn hàng</a>`
            }
            if(e.order_status.toLocaleLowerCase() !== 'new') {

                htmlReOrder = `<a href="javascript:void(0)" onclick="" class="cancel__order-btn link-to-tab btn btn-outline btn-default btn-block btn-sm btn-rounded">Mua lại</a>`
            }
            html += `<tr>
                <td class="order-id order-id-small">${e.id}</td>
                <td class="order-status">
                    <span class="${statusOrder(e.order_status).class}">${statusOrder(e.order_status).current}</span>
                </td>
                <td class="order-detail-product">${dataProductDetailName[i]}</td>
                <td class="order-date">
                    ${getDateCurrStr(e.created_at)}
                </td>
                
                <td class="order-action">
                    <a href="#order-details" onclick="genderDetailOrder(${e.id})"
                        class="link-to-tab btn btn-outline btn-default btn-block btn-sm btn-rounded">Chi tiết</a>
                    ${htmlCancelOrder}
                    ${htmlReOrder}
                </td>
            </tr>`
        })
        wrapOrders.innerHTML = html
    }

    http.get(`${_URL_MAIN}/Orders/get/customerid/${idCustomer}`)
        .then(data => handleOrders(data))
}

orderList() // orderlist

const reviewlist = () => {

    const handleReviews = (data) => {
        let wrapOrders = _$('.reviewslist')
        let html = ``
        dataEach = data.data.response.reverse()
        
        dataEach.forEach((e, i)=> {
            let htmlReviewStatus = /*html*/`
                <div class="order-status">
                    <span class="new">Chờ bạn đánh giá</span>
                </div>
            `
            let htmlButton = /*html*/`
                <td class="order-action">
                    <a href="javascript:void(0)" onclick="reviewProduct(${e.detail_order.products.id})"  class="btn btn-outline btn-default btn-block btn-sm btn-rounded">Đánh giá</a>
                </td>
            `
            
            if(e.review_status != false) {
                let spanStar = '<i class="w-icon-star-full"></i> '.repeat(e.review_status.rating)
                htmlReviewStatus = /*html*/`
                    <div class="order-status">
                        <span> ${e.review_status.noidung} <br> (${e.review_status.rating}) ${spanStar}</span>
                    </div>
                `
                htmlButton = /*html*/`
                    <td class="order-action">
                        <p>Đánh giá rồi</p>
                    </td>
                `
            }
            html += `<tr>
                <td class="order-id order-id-small">${(i + 1)}</td>
                <td style="width:25%">
                    ${htmlReviewStatus}
                </td>
                <td style="width:25%" class="order-detail-product">
                    <a target="_blank" href="/product/${e.detail_order.products.product_code}-${e.detail_order.products.id}">${e.detail_order.products.product_name}</a>
                </td>
                <td>
                    ${getDateCurrStr(e.detail_order.orders.order_date)}
                </td>
                ${htmlButton}
                
            </tr>`
        })
        wrapOrders.innerHTML = html
    }

    http.get(`${_URL_MAIN}/Orders/getReviewList`)
        .then(data => handleReviews(data))


}
reviewlist() // reviewlist
    
function reviewProduct(id) {

    Swal.fire({
        title: "Đánh giá sản phẩm",
        html: `
        <p>Nhập nội dung và chọn đánh giá</p>
        <div class="rate-wrap">
            <i class="w-icon-star-full"></i>
            <i class="w-icon-star-full"></i>
            <i class="w-icon-star-full"></i>
            <i class="w-icon-star-full"></i>
            <i class="w-icon-star-full"></i>
        </div>
        `,
        icon: "info",
        input: "text", 
        showCancelButton: true,
        inputPlaceholder: "Nội dung đánh giá của bạn..",
        inputValidator: (value) => {
            if (value) {
                if(value.replaceAll(' ', '').length < 3) {
                    return
                }

                let rateSelected = _$$('.rate-wrap i.selected')
                let data = {
                    products: {
                        id
                    },
                    customers: {
                        id: _$('#idCustomer').value * 1,
                    } ,
                    rating: rateSelected.length * 1,
                    comment: value,
                }

                http.post(`${_URL_MAIN}/ProductReviews/add`, data)
                .then(data => {
                    if(data.status == 200) {
                        Swal.fire('Success', data.data, 'success')
                        .then(() => window.location.reload())
                    } else {
                        Swal.fire('Message', data.data, 'info')
                    }
                })
            }
        }
    })    

    let listIcon = _$$('.rate-wrap i')
    listIcon.forEach((e, i) => {
        e.addEventListener('mouseover', () => {
            for (let j = 0; j <= i; j++) {
                listIcon[j].style.color = '#fff305'
            }
        })
        e.addEventListener('mouseout', () => {
            for (let j = 0; j <= i; j++) {
                if(!listIcon[j].classList.contains('selected')) {
                    listIcon[j].style.color = 'unset'
                }
            }
        })
        e.addEventListener('click', () => {
            for (let j = 0; j <= i; j++) {
                listIcon[j].classList.add('selected')
            }
            for (let j = i + 1; j < 5; j++) {
                listIcon[j].classList.remove('selected')
                listIcon[j].style.color = 'unset'
            }
        })
    })
}



function statusOrder(status) {
    let data = {}
    status = status.toLocaleLowerCase()
    switch(status) {
        case 'new': {
            data.class = 'new'
            data.current = 'Đơn hàng mới'
            data.next = 'Shop Confirmed'
            break
        }
        case 'shop confirmed': {
            data.class = ''
            data.current = 'Đơn hàng đã xác nhận'
            data.next = 'Shipped'
            break
        }
        case 'shipped': {
            data.class = ''
            data.current = 'Đã giao hàng'
            data.next = 'Complete'
            break
        }
        case 'complete': {
            data.class = 'close'
            data.current = 'Đơn hàng đã kết thúc'
            data.next = 'close'
            break
        }
        case 'close': {
            data.class = 'close'
            data.current = 'Đơn hàng đã kết thúc'
            data.next = 'close'
            break
        }
        case 'cancel': {
            data.class = 'cancel'
            data.current = 'Bạn đã hủy đơn này'
            data.next = 'Cancel'
            break
        }
        case 'canceled by store': {
            data.class = 'store_cancel'
            data.current = 'Store đã hủy đơn này'
            data.next = 'canceled by store'
            break
        }
    }

    return data
}

function customerInvalid() {

    let first_name = _$('#first_name')
    let last_name = _$('#last_name')
    let avatar = _$('#avatar')
    let billing_address = _$('#billing_address')
    let shipping_address = _$('#shipping_address')
    let city = _$('#city')
    let country = 'Việt Nam'
    let state = _$('#state')
    let ward = _$('#ward')
    let phone = _$('#phone')
    let code = _$('#code')
    let company = _$('#company')
    let birthday = _$('#birthday')
    let updated_at = getDateCurr()

    if(!first_name || first_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên đệm của bạn', 'info')
        return false
    }

    if(!last_name || last_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên của bạn', 'info')
        return false
    }

    if(!avatar || avatar.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn ảnh đại diện', 'info')
        return false
    }

    if(!billing_address || billing_address.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập địa chỉ thanh toán', 'info')
        return false
    }

    if(!shipping_address || shipping_address.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập địa chỉ giao hàng', 'info')
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

    if(!phone || phone.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số điện thoại', 'info')
        return false
    }

    if(!code || code.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mã CMND / CCCD', 'info')
        return false
    }

    let result = {
        id: idCustomer.value,
        first_name: first_name.value,
        last_name: last_name.value,
        avatar: avatar.value,
        billing_address: billing_address.value,
        shipping_address: shipping_address.value,
        city: city.options[_$('#city').selectedIndex].text,
        country: country.value,
        state: state.options[_$('#state').selectedIndex].text,
        ward: ward.options[_$('#ward').selectedIndex].text,
        phone: phone.value,
        code: code.value,
        email: email.value,
        company: company.value,
        birthday: birthday.value,
        gender: _$('#nam').checked ? true : false,
        updated_at
    }

    return result
}

const changeProfile = () => {
    let profile = customerInvalid()

    if(!profile) {
        return false
    }

    http.put(`${_URL_MAIN}/Customer/update`, profile)
    .then(rs => {
        Swal.fire('Success', 'Chỉnh sửa thành công', 'success')
        .then(rp => window.location.reload())
    })
}

function passwordValid() {
    let currentPass = _$('#cur-password')
    let newPass = _$('#new-password')
    let confirmPass = _$('#conf-password')

    if(!currentPass || currentPass.value.replaceAll(' ', '').length < 3) {
        Swal.fire('Message', 'Vui lòng nhập mật khẩu hiện tại từ 3 kí tự trở lên.', 'info')
        return false
    }

    if(!newPass || newPass.value.replaceAll(' ', '').length < 6) {
        Swal.fire('Message', 'Vui lòng nhập mật khẩu mới từ 6 kí tự trở lên.', 'info')
        return false
    }

    if(!confirmPass || confirmPass.value.replaceAll(' ', '').length < 6 || confirmPass.value != newPass.value) {
        Swal.fire('Message', 'Nhập lại mật khẩu mới chưa chính xác', 'info')
        return false
    }

    return {
        newPass: newPass.value, currentPass: currentPass.value
    }
}

const changePassword = () => {
    let newPassword = passwordValid()

    if(!newPassword) return

    http.post(`${_URL_MAIN}/Customer/changepassword`, newPassword)
    .then(data => {
        if(data.status == 200) {
            Swal.fire('Success', data.data, 'success')
            .then(rs => window.location.reload())
        } else {
            Swal.fire('Message', data.data, 'error')
        }
    })
}
let currentCity = _$('#city').getAttribute('data-current')
renderCity('#city', currentCity)

function defaultInfoSeller() {
    shopFullName.value = first_name.value + ' ' + last_name.value
    shopPhone.value = phone.value
    shopCode.value = code.value
    shopEmail.value = email[0].value
}

function sellerInvalid() {

    if(!shopFullName || shopFullName.value.replaceAll(' ', '') == '' || shopFullName.value.replaceAll(' ', '').length < 3) {
        Swal.fire('Message', 'Vui lòng nhập tên cửa hàng từ 3 ký tự', 'info')
        return false
    }

    if(!shopPhone || shopPhone.value.replaceAll(' ', '') == '' || !shopPhone.value.replaceAll(' ', '').match(/^\d{10}$/)) {
        Swal.fire('Message', 'Vui lòng nhập đúng định dạng số điện thoại cửa hàng', 'info')
        return false
    }

    if(!shopCode || shopCode.value.replaceAll(' ', '') == '' || !shopCode.value.replaceAll(' ', '').match(/^\d{12}$/)) {
        Swal.fire('Message', 'Vui lòng nhập đúng định dạng CMND / CCCD', 'info')
        return false
    }

    if(!shopEmail || shopEmail.value.replaceAll(' ', '') == '' || !shopEmail.value.replaceAll(' ', '').match(/^\S+@\S+\.\S+$/)) {
        Swal.fire('Message', 'Vui lòng nhập đúng định dạng email của cửa hàng', 'info')
        return false
    }

    if(!shopType || shopType.options[_$('#shopType').selectedIndex].text.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn loại hình Cá nhân / Doanh nghiệp', 'info')
        return false
    }

    if(!shopCategory || shopCategory.options[_$('#shopCategory').selectedIndex].text.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng chọn ngành hàng chủ yếu của shop', 'info')
        return false
    }

    let result = {
        name: shopFullName.value,
        phone: shopPhone.value,
        code: shopCode.value,
        email: shopEmail.value,
        type: shopType.value,
        category: {
            id: shopCategory.value
        },
        customer: {
            id: idCustomer.value
        },
    }

    return result
}

const sellerOpen = (btn) => {

    let seller = sellerInvalid()

    if(!seller) {
        return false
    }

    btn.style.pointerEvents = 'none';
    btn.innerHTML = 'Đang gửi yêu cầu..'

    http.post(`${_URL_MAIN}/Seller/add`, seller)
    .then(rs => {
        if(rs.status == 200) {
            Swal.fire('Success', rs.data.msg, 'success')
            .then(() => window.location.reload())
        } else {
            Swal.fire('Message', rs.data, 'info')
            .then(() => window.location.reload())
        }
    })
}

http.get(`${_URL_MAIN}/ShopCategories/get/level/0`)
.then(data => {
    let html = ''
    data.forEach(e => {
        html += `<option value="${e.id}">${e.category_name}</option>`
    })

    shopCategory.innerHTML = html
})

function cancelOrder(id) {

    Swal.fire({
        title: "Xác nhận hủy đơn hàng",
        html: "Cảm ơn bạn đã đặt hàng.<br> Có thể cho chúng tôi biết lý do vì sao bạn muốn hủy không?",
        icon: "info",
        input: "text", 
        inputPlaceholder: 'Mua nhằm, Đổi sản phẩm, Có việc gấp..',
        showCancelButton: true,
        confirmButtonText: 'Hủy đơn hàng',
        cancelButtonText: 'Thôi mua luôn',
        didOpen: () => {
          Swal.getInput().select()
        },
        inputValidator: (value) => {
            if (value) {
                if(value.replaceAll(' ', '').length < 3) {
                    return
                }

                http.put(`${_URL_MAIN}/Orders/cancel/${id}`, {content: value})
                .then(data => {
                    if(data.status == 200) {
                        Swal.fire('Success', data.data, 'success')
                        .then(rs => window.location.reload())
                    } else {
                        Swal.fire('Message', data.data, 'info')
                    }
                })
            }
        }
    })
    
}

function onChangeFile (e, output = '', outputSrc = '') {
    let outputDOM = output == '' ? false : _$(output);
    let outputSrcDOM = outputSrc == '' ? false : _$(outputSrc);
    
    if(outputDOM) {
        uploadToImbb(e, outputDOM, outputSrcDOM, (response) => {
            if(response.status_code == 200) {

                let imgReview = _$(outputSrc)
                if(imgReview) {
                    imgReview.src = response.image.display_url
                    Swal.fire('Message', 'Upload hình ảnh thành công.', 'success')
                    
                }
            } else {
                Swal.fire('Message', 'Upload thất bại, vui lòng thử lại.', 'info')
            }
        })
    }
}

const avatarImg = _$('.avatar--review')

avatarImg.addEventListener('click', () => {
    _$('#avatar_file').click()
})