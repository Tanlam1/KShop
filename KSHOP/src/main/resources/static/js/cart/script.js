let firstLoad = true
let dataCartList = []
if(idCustomer) {

    let htmlActionBtn = `<div class="cart-action">
        <a href="/cart" class="btn btn-dark btn-outline btn-rounded">Xem giỏ hàng</a>
        <a href="/checkout" class="btn btn-primary  btn-rounded">Thanh toán</a>
    </div>`

    var handleCartReview = (data) => {
        dataCartList = data
        if(data.length == 0 && _$('.shop-table.cart-table')) {
            _$('.shop-table.cart-table').innerHTML = 'Giỏ hàng trống, quay lại shop để mua nhe'
            _$('.shop-table.cart-table').style = 'font-size: 18px; text-align: center; height: 150px; line-height: 150px; border-top: 1px solid #eee;'
        }
        if(data.length == 0 && _$('.products')) {
            _$('.products').innerHTML = '<span style=" display: block; text-align: center; margin: 20px; font-size: 12px; ">Giỏ hàng trống</span>'
            _$('.cart-total').innerHTML = ''
            _$('.cart-action').innerHTML = '<a href="/" style="width: 100%;" class="btn btn-dark btn-outline btn-rounded">Shoping ngay!!</a>'
        }
        let htmlCartItem = ``
        let tongTien = 0, totalItems = 0
        let cartProducts = _$('.products')
        let cartAction = _$('.cart-action')
        let cartTotal = _$('.cart-total')
        if(data.length > 0) {
            cartTotal.innerHTML = ``
            cartAction.innerHTML = ``
            cartProducts.innerHTML = ``
        }

        const cartCount = _$('.cart-count')
        if(cartCount) cartCount.innerText = data.length
        let arrayTypeItem = []
        data = data.reverse()
        data.forEach(e => {
            tongTien += e.quantity * e.product.list_price
            totalItems += e.quantity
            htmlCartItem += /*html */`<div class="product product-cart">
                <div class="product-detail table-in-cart-rv">
                    <a href="/product/${e.product.product_code}-${e.product.id}" class="product-name">${e.product.product_name} </a>
                    <div class="price-box">
                        <span class="product-quantity">${e.quantity}</span>
                        <span class="product-price">${formatVND(e.product.list_price)}</span>
                    </div>
                </div>
                <figure class="product-media">
                    <a class="table-in-cart-rv" href="/product/${e.product.product_code}-${e.product.id}">
                        <img src="${!!e.product.image.match('http|https') ? e.product.image : '/'+e.product.image}" alt="product" height="84" width="94">
                    </a>
                </figure>
                <button onclick="removeItemReview(${e.id})" class="btn btn-link btn-close" aria-label="button">
                    <i class="fas fa-times"></i>
                </button>
            </div>`
            arrayTypeItem.push(e.type_item_id)
        })
        if(data.length > 0) {
            cartTotal.innerHTML = `<label>Tổng tiền:</label><span class="price">${formatVND(tongTien)} <small>(${totalItems} items)</small></span>`

            cartAction.innerHTML = htmlActionBtn
            cartProducts.innerHTML = htmlCartItem
            getTypeItemRV(arrayTypeItem)
        }
    }

    function cartReview() {
        http.get(`${_URL_MAIN}/ShopUsersCart/get/CustomerId/${idCustomer.value}`)
            .then(data => handleCartReview(data))
            .catch(err => {
                console.log(err)
                handleCartReview([])
            })
    }
    cartReview()

    var getTypeItem = (typeText) => {
        typeText.forEach((TIT, i) => {
            if(TIT != null && TIT != '') {
                let nameProduct = _$$('.product-name.table-in-cart a')
                let imgProduct = _$$('.img.table-in-cart img')
                JSON.parse(`[${TIT}]`).forEach(e => {
                    http.get(`${_URL_MAIN}/ProductTypeItem/get/${e}`)
                    .then(data => {
                        if(data.image != null && data.image != '') {
                            imgProduct[i].src = data.image
                        }
                        nameProduct[i].innerHTML += ` ${data.name}`
                    })
                })
                
            }
        })

    }

    var getTypeItemRV = (typeText) => {
        typeText.forEach((TIT, i) => {
            if(TIT != null && TIT != '') {
                let nameProduct = _$$('.table-in-cart-rv .product-name')
                let imgProduct = _$$('.table-in-cart-rv img')
                JSON.parse(`[${TIT}]`).forEach(e => {
                    http.get(`${_URL_MAIN}/ProductTypeItem/get/${e}`)
                    .then(data => {
                        if(data.image != null && data.image != '') {
                            imgProduct[i].src = data.image
                        }
                        nameProduct[i].innerHTML += ` ${data.name}`
                    })
                })
                
            }
        })

    }

    const loadCartList = (data) => {
        let wrap = _$(".cart-list")
        let html = ''
        let totalItems = 0
        data = data.reverse()
        let arrayTypeItem = []
        data.forEach(e => {
            if(e._checked) totalItems += e.quantity
            let htmlDiscount = '', discount = 0
            let shopKhuyenMai = `
                <button class="box-product__voucher-btn btn btn-rounded btn-default">Shop khuyến mãi</button>
                <div class="box-product__voucher">
                    <span>Shop khuyến mãi</span>
                    <div class="box-product__voucher-body">
                        <input value="" id="product_voucher_${e.id}_${e.product.id}" type="text" class="form-control mb-4" placeholder="Nhập mã khuyến mãi" required>
                        <button onclick="checkProductVouchers(${e.product.id}, ${e.id})" class="product_voucher_${e.id}_${e.product.id} btn btn-primary btn-outline btn-rounded">Áp dụng</button>
                    </div></div>
            `
            if(e._discount) {
                let max_disa = e.shopProductVouchers.vouchers.max_disa
                let display_voucher = ``
                if(e.shopProductVouchers.vouchers._fixed) {
                    discount = e.shopProductVouchers.vouchers.discount_amount * e.quantity
                    display_voucher = `GIẢM ${formatVND(e.shopProductVouchers.vouchers.discount_amount)} - TỐI ĐA ${formatVND(max_disa)}`
                } else {
                    discount = (e.product.list_price * e.quantity) * (e.shopProductVouchers.vouchers.discount_amount/100)
                    display_voucher = `GIẢM ${e.shopProductVouchers.vouchers.discount_amount}% - TỐI ĐA ${formatVND(max_disa)}`
                }

                discount = discount > max_disa ? max_disa : discount

                if(e.shopProductVouchers.vouchers._fixed) {
                    htmlDiscount = `<small data-amount="${discount}" class="product-voucher">-${formatVND(discount)} <span>${formatVND(e.product.list_price * e.quantity)}</span></small>`
                } else {
                    htmlDiscount = `<small data-amount="${discount}"  class="product-voucher">-${e.shopProductVouchers.vouchers.discount_amount}% <span>${formatVND(e.product.list_price * e.quantity)}</span></small>`
                }
                shopKhuyenMai = `
                    <button class="box-product__voucher-btn btn btn-rounded btn-primary">KM đã áp dụng (1)</button>
                    <div class="box-product__voucher">
                        <span>Khuyến mãi đã áp dụng (1)</span>
                        <p class="mt-1">${display_voucher}</p>
                        <div class="box-product__voucher-body">
                            <input disabled value="${e.shopProductVouchers.vouchers.voucher_name}" id="product_voucher_${e.id}_${e.product.id}" type="text" class="form-control mb-4" placeholder="Nhập mã khuyến mãi" required>
                            <button onclick="unCheckProductVouchers(${e.id})" class="product_voucher_${e.id}_${e.product.id} btn btn-primary btn-block btn-rounded">Bỏ chọn</button>
                        </div></div>
                    `
            }
            html += /*html */`<tr>
                <td class="product-thumbnail">
                    <div class="p-relative">
                        <a href="/product/${e.product.product_code}-${e.product.id}">
                            <figure class="img table-in-cart">
                                <img src="${e.product.image}" alt="${e.product.product_name}" width="300" height="338">
                            </figure>
                        </a>
                        <button onclick="removeItemReview(${e.id}, this)" class="btn btn-close"><i class="fas fa-times"></i></button>
                    </div>
                </td>
                <td style=" margin-top: 0; " class="product-name table-in-cart">
                    <a href="/product/${e.product.product_code}-${e.product.id}">
                        ${e.product.product_name}                 
                    </a>
                </td>
                <td data-price="${e.product.list_price}" class="product-price in-table--cart">
                    <span class="amount">${formatVND(e.product.list_price)}</span>
                    <p class="table__cart-quantity">${e.product.quantity <= 10 ? 'Chỉ còn' : 'Còn'} lại: ${e.product.quantity} sản phẩm</p>    
                </td>
                <td class="product-quantity">
                    <div class="input-group">
                        <input oninput="changeQuantity(${e.id}, -1, ${e.product.id}, this)" id="quantity_${e.id}" class=" form-control" type="number" min="1" max="100000" value="${e.quantity}">
                        <button onclick="changeQuantity(${e.id}, 0, ${e.product.id}, this)" class="quantity-plus w-icon-plus"></button>
                        <button onclick="changeQuantity(${e.id}, 1, ${e.product.id}, this)" class="quantity-minus w-icon-minus"></button>
                    </div>
                </td>
                <td class="product-subtotal">
                    <span class="amount">${formatVND((e.quantity * e.product.list_price) - discount)}</span><br>
                    ${htmlDiscount}
                </td>
                <td>
                    <div class="custom-radio">
                        <input ${e._checked ? 'checked' : ''} type="checkbox" id="chon_${e.id}" class="checked_item custom-control-input" name="choose" value="${e.id}" onchange="checkItem(${e.id})">
                        <label for="chon_${e.id}" class="custom-control-label"></label>
                        ${shopKhuyenMai}
                    </div>
                </td>
            </tr>`  
            _$('.btn-checkout').innerHTML = `Mua Hàng (${totalItems})`
            arrayTypeItem.push(e.type_item_id)
        })
        wrap.innerHTML = html
        genderTotal(data)
        getTypeItem(arrayTypeItem)
    }

    //trả về true nếu min price order trong voucher <= min price order hiện tại
    function checkMinPriceOrder(idSU, min_pro) { // Store user
        let priceOrderOfStore = 0
        dataCartList.forEach(e => {
            if(e.product.users.id == idSU) {
                let discount = 0                
                if(e._discount) {
                    let max_disa = e.shopProductVouchers.vouchers.max_disa
                    if(e.shopProductVouchers.vouchers._fixed) {
                        discount += e.shopProductVouchers.vouchers.discount_amount * e.quantity
                    } else {
                        discount += (e.product.list_price * e.quantity) * (e.shopProductVouchers.vouchers.discount_amount / 100)
                    }
                    discount = (discount > max_disa) ? max_disa : discount
                    
                }
                priceOrderOfStore += e.quantity * e.product.list_price - discount
            }
        })
        
        return {
            status: priceOrderOfStore >= min_pro,
            value: min_pro - priceOrderOfStore
        }
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

                dataCartList.forEach(elm => {
                    if(elm._checked) {
                        if(elm.product.users.id == e.vouchers.use_by) {
                            let max_disa = e.vouchers.max_disa
                            let min_pro = e.vouchers.min_pro
                            let canUse = checkMinPriceOrder(e.vouchers.use_by, min_pro)
                            
                            if(e.vouchers._fixed) {
                                discount = e.vouchers.discount_amount
                            } else {
                                discount = subtotal * (e.vouchers.discount_amount / 100)
                            }
                            if(max_disa > 0) {
                                discount = discount > max_disa ? max_disa : discount
                            }
                            total_discount += discount

                            let htmlDiscDisplay = `(Giảm ${e.vouchers._fixed ? formatVND(e.vouchers.discount_amount) : e.vouchers.discount_amount + '% - tối đa ' + formatVND(max_disa)})`
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
                        }
                    }
                })

                
            })
            wrapVoucherList.innerHTML = htmlDiscountDisplay
            wrap.innerHTML = formatVND(total_discount)
            wrapTotal.innerHTML = formatVND(subtotal - total_discount)
        })        
    }

    function genderTotal(data) {
        let wrapSubTotal = _$('.sub_total')
        let subtotal = 0
        data.forEach(e => {
            if(e._checked) {
                
                let discount = 0
                if(e._discount) {
                    let max_disa = e.shopProductVouchers.vouchers.max_disa
                    if(e.shopProductVouchers.vouchers._fixed) {
                        discount = e.shopProductVouchers.vouchers.discount_amount * e.quantity
                    } else {
                        discount = (e.product.list_price * e.quantity) * (e.shopProductVouchers.vouchers.discount_amount/100)
                    }
                    discount = discount > max_disa ? max_disa : discount
                }

                subtotal += e.quantity * e.product.list_price - discount
            }
        })
        wrapSubTotal.innerHTML = formatVND(subtotal)
        if(subtotal > 0) genderDiscount(subtotal) 
    }

    function cartList(type = 0) {
        http.get(`${_URL_MAIN}/ShopUsersCart/get/CustomerId/${idCustomer.value}`)
            .then(data => {
                dataCartList = data
                let totalItems = 0
                dataCartList.forEach(e => {
                    if(e._checked) totalItems += e.quantity
                })
                _$('.btn-checkout').innerHTML = `Mua Hàng (${totalItems})`
                localStorage.setItem("cartItem", JSON.stringify(data))
                if(type == 1) {
                    genderTotal(data)
                    return
                }
                loadCartList(data)
                // toggle .box-product__voucher
                let productVoucherBtn = _$$('.box-product__voucher-btn')
                let productVoucher = _$$('.box-product__voucher')

                if(productVoucherBtn) {
                    productVoucherBtn.forEach((e, i) => {
                        if(e) {
                            e.addEventListener('click', () => {
                                productVoucher[i].classList.toggle('active')
                            })
                        }
                    })
                }
            })
            .catch(err => console.log(err))
    }
    cartList()
    
}

setTimeout(() => {
    let btn = _$('.checkAllBtn')
    let allCheck = true
    _$$('.checked_item').forEach(e => {
        if(!e.checked) {
            allCheck = false
        }
    })
    if(btn) {
        if(allCheck) {
            btn.setAttribute('data-type', '0')
            btn.innerText = 'Bỏ chọn tất cả'
        } else {
            btn.setAttribute('data-type', '1')
            btn.innerText = 'chọn tất cả'
        }
    }
    
}, 500)

function checkAllCart() {
    let btn = _$('.checkAllBtn')
    let type = btn.getAttribute('data-type')
    
    _$$('.checked_item').forEach(e => {
        if(type == 1) {
            if(!e.checked) e.click() 
            btn.setAttribute('data-type', '0')
            btn.innerText = 'Bỏ chọn tất cả'
        } else {
            if(e.checked) e.click() 
            btn.setAttribute('data-type', '1')
            btn.innerText = 'chọn tất cả'
        }
    })
}

function removeAllCart() {
    Swal.fire({
        title: "Are you sure?",
        text: "Xóa tất sản phẩm đã chọn khỏi giỏ hàng, đúng hong?",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((willBlock) => {
        if (willBlock.isConfirmed) {
            _$$('.checked_item').forEach(e => {
                if(e.checked) {
                    http.delete(`${_URL_MAIN}/ShopUsersCart/delete/${e.value}`)
                }
            })
            window.location.reload()
        }
    })
}

function removeVoucher(voucherId) {
    Swal.fire({
        title: "Message",
        text: "Xóa voucher này khỏi giỏ hàng của bạn",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((willBlock) => {
        if (willBlock.isConfirmed) {
            http.post(`${_URL_MAIN}/ShopCustomerVouchers/get/${idCustomer.value}/${voucherId}/remove`)
            .then(data => {
                if(data.status == 200) {
                    Swal.fire('Message', data.data, 'success')
                    .then(e => window.location.reload())
                } else {
                    Swal.fire('Message', data.data, 'error')
                }
            })
        }
    })
    
}

function checkCustomerVouchers () {
    let id = _$('#customer_voucher_id').value

    if(id.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập voucher của bạn', 'info')
        return
    }

    http.get(`${_URL_MAIN}/ShopVouchers/get/vouchername/${id}`)
    .then(data => {
        let voucherId = data.id
        if(voucherId != null) {
            let hasExp = dateCheck(data.start_date, data.end_date, getDateCurr())
            if(!hasExp) {
                Swal.fire('Message', 'Vouchers này đã hết hạn sử dụng', 'info')
                return
            } 
            if(data.uses >= data.max_uses) {
                Swal.fire('Message', 'Vouchers này đã hết lượt sử dụng', 'info')
                return
            } 

            //Customer voucher
            http.get(`${_URL_MAIN}/ShopCustomerVouchers/get/${idCustomer.value}/${voucherId}`).then(data => {
                if(data.status == 200) {
                    http.post(`${_URL_MAIN}/ShopCustomerVouchers/get/${idCustomer.value}/${voucherId}/apply`)
                    .then(data => {
                        if(data.status == 200) {
                            Swal.fire('Message', data.data, 'success')
                            .then(e => window.location.reload())
                        } else {
                            Swal.fire('Message', data.data, 'error')
                            .then(e => '')
                        }
                    })
                } else {
                    Swal.fire('Message', data.data, 'error').then(e => '')
                }
            }).catch(err => console.log(err)).then(e => '')
            
        } else {
            Swal.fire('Message', 'Không tồn tại vouchers này', 'info')
            .then(e => '')
        }
    })
}

function checkProductVouchers (productId, cartId) {
    let id = _$('#product_voucher_'+ cartId + '_' + productId).value

    if(id.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập voucher của bạn', 'info')
        return
    }

    http.get(`${_URL_MAIN}/ShopVouchers/get/vouchername/${id}`)
    .then(data => {
        let voucherId = data.id
        if(voucherId != null) {
            let hasExp = dateCheck(data.start_date, data.end_date, getDateCurr())
            if(!hasExp) {
                Swal.fire('Message', 'Vouchers này đã hết hạn sử dụng', 'info')
                return
            } 
            if(data.uses >= data.max_uses) {
                Swal.fire('Message', 'Vouchers này đã hết lượt sử dụng', 'info')
                return
            } 
            
            //Product voucher
            http.get(`${_URL_MAIN}/ShopUsersCart/get/${cartId}`).then(cartItem => {
                
                if(!cartItem._discount) {
                    let idProduct = cartItem.product.id
                    http.get(`${_URL_MAIN}/ShopProductVouchers/get/${idProduct}/${voucherId}/get`)
                    .then(data => {
                        if(data.status == 200) {
                            if(!cartItem._checked) cartItem._checked = true
                            cartItem._discount = true
                            cartItem.shopProductVouchers = data.data

                            http.put(`${_URL_MAIN}/ShopUsersCart/update`, cartItem)

                            Swal.fire('Message', 'Áp dụng voucher thành công!', 'success')
                            .then(e => {
                                window.location.reload()
                            })
                        } else {
                            Swal.fire('Message', data.data, 'error')
                            .then(e => '')
                        }
                    })
                } else {
                    Swal.fire('Message', 'Bạn không thể sử dụng hoặc Voucher này đã được áp dụng rồi!', 'info').then(e => '')
                }
                
            }).catch(err => console.log(err)).then(e => '')
            
        } else {
            Swal.fire('Message', 'Không tồn tại vouchers này', 'info')
            .then(e => '')
        }
        _$$('.box-product__voucher.active')?.forEach(e => e.classList.remove('active'))
    })
}

function unCheckProductVouchers (cartId) {
    //Product voucher
    http.get(`${_URL_MAIN}/ShopUsersCart/get/${cartId}`).then(cartItem => {
                
        if(cartItem._discount) {
            http.post(`${_URL_MAIN}/ShopProductVouchers/remove/${cartItem.shopProductVouchers.vouchers.id}`)
            .then(rs => {
                cartItem._discount = false
                cartItem.shopProductVouchers = null

                http.put(`${_URL_MAIN}/ShopUsersCart/update`, cartItem)

                Swal.fire('Message', rs.data, 'success')
                .then(e => {
                    window.location.reload()
                })
            })
        } else {
            Swal.fire('Message', 'Bạn không thể sử dụng hoặc Voucher này đã được áp dụng rồi!', 'info').then(e => '')
        }
        
    }).catch(err => console.log(err)).then(e => '')

    _$$('.box-product__voucher.active')?.forEach(e => e.classList.remove('active'))
}

function changeQuantity(id, type, idProduct, inputThis = 0) {
    
    let item = {}
    let quantity = _$('#quantity_'+id), quantityNumber = quantity.value
    if(type == 0) {
        quantityNumber = quantityNumber*1 + 1
    } else if(type == 1) {
        if(quantityNumber < 2) return
        quantityNumber = (quantityNumber < 2) ? 1 : quantityNumber*1 - 1
    } else {
        if(quantityNumber < 1 || !isNumeric(quantityNumber)) quantityNumber = 1
    }

    quantity.value = quantityNumber

    http.get(`${_URL_MAIN}/Products/get/${idProduct}`)
    .then(data => {

        let handlePriceOnChangeQuantity = (quantityInFunc) => {
            let colItem = inputThis?.parentNode?.parentNode?.parentNode

            if(colItem) {
                let productPrice = colItem.querySelector('.product-price').getAttribute('data-price') * 1
                let productSubtotal = colItem.querySelector('.product-subtotal .amount')
                let productDiscount = colItem.querySelector('.product-subtotal .product-voucher')
                let discount = 0
                if(productDiscount) {
                    discount = productDiscount.getAttribute('data-amount')*1
                }
                let newPrice = (productPrice * quantityInFunc) - discount
                let oldPrice = (productPrice * quantityInFunc)

                productSubtotal.innerHTML = formatVND(newPrice)
                _$('.product-voucher span').innerHTML = formatVND(oldPrice)
            }
        }

        if(quantityNumber > data.quantity) {
            Swal.fire('Message', _$('.product-name a').innerHTML + ' chỉ còn lại ' + data.quantity + ' sản phẩm.', 'info')
            _$('#quantity_'+id).value = data.quantity
            quantityNumber = data.quantity            
        } 

        http.get(`${_URL_MAIN}/ShopUsersCart/get/${id}`)
            .then(data => {
                item = data
                item.quantity = quantityNumber
                http.put(`${_URL_MAIN}/ShopUsersCart/update`, item)
                .then(data => {
                    cartList(1)
                    handlePriceOnChangeQuantity(quantityNumber)
                })
            })
            .catch(err => console.log(err))
    })

    
}

function checkItem(id) {
    let item = {}

    http.get(`${_URL_MAIN}/ShopUsersCart/get/${id}`)
        .then(data => {
            item = data
            item._checked = !item._checked
            http.put(`${_URL_MAIN}/ShopUsersCart/update`, item)
            .then(data => cartList())
        })
        .catch(err => console.log(err))
}

function removeItemReview (id, btn = '') {
    
    Swal.fire({
        title: "Are you sure?",
        text: "Xóa sản phẩm này khỏi giỏ hàng, đúng hong?",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((willBlock) => {
        if (willBlock.isConfirmed) {
            http.delete(`${_URL_MAIN}/ShopUsersCart/delete/${id}`)
            .then(data => {
                if(data.id > 0) {
                    
                    if(btn != '') {
                        Swal.fire("Message", "Đã xóa khỏi giỏ hàng.", "success")
                        .then(rs => window.location.reload())
                        
                    } else {
                        cartReview()
                    }
                } else {
                    Swal.fire("Message", "Đã có lỗi xảy ra, vui lòng thử lại.", "error")
                    
                }
            })
        }
    })
}

function addProductToCart(idProduct, quantity = 1, type_item_id = '') {
    
    let idCtm = _$('#idCustomer')
    if(!idCtm) {
        window.location.href = "/auth/login"
    }
    let data = {
        "quantity": quantity,
        "product": {
            "id": idProduct
        },
        "customer": {
            "id": idCtm.value
        },
        "type_item_id": type_item_id,
        "created_at": getDateCurr()
    }
    
    http.post(`${_URL_MAIN}/ShopUsersCart/add`, data)
        .then(data => {
            if(data.status == 200) {
                cartReview()
            } else {

                Swal.fire("Message", "Sản phẩm đã hết hàng hoặc số lượng còn lại không đủ. Vui lòng thử lại sau.", "info")
            }
        })
}

function isNumeric(str) {
    if (typeof str != "string") return false // we only process strings!  
    return !isNaN(str) && // use type coercion to parse the _entirety_ of the string (`parseFloat` alone does not do this)...
           !isNaN(parseFloat(str)) // ...and ensure strings of whitespace fail
}


function dateCheck(from, to, check) {

    var fDate,lDate,cDate;
    fDate = Date.parse(from);
    lDate = Date.parse(to);
    cDate = Date.parse(check);

    if((cDate <= lDate && cDate >= fDate)) {
        return true;
    }
    return false;
}