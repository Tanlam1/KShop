let dataArrayImgs = [..._$$('.product-thumb img')].map(e => e.src)
let dataArrayImgsIndex = dataArrayImgs.map((e, i) => i)
let dataTypeListImgs = []

_$$('.priceFormat').forEach(e => {
    e.innerText = formatVND(parseFloat(e.innerText))
})
let quantityProductHTML = _$('.products-quantity span')
let quantityProductHTMLS = _$('.product-variation-clean span')
if(quantityProductHTML && quantityProductHTMLS) {
    quantityProductHTML.innerHTML = (quantityProductHTML.innerHTML)* 1
    quantityProductHTMLS.innerHTML = (quantityProductHTMLS.innerHTML)* 1
}


const addProductInDetail = (button) => {
    let id = _$('#product_id')
    let quantity = _$('.quantity')
    let typeSelects = _$$('.product-variations a.active')
    let type_type_id = ''
    if(typeSelects) {

        typeSelects.forEach(e => {
            type_type_id += e.getAttribute('data-id')*1 + ','
        })
        type_type_id = (type_type_id + 'kodoku').replaceAll(',kodoku', '').replaceAll('kodoku', '')
    }

    if(id && quantity && !button.classList.contains('disabled')) {
        let slConLai = _$('.product-sku.products-quantity span').innerHTML*1
        let productInCart = 0
        dataCartList.forEach(e => {
            if(e.product.id == id.value) productInCart += e.quantity
        })
        button.classList.add('notAdd')
        if(quantity.value*1 < 1) {
            Swal.fire('Message', 'Số lượng không hợp lệ.', 'info')

        } else if(quantity.value*1 > slConLai) {
            Swal.fire('Message', _$('.product-title').innerHTML + ' chỉ còn lại ' + slConLai + ' sản phẩm.', 'info')
        } else if(quantity.value*1 + productInCart > slConLai ) {
            Swal.fire("Message", "Số lượng còn lại của sản phẩm không đủ. Vui lòng thử lại sau.", "info")
        } else {
            button.classList.remove('notAdd')
            addProductToCart(id.value, quantity.value*1, type_type_id)
        }
    }
}

const listTypeBtn = _$$('.product-variations > a')
if(listTypeBtn) {
    listTypeBtn.forEach(e => {
        e.addEventListener('click', () => {
            let dataListImgs = e.getAttribute('data-list-imgs')
            if(dataListImgs) {

                dataTypeListImgs = JSON.parse(`[${dataListImgs}]`)
                let arrayIndex = [...dataTypeListImgs, ...dataArrayImgsIndex.filter(e => !dataTypeListImgs.includes(e))]

                renderImgProducts(arrayIndex)
            }
        })
    })
}

function renderImgProducts (arrayIndex) {

    let wrapProductBig = _$$('.product-image > img:first-child')
    let wrapProductBigZoom = _$$('.product-image > img:last-child')
    let wrapProductSmall = _$$('.product-thumb img')

    if(wrapProductBig && wrapProductSmall) {
        arrayIndex.forEach((e, i) => {
            wrapProductBig[i].src = dataArrayImgs[arrayIndex[i]]
            wrapProductBigZoom[i].src = dataArrayImgs[arrayIndex[i]]
            wrapProductSmall[i].src = dataArrayImgs[arrayIndex[i]]
        })
    }

}