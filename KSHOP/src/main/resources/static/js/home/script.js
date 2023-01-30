const loadProduct = (data, wrapClass) => {
    let wrapProduct = _$(wrapClass)
    let html = ``
    let dataEach = data.products

    if(!data.products) {
        dataEach = data
        data.reviews = []
    }

    dataEach.forEach((e ,i) => {
        if(!data.products) {
            data.reviews[i] = {
                rating: 0,
                review_count: 0
            }
        }
        html += /*html*/ `<div class="product-wrap">
            <div class="product text-center product-wrap-list">
                <figure class="product-media">
                    <a href="/product/${e.product_code}-${e.id}">
                        <img class="home-product" src="${e.image}" alt="Product" width="300" height="338">
                        <img class="home-product" src="${e.image}" alt="Product" width="300" height="338">
                    </a>
                    <div class="product-action-vertical">
                        
                        <a href="#" class="btn-product-icon btn-wishlist w-icon-heart" title="Add to wishlist"></a>
                        <a href="#" class="btn-product-icon btn-quickview w-icon-search" title="Quickview"></a>
                        <a href="#" class="btn-product-icon btn-compare w-icon-compare" title="Add to Compare"></a>
                    </div>
                </figure>
                <div class="product-details">
                    <h4 class="product-name"><a href="/product/${e.product_code}-${e.id}">${e.product_name}</a></h4>
                    <div class="ratings-container">
                        <div class="ratings-full">
                            <span class="ratings" style="width: ${data.reviews[i].rating * 20}%;"></span>
                            <span class="tooltiptext tooltip-top"></span>
                        </div>
                        <a href="/product/${e.product_code}-${e.id}" class="rating-reviews">(${data.reviews[i].review_count} Đánh giá)</a>
                    </div>
                    <div class="product-price">
                        <ins class="new-price">${formatVND(e.list_price)}</ins>
                    </div>
                </div>
            </div>
        </div>`
    })
    wrapProduct.innerHTML = html
}

const loadProductSwip = (data, wrapClass) => {
    let wrapProduct = _$(wrapClass)
    let html = ``
    data.forEach((e, i) => {
        if(i % 2 == 0) {
            html += `<div class="swiper-slide product-col">`
        }
        html += /*html */`<div class="product-wrap product text-center product-wrap-list">
            <figure class="product-media">
                <a href="#">
                    <img src="${e.image}" alt="Product" width="216" height="243">
                </a>
                <div class="product-action-vertical">
                    
                    <a href="#" class="btn-product-icon btn-wishlist w-icon-heart"
                        title="Add to wishlist"></a>
                    <a href="#" class="btn-product-icon btn-quickview w-icon-search"
                        title="Quickview"></a>
                    <a href="#" class="btn-product-icon btn-compare w-icon-compare"
                        title="Add to Compare"></a>
                </div>
            </figure>
            <div class="product-details">
                <h4 class="product-name"><a href="/product/${e.product_code}-${e.id}">${e.product_name}</a></h4>
                <div class="ratings-container">
                    <div class="ratings-full">
                        <span class="ratings" style="width: 60%;"></span>
                        <span class="tooltiptext tooltip-top"></span>
                    </div>
                    <a href="/product/${e.product_code}-${e.id}" class="rating-reviews">(1Đánh giá)</a>
                </div>
                <div class="product-price">
                    <ins class="new-price">${formatVND(e.list_price)}</ins>
                </div>
            </div>
        </div>`
        if(i % 2 == 1) {
            html += `</div>`
        }
    })

    wrapProduct.innerHTML = html
}

const loadProductTopSwip = (data) => {
    let wrap = _$('.swiper-top-best-seller')
    let html = ``
    
    data.products.forEach((e, i) => {
        if(i % 3 == 0) {
            html += `<div class="swiper-slide product-widget-wrap">`
        }
        html += /*html*/`<div class="product product-widget bb-no">
            <figure class="product-media">
                <a href="/product/${e.product_code}-${e.id}">
                    <img src="${e.image}" alt="${e.product_name}" width="105" height="118">
                </a>
            </figure>
            <div class="product-details">
                <h4 class="product-name">
                    <a href="/product/${e.product_code}-${e.id}">${e.product_name}</a>
                </h4>
                <div class="ratings-container">
                    <div class="ratings-full">
                        <span class="ratings" style="width: ${data.reviews[i].rating * 20}%;"></span>
                        <span class="tooltiptext tooltip-top"></span>
                    </div>
                </div>
                <div class="product-price">
                    <ins class="new-price">${formatVND(e.list_price)}</ins>
                </div>
            </div>
        </div>`
        if(i % 3 == 2) {
            html += `</div>`
        }
    })
    wrap.innerHTML = html
}

const addToCartIndex = (btn, id, slConLai) => {
    if(!btn.classList.contains('disabled')) {
        let quantityAdd = _$('#quantity_' + id).value * 1
        let productInCart = 0
        dataCartList.forEach(e => {
            if(e.product.id == id) productInCart += e.quantity
        })
        btn.classList.add('notAdd')
        if(quantityAdd > slConLai) {
            Swal.fire('Message', 'Sản phẩm này chỉ còn lại ' + slConLai + ' sản phẩm.', 'info')
        } else if(quantityAdd + productInCart > slConLai ) {
            Swal.fire("Message", "Số lượng còn lại của sản phẩm không đủ. Vui lòng thử lại sau.", "info")
        } else if(!isNumeric(quantityAdd) && quantityAdd < 0) {
            Swal.fire("Message", "Số lượng không hợp lệ, vui lòng thử lại.", "error")
            btn.classList.add('disabled')
        } else {
            btn.classList.remove('notAdd')
            addProductToCart(id, quantityAdd)
        }
    }
}

const loadProductDealHotOfDay = (data) => {
    let wrap = _$('.swiper-deal-of-day')
    let html = ''
    const handle = (dataProductImg, e, i) => {
        let productImgs = '', smallImg = ''
        if(dataProductImg.length == 1) {
            dataProductImg = [...dataProductImg, ...dataProductImg ,...dataProductImg, ...dataProductImg]
        }

        dataProductImg.forEach(element => {
            element.image = (element.id != null) ? element.image : e.image
            productImgs += `<div class="swiper-slide">
                <figure class="product-image">
                    <img src="${element.image}"
                        data-zoom-image="${element.image}"
                        alt="Product Image" width="800" height="900">
                </figure>
            </div>`
            smallImg += `<div class="product-thumb swiper-slide">
                <img src="${element.image}" alt="Product thumb"
                    width="60" height="68">
            </div>`
        })
        
        html += /*html */`<div class="swiper-slide">
            <div class="product product-single row">
                <div class="col-md-6">
                    <div
                        class="product-gallery product-gallery-sticky product-gallery-vertical">
                        <div
                            class="swiper-container product-single-swiper swiper-theme nav-inner">
                            <div class="swiper-wrapper row cols-1 gutter-no">
                                <div class="swiper-slide">
                                    <figure class="product-image">
                                        <img src="${e.image}"
                                            data-zoom-image="${e.image}"
                                            alt="Product Image" width="800" height="900">
                                    </figure>
                                </div>
                                ${productImgs}
                            </div>
                            <button class="swiper-button-next"></button>
                            <button class="swiper-button-prev"></button>
                            <div class="product-label-group">
                                <label class="product-label label-discount">HOT</label>
                            </div>
                        </div>
                        <div class="product-thumbs-wrap swiper-container" data-swiper-options="{
                            'direction': 'vertical',
                            'breakpoints': {
                                '0': {
                                    'direction': 'horizontal',
                                    'slidesPerView': 4
                                },
                                '992': {
                                    'direction': 'vertical',
                                    'slidesPerView': 'auto'
                                }
                            }
                        }">
                            <div
                                class="product-thumbs swiper-wrapper row cols-lg-1 cols-4 gutter-sm">
                                ${smallImg}
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="product-details scrollable">
                        <h2 class="product-title mb-1"><a href="/product/${e.product_code}-${e.id}">${e.product_name}</a></h2>

                        <hr class="product-divider">

                        <div class="product-price"><ins class="new-price ls-50">${formatVND(e.list_price)} - <span style=" text-decoration: line-through; font-weight: 400; font-size: 18px; font-style: italic; ">${formatVND(e.standard_cost)}</span></ins></div>

                        <div class="product-countdown-container flex-wrap">
                            <label class="mr-2 text-default">Offer Ends In:</label>
                            <div class="product-countdown countdown-compact"
                                data-until="2022, 12, 31" data-compact="true">
                                629 days, 11: 59: 52</div>
                        </div>

                        <div class="ratings-container">
                            <div class="ratings-full">
                                <span class="ratings" style="width: ${data.reviews[i].rating * 20}%;"></span>
                                <span class="tooltiptext tooltip-top"></span>
                            </div>
                            <a href="#" class="rating-reviews">(${data.reviews[i].review_count}Đánh giá)</a>
                        </div>

                        <div
                            class="product-form product-variation-form product-size-swatch mb-3">
                            <label class="mb-1">Size:</label>
                            <div class="flex-wrap d-flex align-items-center product-variations">
                                <a href="#" class="size">Extra Large</a>
                                <a href="#" class="size">Large</a>
                                <a href="#" class="size">Medium</a>
                                <a href="#" class="size">Small</a>
                            </div>
                            <a href="#" class="product-variation-clean">Clean All</a>
                        </div>

                        <div class="product-variation-price">
                            <span></span>
                        </div>

                        <div class="product-form pt-4">
                            <div class="product-qty-form mb-2 mr-2">
                                <div class="input-group">
                                    <input id="quantity_${e.id}" class="quantity form-control" type="number" min="1"
                                        max="10000000">
                                    <button class="quantity-plus w-icon-plus"></button>
                                    <button class="quantity-minus w-icon-minus"></button>
                                </div>
                            </div>
                            <button onclick="addToCartIndex(this, ${e.id}, ${e.quantity})" class="btn btn-primary btn-cart">
                                <i class="w-icon-cart"></i>
                                <span>Add to Cart</span>
                            </button>
                        </div>

                        <div class="social-links-wrapper mt-1">
                            <div class="social-links">
                                <div class="social-icons social-no-color border-thin">
                                    <a href="#"
                                        class="social-icon social-facebook w-icon-facebook"></a>
                                    <a href="#"
                                        class="social-icon social-twitter w-icon-twitter"></a>
                                    <a href="#"
                                        class="social-icon social-pinterest fab fa-pinterest-p"></a>
                                    <a href="#"
                                        class="social-icon social-whatsapp fab fa-whatsapp"></a>
                                    <a href="#"
                                        class="social-icon social-youtube fab fa-linkedin-in"></a>
                                </div>
                            </div>
                            <span class="divider d-xs-show"></span>
                            <div class="product-link-wrapper d-flex">
                                <a href="#"
                                    class="btn-product-icon btn-wishlist w-icon-heart"></a>
                                <a href="#"
                                    class="btn-product-icon btn-compare btn-icon-left w-icon-compare"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
        wrap.innerHTML = html
    }

    data.products.forEach((e, i) => {
        http.get(`${_URL_MAIN}/ProductImages/get/productid/${e.id}`)
        .then(dataProductImg => {
            handle(dataProductImg, e, i)
        })
    })
}

// //swiper-top-best-seller
// http.get("http://localhost:8080/Products/get/0/2")
//     .then(data => loadProductDealHotOfDay(data.data))

// //swiper-top-best-seller
// http.get("http://localhost:8080/Products/get/0/9")
//     .then(data => loadProductTopSwip(data.data))

//new-products
http.get("http://localhost:8080/Products/get/0/30")
    .then(data => loadProduct(data.data, '.new-products'))
    .catch(err => console.log(err))
//best-seller-products
http.get("http://localhost:8080/Products/get/best-seller/0/30")
    .then(data => loadProduct(data, '.best-seller-products'))
    .catch(err => console.log(err))
//best-popular-products
http.get("http://localhost:8080/Products/get/best-popular/0/30")
    .then(data => loadProduct(data, '.best-popular-products'))
    .catch(err => console.log(err))
//featured-products
http.get("http://localhost:8080/Products/get/featured/0/30")
    .then(data => loadProduct(data, '.featured-products'))
    .catch(err => console.log(err))

//swiper-trangphuc
http.get("http://localhost:8080/Products/get/category/trangphuc")
    .then(data => loadProductSwip(data, '.swiper-trangphuc'))
    .catch(err => console.log(err))

//swiper-dientu
http.get("http://localhost:8080/Products/get/category/dientu")
    .then(data => loadProductSwip(data, '.swiper-dientu'))
    .catch(err => console.log(err))

//swiper-nhavsbep
http.get("http://localhost:8080/Products/get/category/nhavsbep")
    .then(data => loadProductSwip(data, '.swiper-nhavsbep'))
    .catch(err => console.log(err))

