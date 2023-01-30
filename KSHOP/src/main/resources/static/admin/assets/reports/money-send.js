let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_vendor
let dataStores = []
let currentIndex = -1
let rootTotalPrice = 0

let wrap1 = _$('.wrap-box-1')
let wrap2 = _$('.wrap-box-2')

const loadVendorStore = (data) => {
    let contentHead = _$('.head-content--pay')
    contentHead.innerHTML = `Ngưỡng thanh toán ${formatVND(data.data.pay_content.min_pay)} | ${data.data.pay_content.pay_info}`

    let wrapVendorStore = document.querySelector('.vendor-js')
    let html = ``
    
    dataStores = data.data.stores
    dataStores.forEach((e, i) => {
        let trangThai = 'OKK', classTT = 'ok'
        if(e.pay_info_next == null) {
            trangThai = 'WAIT'
            classTT = ''
        }
        if(e.user.stores != null)
            html +=  /*html*/`<tr>

            <td>${e.user.id}</td>
            <td class="fl-table-info">
                <img src="${!!e.user.stores.image.match('http|https') ? e.user.stores.image : '/' + e.user.stores.image}" class="img-fluid" alt="" width="80px">
                <p>
                    ${e.user.stores.store_code}
                    <span class="box-green">${e.user.stores.store_name}</span>
                </p>
                
            </td>

            <td>${formatVND(e.user.sodu)}</td>
            <td><span class="box-brand ${classTT}">${trangThai}</span></td>
            <td>${formatVND(e.user.sodu_hold)}</td>

            <td>${getDateCurrStr(e.user.stores.created_at)}</td>

            <td>
                <ul>
                    <li>
                        <a href="javascript:void(0)" onclick="payStoreView(${i})">
                            <span class="lnr lnr-eye"></span>
                        </a>
                    </li>
                </ul>
            </td>
        </tr>`
    })
    wrapVendorStore.innerHTML = html
}

const loadPages = (totalVendor) => {
    let pagination = _$('.pagination')
    total_vendor = totalVendor

    total_page = Math.floor(total_vendor / limit) + 1

    let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderVendor(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderVendor(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderVendor(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderVendor(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderVendor(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

const genderVendor = (page) => {
    currentPage = page
    loadPages(total_vendor)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`http://localhost:8080/ShopStores/min-pay/${currentPage - 1}/${limit}`)
        .then(data => loadVendorStore(data))
        .catch(err => console.log(err))
}
genderVendor(currentPage)

http.get(`http://localhost:8080/ShopStores/get`)
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

function payStoreView(i = -1) {
    let wrapInfo = _$('.paynext__info')
    let wrapOverlay = _$('.paynext__overlay')
    let stores = dataStores[i]
    currentIndex = i

    wrapOverlay.classList.toggle('active')
    wrapInfo.classList.toggle('active')
    wrap1.style.display = 'block';
    wrap2.style.display = 'none';
    let btn = _$('.submitBtn')
    let textRMFail = _$('.text-rm-fail')

    btn.setAttribute('onclick', 'accept()')
    btn.innerHTML = 'Tiếp tục'
    textRMFail.style.display = 'none';

    if(i >= 0) {
        showPopup(stores)
    }
}

function showPopup(stores) {
    
    let shopName = _$('.shopName')
    let shopCode = _$('.shopCode')
    let infoNextPay = _$('.infoNextPay')
    let paymentType = _$('.paymentType')
    let paymentName = _$('.paymentName')
    let paymentNumber = _$('.paymentNumber')
    let sodu = _$('.sodu')
    let soduNgoai = _$('.soduNgoai')
    let soduHold = _$('.soduHold')
    let totalPay = _$('.totalPay')

    
    let payStatus = false, paynext = 'Chưa đủ điều kiện'
    if(stores.pay_info_next) {
        
        payStatus = true
        paynext = stores?.pay_info_next?.pay_info
    }

    let payments = ['', '', '', '']
    let paymtType = `Shop này chưa thêm phương thức thanh toán`
    let paymtBrand = `Shop này chưa thêm phương thức thanh toán`
    if(stores.user.info_receive_money) {
        payments = stores.user.info_receive_money.split('|')
        paymtType = (payments[1] == 'CK') ? 'Chuyển khoản ngân hàng' : 'Ví điện tử: ' + payments[1]
        paymtBrand = (payments[1] == 'CK') ? payments[3] : payments[1]
    }

    if(shopName,
        shopCode,
        infoNextPay,
        paymentType,
        paymentName,
        paymentNumber,
        sodu,
        soduNgoai,
        soduHold,
        totalPay) {

        shopName.innerHTML = stores.user.stores.store_name
        shopCode.innerHTML = stores.user.stores.store_code
        infoNextPay.innerHTML = paynext
        paymentType.innerHTML = paymtType
        paymentName.innerHTML = payments[0]
        paymentNumber.innerHTML = `${payments[2]} - ${paymtBrand}`
        sodu.innerHTML = formatVND(stores.user.sodu)
        soduNgoai.innerHTML = formatVND(stores.user.sodu_ngoai)
        soduHold.innerHTML = formatVND(stores.user.sodu_hold)
        totalPay.innerHTML = formatVND(stores.user.sodu - stores.user.sodu_ngoai + stores.user.sodu_hold)
        let btn = _$('.submitBtn')
        let textRMFail = _$('.text-rm-fail')
        if(!payStatus) {
            btn.style.display = 'none'
            textRMFail.style.display = 'block'
        } else {
            btn.style.display = 'block'
            textRMFail.style.display = 'none'
        }
    }
}

function accept() {
    let shopName = _$('.shopName')
    Swal.fire({
        title: "Message",
        html: "Nội dung ghi chú giao dịch <br><small>(Tin nhắn, thông báo, gửi kèm, lỗi.. )</small>",
        icon: "info",
        input: "text", 
        inputValue: 'Xác nhận chuyển khoản thanh toán nhận tiền đến: ' + shopName.innerText.trim(),
        showCancelButton: true,
        didOpen: () => {
          Swal.getInput().select()
        },
        inputValidator: (value) => {
            if (value) {
                if(value.replaceAll(' ', '').length < 3) {
                    return
                }

                let note = _$('.wrap-box-2 .note')
                let btn = _$('.submitBtn')
                wrap1.style.display = 'none';
                wrap2.style.display = 'block';
                note.innerHTML = 'Ghi chú: ' + value
                btn.setAttribute('onclick', 'finish(this)')
                btn.innerHTML = 'Tạo Giao Dịch & Kết Thúc'
            }
        }
    })    
}

function finish(element) {
    if(currentIndex < 0) return
    
    let banking_image = _$('#banking_image')?.value
    let note = _$('.note')?.innerText.trim()

    Swal.fire({
        title: "Message",
        text: "Bằng việc xác nhận chuyển tiền thành công cho shop, hệ thống sẽ trừ vào số dư tài khoản của shop.",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((oke) => {
        if (oke.isConfirmed) {
            element.style.pointerEvents = 'none';
            
            let stores = dataStores[currentIndex]
            let data = {
                user: {
                    id: stores.user.id
                },
                info_banking: stores.user.info_receive_money,
                image: banking_image,
                note
            }
        
            // add
            http.post(`${_URL_MAIN}/MoneySends/add`, data)
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
    let items = _$$('.vendor-js tr')
    let keyword = _$('#filter-search')
    let xacNhan = _$('#filter-xn') // 0 - 1 - 2
    let regexXN = '.+'

    if(xacNhan.value == 1) {
        regexXN = 'okk'
    } else if(xacNhan.value == 2) {
        regexXN = 'wait'
    }

    keyword = keyword.value.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
    if(keyword.length == 0 || keyword === '') keyword = '.+'
    items.forEach(e => {
        let xacNhanTable = e.querySelector('.box-brand').innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
        let tableSearch = e.innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')

        if(xacNhanTable.match(regexXN) && tableSearch.match(keyword)) {
            e.style = ''
        } else {
            e.style = 'display: none;'
        }

    })
}


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