let dataCart = [], dataNewOrder = [], listOrders = [], ppIsShow = false
let dataCartGroup = [], timestampShip = [], discountOrders = []
let maHuyen = 0, maXa = ''
let shipping_fee_Root = 0
let waitMathFee = true

http.get(`${_URL_MAIN}/ShopUsersCart/get/CustomerId/${idCustomer.value}`)
.then(response => {
    response.forEach(e => {
        if(e._checked) {
            dataCart.push(e)
        }
    })

})

let runApp = setInterval(() => {
    
    if(dataCart.length > 0) {
        clearInterval(runApp)
        let checkQuantityItem = checkQuantity()
        if(!checkQuantityItem.result) {
            Swal.fire({
                title: "Thông báo",
                text: "Số lượng còn lại của sản phẩm không đủ, vui lòng thử lại.",
                icon: "info",
                showCancelButton: true,
                cancelButtonColor: '#d33',
                cancelButtonText: 'Xem lại sản phẩm',
                confirmButtonText: 'Về giỏ hàng'
            }).then((quantityCheck) => {
                if (quantityCheck.isConfirmed) {
                    
                    window.location.href = '/cart'
                } else {
                    
                    window.location.href = checkQuantityItem.url
                }
            })
        }
        loadTableProduct()
        loadCartList(dataCart)
        loadPayment()
        setDiscountOrder()
    }
    
}, 100)

function checkQuantity () {
    let result = true, url = ''
    let firstId = dataCart[0].product.id
    let quantityItem = 0
    dataCart.forEach(e => {
        if(firstId != e.product.id){
            firstId = e.product.id
            quantityItem = 0
        }
        quantityItem += e.quantity
        if(quantityItem > e.product.quantity) {
            result = false
            url = '/product/' + e.product.product_code + '-' + e.product.id
        }
        
    })

    return {
        result, url
    }
}

function setDiscountOrder() {
    http.get(`${_URL_MAIN}/ShopCustomerVouchers/get/used/${idCustomer.value}`)
    .then(data => {
        
        dataCartGroup.forEach((eCarts) => {
            let dis_amounts = 0
            eCarts.data.forEach((eCart, i) => {
                data.forEach(e => {
                    
                    if(e.vouchers.use_by == eCart.product.users.id) {
                        let max_disa = e.vouchers.max_disa
                        let min_pro = e.vouchers.min_pro
                        let discount = 0
                        let canUse = checkMinPriceOrder(e.vouchers.use_by, min_pro)
                        if(e.vouchers._fixed) {
                            discount = e.vouchers.discount_amount
                        } else {
                            discount = (dataCart[i].product.list_price * dataCart[i].quantity) * (e.vouchers.discount_amount / 100)
                        }
                        if(max_disa > 0) {
                            discount = discount > max_disa ? max_disa : discount
                        }
                        dis_amounts += discount
                        if(!canUse.status) {
                            dis_amounts -= discount
                        }
                    }

                    
                })
            }) 
            discountOrders.push(dis_amounts)
        })
    }) 
}

function orderNow(typePM = -1) {
    
    let checkOrder = orderValid(typePM)
    if(checkOrder) {
        orderCreate()
    }
}

function orderValid(typePM = -1) {
    dataNewOrder = []
    let iniIdStore = dataCart[0].product.users.id
    listOrders.push(iniIdStore)
    dataCart.forEach(e => {
        if(iniIdStore != e.product.users.id) {
            iniIdStore = e.product.users.id
            listOrders.push(iniIdStore)
        }
    })

    listOrders = [...new Set(listOrders)]

    let payment_type = _$('.card-body.expanded')    
    if(!payment_type) {
        Swal.fire('Message', 'Vui lòng chọn phương thức thanh toán', 'info')
        return false
    }
    if(waitMathFee) {
        Swal.fire('Message', 'Vui lòng đợi tính toán phí vận chuyển', 'info')
        return false

    }

    let ship_name = _$('.customer_name').innerText
    let ship_phone = _$('.customer_phone').innerText
    let ship_address1 = (_$('.customer_address').innerText).split('\,')[0]
    let ship_address2 = null
    let ship_city = (_$('.customer_city').innerText).trim()
    let ship_state = _$('#customer_state').value
    let ship_ward = _$('.customer_ward').innerText
    let ship_postal_code = _$('#customer_postal_code').value
    let ship_country = _$('.customer_country').innerText
    let payment_type_id = payment_type.getAttribute('data-id')
    let order_status = 'New'
    let order_note = _$('#order-notes').value
    let otherAddress = _$('#shipping-toggle')

    if(otherAddress.classList.contains('checked')) {
        let ip_ship_name = _$('#ship_name')
        let ip_ship_phone = _$('#ship_phone')
        let ip_ship_address1 = _$('#ship_address1')
        let ip_ship_ward = _$('#ship_ward')
        let ip_ship_state = _$('#ship_state')
        let ip_ship_city = _$('#ship_city')
        let ip_ship_postal_code = _$('#ship_postal_code')

        if(!ip_ship_name || ip_ship_name.value.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng nhập họ tên người nhận hàng', 'info')
            return false
        }

        if(!ip_ship_phone || ip_ship_phone.value.replaceAll(' ', '') == '' || !ip_ship_phone.value.replaceAll(' ', '').match(/^\d{10}$/)) {
            Swal.fire('Message', 'Vui lòng nhập số điện thoại người nhận hàng', 'info')
            return false
        }

        if(!ip_ship_address1 || ip_ship_address1.value.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng nhập địa chỉ nhận hàng', 'info')
            return false
        }

        if(!ip_ship_ward || ip_ship_ward.options[ip_ship_ward.selectedIndex].text.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng chọn Phường / Xã của bạn', 'info')
            return false
        }

        if(!ip_ship_state || ip_ship_state.options[ip_ship_state.selectedIndex].text.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng chọn Quận / Huyện của bạn', 'info')
            return false
        }

        if(!ip_ship_city || ip_ship_city.options[ip_ship_city.selectedIndex].text.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng chọn Tỉnh / Thành Phố của bạn', 'info')
            return false
        }

        if(!ip_ship_postal_code || ip_ship_postal_code.value.replaceAll(' ', '') == '') {
            Swal.fire('Message', 'Vui lòng nhập mã bưu điện của bạn', 'info')
            return false
        }

        ship_name = ip_ship_name.value
        ship_phone = ip_ship_phone.value
        ship_address1 = ip_ship_address1.value
        ship_city = ip_ship_city.options[ip_ship_city.selectedIndex].text
        ship_postal_code = ip_ship_postal_code.value
        ship_state = ip_ship_state.options[ip_ship_state.selectedIndex].text
        ship_ward = ip_ship_ward.options[ip_ship_ward.selectedIndex].text
    }

    if(typePM != -1) {
        payment_type_id = typePM
        _$$('.card-header a')[3]?.click()
    }

    listOrders.forEach((idStore, i) => {           

        let timestamp_ship = ''
        timestampShip.forEach(e => {
            if(e.package == i + 1) {
                timestamp_ship = e.timestamp
            }
        })

        dataNewOrder.push({
            users: {
                id : idStore
            },
            customers: {
                id: idCustomer.value
            },
            ship_name,
            ship_phone,
            shipped_date: timestamp_ship,
            ship_address1: ship_address1,
            ship_address2,
            ship_city,
            ship_state,
            ship_ward,
            ship_postal_code,
            ship_country,
            shipping_fee: dataCartGroup[i].shipping_fee,
            paymentTypes: {
                id: payment_type_id
            },
            order_status,
            order_note,
            discount_amount: discountOrders[i]
        })
    })

    let vnpayStorage = JSON.parse(localStorage.getItem("address-vnpay"))
    if(vnpayStorage) {
        console.log(vnpayStorage);
        dataNewOrder = vnpayStorage.data_new_order
    }

    console.log(dataNewOrder);
    if(typePM != -1) {
        localStorage.setItem("address-vnpay", false)
    }

    // nếu paypal thì check ở đây trước, thanh toán xong thì chạy tiếp
    if(payment_type_id == 2 && typePM == -1) {
        if(!ppIsShow) {
            setTimeout(paypalStart, 1000)
            ppIsShow = true
        }
        Swal.fire('Message', 'Bạn đang chọn phương thức thanh toán bằng paypal, vui lòng thanh toán để hoàn tất đơn hàng', 'info')
        
        return false
    }

    if(payment_type_id == 4 && typePM == -1) {

        VNPayHandle()
        return false
    }

    return true

}

function VNPayHandle() {
    //check xem người dùng chọn address nào
    if(_$('#shipping-toggle').classList.contains('checked')) {
        
        let dataLocal = {
            
            data_new_order: dataNewOrder,
        }
        localStorage.setItem("address-vnpay", JSON.stringify(dataLocal))
    } else {
        localStorage.setItem("address-vnpay", false)
    }

    let tongTien = _$('.total_price').getAttribute('data-price') * 100

    const url = `${_URL_MAIN}/vnpay/createPayment?ordertype=topup&amount=${tongTien}&vnp_OrderInfo=order_info&bankcode=&language=vn`

    http.post(`${url}`)
    .then(data => {
        if(data.status == 200) {

            window.location.href = data.data.url_redirect
        } else {
            Swal.fire('Message', data.data, 'info')
        }
    })

    
    
}

function orderCreate() {

    if(dataNewOrder.length == 0) {
        return false
    }
    let orderIdRS = 0
    let arrayOrderDetailName = []
    _$$('.product-name.table-in-cart a').forEach(e => {
        arrayOrderDetailName.push(e.innerHTML.trim())
    })

    http.post(`${_URL_MAIN}/Orders/adds`, dataNewOrder)
    .then(data => {
        let indexOrderDetailName = 0
        data.forEach(order => {
            if(order.id) {
                let idOrder = order.id
                orderIdRS = idOrder
                dataCartGroup.forEach(cartGroup => {
                    cartGroup.data.forEach(e => {
                        
                        if(e.product.users.id == order.users.id) {
                            let dis_percent = 0, dis_amount = 0
                            if(e._discount) {
                                let max_disa = e.shopProductVouchers.vouchers.max_disa
                                
                                let min_pro = e.shopProductVouchers.vouchers.min_pro
                                let canUse = checkMinPriceOrder(e.shopProductVouchers.vouchers.use_by, min_pro)
                                if(e.shopProductVouchers.vouchers._fixed) {
                                    dis_amount = e.shopProductVouchers.vouchers.discount_amount * e.quantity
                                } else {
                                    dis_amount = (e.product.list_price * e.quantity) * (e.shopProductVouchers.vouchers.discount_amount/100)
                                }
                                dis_amount = dis_amount > max_disa ? max_disa : dis_amount
    
                                if(!canUse.status) {
                                    dis_amount = 0
                                }
                            }
                            let dataNewOrderDetail = {
                                orders: {
                                    id: idOrder
                                },
                                products: {
                                    id: e.product.id
                                },
                                quantity: e.quantity,
                                unit_price: e.product.list_price,
                                discount_percentage: dis_percent,
                                discount_amout: dis_amount,
                                order_detail_name: arrayOrderDetailName[indexOrderDetailName]
                            }
                            indexOrderDetailName++
                    
                            //post create order detail
                            http.post(`${_URL_MAIN}/OrderDetail/add`, dataNewOrderDetail)
                        }
                    })
                })
                
            }
        })
    })
    
    //remove discount customer
    http.delete(`${_URL_MAIN}/ShopCustomerVouchers/delete/customer/${idCustomer.value}`)
    //remove cart checkout
    http.delete(`${_URL_MAIN}/ShopUsersCart/delete/customerid/${idCustomer.value}`)
    .then(rsp => {
        setTimeout(() => {
            window.location.href = `/order/${orderIdRS}`
        }, 5000)
    })
    Swal.fire('Thanh toán thành công', 'Trang sẽ tự động reload sau 5s.<br> Bạn vui lòng chờ và không tải lại trang này.', 'warning')
}

function loadTableProduct () {
    let wrapProductCheckout = _$('.list-checkout')

    let listCheckout = ``, discount = 0
    dataCart.forEach(e => {
        if(e._discount) {
            let max_disa = e.shopProductVouchers.vouchers.max_disa
            if(e.shopProductVouchers.vouchers._fixed) {
                discount = e.shopProductVouchers.vouchers.discount_amount
            } else {
                discount = e.product.list_price * (e.shopProductVouchers.vouchers.discount_amount/100)
            }
            discount = (discount > max_disa) ? max_disa : discount
        }
        listCheckout += `<tr class="bb-no">
            <td class="product-name">${e.product.product_name} <i class="fas fa-times"></i> <span class="product-quantity">${e.quantity}</span></td>
            <td class="product-total">
                ${formatVND((e.quantity * e.product.list_price) - discount)}<br>
            </td>
        </tr>`
    })
    listCheckout += `<tr class="cart-subtotal bb-no">
        <td>
            <b>Tạm tính</b>
        </td>
        <td class="sub_total">
            <b>$100.00</b>
        </td>
    </tr>`
    wrapProductCheckout.innerHTML = listCheckout
}

function loadCartList (data) {
    let wrap = _$(".cart-list")
    let html = ''
    let totalItems = 0
    let arrayTypeItem = []
    data = data.reverse()
    let nData = []
    data.forEach(e => {
        let id = e.product.users.id
        data.forEach(item => {
            if(item.product.users.id == id) {
                
                nData.push(item)
            }
        })
    })
    
    data = [...new Set(nData)]
    let firstId = data[0].product.users.id
    let ncData = []
    let item = []
    data.forEach(e => {
        if(firstId != e.product.users.id) {
            ncData.push({
                data: item
            })
            firstId = e.product.users.id
            item = []
        }
        item.push(e)
    })
    ncData.push({
        data: item
    })

    dataCartGroup = ncData
    
    ncData.forEach(eNc => {
        let newClassStore = `<div class="ngaygiao">
            Đang tính toán phí ship và ngày giao hàng..
        </div>`
        
        eNc.data.forEach(e => {
            
            if(e._checked) totalItems += e.quantity
            let htmlDiscount = '', discount = 0
    
            if(e._discount) {
                let max_disa = e.shopProductVouchers.vouchers.max_disa
                if(e.shopProductVouchers.vouchers._fixed) {
                    discount = e.shopProductVouchers.vouchers.discount_amount
                    htmlDiscount = `<small class="product-voucher">-${formatVND(discount)} <span>${formatVND(e.product.list_price * e.quantity)}</span></small>`
                } else {
                    discount = e.product.list_price * (e.shopProductVouchers.vouchers.discount_amount/100)
                    htmlDiscount = `<small class="product-voucher">-${e.shopProductVouchers.vouchers.discount_amount}% <span>${formatVND(e.product.list_price * e.quantity)}</span></small>`
                }
                discount = (discount > max_disa) ? max_disa : discount
            }
            if(firstId != e.product.users.id) {
                firstId = e.product.users.id
            }

            html += /*html */`${newClassStore}<tr style="position: relative;">
                <td class="product-name table-in-cart" style=" display: flex; align-items: center; margin-top: 0; ">
                    <div class="p-relative ml-4 mr-4">
                        <figure class="img table-in-cart">
                            <img src="${e.product.image}" alt="${e.product.product_name}" width="300" height="338">
                        </figure>
                    </div>
                    <a href="/product/${e.product.product_code}-${e.product.id}">${e.product.product_name}</a>
                </td>
                <td class="product-price">
                    <span class="amount">${formatVND(e.product.list_price)}</span> x ${e.quantity}
                </td>
                <td class="product-price" style="text-align: center;">
                    <span class="amount">${formatVND((e.quantity * e.product.list_price) - discount)}</span><br>
                    ${htmlDiscount}
                </td>
            </tr>`  
            arrayTypeItem.push(e.type_item_id)
            newClassStore = ''
        })
    })
    wrap.innerHTML = html
    genderTotal(data)
    getTypeItem(arrayTypeItem)
}

const genderDiscount = (subtotal) => {
    let wrap = _$('.discount_total')
    let wrapTotal = _$('.total_price')
    let wrapVoucherList = _$('.voucher_list')
    _$('.discount').innerHTML = 'Giảm giá'
    _$('.discount_hr').style = 'display: block'
    let total_discount = 0
    let discount = 0
    let htmlDiscountDisplay = ''
    http.get(`${_URL_MAIN}/ShopCustomerVouchers/get/used/${idCustomer.value}`)
    .then(data => {
        data.forEach(e => {
            let max_disa = e.vouchers.max_disa
            let min_pro = e.vouchers.min_pro
            let canUse = checkMinPriceOrder(e.vouchers.use_by, min_pro)
            let htmlDiscDisplay = `(Giảm ${e.vouchers._fixed ? formatVND(e.vouchers.discount_amount) : e.vouchers.discount_amount + '% - tối đa ' + formatVND(max_disa)})`
            if(e.vouchers._fixed) {
                discount = e.vouchers.discount_amount
            } else {
                discount = subtotal * (e.vouchers.discount_amount / 100)
            }
            if(max_disa > 0) {
                discount = discount > max_disa ? max_disa : discount
            }
            total_discount += discount
            if(!canUse.status) {
                htmlDiscDisplay = `(Mua thêm ${formatVND(canUse.value)}, để giảm ${formatVND(discount)} bạn nhé.)`
                total_discount -= discount
            }
            htmlDiscountDisplay += `<div class="voucher_item">
                <hr class="divider">
                <div class="cart-subtotal d-flex align-items-center justify-content-between p-relative">
                    <label class="ls-25"><br>${e.vouchers.voucher_name}<span > ${stripHtml(e.vouchers.description)} </span><p class="mb-0"> ${htmlDiscDisplay}</p></label>
                    
                    <button onclick="removeVoucher(${e.vouchers.id})" class="btn btn-close">x</button>
                </div>
            </div>`
        })
        wrapVoucherList.innerHTML = htmlDiscountDisplay
        wrap.innerHTML = formatVND(total_discount)
        wrap.setAttribute('data-total-discount', (total_discount))
        wrapTotal.innerHTML = formatVND(subtotal - total_discount)
        wrapTotal.setAttribute('data-price', (subtotal - total_discount))
    })        
}

function genderTotal(data) {
    let wrapSubTotal = _$('.sub_total')
    let subtotal = 0
    data.forEach(e => {

        let discount = 0
        if(e._discount) {
            let max_disa = e.shopProductVouchers.vouchers.max_disa
            if(e.shopProductVouchers.vouchers._fixed) {
                discount = e.shopProductVouchers.vouchers.discount_amount
            } else {
                discount = e.product.list_price * (e.shopProductVouchers.vouchers.discount_amount/100)
            }
            discount = (discount > max_disa) ? max_disa : discount
        }
        subtotal += e.quantity * e.product.list_price - discount
    })
    wrapSubTotal.innerHTML = formatVND(subtotal)
    wrapSubTotal.setAttribute('data-price', subtotal)
    if(subtotal > 0) genderDiscount(subtotal) 
}

function loadPayment() {
    let html = ``
    let wrap = _$('.payment-accordion')
    
    http.get(`${_URL_MAIN}/PaymentType/get`)
    .then(data => {
        data.forEach((e, i) => {
            html += `<div class="card">
                <div class="card-header">
                    <a href="#${e.payment_code}" class=" ${i == 0 ? 'collapse' : 'expand'}">${e.payment_name}</a>
                </div>
                <div data-id="${e.id}" id="${e.payment_code}" class="card-body ${i == 0 ? 'expanded' : 'collapsed'}">
                    <p class="mb-0">
                        ${e.description}
                    </p>
                    ${i == 1 ? '<div id="paypal-button-container"></div>' : ''}
                </div>
            </div>`
        })
        wrap.innerHTML = html
        
    })
}

function convertCurrency() {
    let tongTien = _$('.total_price').getAttribute('data-price')
    let rate = 22000
    

    return Math.round((tongTien/rate) * 100) / 100
}

function paypalStart () {

    paypal.Buttons({
        style: {
          color: "blue",
          shape: "rect",
          layout: "vertical"
        },
    
        // set up the transaction
        createOrder: (data, actions) => {
            const createOrderPayload = {
                purchase_units: [{
                    amount: {
                        value: convertCurrency()
                    }
                }]
            };
    
            return actions.order.create(createOrderPayload)
        },
    
        onApprove: (data, actions) => {
            const captureOrderHandler = (details) => {
                orderCreate()
            };
    
            return actions.order.capture().then(captureOrderHandler)
        },
    
        onCancel: (data) => {
            console.log('Cancel payment')
        },
    
        onError: (err) => console.error(err)

    }).render("#paypal-button-container").catch((err) => console.error(err))
}

renderCity('#ship_city')
function handleFeeOtherAddress () {
    let otherAddress = _$('#shipping-toggle')

    if(otherAddress.classList.contains('checked')) {
        otherAddress.classList.remove('checked')
        shipFee()
    } else {
        otherAddress.classList.add('checked')
        shipFee()
    }
}
function shipFee() {
    
    let tinhText = _$('.customer_city').innerText.trim()
    let huyenText = _$('.customer_state').innerText.trim()
    let xaText = _$('.customer_ward').innerText.trim()
    let maTinh = _$(`option[data-text="${tinhText}"]`).value*1

    let otherAddress = _$('#shipping-toggle')

    if(otherAddress.classList.contains('checked')) {
        let ward = _$('#ship_ward')
        let state = _$('#ship_state')
        let city = _$('#ship_city')
        
        if(city.value.replaceAll(' ', '').length > 0 && state.value.replaceAll(' ', '').length > 0 && ward.value.replaceAll(' ', '').length > 0) {
            maTinh = city.value * 1
            maHuyen = state.value * 1
            maXa = ward.value
            groupOrder()
        }
        
    } else {
        getDistrict(maTinh, huyenText)

        let waitDistrictNguoiNhan = setInterval(() => {
            if(dataDistrictRoot.DistrictName == huyenText) {
                clearInterval(waitDistrictNguoiNhan)
                continueWard(dataDistrictRoot.DistrictID)
            }
        }, 100)

        function continueWard(DistrictID) {
            maHuyen = DistrictID

            getWard(maHuyen, xaText)
            let waitWardNguoiNhan = setInterval(() => {
                if(dataWardRoot.WardName == xaText) {
                    clearInterval(waitWardNguoiNhan)

                    maXa = dataWardRoot.WardCode
                    groupOrder()
                }
            }, 100)
        }
    }
}

let feeHandle = setInterval(() => {
    if(_$('#ship_city option') != null) {
        clearInterval(feeHandle)
        shipFee()        
    }
}, 100)

var groupOrder = () => {
    let handlGroupOrder = setInterval(() => {
        if(dataCartGroup.length > 0) {
            clearInterval(handlGroupOrder)
            shipping_fee_Root = 0
            dataCartGroup.forEach((e, i) => {
                let timeGiao = '', giaShip = 0
                let ngayGiao = _$$('.ngaygiao')
                let height = 0, length = 0, weight = 0, width = 0
                e.data.forEach(item => {
                    height += Math.round(item.product.height)
                    length += Math.round(item.product.length)
                    weight += Math.round(item.product.weight)
                    width += Math.round(item.product.width)
                })

                let maTinh = _$(`option[data-text="${e.data[0].product.users.city.replaceAll('Tp', '').replaceAll('tp', '').replaceAll('TP', '').trim()}"]`).value *1
                let maHuyenFrom = 0, maXaFrom = ''
                let huyenTextFrom = e.data[0].product.users.state
                let xaTextFrom = e.data[0].product.users.ward
                let data = {
                    "province_id": maTinh
                }
                let header = {
                    token: '68c0df7b-601d-11ed-8008-c673db1cbf27'
                }
                let url = `https://online-gateway.ghn.vn/shiip/public-api/master-data/district`
            
                http.post(url, data, header)
                .then(data_district => {
                    data_district.data.forEach(e => {
                        if(e.DistrictName == huyenTextFrom) {
                            maHuyenFrom = e.DistrictID
                            let data = {
                                "district_id": maHuyenFrom * 1
                            }
                            let url = `https://online-gateway.ghn.vn/shiip/public-api/master-data/ward`
            
                            http.post(url, data, header)
                            .then(data_ward => {
                                timestampShip = []
                                data_ward.data.forEach(e => {
                                    if(e.WardName == xaTextFrom) {
                                        maXaFrom = e.WardCode
            
                                        let data = {
                                            "service_type_id": 2,
                                            "from_district_id": maHuyenFrom,
                                            "to_district_id": maHuyen,
                                            "to_ward_code": maXa,
                                            "height": height,
                                            "length": length,
                                            "weight": weight,
                                            "width": width
                                        }
                                        header = {
                                            token: '68c0df7b-601d-11ed-8008-c673db1cbf27',
                                            shop_id : '3435595'
                                        }
                        
                                        http.post('https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee', data, header)
                                        .then(data_fee => {
                                            giaShip = Math.round((data_fee.data.total / 1000)) * 1000
                                            shipping_fee_Root += giaShip
                                            dataCartGroup[i].shipping_fee = giaShip
                                            
        _$('.custom-control-label span').innerHTML = formatVND(shipping_fee_Root)
        _$('.total_price').setAttribute('data-price', _$('.sub_total').getAttribute('data-price') * 1 + shipping_fee_Root - _$('.discount_total').getAttribute('data-total-discount') * 1)
        _$('.total_price').innerHTML = formatVND(_$('.sub_total').getAttribute('data-price') * 1 + shipping_fee_Root - _$('.discount_total').getAttribute('data-total-discount') * 1)
                                            dataNgayGiaoHang = {
                                                "from_district_id": maHuyenFrom,
                                                "from_ward_code": maXaFrom,
                                                "to_district_id": maHuyen,
                                                "to_ward_code": maXa,
                                                "service_id": 53320
                                            }
                                            http.post('https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/leadtime', dataNgayGiaoHang, header)
                                            .then(data_leadtime => {
                                                
                                                timeGiao = data_leadtime.data.leadtime
                                                timestampShip.push({
                                                    package : i + 1,
                                                    timestamp: timeGiao + '000'
                                                })
                                                waitMathFee = false
                                                _$('#waitMathFee').value = false
                                    ngayGiao[i].innerHTML = /*html */`
                                        <div class="package-title">
                                            <img width="24" height="24" alt="icon" src="/images/7094a85d0b6d299f30ed89b03511deb9.png">Gói ${i + 1}: Giao vào ${getDateVN(timeGiao + '000')}
                                        </div> 
                                        <div class="package-summary">
                                            <div class="info-ship-fee">
                                                <span class="method-logo">
                                                ${fast_svg}
                                                </span>
                                                <span class="method-text" style="font-size: 12px; line-height: 16px;"> 
                                                Giao Hàng Tiết Kiệm</span>
                                            </div>
                                            <div class="price-ship-fee">
                                                <span class="original-fee">
                                                ${formatVND(giaShip + 10000)}
                                                </span>
                                                <span class="current-fee">
                                                ${formatVND(giaShip)}
                                                </span>
                                            </div>
                                        </div>
                                        <div class="package-info-ship">
                                            ${ship_svg}
                                            <div>
                                                <span class="fulfillment-text">
                                                    Được giao bởi Giao Hàng Nhanh
                                                </span>
                                            </div>
                                        </div>
                                        `
                                            })
                                        })
                                    }
                                })
                            })
                            
                        }
                    })
                })
            })
        }
    }, 100)
}