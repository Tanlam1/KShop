const loadStore = (data) => {
    let wrapStore = document.querySelector('.store-js')
    let html = /*html*/`<figure class="store-media">
    <img src="${data.background}" alt="Vendor" width="930" height="446" style="background-color: #414960;">
</figure>
<div class="store-content">
    <figure class="seller-brand">
        <img src="${data.image}" alt="Brand" width="80" height="80">
    </figure>
    <h4 class="store-title">${data.store_name}</h4>
    <ul class="seller-info-list list-style-none mb-6">
        <li class="store-address">
            <i class="w-icon-map-marker"></i>
            ${data.address}
        </li>
        <li class="store-phone">
            <a href="tel:1234567890">
                <i class="w-icon-phone"></i>
                ${data.phone}
            </a>
        </li>
        <li class="store-rating">
            <i class="w-icon-star-full"></i>
            4.33 rating from 3 reviews
        </li>
        <li class="store-open">
            <i class="w-icon-cart"></i>
            Store Open
        </li>
    </ul>
    <div class="social-icons social-no-color border-thin">
        <a href="#" class="social-icon social-facebook w-icon-facebook"></a>
        <a href="#" class="social-icon social-google w-icon-google"></a>
        <a href="#" class="social-icon social-twitter w-icon-twitter"></a>
        <a href="#" class="social-icon social-pinterest w-icon-pinterest"></a>
        <a href="#" class="social-icon social-youtube w-icon-youtube"></a>
        <a href="#" class="social-icon social-instagram w-icon-instagram"></a>
    </div>
</div>`
    wrapStore.innerHTML = html
}

var storeId = _$('#storeId').value
http.get(`${_URL_MAIN}/ShopStores/get/${storeId}`)
    .then(data => loadStore(data))
    .catch(err => console.log(err))



_$$('.product-price').forEach(e => {
    e.innerText = formatVND(parseFloat(e.innerText))
})

