let currentVoucherId, currentCustomerList, currentProductList
let listIdCustomerAdd = [], listIdProductAdd = []
let wrapStep1 = _$('.box-step-1')
let wrapStep2 = _$('.box-step-2')
// type 0 -> đăng ký
function voucherView(t = 0, type = 0) {
    let wrapInfo = _$('.voucher__info')
    let wrapOverlay = _$('.voucher__overlay')

    wrapOverlay.classList.toggle('active')
    wrapInfo.classList.toggle('active')

    if(t > 0) {
        setPopup(t, type)
    }
}

function setPopup(id, type) {
    let [voucher_code, voucher_name, price, description, discount_amount, uses, max_uses, max_uses_user, min_pro, max_disa] = _$(`.raw-voucher[data-id="${id}"]`)?.innerText.trim()?.split('|')

    let voucherName = _$('.voucher-name')
    let voucherPrice = _$('.voucher-price')
    let voucherDiscAmount = _$$('.voucher-disc-amount')
    let voucherDescription = _$('.description')
    let voucherMaxUses = _$('.voucher-max-uses')
    let voucherMaxUsesUser = _$('.voucher-max-uses-user')
    let voucherMinPro = _$('.voucher-min-pro')
    let voucherMaxDisa = _$$('.voucher-max-disa')
    let boxBtn = _$('.btn-box-voucher')

    if(voucherName,
        voucherPrice,
        voucherDiscAmount,
        voucherDescription,
        voucherMaxUses,
        voucherMaxUsesUser,
        voucherMinPro,
        voucherMaxDisa,
        boxBtn) {

        voucherName.innerHTML = voucher_name
        voucherPrice.innerHTML = price
        voucherDiscAmount.forEach(e => e.innerHTML = discount_amount)
        voucherDescription.innerHTML = description
        voucherMaxUsesUser.innerHTML = max_uses_user
        voucherMinPro.innerHTML = formatVND(min_pro)
        voucherMaxDisa.forEach(e => e.innerHTML = formatVND(max_disa))

        if(type == 1) {
            voucherMaxUses.innerHTML = `${max_uses} (${uses - 1} đã dùng)`
            boxBtn.innerHTML = 'Thêm(Sửa) Đối tượng sử dụng'
            boxBtn.setAttribute('data-edit', 1)
        } else {
            voucherMaxUses.innerHTML = `${max_uses}`
            boxBtn.innerHTML = 'Đăng ký mã này'
            boxBtn.setAttribute('data-edit', 0)
        }

        currentVoucherId = id
        wrapStep1.style.transform = 'unset'
        wrapStep1.style.contentVisibility = 'unset'
        wrapStep2.style.display = 'none'
    }
}

function useVoucher(type = 1) {
    let boxBtn = _$('.btn-box-voucher')

    if(boxBtn.getAttribute('data-edit') == 0 && type == 1) {

        regVoucher()

    } else if(boxBtn.getAttribute('data-edit') == 1 && type != 0) {

        wrapStep1.style.transform = 'translateX(-111%)'
        wrapStep1.style.contentVisibility = 'hidden'
        wrapStep2.style.display = 'block'
        setCustomerList()
    } else {
        wrapStep1.style.transform = 'unset'
        wrapStep1.style.contentVisibility = 'unset'
        wrapStep2.style.display = 'none'

    }
}

function regVoucher() {

    http.get(`${_URL_MAIN}/ShopVouchers/regVoucher/${currentVoucherId}`)
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

function setCustomerList() {

    _$$(`.select-product-item`).forEach(e => {
        e.classList.remove('active', 'indb')
    })

    http.get(`${_URL_MAIN}/ShopCustomerVouchers/get/voucherid/${currentVoucherId}`)
    .then(data => {
        if(data.status == 200) {
            let html = `<p>Chọn khách hàng được áp dụng mã này</p>`
            if(data.data.customers.length > 0) {
                html = ``
                currentCustomerList = data.data.customers
                data.data.customers.forEach(e => {
                    html += `<span data-id="${e.customer.id}">${e.customer.username}</span>`
                })
                setActiveCustomer()

            } else {
                listIdCustomerAdd = []
            }
            _$('.selected-items.customers').innerHTML = html
        } else {
            Swal.fire('Message', data.data, 'info')
            .then(() => window.location.reload())
        }
    })

    http.get(`${_URL_MAIN}/ShopProductVouchers/get/voucherid/${currentVoucherId}`)
    .then(data => {
        if(data.status == 200) {
            let html = `<p>Chưa có sản phẩm nào được áp dụng mã này</p>`
            if(data.data.products.length > 0) {
                html = ``
                currentProductList = data.data.products
                data.data.products.forEach(e => {
                    html += `<span data-id="${e.product.id}">${e.product.product_name}</span>`
                })
                setActiveProduct()

            } else {
                listIdProductAdd = []
            }
            _$('.selected-items.products').innerHTML = html
        } else {
            Swal.fire('Message', data.data, 'info')
            .then(() => window.location.reload())
        }
    })  

}

//set event select item
let itemsS = _$$('.select-product-item')
if(itemsS) {
    itemsS.forEach(e => {
        e.addEventListener('click', () => {
            let isCustomer = true
            if(e.classList.contains('product'))
                isCustomer = false
                
            if(!e.classList.contains('indb')) {
                e.classList.toggle('active')
                let html = `<span data-id="${e.getAttribute('data-id')}">${e.innerText.trim()}</span>`
                let wrapItemSelected = isCustomer ? _$('.selected-items.customers') : _$('.selected-items.products')
                if(!!wrapItemSelected.innerHTML.replaceAll(' ', '').replaceAll('\n', '').trim().match('<p>.+</p>')) {
                    wrapItemSelected.innerHTML = ''
                }
                
                if(e.classList.contains('active')) {
                    wrapItemSelected.innerHTML += html // add lên span
                    if(isCustomer) {
                        listIdCustomerAdd.push(e.getAttribute('data-id'))
                    } else {
                        listIdProductAdd.push(e.getAttribute('data-id'))
                    }
                } else {
                    wrapItemSelected.innerHTML = wrapItemSelected.innerHTML.replace(`<span data-id="${e.getAttribute('data-id')}">${e.innerText.trim()}</span>`, '')
                    if(wrapItemSelected.innerHTML == '') {
                        wrapItemSelected.innerHTML = isCustomer ? '<p>Chọn khách hàng được áp dụng mã này</p>' : '<p>Chưa có sản phẩm nào được áp dụng mã này</p>'
                    }
                    if(isCustomer) {
                        listIdCustomerAdd = listIdCustomerAdd.filter(elm => elm != e.getAttribute('data-id'))
                    } else {
                        listIdProductAdd = listIdProductAdd.filter(elm => elm != e.getAttribute('data-id'))
                    }
                }
            } else {
                Swal.fire('Message', 'Không thể thay đổi trường đã thêm', 'info')
            }
        })
    })
}

function search(type = 0) {
    let input = type == 1 ? _$('#customer_search') : _$('#product_search')

    let items = type == 1 ? _$$('.select-product-item.customer') : _$$('.select-product-item.product')

    let keyword = input.value.trim().toLocaleLowerCase()
    if(keyword.length == 0 || keyword === '') keyword = '.+'
    items.forEach(e => {
        let customer_username = e.innerHTML.trim().toLocaleLowerCase()

        if(customer_username.match(keyword)) {
            e.style = ''
        } else {
            e.style = 'display: none;'
        }

    })
}

function saveCustomerApply() {
    if(listIdCustomerAdd.length <= 0 && listIdProductAdd.length <= 0) {
        return
    }
    Swal.fire({
        title: "Message",
        text: `Xác nhận này sẽ không thể hoàn tác.`,
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((oke) => {
        if (oke.isConfirmed) {
            let arrayCustomer = [], arrayProduct = []
            listIdCustomerAdd.forEach(e => {
                arrayCustomer.push({
                    customers : {
                        id : e * 1
                    },
                    vouchers : {
                        id : currentVoucherId
                    }
                })
            })
            listIdProductAdd.forEach(e => {
                arrayProduct.push({
                    products : {
                        id : e * 1
                    },
                    vouchers : {
                        id : currentVoucherId
                    }
                })
            })
            
            let productAddVCPost = () => {
                
                http.post(`${_URL_MAIN}/ShopProductVouchers/adds`, arrayProduct)
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
            if(listIdCustomerAdd.length > 0) {
                http.post(`${_URL_MAIN}/ShopCustomerVouchers/adds`, arrayCustomer)
                .then(data => {
                    if(data.status == 200) {
                        if(listIdProductAdd.length > 0) {
                            productAddVCPost()
                        } else {
                            Swal.fire('Success', data.data, 'success')
                            .then(() => window.location.reload())
                        }
                    } else {
                        Swal.fire('Message', data.data, 'info')
                        .then(() => window.location.reload())
                    }
                })
            } else if(listIdProductAdd.length > 0) {
                productAddVCPost()
            }
        }
    })
    
}

function setActiveCustomer() {
    currentCustomerList.forEach(e => {
        let item = _$(`.select-product-item.customer[data-id="${e.customer.id}"]`)

        if(item) item.classList.add('active', 'indb')
    })
}

function setActiveProduct() {
    currentProductList.forEach(e => {
        let item = _$(`.select-product-item.product[data-id="${e.product.id}"]`)

        if(item) item.classList.add('active', 'indb')
    })
}

function checkChangeCustomer() {
    let customer = _$$(`.selected-products span`)
    let customerDelete = []
    let customerAdd = []


}

//Định dạng lại date
(() => {
    _$$('.date-format').forEach(e => {
        e.innerHTML = getDateCurrStr(e.innerText.trim()).split(', ')[1]
    })
    _$$('.date-count').forEach(e => {
        let trString = new Date(e.innerText.trim()).getTime()
        let nowTS = new Date().getTime()
        let dayLeft = Math.ceil((trString - nowTS) / (60 * 60 * 24 * 1000))
        e.innerHTML = '<span class="dkkm-span">' + (dayLeft < 0 ? 'Hết hạn ' + dayLeft * (-1) : 'Còn lại ' + dayLeft) + ' ngày</span>'
    })
})()