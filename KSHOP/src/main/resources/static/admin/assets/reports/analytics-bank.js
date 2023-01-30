let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_vendor
let dataMoneySends = []
let currentIndex = -1
let rootTotalPrice = 0

let wrap1 = _$('.wrap-box-1')
let wrap2 = _$('.wrap-box-2')

const loadMoneySend = (data) => {
    let wrapOrder = _$('.anl_bank')
    let html = ``
    dataMoneySends = data
    data.forEach((e, i) => {
        let payments = e.info_banking.split('|')
        let paymtType = (payments[1] == 'CK') ? 'Chuyển khoản' : (payments[1] == 'PAYPAL') ? 'Nhận qua paypal' : 'Ví điện tử'
        html +=  /*html*/`<tr>
            <td>${e.id}</td>

            <td>
                ${formatVND(e.total_money)}
            </td>

            <td>
                <a href="javascript:void(0)">
                    ${e.employee.username}
                </a>
            </td>

            <td>
                <a href="javascript:void(0)">
                    ${e.user.stores.store_name}
                </a>
            </td>

            <td class="order-success">
                <span>${paymtType}</span>
            </td>

            <td>
                ${getDateCurrStr(e.created_at)}
            </td>

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
    wrapOrder.innerHTML = html
}

const loadPages = (totalVendor) => {
    let pagination = _$('.paginationInBank')
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

const genderOrder = (page) => {
    currentPage = page
    loadPages(total_vendor)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`${_URL_MAIN}/MoneySends/get/${currentPage - 1}/${limit}`)
        .then(data => {
            if(data.status == 200) {
                
                loadMoneySend(data.data)
            }
        })
        .catch(err => console.log(err))
}
genderOrder(currentPage)

http.get(`${_URL_MAIN}/MoneySends/get`)
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

function payStoreView(i = -1) {
    let wrapInfo = _$('.paynext__info')
    let wrapOverlay = _$('.paynext__overlay')
    currentIndex = i

    wrapOverlay.classList.toggle('active')
    wrapInfo.classList.toggle('active')
    wrap1.style.display = 'block';
    wrap2.style.display = 'none';
    let btn = _$('.submitBtn')
    let textRMFail = _$('.text-rm-fail')

    btn.setAttribute('onclick', 'accept()')
    btn.innerHTML = 'Thông tin chuyển khoản'
    textRMFail.style.display = 'none';

    if(i >= 0) {
        showPopup(dataMoneySends[i])
    }
}

function showPopup(dataMoneySend) {
    
    let shopName = _$('.shopName')
    let shopCode = _$('.shopCode')
    let infoNextPay = _$('.infoNextPay')
    let paymentType = _$('.paymentType')
    let paymentName = _$('.paymentName')
    let paymentNumber = _$('.paymentNumber')
    let totalPay = _$('.totalPay')

    let stores = {
        user: dataMoneySend.user
    }
    let payments = ['', '', '', '']
    let paymtType = `Shop này chưa thêm phương thức thanh toán`
    let paymtBrand = `Shop này chưa thêm phương thức thanh toán`
    if(dataMoneySend.info_banking) {
        payments = dataMoneySend.info_banking.split('|')
        paymtType = (payments[1] == 'CK') ? 'Chuyển khoản ngân hàng' : 'Ví điện tử: ' + payments[1]
        paymtBrand = (payments[1] == 'CK') ? payments[3] : payments[1]
    }

    if(shopName,
        shopCode,
        infoNextPay,
        paymentType,
        paymentName,
        paymentNumber,
        totalPay) {

        infoNextPay.innerHTML = getDateCurrStr(dataMoneySend.created_at)
        shopName.innerHTML = stores.user.stores.store_name
        shopCode.innerHTML = stores.user.stores.store_code
        paymentType.innerHTML = paymtType
        paymentName.innerHTML = payments[0]
        paymentNumber.innerHTML = `${payments[2]} - ${paymtBrand}`
        totalPay.innerHTML = formatVND(dataMoneySend.total_money)
        let btn = _$('.submitBtn')
        btn.style.display = 'block'
    }
}

function accept() {
    let note = _$('.wrap-box-2 .note')
    let btn = _$('.submitBtn')
    let img = _$('.banking_image--review')
    wrap1.style.display = 'none';
    wrap2.style.display = 'block';
    note.innerHTML = dataMoneySends[currentIndex].note
    btn.setAttribute('onclick', `review()`)
    img.src = dataMoneySends[currentIndex].image
    btn.innerHTML = 'Quay lại'   
}

function review() {
    let btn = _$('.submitBtn')
    btn.setAttribute('onclick', `accept()`)
    btn.innerHTML = 'Thông tin chuyển khoản'
    wrap2.style.display = 'none';
    wrap1.style.display = 'block';
   
}

function filter() {
    let items = _$$('.anl_bank tr')
    let keyword = _$('#filter-search')
    let xacNhan = _$('#filter-xn') // 0 - 1 - 2
    let regexXN = '.+'

    if(xacNhan.value == 1) {
        regexXN = 'chuyểnkhoản'
    } else if(xacNhan.value == 2) {
        regexXN = 'víđiệntử'
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
    let start_date = _$('.bank-sort-date #start_date')
    let end_date = _$('.bank-sort-date #end_date')
    let data = {
        start_date: start_date.value,
        end_date: end_date.value
    }

    http.post(`${_URL_MAIN}/MoneySends/report`, data)
    .then(data => {

        if(data.data.result.length == 0) {
            Swal.fire('Message', 'Không tìm thấy kết quả nào.', 'info')
        } else {

            loadMoneySend(data.data.result)
            loadPages(data.data.result.length)
        }

    })

}
//set cho input date time hiện tại
_$('.bank-sort-date #start_date').valueAsDate = new Date()
_$('.bank-sort-date #end_date').valueAsDate = new Date()


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