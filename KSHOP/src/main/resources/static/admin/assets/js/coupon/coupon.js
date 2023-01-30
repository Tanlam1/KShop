let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_coupon

const loadCoupon = (data) => {
    let wrapCoupon = document.querySelector('.coupons-js')
    let html = ``
    data.forEach(e => {
        html +=  /*html*/`<tr>
        <td>${e.id}</td>
        <td>${e.voucher_name}</td>
        <td>${!e._fixed ? e.discount_amount + '%' : formatVND(e.discount_amount)}</td>
        <td>${getDateCurrStr(e.start_date)}</td>
        <td>${getDateCurrStr(e.end_date)}</td>
        <td>
            <ul>
                <li>
                    <a href="./edit/coupon/${e.id}">
                        <span class="lnr lnr-pencil"></span>
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0)" onclick="deleteVoucher(${e.id})">
                        <i class="far fa-trash-alt theme-color"></i>
                    </a>
                </li>
            </ul>
        </td>
    </tr>`
    })
    wrapCoupon.innerHTML = html
}

const loadPages = (totalCoupon) => {
    let pagination = _$('.pagination')
    total_coupon = totalCoupon

    total_page = Math.floor(total_coupon / limit) + 1

    let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderCoupon(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderCoupon(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderCoupon(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderCoupon(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderCoupon(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

const genderCoupon = (page) => {
    currentPage = page
    loadPages(total_coupon)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`http://localhost:8080/ShopVouchers/get/${currentPage - 1}/${limit}`)
        .then(data => loadCoupon(data))
        .catch(err => console.log(err))
}
genderCoupon(currentPage)


http.get("http://localhost:8080/ShopVouchers/get")
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

    function deleteVoucher(id) {
        Swal.fire({
            title: "Are you sure ?",
            text: "Xóa voucher đúng hong? Onii-chan",
            icon: "info",
            showCancelButton: true,
            cancelButtonColor: '#d33',
            confirmButtonText: 'Oke'
        }).then(isDeleted => {
            if (isDeleted.isConfirmed) {
                
                http.delete(`http://localhost:8080/ShopVouchers/delete/${id}`)
                .then(data => {
                    Swal.fire('Success', 'Xóa voucher thành công!! desu~~', 'success')
                    window.location.reload()    
                })
                .catch(err => Swal.fire('Message', 'Đã có lỗi xãy ra, vui lòng thử lại', 'info'))
            }
        })
    }