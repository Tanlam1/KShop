let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_product

const loadProduct = (data) => {
    let wrapProduct = _$('.product_list')
    let html = ``
    data.forEach(e => {
        html += /*html*/ `<tr>
            <td>
                <img src="${!!e.image.match('http|https') ? e.image : '/'+e.image}" class="img-fluid"
                    alt="">
            </td>

            <td>
                <a href="javascript:void(0)">${e.product_name}</a>
            </td>

            <td>
                <a href="javascript:void(0)">${e.categories.category_name}</a>
            </td>

            <td>${e.quantity_per_unit}</td>

            <td class="td-price">${formatVND(e.standard_cost)}</td>

            <td>
                <ul>
                    <li>
                        <a href="javascript:void(0)">
                            <span class="lnr lnr-eye"></span>
                        </a>
                    </li>

                    <li>
                        <a href="./edit/product/${e.id}">
                            <span class="lnr lnr-pencil"></span>
                        </a>
                    </li>

                    <li>
                        <a href="javascript:void(0)" onclick="deleteProduct(${e.id})">
                            <i class="far fa-trash-alt theme-color"></i>
                        </a>
                    </li>
                </ul>
            </td>
        </tr>`
    })
    wrapProduct.innerHTML = html
}

const loadPages = (totalProduct) => {
    let pagination = _$('.pagination')
    total_product = totalProduct

    total_page = Math.floor(total_product / limit) + 1

    let htmlPages = ``,
        htmlPagesPrev = ``,
        htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderProduct(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderProduct(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderProduct(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderProduct(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderProduct(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

http.get("http://localhost:8080/Products/admin/get")
    .then(data => loadPages(data.data.products.length))
    .catch(err => console.log(err))

const genderProduct = (page) => {
    currentPage = page
    loadPages(total_product)
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
    http.get(`http://localhost:8080/Products/admin/get/${currentPage-1}/${limit}`)
        .then(data => loadProduct(data.data.products))
        .catch(err => console.log(err))
}
genderProduct(currentPage)

function deleteProduct(id) {
    Swal.fire({
        title: "Are you sure ?",
        text: "Xóa sản phẩm đúng hong?",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then(isDeleted => {
        if (isDeleted.isConfirmed) {
            
            http.delete(`http://localhost:8080/Products/delete/${id}`)
            .then(data => {
                if(data.status == 200) {
                    Swal.fire('Success', data.data, 'success')
                    .then(() => window.location.reload())
                } else {
                    Swal.fire('Message', data.data, 'info')
                    .then(() => window.location.reload())
                }    
            })
            .catch(err => Swal.fire('Message', 'Đã có lỗi xãy ra, vui lòng thử lại', 'info'))
        }
    })
}