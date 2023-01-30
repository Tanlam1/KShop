let limit = 20
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_product
let first_arr_product = []

const loadShopGridProduct = (data) => {
    let wrapGridProduct = _$('#shop-grid-js')
    let html = ``
    let dataEach = data.products
    dataEach.forEach((e, i) => {
        html += /*html*/ `<div class="product-wrap" style="">
        <div class="product text-center product-wrap-list">
            <figure class="product-media">
                <a href="/product/${e.product_code}-${e.id}"> <img src="${!!e.image.match('http|https') ? e.image : '/'+e.image}" alt="Product" width="300" height="338">
                </a>
                <div class="product-action-horizontal">
                    <a href="#" class="btn-product-icon btn-cart w-icon-cart" title="Add to cart"></a> <a href="#" class="btn-product-icon btn-wishlist w-icon-heart" title="Wishlist"></a> <a href="#" class="btn-product-icon btn-compare w-icon-compare" title="Compare"></a> <a href="#" class="btn-product-icon btn-quickview w-icon-search" title="Quick View"></a>
                </div>
            </figure>
            <div class="product-details">
                <h3 class="product-name">
                    <a href="/product/${e.product_code}-${e.id}">${e.product_name}</a>
                </h3>
                <div class="ratings-container justify-content-start">
                    <div class="total-rating_k" data-rating="${data.reviews[i].rating}">
                        ${'<svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 24 24" size="16" color="#fdd836" height="16" width="16" xmlns="http://www.w3.org/2000/svg" style="color: rgb(253, 216, 54);"><path d="M12 17.27L18.18 21l-1.64-7.03L22 9.24l-7.19-.61L12 2 9.19 8.63 2 9.24l5.46 4.73L5.82 21z"></path></svg>'.repeat(data.reviews[i].rating)}
                    </div>
                    <a href="/product/${e.product_code}-${e.id}" class="rating-reviews" style=" margin: 2px 4px; font-size: 12px; ">(${data.reviews[i].review_count} đánh giá)</a>
                    <a href="shop-banner-sidebar.html">${e.categories.category_name}</a>
                </div>
                <div class="product-pa-wrapper">
                    <div data-price="${e.standard_cost}" class="product-price standard">${formatVND(e.standard_cost)}</div>
                    <div data-price="${e.list_price}" class="product-price">${formatVND(e.list_price)}</div>
                </div>
            </div>
        </div>
    </div>`
    })
    wrapGridProduct.innerHTML = html
    if(dataEach.length == 0)
    wrapGridProduct.innerHTML = `<div style=" padding: 20px; ">Không có sản phẩm</div>`
    first_arr_product = _$$('.product-wrap')
}

const loadPages = (totalProduct) => {
    if(totalProduct > 0) {

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
}

const params = new Proxy(new URLSearchParams(window.location.search), {
    get: (searchParams, prop) => searchParams.get(prop),
  });

let value1 = params.keyWords; // "some_value"
let url = `http://localhost:8080/Products/get/products/${value1}`
if(value1 == '' || value1 == null) {
    url = `http://localhost:8080/Products/get`
} else {
    _$('.search-box').style.display = 'block'
    _$('.search-box span').innerHTML = value1
}

http.get(`${url}`)
    .then(data => loadPages(data.data.products.length - 1))
    .catch(err => console.log(err))

const genderProduct = (page) => {
    currentPage = page
    loadPages(total_product)
    _$('.shop-content').scrollIntoView({behavior: "smooth", block: "start", inline: "start"});
    http.get(`${url}/${currentPage-1}/${limit}`)
        .then(data => loadShopGridProduct(data.data))
        .catch(err => {
            console.log(err)
                    
            let wrapGridProduct = _$('#shop-grid-js')
            wrapGridProduct.innerHTML = `<div>Không có sản phẩm</div>`
        })
}
genderProduct(currentPage)


function filterSP() {
    _$('.vnpay-overlay.loading-filter').style.display = 'unset'
    _$('.vnpay-loading.loading-filter').style.display = 'unset'

    let itemSeleteds = _$$('.selected-item')
    let items = _$$('.product-wrap')

    if(itemSeleteds.length == 0) itemSeleteds = ['']
    let categoryItem = ''
    
    itemSeleteds.forEach(e => {
        let categoryAnchor = e != '' ? e.innerText : '.+'
    
        categoryAnchor = categoryAnchor.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')

        if(!categoryAnchor.match('[0-9]+')) {

            categoryItem += categoryAnchor + '|'
        }
    })

    categoryItem = (categoryItem + 'zkodokuu').replace('|zkodokuu', '')
    
    if(categoryItem.length == 0 || categoryItem === '' || categoryItem === 'zkodokuu') categoryItem = '.+'
    
    setTimeout(() => {
        items.forEach(e => {
            let categorySearch = e.querySelector('.ratings-container a:last-child').innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
    
            if(categorySearch.match(categoryItem)) {
                e.style = ''
            } else {
                e.style = 'display: none;'
            }
    
        })
        priceCheck()
        
        _$('.vnpay-overlay.loading-filter').style.display = 'none'
        _$('.vnpay-loading.loading-filter').style.display = 'none'
    }, 200)

    function priceCheck() {
        let itemSeleteds = _$$('.selected-item')
        let items = _$$('.product-wrap')

        if(itemSeleteds.length == 0) itemSeleteds = ['']
        switch(itemSeleteds[itemSeleteds.length - 1].innerText) {
            case 'Dưới 40.000đ' : {
                let result = 0
                items.forEach(e => {
                    let priceSearch = e.querySelector('.product-pa-wrapper .product-price:last-child').getAttribute('data-price') * 1
                    if(priceSearch <= 40000) {
                        result++
                        e.style = ''
                    }  else {
                        e.style = 'display: none;'
                    }
                })
                if(result == 0) {
                    Swal.fire('Message', 'Không có sản phẩm nào dưới 40.000đ được tìm thấy', 'info')
                    .then(rs => {
                        items.forEach(e => {
                            e.style = ''
                        })
                    })                        
                }
                break
            }
            case '40.000đ - 100.000đ' : {
                let result = 0
                items.forEach(e => {
                    let priceSearch = e.querySelector('.product-pa-wrapper .product-price:last-child').getAttribute('data-price') * 1
                    if(priceSearch >= 40000 && priceSearch <= 100000) {
                        result++
                        e.style = ''
                    }  else {
                        e.style = 'display: none;'
                    }
                })
                if(result == 0) {
                    Swal.fire('Message', 'Không có sản phẩm nào có giá từ 40.000đ - 100.000đ', 'info')
                    .then(rs => {
                        items.forEach(e => {
                            e.style = ''
                        })
                    })                        
                }
                break
            }
            case '100.000đ - 300.000đ' : {
                let result = 0
                items.forEach(e => {
                    let priceSearch = e.querySelector('.product-pa-wrapper .product-price:last-child').getAttribute('data-price') * 1
                    if(priceSearch >= 100000 && priceSearch <= 300000) {
                        result++
                        e.style = ''
                    }  else {
                        e.style = 'display: none;'
                    }
                })
                if(result == 0) {
                    Swal.fire('Message', 'Không có sản phẩm nào có giá từ 100.000đ - 300.000đ', 'info')
                    .then(rs => {
                        items.forEach(e => {
                            e.style = ''
                        })
                    })                        
                }
                break
            }
            case '300.000đ - 500.000đ' : {
                let result = 0
                items.forEach(e => {
                    let priceSearch = e.querySelector('.product-pa-wrapper .product-price:last-child').getAttribute('data-price') * 1
                    if(priceSearch >= 300000 && priceSearch <= 500000) {
                        result++
                        e.style = ''
                    }  else {
                        e.style = 'display: none;'
                    }
                })
                if(result == 0) {
                    Swal.fire('Message', 'Không có sản phẩm nào có giá từ 300.000đ - 500.000đ', 'info')
                    .then(rs => {
                        items.forEach(e => {
                            e.style = ''
                        })
                    })                        
                }
                break
            }
            case 'Trên 500.000đ' : {
                let result = 0
                items.forEach(e => {
                    let priceSearch = e.querySelector('.product-pa-wrapper .product-price:last-child').getAttribute('data-price') * 1
                    if(priceSearch >= 500000) {
                        result++
                        e.style = ''
                    }  else {
                        e.style = 'display: none;'
                    }
                })
                if(result == 0) {
                    Swal.fire('Message', 'Không có sản phẩm nào trên 500.000đ được tìm thấy', 'info')
                    .then(rs => {
                        items.forEach(e => {
                            e.style = ''
                        })
                    })                        
                }
                break
            }
        }

        
    }
}

function sortSP() {
    _$('.vnpay-overlay.loading-filter').style.display = 'unset'
    _$('.vnpay-loading.loading-filter').style.display = 'unset'

    let option = _$('select[name="orderby"]')
    let value = option.value
    let items = _$$('.product-wrap')
    first_arr_product = first_arr_product.length == 0 ? items : first_arr_product

    setTimeout(handleSortSP, 274)

    function handleSortSP() {
        let html = ''
        if(value == 0) { // mặc định
                        
            first_arr_product.forEach(e => {
                html += `<div class="product-wrap" style="display: ${e.style.display};">${e.innerHTML}</div>`
            })
        } else if(value == 1) { // rating: cao -> thap
            let htmlArr = [...items]
            for (let i = 0; i < htmlArr.length - 1; i++) {
                for (let j = i + 1; j < htmlArr.length; j++) {
                    let rateI = htmlArr[i]
                        .querySelector('.total-rating_k')
                        .getAttribute('data-rating') * 1
                    let rateJ = htmlArr[j]
                        .querySelector('.total-rating_k')
                        .getAttribute('data-rating') * 1
                    if (rateI < rateJ) {
                        [htmlArr[i], htmlArr[j]] = [htmlArr[j], htmlArr[i]];
                    }
                }
            }
            
            htmlArr.forEach(e => {
                html += `<div class="product-wrap" style="display: ${e.style.display};">${e.innerHTML}</div>`
            })
        } else if(value == 2) { // rating: thap -> cao
            let htmlArr = [...items]
            for (let i = 0; i < htmlArr.length - 1; i++) {
                for (let j = i + 1; j < htmlArr.length; j++) {
                    let rateI = htmlArr[i]
                        .querySelector('.total-rating_k')
                        .getAttribute('data-rating') * 1
                    let rateJ = htmlArr[j]
                        .querySelector('.total-rating_k')
                        .getAttribute('data-rating') * 1
                    if (rateI > rateJ) {
                        [htmlArr[i], htmlArr[j]] = [htmlArr[j], htmlArr[i]];
                    }
                }
            }
            
            htmlArr.forEach(e => {
                html += `<div class="product-wrap" style="display: ${e.style.display};">${e.innerHTML}</div>`
            })
        } else if(value == 3) { // giá: cao -> thap
            let htmlArr = [...items]
            for (let i = 0; i < htmlArr.length - 1; i++) {
                for (let j = i + 1; j < htmlArr.length; j++) {
                    let priceI = htmlArr[i]
                        .querySelector('.product-pa-wrapper .product-price:last-child')
                        .getAttribute('data-price') * 1
                    let priceJ = htmlArr[j]
                        .querySelector('.product-pa-wrapper .product-price:last-child')
                        .getAttribute('data-price') * 1
                    if (priceI < priceJ) {
                        [htmlArr[i], htmlArr[j]] = [htmlArr[j], htmlArr[i]];
                    }
                }
            }
            
            htmlArr.forEach(e => {
                html += `<div class="product-wrap" style="display: ${e.style.display};">${e.innerHTML}</div>`
            })
        } else if(value == 4) { // giá: thap -> cao
            let htmlArr = [...items]
            for (let i = 0; i < htmlArr.length - 1; i++) {
                for (let j = i + 1; j < htmlArr.length; j++) {
                    let priceI = htmlArr[i]
                        .querySelector('.product-pa-wrapper .product-price:last-child')
                        .getAttribute('data-price') * 1
                    let priceJ = htmlArr[j]
                        .querySelector('.product-pa-wrapper .product-price:last-child')
                        .getAttribute('data-price') * 1
                    if (priceI > priceJ) {
                        [htmlArr[i], htmlArr[j]] = [htmlArr[j], htmlArr[i]];
                    }
                }
            }
            
            htmlArr.forEach(e => {
                html += `<div class="product-wrap" style="display: ${e.style.display};">${e.innerHTML}</div>`
            })
        }
        let wrapGridProduct = _$('#shop-grid-js')
        wrapGridProduct.innerHTML = html
        _$('.vnpay-overlay.loading-filter').style.display = 'none'
        _$('.vnpay-loading.loading-filter').style.display = 'none'
    }
}