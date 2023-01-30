let limit = 3
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_shopstore

const loadStoreList = (data) => {
    let wrapStoreList = document.querySelector('.store-list-js')
    let html = ``
    data.forEach(e => {
        html +=  /*html*/`<div class="store store-list mt-4">
        <div class="store-header">
            <a href="vendor-store.html">
                <figure class="store-banner">
                    <img src="${e.image}" alt="Vendor" width="400"
                        height="188" style="background-color: #40475E">
                </figure>
            </a> <label class="featured-label">Featured</label>
        </div>
        <!-- End of Store Header -->
        <div class="store-content">
            <figure class="seller-brand">
                <img src="images/vendors/1.jpg" alt="Brand" width="80" height="80">
            </figure>
            <div class="seller-date">
                <h4 class="store-title">
                    <a href="vendor-store.html">${e.store_name}</a>
                </h4>
                <div class="ratings-container">
                    <div class="ratings-full">
                        <span class="ratings" style="width: 100%;"></span> <span
                            class="tooltiptext tooltip-top"></span>
                    </div>
                </div>
                <div class="store-address">${e.address}</div>
                <a href="vendor-store.html"
                    class="btn btn-dark btn-link btn-underline btn-icon-right btn-visit">
                    Visit Store<i class="w-icon-long-arrow-right"></i>
                </a>
            </div>
        </div>
        <!-- End of Store Content -->
    </div>`
    })
    wrapStoreList.innerHTML = html
}

const loadPages = (totalShopStores) => {
    let pagination = _$('.pagination')
    total_shopstore = totalShopStores

    total_page = Math.floor(total_shopstore / limit) + 1

    let htmlPages = ``,
        htmlPagesPrev = ``,
        htmlPagesNext = ``

    for (let i = currentPage + 1; i <= currentPage + 3; i++) {
        if (i > total_page) break
        htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderStore(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPage - 1; i >= currentPage - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
            <button class="page-link" onclick="genderStore(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderStore(${currentPage})">${currentPage}</button>
    </li>`

    pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    htmlPagePrev = `<li class="page-item">
        <button class="page-link" onclick="genderStore(${pagePrev})"
            tabindex="-1">Previous</button>
    </li>`
    htmlPageNext = `<li class="page-item">
        <button class="page-link" onclick="genderStore(${pageNext})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
}

http.get("http://localhost:8080/ShopStores/get")
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

const genderStore = (page) => {
    currentPage = page
    loadPages(total_shopstore)
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
    http.get(`http://localhost:8080/ShopStores/get/${currentPage - 1}/${limit}`)
        .then(data => loadStoreList(data))
        .catch(err => console.log(err))
}
genderStore(currentPage)