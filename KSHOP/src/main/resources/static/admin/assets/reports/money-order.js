let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_order
let dataOrders = []
let currentIndex = -1
let rootTotalPrice = 0

const loadOrder = (data) => {
    let wrapOrder = _$('.order_list')
    let html = ``
    dataOrders = data
    data.forEach((e, i) => {
        if(e.order_status == 'Complete' || e.order_status == 'Close') {
            html +=  /*html*/`<tr>
                <td>${e.users.stores.id}</td>

                <td class="order-success">
                    <span>${e._mo ? "Đã xử lý" : "Chưa xử lý"}</span>
                </td>

                <td>
                    <a href="${_URL_MAIN}/store/${e.users.stores.id}" target="_blank">
                        ${e.users.stores.store_name}
                    </a>
                </td>

                <td class="order-success">
                    <span>${getDateCurrStr(e.created_at)}</span>
                </td>

                <td>
                    <a href="javascript:void(0)">
                        ${e.customers.first_name}
                        ${e.customers.last_name}
                    </a>
                </td>

                <td class="order-success">
                    <span>${e.order_status}</span>
                </td>

                <td>
                    <ul>
                        <li>
                            <a href="javascript:void(0)" onclick="morderView(${i})">
                                <span class="lnr lnr-eye"></span>
                            </a>
                        </li>
                    </ul>
                </td>
            </tr>`
        }
            
    })
    wrapOrder.innerHTML = html
}

const loadPages = (totalOrder) => {
    let pagination = _$('.pagination')
    total_order = totalOrder

    total_page = Math.floor(total_order / limit) + 1

    let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderOrder(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderOrder(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderOrder(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderOrder(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderOrder(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

const genderOrder = (page) => {
    currentPage = page
    loadPages(total_order)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`${_URL_MAIN}/Orders/get/${currentPage - 1}/${limit}`)
        .then(data => {
            if(data.status == 200) {
                loadOrder(data.data)
            }
        })
        .catch(err => console.log(err))
}
genderOrder(currentPage)

http.get(`${_URL_MAIN}/Orders/get`)
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

function morderView(i = -1) {
    let wrapInfo = _$('.morder__info')
    let wrapOverlay = _$('.morder__overlay')
    let order = dataOrders[i]
    currentIndex = i

    wrapOverlay.classList.toggle('active')
    wrapInfo.classList.toggle('active')
    if(i >= 0) {
        http.get(`${_URL_MAIN}/OrderDetail/get/orderid/${order.id}`)
        .then(data => {
            showPopup(order, data)
        })
        .catch(err => console.log(err))
    }
}

function showPopup(order, orderDetail) {

    let orderId = _$('.orderId')
    let ngayGiao = _$('.ngayGiao')
    let customerInfo = _$('.customerInfo')
    let paymentType = _$('.paymentType')
    let shipInfo = _$('.shipInfo')
    let shopInfo = _$('.shopInfo')
    let subtotalPrice = _$('.subtotalPrice')
    let discount = _$('.discount')
    let totalPrice = _$('.totalPrice')
    let btnGHN = _$('.btn-ghn')

    let totalPriceNb = 0, discountNb = 0, unitPrice = 0
    let shipPrice = order.shipping_fee, subtotal = shipPrice

    orderDetail.forEach(e => {
        discountNb = (e.discount_percentage/100) * (e.unit_price * e.quantity) + e.discount_amout
        unitPrice = (e.unit_price * e.quantity) - discountNb
        subtotal += unitPrice
    })

    subtotal = subtotal - discountNb - order.discount_amount

    totalPriceNb = subtotal - shipPrice

    rootTotalPrice = totalPriceNb

    if(orderId,
        ngayGiao,
        customerInfo,
        paymentType,
        shipInfo,
        shopInfo,
        subtotalPrice,
        discount,
        totalPrice) {

        orderId.innerHTML = `Đơn #${order.id} - Tạo vào lúc: ${getDateCurrStr(order.order_date)}`
        ngayGiao.innerHTML = `Ngày giao: ${getDateCurrStr(order.shipped_date * 1)}`
        customerInfo.innerHTML= `${order.ship_name} | ${order.ship_phone} , ${order.ship_address1}, ${order.ship_ward}, ${order.ship_state}, ${order.ship_city}`
        paymentType.innerHTML = `${order.paymentTypes.payment_name}`
        shipInfo.innerHTML = `Giao Hàng Nhanh - ${formatVND(order.shipping_fee)}.`
        shopInfo.innerHTML = `${order.users.stores.store_name} - ${order.users.stores.address}`
        subtotalPrice.innerHTML = formatVND(subtotal)
        discount.innerHTML = formatVND(discountNb + order.discount_amount)
        totalPrice.innerHTML = `${formatVND(totalPriceNb)} (Không tính phí ship hàng)`
        btnGHN.setAttribute('href', order.id_order_ghn != null ? 'https://donhang.ghn.vn/?order_code=' + order.id_order_ghn : 'javascript:Swal.fire(\'Message\', \'Đơn hàng đang chờ shop xác nhận đơn hàng\', \'info\')')
        
    }
}

function accept(status = 1) {
    if(currentIndex < 0) return
    
    Swal.fire({
        title: "Message",
        text: status == 1 ? "Bằng việc chấp thuận, hệ thống sẽ cộng tiền cho user giống với thông tin đơn hàng này." : "Từ chối cộng tiền cho đơn hàng này?",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((oke) => {
        if (oke.isConfirmed) {
            let order = dataOrders[currentIndex]
            status = !!status
            let data = {
                order: {
                    id: order.id
                },
                total_money: rootTotalPrice,
                status
            }
        
            // add
            http.post(`${_URL_MAIN}/MoneyOrders/add`, data)
            .then(response => {
                if(response.status == 200) {
                    Swal.fire('Success', response.data, 'success')
                    .then(() => window.location.reload())
                } else {
                    Swal.fire('Message', response.data, 'info')
                    .then(() => window.location.reload())
                }
            })
        }
    })
}

function filter() {
    let items = _$$('.order_list tr')
    let keyword = _$('#filter-search')
    let xacNhan = _$('#filter-xn') // 0 - 1 - 2
    let regexXN = '.+'

    if(xacNhan.value == 1) {
        regexXN = 'đãxửlý'
    } else if(xacNhan.value == 2) {
        regexXN = 'chưaxửlý'
    }

    keyword = keyword.value.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
    if(keyword.length == 0 || keyword === '') keyword = '.+'
    items.forEach(e => {
        let xacNhanTable = e.querySelector('.order-success').innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
        let tableSearch = e.innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')

        if(xacNhanTable.match(regexXN) && tableSearch.match(keyword)) {
            e.style = ''
        } else {
            e.style = 'display: none;'
        }

    })
}

function sortByDate() {

    let data = {
        start_date: start_date.value,
        end_date: end_date.value
    }

    http.post(`${_URL_MAIN}/Orders/report`, data)
    .then(data => {

        if(data.data.result.length == 0) {
            Swal.fire('Message', 'Không tìm thấy kết quả nào.', 'info')
        } else {

            loadOrder(data.data.result)
            loadPages(data.data.result.length)
        }

    })

}
//set cho input date time hiện tại
start_date.valueAsDate = new Date()
end_date.valueAsDate = new Date()