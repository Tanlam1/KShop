let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_seller
let dataSeller = {}

const loadSeller = (data) => {
    let wrapSeller = _$('.table__shops')
    let html = ``
    data.forEach(e => {
        html += /*html */`<tr>
            <td>${e.id}</td>
            <td>${e.name}</td>
            <td>${e.email}</td>
            <td>${type_seller(e.type)}</td>
            <td>${e.status ? 'Đã duyệt' : 'Chờ duyệt'}</td>
            <td>${getDateCurrStr(e.createdAt)}</td>
            <td>
                <ul>
                    <li>
                        <a href="javascript:void(0)" onclick="infoSeller(${e.id})">
                            <span class="lnr lnr-eye"></span>
                        </a>
                    </li>
                </ul>
            </td>
        </tr>`
    })
    if(html == '') {

        _$('.table-responsive.table-desi').innerHTML = '<div style=" padding: 20px; text-align: center; font-size: 20px; background-color: #fafafa; ">Chưa có dữ liệu gì hết ><</div>'
    } else {

        wrapSeller.innerHTML = html
    }
}

const loadPages = (totalSeller) => {
    let pagination = _$('.pagination')
    total_seller = totalSeller

    total_page = Math.floor(total_seller / limit) + 1

    let htmlPages = ``,
        htmlPagesPrev = ``,
        htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderSeller(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderSeller(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderSeller(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderSeller(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderSeller(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

http.get("http://localhost:8080/Seller/get")
    .then(data => loadPages(data.data.sellers.length))
    .catch(err => console.log(err))

const genderSeller = (page) => {
    currentPage = page
    loadPages(total_seller)
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
    http.get(`http://localhost:8080/Seller/get/${currentPage-1}/${limit}`)
        .then(data => loadSeller(data.data))
        .catch(err => console.log(err))
}
genderSeller(currentPage)

function type_seller(type) {
    switch(type) {
        case 'DOANH_NGHIEP' : return 'Doanh nghiệp'
        case 'CA_NHAN' : return 'Cá nhân'
    }
}

function infoSeller(t = 0) {
    let wrapInfo = _$('.seller__info')
    let wrapOverlay = _$('.seller__overlay')

    wrapOverlay.classList.toggle('active')
    wrapInfo.classList.toggle('active')

    if(t > 0) {
        setInfoSeller(t)
    }
}

function setInfoSeller(id) {
    http.get(`${_URL_MAIN}/Seller/get/${id}`)
    .then(data => {
        let shopName = _$('.save-name b')
        let shopType = _$('.save-position h6')
        let shopAddress = _$('.save-address span')
        let shopPhone = _$('.mobile span')
        let shopCode = _$('.code span')
        let shopEmail = _$('.email span')
        let shopCategory = _$('.category span')
        let actions = _$$('.action-seller a')

        
        if(shopName && shopType && shopAddress && shopPhone && shopCategory) {
            shopName.innerHTML = data.data.name
            shopType.innerHTML = type_seller(data.data.type)
            shopPhone.innerHTML = data.data.phone
            shopCategory.innerHTML = data.data.category.category_name
            shopCode.innerHTML = data.data.code
            shopEmail.innerHTML = data.data.email
            shopAddress.innerHTML = `${data.data.customer.ward}, ${data.data.customer.state}, ${data.data.customer.city}`
            dataSeller = data.data
            actions[0].setAttribute('onclick', `chapThuan(this, ${id})`)
            actions[1].setAttribute('onclick', `huyYeuCau(this, ${id})`)
        }

    })
}

function chapThuan(btn, id) {
    Swal.fire({
        title: "Message",
        text: `Bằng việc chấp thuận, hệ thống sẽ tạo tự động base store & user giống với thông tin khách hàng.`,
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Chấp thuận ngay',
        cancelButtonText: 'Để sau đi'
    }).then((oke) => {
        if (oke.isConfirmed) {

            btn.style.pointerEvents = 'none'
            btn.innerHTML = 'Đang thực hiện..'

            http.post(`${_URL_MAIN}/Seller/accept/${id}`)
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
    })
}

function huyYeuCau(btn, id) {
    Swal.fire({
        title: "Message",
        text: `Từ chối yêu cầu mở shop của khách hàng này ?`,
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then((oke) => {
        if (oke.isConfirmed) {

            btn.style.pointerEvents = 'none'
            btn.innerHTML = 'Đang thực hiện..'

            http.delete(`${_URL_MAIN}/Seller/delete/${id}`)
            .then(rs => {
                if(rs.status == 200) {
                    Swal.fire('Success', rs.data, 'success')
                    .then(() => window.location.reload())
                } else {
                    Swal.fire('Message', rs.data, 'info')
                    .then(() => window.location.reload())
                }
            })

        }
    })
}