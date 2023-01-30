let limit = 6
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_shopstore

const loadStoreGrid = (data) => {
    let wrapProduct = document.querySelector('.store-grid-js')
    let html = ``
    data.forEach(e => {
        html +=  /*html*/`<div class="store-wrap mb-4">
        <div class="store store-grid">
            <div class="store-header">
                <figure class="store-banner">
                    <img src="${e.image}" alt="Vendor" width="400p"
                        height="194px" style="background-color: #40475E">
                </figure>
            </div>
            <!-- End of Store Header -->
            <div class="store-content">
                <h4 class="store-title">
                    <a href="store/${e.id}">${e.store_name}</a> <label
                        class="featured-label">Featured</label>
                </h4>
                <div class="ratings-container">
                    <div class="ratings-full">
                        <span class="ratings" style="width: 100%;"></span> <span
                            class="tooltiptext tooltip-top"></span>
                    </div>
                </div>
                <div class="store-address">${e.address}</div>
                <ul class="seller-info-list list-style-none">
                    <li class="store-phone"><a href="tel:1234567890"><i
                            class="w-icon-phone"></i>${e.phone}</a></li>
                </ul>
            </div>
            <!-- End of Store Content -->
            <div class="store-footer">
                <figure class="seller-brand">
                    <img src="images/vendors/1.jpg" alt="Brand" width="80"
                        height="80">
                </figure>
                <a href="store/${e.id}"
                    class="btn btn-dark btn-link btn-underline btn-icon-right btn-visit">
                    Đến cửa hàng<i class="w-icon-long-arrow-right"></i>
                </a>
            </div>
            <!-- End of Store Footer -->
        </div>
        <!-- End of Store -->
    </div>`
    })
    wrapProduct.innerHTML = html
}

const loadStoreList = (data) => {
    let wrapStoreList = document.querySelector('.store-list-js')
    let html = ``
    data.forEach(e => {
        html +=  /*html*/`<div class="store store-list mt-4">
        <div class="store-header">
            <a href="store/${e.id}">
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
                    <a href="store/${e.id}">${e.store_name}</a>
                </h4>
                <div class="ratings-container">
                    <div class="ratings-full">
                        <span class="ratings" style="width: 100%;"></span> <span
                            class="tooltiptext tooltip-top"></span>
                    </div>
                </div>
                <div class="store-address">${e.address}</div>
                <a href="store/${e.id}"
                    class="btn btn-dark btn-link btn-underline btn-icon-right btn-visit">
                    Đến cửa hàng<i class="w-icon-long-arrow-right"></i>
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

http.get("http://localhost:8080/ShopStores/get")
    .then(data => loadPages(data.length))
    .catch(err => console.log(err))

const genderProduct = (page) => {
    currentPage = page
    loadPages(total_shopstore)
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    })
    http.get(`http://localhost:8080/ShopStores/get/${currentPage - 1}/${limit}`)
        .then(data => loadStoreGrid(data))
        .catch(err => console.log(err))
    http.get(`http://localhost:8080/ShopStores/get/${currentPage - 1}/${limit}`)
        .then(data => loadStoreList(data))
        .catch(err => console.log(err))
}
genderProduct(currentPage)