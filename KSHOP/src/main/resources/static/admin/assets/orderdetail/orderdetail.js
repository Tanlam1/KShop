let idOrderGHN = '', dataBodyGHN = {}, dataOrder = {}

const loadOrder = (data) => {
    dataOrder = data
    
    if(data.order_status == 'Shop Confirmed' && data.id_order_ghn == null) {
        if(_$('.ghn-btn')) {
            _$('.ghn-btn').style = 'display: block'
        }
    }
    dataBodyGHN =  {
        "payment_type_id": 2,
        "note": data.order_note,
        "content": "Đơn hàng #" + data.id,
        "from_name": data.users.stores.store_name.trim(),
        "from_phone": data.users.phone.trim(),
        "from_address": data.users.address1.trim(),
        "from_ward_name": data.users.ward.trim(),
        "from_district_name": data.users.state.trim(),
        "from_province_name": data.users.city.trim(),
        "required_note": "KHONGCHOXEMHANG",
        "return_name": data.users.stores.store_name.trim(),
        "return_phone": data.users.phone.trim(),
        "return_address": data.users.address1.trim(),
        "return_ward_name": data.users.ward.trim(),
        "return_district_name": data.users.state.trim(),
        "return_province_name": data.users.city.trim(),
        "client_order_code": "",
        "to_name": data.ship_name.trim(),
        "to_phone": data.ship_phone.trim(),
        "to_address": data.ship_address1.trim(),
        "to_ward_name": data.ship_ward.trim(),
        "to_district_name": data.ship_state.trim(),
        "to_province_name": data.ship_city.trim(),
    }

    let wrapOrderDetail = _$('.order-js')
    let storeCancel = ``
    console.log(data.order_status);
    if(data.order_status === 'New') 
        storeCancel = `<a onclick="changeTypeOrder(1)" href="javascript:void(0)" class="theme-color cancel-type-order" data-status="Canceled By Store">Hủy đơn này</a>`
    let html =  /*html*/`
        <div class="card-order-section">
            <div style=" display: flex; gap: 10px; ">
                <h5 class="mb-4">Đơn hàng #${orderId}</h5>
                ${storeCancel}
            </div>
            <ul>
                <li>${getDateCurrStr(data.created_at)}</li>
                <li class="total_item"></li>
                <li class="total_amount"></li>
                <li style="background-color: #123456be; padding: 4px 12px; border-radius: 6px; font-size: 16px; color: #fafafa; text-transform: uppercase;">${checkTypeOrder(data.order_status).current}</li>
            </ul>
        </div>`
    wrapOrderDetail.innerHTML = html
    _$('.discount_price_order').innerHTML = formatVND(data.discount_amount)
    _$('.discount_price_order').setAttribute('data-price', data.discount_amount)
    
    let wrapOrderAll = _$('.orderall-js')
    let html2 =  /*html*/`<div class="row g-4">
        <h4>Tổng quan</h4>
        <ul class="order-details">
            <li>Order ID: ${orderId}</li>
            <li>Ngày tạo: ${getDateCurrStr(data.created_at)}</li>
            <li class="total_amount"></li>
        </ul>

        <h4>Thông tin cửa hàng</h4>
        <ul class="order-details">
            <li class="mt-1">
                <b>${data.users.stores.store_name} | ${data.users.stores.phone}</b>
            </li>
            <li>Địa chỉ: ${data.users.stores.address}</li>
        </ul>

        <h4>Thông tin khách hàng</h4>
        <ul class="order-details">
            <li class="mt-1">
                <b>${data.ship_name} | ${data.ship_phone}</b>
            </li>
            <li>Địa chỉ: ${data.ship_address1}, ${data.ship_ward}, ${data.ship_state}, ${data.ship_city}</li>
        </ul>

        <div class="payment-mode">
            <h4>Phương thức thanh toán</h4>
            <p>${data.paymentTypes.payment_name}</p>
        </div>

        <div class="order-note">
            <h4>Ghi chú</h4>
            <p class="mt-4 mb-4">${data.order_note == '' ? '<em>Không có</em>' : `"${data.order_note}"`}</p>
        </div>

        <div class="delivery-sec">
            <h3>Ngày Giao Hàng Dự Kiến: <span>${getDateCurrStr(dataOrder.shipped_date * 1)}</span>
            </h3>
            <a href="${dataOrder.id_order_ghn != null ? 'https://donhang.ghn.vn/?order_code=' + dataOrder.id_order_ghn : 'javascript:Swal.fire(\'Message\', \'Đơn hàng đang chờ shop xác nhận đơn hàng\', \'info\')'}">Theo dõi đơn hàng</a>
        </div>
    </div>`
    wrapOrderAll.innerHTML = html2

    //handle-type-order
    let typeOrder = _$('.handle-type-order')
    let statusNext = checkTypeOrder(data.order_status)
    typeOrder.innerHTML = statusNext.nextText
    typeOrder.setAttribute('data-status', statusNext.next)
    if(statusNext.next == 'close') typeOrder.remove()
}
const loadOrderDetail = (data) => {
    let wrapOrderDetail = _$('.orderdetail-js')
    let subtotal_order = _$('.subtotal_order')
    let total_item = _$('.total_item')
    let total_amount = _$$('.total_amount')
    let html = ``, subtotal = 0, totalItem = 0, shipPrice = dataOrder.shipping_fee, totalPrice = 0
    let discount = 0
    let weightOrder = 0, heightOrder = 0, lengthOrder = 0, widthOrder = 0
    dataBodyGHN =  {
        ...dataBodyGHN,
        "items": []
    }
    data.forEach(e => {
        
        discount = (e.discount_percentage/100) * (e.unit_price * e.quantity) + e.discount_amout
        unitPrice = (e.unit_price * e.quantity) - discount
        subtotal += unitPrice
        totalItem += e.quantity
        
        html += /*html*/`<tr class="table-order" style="border-bottom: 1px solid #12345642;">
            <td>
                <a href="javascript:void(0)">
                    <img src="${!!e.products.image.match('http|https') ? e.products.image : '/'+e.products.image}"
                        class="img-fluid blur-up lazyload" alt="${e.order_detail_name != null && e.order_detail_name != '' ? e.order_detail_name : e.products.product_name}">
                </a>
            </td>
            <td>
                <h5>${e.order_detail_name != null && e.order_detail_name != '' ? e.order_detail_name : e.products.product_name}</h5>
            </td>
            <td>
                <h5>x ${e.quantity}</h5>
            </td>
            <td>
                <p>Giá</p>
                <h5>${formatVND(unitPrice)}</h5>
            </td>
        </tr>`

        weightOrder += e.products.weight
        lengthOrder += e.products.length
        heightOrder += e.products.height
        widthOrder += e.products.width

        dataBodyGHN.items.push({
            "name": e.products.product_name,
            "code": e.products.id + '',
            "quantity": e.quantity,
            "price": unitPrice,
            "weight": Math.ceil(e.products.weight),
            "length": Math.ceil(e.products.length),
            "width": Math.ceil(e.products.width),
            "height": Math.ceil(e.products.height)
        })
    })
    totalPrice = subtotal + shipPrice  - (_$('.discount_price_order').getAttribute('data-price')) * 1

    wrapOrderDetail.innerHTML = html
    subtotal_order.innerHTML = formatVND(subtotal)
    total_item.innerHTML = totalItem + ' sản phẩm'
    _$('.shipping_price').innerHTML = formatVND(shipPrice) + ''

    total_amount.forEach(e => {
        e.innerHTML = 'Tổng tiền: ' + formatVND(totalPrice)
    })
    _$('.total_price_order').innerHTML = formatVND(totalPrice)


    let cod_amount = totalPrice
    if(data[0].orders.paymentTypes.id == 2) cod_amount = 0
    dataBodyGHN =  {
        ...dataBodyGHN,
        "cod_amount": cod_amount,
        "weight": Math.ceil(weightOrder),// chưa ổn
        "length": Math.ceil(lengthOrder),// chưa ổn
        "width": Math.ceil(widthOrder),// chưa ổn
        "height": Math.ceil(heightOrder),// chưa ổn
        "pick_station_id": null,
        "deliver_station_id": null,
        "insurance_value": totalPrice > 5000000 ? 5000000 : totalPrice, // chưa
        "service_id": 53320,
        "service_type_id": 2,
        "coupon": null,
        "pick_shift": null,
        "pickup_time": null
    }
    
}

var orderId = _$('#orderId').value

http.get(`${_URL_MAIN}/Orders/get/${orderId}`)
    .then(data => loadOrder(data))
    .catch(err => console.log(err))

http.get(`${_URL_MAIN}/OrderDetail/get/orderid/${orderId}`)
    .then(data => loadOrderDetail(data))
    .catch(err => console.log(err))


function changeTypeOrder(flag = 0) {
    //type : 0 new -> xác nhận còn hàng & tạo đơn
    let type = _$('.handle-type-order')
    Swal.fire({
        title: "Message",
        text: "Xác nhận cập nhật trạng thái đơn hàng",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((oke) => {
        if (oke.isConfirmed) {
            let status = type.getAttribute('data-status')
            if(flag == 1) {
                status = 'Canceled By Store'
            }
            let data = {status}
            http.post(`${_URL_MAIN}/Orders/updatestatus/${orderId}`, data)
            .then(data => {
                if(data.status == 200) {
                    Swal.fire('Message', data.data, 'success')
                    .then(rsp => window.location.reload())
                } else if(data.status == 19) {
                    Swal.fire('Error', data.data, 'error')
                } else {
                    Swal.fire('Message', data.data, 'info')
                }
            })
        }
    })
}

function checkTypeOrder(status) {
    let data = {}
    status = status.toLocaleLowerCase()
    switch(status) {
        case 'new': {
            data.nextText = 'Xác nhận còn hàng'
            data.current = 'Đơn hàng mới'
            data.next = 'Shop Confirmed'
            break
        }
        case 'shop confirmed': {
            data.nextText = 'Xác nhận đã giao hàng'
            data.current = 'Đơn hàng đã xác nhận'
            data.next = 'Shipped'
            break
        }
        case 'shipped': {
            data.nextText = 'Xác nhận hoàn tất đơn hàng'
            data.current = 'Đã giao hàng'
            data.next = 'Complete'
            break
        }
        case 'complete': {
            data.nextText = 'Đóng đơn hàng'
            data.current = 'Đơn hàng đã hoàn tất'
            data.next = 'close'
            break
        }
        case 'close': {
            data.nextText = ''
            data.current = 'Quản trị viên đã xác nhận và đóng đơn hàng'
            data.next = 'close'
            _$('.handle-type-order').remove()
            break
        }
        case 'cancel': {
            data.nextText = ''
            data.current = 'Đơn hàng đã hủy'
            data.next = 'cancel'
            _$('.handle-type-order').remove()
            break
        }
        case 'canceled by store': {
            data.nextText = ''
            data.current = 'Store đã hủy đơn này'
            data.next = 'canceled by store'
            break
        }
    }

    return data
}
function createOrderGHN() {
    
    if(_$('.ghn-btn')) {
        _$('.ghn-btn').remove()
    }
    let header = {
        "token": "68c0df7b-601d-11ed-8008-c673db1cbf27",
        "shop_id": "3435595",
    }
    
    let url = 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create'

    // idOrderGHN = 'GAHRU3K7'
    //tạo đơn lên giao hàng nhanh

    http.post(`${url}`, dataBodyGHN, header)
    .then(data => {
        if(data.code == 200) {
            
            console.log(data)
            idOrderGHN = data.data.order_code
            updateIdOrderGHN()
        } else {
            Swal.fire('Message', data.message, 'info')
        }
    })

    // updateIdOrderGHN()
}

function updateIdOrderGHN() {
    let data = {
        id: orderId,
        id_order_ghn : idOrderGHN
    }

    let url = `${_URL_MAIN}/Orders/updateGHNOrderID`

    http.put(`${url}`, data)
    .then(response => {
        if(response.status == 200) {
            Swal.fire('Message', response.data, 'success')
            .then(rsp => window.location.reload())
        } else if(response.status == 19) {
            Swal.fire('Error', response.data, 'error')
        } else {
            Swal.fire('Message', response.data, 'info')
        }
    })
}