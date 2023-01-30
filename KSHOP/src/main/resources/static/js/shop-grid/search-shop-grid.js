// let limit = 12
// let currentPage = 1
// let pagePrev, pageNext
// let htmlPagePrev, htmlPageNext
// let total_page, total_product

// const loadShopGridProduct = (data) => {
//     let wrapGridProduct = _$('#shop-grid-js')
//     let html = ``

//     console.log(data)
//     data.forEach(e => {
//         html += /*html*/ `<div class="product-wrap">
//         <div class="product text-center">
//             <figure class="product-media">
//                 <a href="/product/${e.product_code}-${e.id}"> <img src="/${e.image}" alt="Product" width="300" height="338">
//                 </a>
//                 <div class="product-action-horizontal">
//                     <a href="#" class="btn-product-icon btn-cart w-icon-cart" title="Add to cart"></a> <a href="#" class="btn-product-icon btn-wishlist w-icon-heart" title="Wishlist"></a> <a href="#" class="btn-product-icon btn-compare w-icon-compare" title="Compare"></a> <a href="#" class="btn-product-icon btn-quickview w-icon-search" title="Quick View"></a>
//                 </div>
//             </figure>
//             <div class="product-details">
//                 <div class="product-cat">
//                     <a href="shop-banner-sidebar.html">${e.categories.category_name}</a>
//                 </div>
//                 <h3 class="product-name">
//                     <a href="/product/${e.product_code}-${e.id}">${e.product_name}</a>
//                 </h3>
//                 <div class="ratings-container">
//                     <div class="ratixngs-full">
//                         <span class="ratings" style="width: 100%;"></span> <span class="tooltiptext tooltip-top"></span>
//                     </div>
//                     <a href="/product/${e.product_code}-${e.id}" class="rating-reviews">(3
//                         reviews)</a>
//                 </div>
//                 <div class="product-pa-wrapper">
//                     <div class="product-price">${formatVND(e.list_price)}</div>
//                 </div>
//             </div>
//         </div>
//     </div>`
//     })
//     wrapGridProduct.innerHTML = html
// }

// const loadPages = (totalProduct) => {
//     let pagination = _$('.pagination')
//     total_product = totalProduct

//     total_page = Math.floor(total_product / limit) + 1

//     let htmlPages = ``,
//         htmlPagesPrev = ``,
//         htmlPagesNext = ``

//     for (let i = currentPage + 1; i <= currentPage + 3; i++) {
//         if (i > total_page) break
//         htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
//             <button class="page-link" onclick="genderProduct(${i})">${i}</button>
//         </li>`
//     }

//     for (let i = currentPage - 1; i >= currentPage - 3; i--) {
//         if (i < 1) break
//         htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
//             <button class="page-link" onclick="genderProduct(${i})">${i}</button>
//         </li>` + htmlPagesPrev
//     }

//     htmlPages += `<li class="page-item active">
//         <button class="page-link" onclick="genderProduct(${currentPage})">${currentPage}</button>
//     </li>`

//     pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
//     pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
//     htmlPagePrev = `<li class="page-item">
//         <button class="page-link" onclick="genderProduct(${pagePrev})"
//             tabindex="-1">Previous</button>
//     </li>`
//     htmlPageNext = `<li class="page-item">
//         <button class="page-link" onclick="genderProduct(${pageNext})"
//             tabindex="-1">Next</button>
//     </li>`

//     pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
// }

// const params = new Proxy(new URLSearchParams(window.location.search), {
//     get: (searchParams, prop) => searchParams.get(prop),
//   });

//   let value1 = params.keyWords; // "some_value"

// //   console.log(value)
// http.get(`http://localhost:8080/Products/get/products/${value1}`)
//     .then(data => loadPages(data.length))
//     .catch(err => console.log(err))

// const genderProduct = (page) => {
//     currentPage = page
//     loadPages(total_product)
//     window.scrollTo({
//         top: 0,
//         behavior: 'smooth'
//     })
//     http.get(`http://localhost:8080/Products/get/products/${value1}/${currentPage-1}/${limit}`)
//         .then(data => loadShopGridProduct(data))
//         .catch(err => console.log(err))
// }
// genderProduct(currentPage)

// // let keySearchGrip = _$('#keySearch').innerHTML
// // http.get(`http://localhost:8080/Products/get/products/${keySearchGrip}`)
// //     .then(data => loadShopGridProduct(data))
// //     .catch(err => console.log(err))