let limitInAnlAdd = 10
let currentPageInAnlAdd = 1
let pagePrevInAnlAdd, pageNextInAnlAdd
let htmlpagePrevInAnlAdd, htmlpageNextInAnlAdd
let totalPageInAnlAdd, totalOrderInAnlAdd
let dataMoneyOrders = []
let currentIndexInAnlAdd = -1
let rootTotalPriceInAnlAdd = 0

const loadMoneyOrders = (data) => {
    let wrapOrder = _$('.anl_add')
    let html = ``
    dataMoneyOrders = data
    data.forEach((e, i) => {
        html +=  /*html*/`<tr>
            <td>ID#${e.id}</td>

            <td class="order-success">
                <span>${e.status ? "Chấp nhận" : "Từ chối"}</span>
            </td>

            <td>
                <a href="javascript:void(0)">
                    ${e.order.users.stores.store_name}
                </a>
            </td>

            <td class="order-success">
                <span>${getDateCurrStr(e.created_at)}</span>
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
            
    })
    wrapOrder.innerHTML = html
}

const loadPagesInAnlAdd = (totalOrder) => {
    let pagination = _$('.paginationInAdd')
    totalOrderInAnlAdd = totalOrder

    totalPageInAnlAdd = Math.floor(totalOrderInAnlAdd / limitInAnlAdd) + 1

    let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``

    for (let i = currentPageInAnlAdd + 1; i <= currentPageInAnlAdd + 3; i++) {
        if (i > totalPageInAnlAdd) break
        htmlPagesNext += `<li class="page-item ${i == currentPageInAnlAdd ? 'active' : ''}">
            <button class="page-link" onclick="genderOrderInAnlAdd(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPageInAnlAdd - 1; i >= currentPageInAnlAdd - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPageInAnlAdd ? 'active' : ''}">
            <button class="page-link" onclick="genderOrderInAnlAdd(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderOrderInAnlAdd(${currentPageInAnlAdd})">${currentPageInAnlAdd}</button>
    </li>`

    pagePrevInAnlAdd = (currentPageInAnlAdd - 1 < 1) ? totalPageInAnlAdd : currentPageInAnlAdd - 1
    pageNextInAnlAdd = (currentPageInAnlAdd + 1 > totalPageInAnlAdd) ? 1 : currentPageInAnlAdd + 1
    htmlpagePrevInAnlAdd = `<li class="page-item">
        <button class="page-link" onclick="genderOrderInAnlAdd(${pagePrevInAnlAdd})"
            tabindex="-1">Previous</button>
    </li>`
    htmlpageNextInAnlAdd = `<li class="page-item">
        <button class="page-link" onclick="genderOrderInAnlAdd(${pageNextInAnlAdd})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlpagePrevInAnlAdd + htmlPagesPrev + htmlPages + htmlPagesNext + htmlpageNextInAnlAdd
}

const genderOrderInAnlAdd = (page) => {
    currentPageInAnlAdd = page
    loadPagesInAnlAdd(totalOrderInAnlAdd)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`${_URL_MAIN}/MoneyOrders/get/${currentPageInAnlAdd - 1}/${limitInAnlAdd}`)
        .then(data => {
            if(data.status == 200) {
                loadMoneyOrders(data.data)
            }
        })
        .catch(err => console.log(err))
}
genderOrderInAnlAdd(currentPageInAnlAdd)

http.get(`${_URL_MAIN}/MoneyOrders/get`)
    .then(data => loadPagesInAnlAdd(data.length))
    .catch(err => console.log(err))

function morderView(i = -1) {
    let wrapInfo = _$('.morder__info')
    let wrapOverlay = _$('.morder__overlay')
    let order = dataMoneyOrders[i]?.order
    currentIndexInAnlAdd = i

    wrapOverlay.classList.toggle('active')
    wrapInfo.classList.toggle('active')
    if(i >= 0) {
        http.get(`${_URL_MAIN}/OrderDetail/get/orderid/${order.id}`)
        .then(data => {
            showPopupInAnlAdd(order, data)
        })
        .catch(err => console.log(err))
    }
}

function showPopupInAnlAdd(order, orderDetail) {

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
    if(currentIndexInAnlAdd < 0) return
    
    Swal.fire({
        title: "Message",
        text: status == 1 ? "Bằng việc chấp thuận, hệ thống sẽ cộng tiền cho user giống với thông tin đơn hàng này." : "Từ chối cộng tiền cho đơn hàng này?",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((oke) => {
        if (oke.isConfirmed) {
            let order = dataMoneyOrders[currentIndexInAnlAdd]
            status = !!status
            let data = {
                order: {
                    id: order.id
                },
                id_ghn: order.id_order_ghn,
                total_money: rootTotalPriceInAnlAdd,
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

function filterInAnlAdd() {
    let items = _$$('.anl_add tr')
    let keyword = _$('#filterInAnlAdd-search')
    let xacNhan = _$('#filterInAnlAdd-xn') // 0 - 1 - 2
    let regexXN = '.+'

    if(xacNhan.value == 1) {
        regexXN = 'chấpnhận'
    } else if(xacNhan.value == 2) {
        regexXN = 'từchối'
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

function sortByDateInAnlAdd() {

    let start_date = _$('.add-sort-date #start_date')
    let end_date = _$('.add-sort-date #end_date')
    let data = {
        start_date: start_date.value,
        end_date: end_date.value
    }

    http.post(`${_URL_MAIN}/MoneyOrders/report`, data)
    .then(data => {

        if(data.data.result.length == 0) {
            Swal.fire('Message', 'Không tìm thấy kết quả nào.', 'info')
        } else {

            loadMoneyOrders(data.data.result)
            loadPagesInAnlAdd(data.data.result.length)
        }

    })

}
//set cho input date time hiện tại
_$('.add-sort-date #start_date').valueAsDate = new Date()
_$('.add-sort-date #end_date').valueAsDate = new Date()