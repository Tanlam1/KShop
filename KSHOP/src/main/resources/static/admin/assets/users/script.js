let limit = 10
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_user

const loadUser = (data) => {
    let wrapUser = _$('.user_list')
    let html = ``
    data.forEach(e => {
        html +=  /*html*/`<tr>
        <td>${e.username}</td>

        <td>
            <a href="javascript:void(0)">
                <span class="d-block">${e.first_name} ${e.last_name}</span>
            </a>
        </td>

        <td>${e.phone}</td>

        <td>${e.email}</td>

        <td>${getDateCurrStr(e.created_at)}</td>

        <td>
            <ul>
                <li>
                    <a href="javascript:void(0)">
                        <span class="lnr lnr-eye"></span>
                    </a>
                </li>

                <li>
                    <a href="${_ADMIN_DIR}/edit/users/${e.id}">
                        <span class="lnr lnr-pencil"></span>
                    </a>
                </li>
            </ul>
        </td>
    </tr>`
    })
    wrapUser.innerHTML = html
}

const loadPages = (totalUser) => {
    let pagination = _$('.pagination')
    total_user = totalUser

    total_page = Math.floor(total_user / limit) + 1

    let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderUser(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderUser(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderUser(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderUser(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderUser(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

const genderUser = (page) => {
    currentPage = page
    loadPages(total_user)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`http://localhost:8080/Users/get/${currentPage - 1}/${limit}`)
        .then(data => loadUser(data.data))
        .catch(err => console.log(err))
}
genderUser(currentPage)

http.get(`http://localhost:8080/Users/get`)
    .then(data => loadPages(data.data.length - 1))
    .catch(err => console.log(err))


