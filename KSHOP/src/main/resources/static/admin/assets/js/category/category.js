let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_category

const loadCategory = (data) => {
    let wrapCategory = _$('.categories-js')
    let html = ``
    data.forEach(e => {
        html += /*html*/
            `<tr>
        <td>${e.category_code}</td>
        <td>${e.category_name}</td>
        <td class="menu-status">
            <span class="success">Success</span>
        </td>
        <td>${e.created_at}</td>
        <td>
            <ul>
                <li>
                    <a href="order-detail.html">
                        <span class="lnr lnr-eye"></span>
                    </a>
                </li>

                <li>
                    <a href="./edit/category/${e.id}">
                        <span class="lnr lnr-pencil"></span>
                    </a>
                </li>

                <li>
                    <a href="javascript:void(0)" onclick="deleteCate(${e.id})">
                            <i class="far fa-trash-alt theme-color"></i>
                    </a>
                </li>
            </ul>
        </td>
        </tr>`
    })
    wrapCategory.innerHTML = html
}

const loadPages = (totalCategory) => {
    let pagination = _$('.pagination')
    total_category = totalCategory

    total_page = Math.floor(total_category / limit) + 1

    let htmlPages = ``,
        htmlPagesPrev = ``,
        htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderCategory(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderCategory(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderCategory(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderCategory(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderCategory(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

const genderCategory = (page) => {
    currentPage = page
    loadPages(total_category)
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
    http.get(`http://localhost:8080/ShopCategories/get/${currentPage-1}/${limit}`)
        .then(data => loadCategory(data))
        .catch(err => console.log(err))
}
genderCategory(currentPage)

http.get("http://localhost:8080/ShopCategories/get")
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

function deleteCate(id) {
    Swal.fire({
        title: "Are you sure ?",
        text: "Xóa category đúng hong?",
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oke'
    }).then(isDeleted => {
        if (isDeleted.isConfirmed) {

            http.delete(`http://localhost:8080/ShopCategories/delete/${id}`)
                .then(data => {
                    Swal.fire('Success', 'Xóa category thành công', 'success')
                    window.location.reload()
                })
                .catch(err => Swal.fire('Message', 'Đã có lỗi xãy ra, vui lòng thử lại', 'info'))
        }
    })
}