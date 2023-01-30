let imgProducts = [], typeProducts = []

const addProduct = () => {
    
    let newProduct = productInvalid()

    if(!newProduct) {
        return false
    }

    http.post(`${_URL_MAIN}/Products/add`, newProduct)
    .then(data => {
        if(data.status == 200) {
            //thêm thành công, tiếp tục xử lý type product và img product
            //setProductType() // trả về mảng typeProducts
            addListImgDB(data.data.id)
            addTypeProductDB(data.data.id)

            Swal.fire('Success', 'Thêm sản phẩm thành công', 'success')
            .then(rs => window.location.href = `${_ADMIN_DIR}/product`)
        } else if(data.status == 21) {
            Swal.fire('Message', data.data, 'info')
        } else if(data.status == 19) {
            Swal.fire('Message', data.data, 'info')
        } else {
            Swal.fire('Message', data.data, 'error')
        }
    })
    
}

const editProduct = () => {

    let productEdit = productInvalid(1)
    
    if(!productEdit) {
        return false
    }

    //Xử lý phần imgProducts

    //Xử lý phần typeProducts
    
    http.put(`${_URL_MAIN}/Products/update`, productEdit)
    .then(data => {
        if(data.status == 200) {
            let id = _$('#product_id').value * 1
            //thêm thành công, tiếp tục xử lý type product và img product
            //setProductType() // trả về mảng typeProducts
            addListImgDB(id)
            addTypeProductDB(id)

            Swal.fire('Success', 'Chỉnh sửa sản phẩm thành công', 'success')
            .then(rs => window.location.reload())
        } else if(data.status == 21) {
            Swal.fire('Message', data.data, 'info')
        } else if(data.status == 19) {
            Swal.fire('Message', data.data, 'info')
        } else {
            Swal.fire('Message', data.data, 'error')
        }
    })
}

function productInvalid(type = 0) {
    let product_name = _$('#product_name')
    let product_code = _$('#product_code')
    let image = _$('#image')
    let short_description = _$('#short_description')
    let description = _$('.ck-content')
    let standard_cost = _$('#standard_cost')
    let list_price = _$('#list_price')
    let quantity_per_unit = _$('#quantity_per_unit')
    let category_id = _$('#categories')
    let supplier_id = _$('#supplier')
    let quantity = _$('#quantity')
    let height = _$('#height')
    let width = _$('#width')
    let weight = _$('#weight')
    let length = _$('#length')
    let discontinued = false
    let is_featured = false
    let is_new = false
    

    if(!product_name || product_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên sản phẩm', 'info')
        return false
    }

    if(!product_code || product_code.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mã sản phẩm (slug)', 'info')
        return false
    }

    if(!image || image.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập hình ảnh chính của sản phẩm', 'info')
        return false
    }

    if(!short_description || short_description.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mô tả ngắn sản phẩm', 'info')
        return false
    }

    if(!description || description.innerHTML.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập mô tả sản phẩm', 'info')
        return false
    }

    if(!standard_cost || standard_cost.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập giá nhập của sản phẩm', 'info')
        return false
    }

    if(!list_price || list_price.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập giá niêm yết sản phẩm', 'info')
        return false
    }

    if(!quantity || quantity.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số lượng còn lại', 'info')
        return false
    }

    if(!quantity_per_unit || quantity_per_unit.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số lượng trên mỗi đơn vị', 'info')
        return false
    }

    if(!height || height.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập chiều cao của sản phẩm', 'info')
        return false
    }

    if(!width || width.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập chiều rộngcủa sản phẩm', 'info')
        return false
    }

    if(!length || length.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập chiều dài của sản phẩm', 'info')
        return false
    }

    if(!weight || weight.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập khối lượng của sản phẩm', 'info')
        return false
    }

    let result = {
        product_code: product_code.value,
        product_name: product_name.value,
        image: image.value,
        short_description: short_description.value,
        description: description.innerHTML,
        standard_cost: standard_cost.value * 1,
        list_price: list_price.value * 1,
        quantity_per_unit: quantity_per_unit.value,
        quantity: quantity.value,
        height: height.value,
        width: width.value,
        length: length.value,
        weight: weight.value,
        discontinued, is_featured, is_new,
        categories: {
            id: category_id.value
        },
        suppliers: {
            id: supplier_id.value
        }
    }

    if(type == 1) { // edit
        result.id = _$('#product_id').value * 1
        result.is_featured = _$('#is_featured').checked
        result.is_new = _$('#is_new').checked
    }

    return result
}

_$$('input[type="number"]').forEach(e => {
    if(e) {
        e.value = parseFloat((e.value) * 1)
    }
})

// set scale img
let imgsScale = _$$('.imgs__product-item')

if(imgsScale) {
    imgsScale.forEach(e => {
        setInterval(()=>{
            e.classList.toggle('scaleimg')
        }, 900)
    })
}

const onChangeFile = (e, output = '', outputSrc = '') => {
    let outputDOM = output == '' ? false : _$(output);
    let outputSrcDOM = outputSrc == '' ? false : _$(outputSrc);
    
    if(outputDOM) { // upload 1 ảnh trong admin
        uploadToImbb(e, outputDOM, outputSrcDOM, (response) => {
            if(response.status_code == 200) {

                let imgReview = _$('.product-img-review')
                if(imgReview) {
                    imgReview.src = response.image.display_url
                    flash('success', 'Upload hình ảnh sản phẩm thành công.')
                    
                }
            } else {
                flash('error', 'Upload thất bại, vui lòng thử lại.')
            }
        })
    }

    if(!outputDOM) { // nhiều ảnh
        uploadToImbb(e, false, outputSrcDOM, (response) => {
            if(response.status_code == 200) {
                
                let imgRV = _$('.imgs__product-item.loadding')
                if(imgRV) {
                    imgRV.src = response.image.display_url
                    imgRV.classList.remove('loadding')
                    setProductImgs()
                    addBehindProductTypeImg(response.image.display_url)
                    handleTypesImgItem()
                    flash('success', 'Upload hình ảnh sản phẩm thành công.')
                }
            } else {
                flash('error', 'Upload thất bại, vui lòng thử lại.')
            }
        })
        outputSrcDOM.innerHTML = outputSrcDOM.innerHTML + 
        `<button class="button-custom remove" onclick="removeProductImgs()" data-bs-original-title="" title="">Xóa tất cả</button>`
        outputSrcDOM.classList.add('hasImgs')
    }
}

//Remove "hình ảnh khác"
function removeProductImgs(btn = '') {
    let idProduct = _$('#product_id') ? _$('#product_id').value * 1 : 0
    
    Swal.fire({
        title: "Message",
        text: `Bạn đang muốn xóa ${btn == '' ? 'tất cả' : ''} ảnh của sản phẩm này ?`,
        icon: "info",
        showCancelButton: true,
        cancelButtonColor: '#d33',
        confirmButtonText: 'Đúng vậy'
    }).then((oke) => {
        if (oke.isConfirmed) {
            
            let wrap = _$('.wrap__imgs-rv')
            let dfHtml = `<div class="col-sm-4 mt-4">
                <div class="review__imgs-product">
                    <img src="https://www.proclinic-products.com/build/static/default-product.30484205.png" alt="IMG" class="imgs__product-item">            
                </div>
            </div>
            <div class="col-sm-4 mt-4">
                <div class="review__imgs-product">
                    <img src="https://www.proclinic-products.com/build/static/default-product.30484205.png" alt="IMG" class="imgs__product-item">            
                </div>
            </div>
            <div class="col-sm-4 mt-4">
                <div class="review__imgs-product">
                    <img src="https://www.proclinic-products.com/build/static/default-product.30484205.png" alt="IMG" class="imgs__product-item">            
                </div>
            </div>`

            if(wrap) {
                if(btn == '') {
                    wrap.innerHTML = dfHtml
                } else {
                    if(btn.parentNode.parentNode) {
                        btn.parentNode.parentNode.remove()
                        let review__imgs = btn.parentNode.querySelector('img')
                        if(review__imgs) {
                            removeBehindProductTypeImg(review__imgs.src)
                            setProductImgs()
                        }
                    }
                }

                if(wrap.innerHTML.trim() == '') {
                    wrap.innerHTML = dfHtml
                }

            }
            // xóa trên DB
            if(idProduct > 0) {
                if(btn == '') { // xóa tất cả

                    http.delete(`${_URL_MAIN}/ProductImages/delete/all/productid/${idProduct}`)
                    .then(data => {
                        if(data.status == 200) {
                            flash('success', data.data)
                        } else {
                            flash('error', data.data)
                        }
                    })
                } else {
                    let idPrImg = btn.parentNode.querySelector('img').getAttribute('data-id')*1
                    
                    http.delete(`${_URL_MAIN}/ProductImages/delete/${idPrImg}`)
                    .then(data => {
                        if(data.status == 200) {
                            flash('success', data.data)
                        } else {
                            flash('error', data.data)
                        }
                    })
                }
            }
        }
    })

    
}

/**
 * Tùy chọn sản phẩm Product Type
 */
//Get product type từ api
http.get(`${_URL_MAIN}/ProductType/get`)
.then(data => {
    let html = '', wrap = _$('.product__types')
    data.forEach(e => {
        html += /*html*/`<div class="product__types-item">
            <div class="product__types-item--id">
                SPT${e.id}
            </div>
            <div class="product__types-item--name" data-id="${e.id}">
                <span>${e.name}</span> <br>(${e.description})
            </div>
            <div class="product__types-action">
                <button class="button-custom add__type-products">Thêm</button>
            </div>
        </div>`
    })
    wrap.innerHTML = html
    handleAddType()
})

function toggleBtnAddType() { // toggle của `thêm mới`
    let btnAddTypeProduct = _$('.add_type_sp') 
    let productTypes = _$('.product__types') 
    if(btnAddTypeProduct) {
        btnAddTypeProduct.addEventListener('click', () => {
            productTypes.classList.toggle('show')
        })
    }
}toggleBtnAddType()

function handleTypesImgItem() { // add sự kiện click selected img của type
    let types_img_item = _$$('.product__types-img-item')

    if(types_img_item) {
        types_img_item.forEach(e => {
            if(!e.classList.contains('hasEvent')) {
                e.addEventListener('click', () => {
                    e.classList.toggle('selected')
                    console.log('selected');
                })
                e.classList.add('hasEvent')
            }
        })
    }
}handleTypesImgItem()

function handleAddType() { // Thêm 1 ProductType mới vào sản phẩm
    
    let addTypeProducts = _$$('.add__type-products')
    let wrapTypes = _$('.wrap__products-type')
    if(addTypeProducts && wrapTypes) {
        addTypeProducts.forEach(e => {
            let parent_node = e.parentNode.parentNode
            let name_type = parent_node.querySelector('.product__types-item--name>span')
            let id_type = parent_node.querySelector('.product__types-item--name').getAttribute('data-id')
            e.addEventListener('click', () => {

                let html = /*html*/`<div class="shop__products-type--item" data-id="${id_type}">
                    <div class="mb-2 row">
                        <label class="form-label-title col-sm-2 mb-0">
                            <span class="lnr lnr-checkmark-circle"></span>
                            ${name_type.innerHTML}
                        </label>
                        <div class="col-sm-1">
                            <button onclick="addTypeProductItem(this)" class="button-custom green-dark size-width">
                                +
                            </button>
                        </div>
                        <div class="col-sm-1">
                            <button onclick="removeTypeProduct(this)" class="button-custom remove size-width">
                                x
                            </button>
                        </div>
                        <div class="shop_type_item mb-4 row align-items-center">
                            <div class="col-sm-4">
                                <input class="form-control" type="text" placeholder="Tên loại" value="">
                            </div>
                            <div class="col-sm-4">
                                <input class="form-control" type="text" placeholder="Hình ảnh" >
                            </div>
                            <div class="col-sm-3">
                                <button onclick="showlistImgAddToType(this)" class="button-custom">
                                    Chọn ảnh hiển thị
                                </button>
                            </div>
                            <div class="col-sm-1">
                                <button onclick="removeTypeProductItem(this)" class="button-custom remove">
                                    X
                                </button>
                            </div>
                            <div class="product__types-img-list">
                                ${getProductTypeImgList()}
                            </div>
                        </div>
                    </div>
                </div>`
                let preHtml = (wrapTypes.innerHTML).replaceAll('hasEvent', '')
                wrapTypes.innerHTML = html + preHtml
                handleTypesImgItem()
                getDataValueInput()
                valueToDataValue()

                //đóng lại box
                let productTypes = _$('.product__types') 
                if(productTypes) {
                    productTypes.classList.toggle('show')
                }
            })
        })
    }
}

function showlistImgAddToType (btn) {

    if(btn.parentNode.parentNode) {
        let parent_node = btn.parentNode.parentNode
        let wrap = parent_node.querySelector('.product__types-img-list')
        if(wrap) {
            wrap.classList.toggle('show')
        }
    }

}
// add thêm type vào type product
//VD: Type: Màu(Xanh, Đen), sẽ add thêm Tím
function addTypeProductItem(btn) { 
    if(btn.parentNode.parentNode) {
        let parent_node = btn.parentNode.parentNode
        if(parent_node) {
            let html = /*html */`<div class="shop_type_item mb-4 row align-items-center">
                <div class="col-sm-4">
                    <input class="form-control" type="text" placeholder="Tên loại" value="">
                </div>
                <div class="col-sm-4">
                    <input class="form-control" type="text" placeholder="Hình ảnh" >
                </div>
                <div class="col-sm-3">
                    <button onclick="showlistImgAddToType(this)" class="button-custom">
                        Chọn ảnh hiển thị
                    </button>
                </div>
                <div class="col-sm-1">
                    <button onclick="removeTypeProductItem(this)" class="button-custom remove">
                        X
                    </button>
                </div>
                <div class="product__types-img-list">
                    ${getProductTypeImgList()}
                </div>
            </div>`
            parent_node.innerHTML = (parent_node.innerHTML).replaceAll('hasEvent', '') + html
            handleTypesImgItem()
            getDataValueInput()
            valueToDataValue()
        }
    }
}

//Sự kiện input value to data-value
function valueToDataValue() {
    let allInputInTypeProduct = _$('.wrap__products-type').querySelectorAll('input')
    if(allInputInTypeProduct) {
        allInputInTypeProduct.forEach(e => {
            if(!e.classList.contains('hasEvent')) {
                e.addEventListener('input', () => {
                    e.setAttribute('data-value', e.value)
                })
                e.classList.add('hasEvent')
            }
        })
    }
}valueToDataValue()

//Set data-value qua cho value của tất cả input
function getDataValueInput () {

    let allInput = _$('.wrap__products-type').querySelectorAll('input')
    allInput.forEach(e => {
        e.value = e.getAttribute('data-value')
    })
}

// Xóa type item
function removeTypeProductItem(btn) { 
    if(btn.parentNode.parentNode) {
        Swal.fire({
            title: "Message",
            text: "Xác nhận tiếp tục xóa mục của loại sản phẩm này",
            icon: "info",
            showCancelButton: true,
            cancelButtonColor: '#d33',
            confirmButtonText: 'Oke'
        }).then((oke) => {
            if (oke.isConfirmed) {
                
                let parent_node = btn.parentNode.parentNode
                if(parent_node) {
                    parent_node.remove()
                    let idProduct = 
                        _$('#product_id') ? _$('#product_id').value * 1 : 0
                    let type_item_id = parent_node.getAttribute('data-id')*1
                    if(idProduct > 0) {
                        http.delete(`${_URL_MAIN}/ProductTypeItem/delete/${type_item_id}`)
                        .then(data => {
                            if(data.status == 200) {
                                flash('success', data.data)
                            } else {
                                flash('error', data.data)
                            }
                        })
                    }
                }
            }
        })
    }
}

// Xóa type product
function removeTypeProduct(btn) { 
    if(btn.parentNode.parentNode.parentNode) {
        Swal.fire({
            title: "Message",
            text: "Xác nhận tiếp tục xóa loại sản phẩm này",
            icon: "info",
            showCancelButton: true,
            cancelButtonColor: '#d33',
            confirmButtonText: 'Oke'
        }).then((oke) => {
            if (oke.isConfirmed) {
                
                let parent_node = btn.parentNode.parentNode.parentNode
                if(parent_node) {
                    let type_id = parent_node.getAttribute('data-id')*1
                    parent_node.remove()
                    let idProduct = 
                        _$('#product_id') ? _$('#product_id').value * 1 : 0
                    if(idProduct > 0) {
                        http.delete(`${_URL_MAIN}/ProductTypeItem/delete/all/productid/typeid/${idProduct}/${type_id}`)
                        .then(data => {
                            if(data.status == 200) {
                                flash('success', data.data)
                            } else {
                                flash('error', data.data)
                            }
                        })
                    }
                }
            }
        })
    }
}

// set Giá trị cho biến typeProducts, dùng để đẩy dô databse
function setProductType() {
    typeProducts = []
    let inputAll = _$$('.shop_type_item input')
    let typeAll = _$$('.shop__products-type--item')
    let idProduct = _$('#product_id') ? _$('#product_id').value * 1 : 0
    if(inputAll) {
        typeAll.forEach((e, i) => {
            
            if(inputAll) {
                let typeItems = e.querySelectorAll('.shop_type_item')
                typeItems.forEach(tip => {
                    let input = tip.querySelectorAll('input')
                    let imgs = tip.querySelectorAll('img')
                    let list_imgs = ''
                    imgs.forEach((e, i) => {
                        if(e.classList.contains('selected')) {
                            list_imgs += i + ','
                        }
                    })
                    let dataItem = {
                        products: {
                            id: idProduct
                        },
                        productType: {
                            id: e.getAttribute('data-id')
                        },
                        name: input[0].value,
                        image: input[1].value,
                        list_imgs: list_imgs.substr(0, list_imgs.length - 1)
                    }
                    if(idProduct > 0) {
                        dataItem.id = (tip.getAttribute('data-id')) * 1
                    }
                    if(input[0].value.replaceAll(' ', '') != '') {
                        
                        typeProducts.push(dataItem)
                    } else {
                        
                        Swal.fire('Message', 'Vui lòng nhập tên hiển thị của loại sản phẩm', 'error')
                    }
                })
            }

        })
    }
}

// set Giá trị cho biến imgProducts, dùng để đẩy dô databse
function setProductImgs() {
    imgProducts = []
    let idProduct = _$('#product_id') ? _$('#product_id').value * 1 : 0
    
    _$$('.review__imgs-product img').forEach(e=> {
        if(e.src != defaultImg && e.src != loadingUrl) {
            let imgProduct = {
                products: {
                    id: idProduct
                },
                image: e.src
            }
            if(idProduct > 0) {
                imgProduct.id = (e.getAttribute('data-id')) * 1
            }
            imgProducts.push(imgProduct)
        }
    })

}

//Trả về html imgs trong "Chọn ảnh hiển thị"
function getProductTypeImgList(type = []) {
    let html = ''
    imgProducts.forEach((e, i) => {
        let selected = ''
        if(type.includes(i)) selected = 'selected'
        html += `
            <img src="${e.image}" alt="AVT" class="product__types-img-item ${selected}">
        `
    })

    return html
}

//add vào sau list ảnh trong "Chọn ảnh hiển thị"
function addBehindProductTypeImg(link) {
    
    let wraps = _$$('.product__types-img-list')

    wraps.forEach(e => {

        let nodeImg = document.createElement('img')

        nodeImg.classList.add('product__types-img-item')
        nodeImg.setAttribute('src', link)

        e.append(nodeImg)
    })

}

//remove thằng link ảnh trong "Chọn ảnh hiển thị"
function removeBehindProductTypeImg(link) {
    
    let wraps = _$$('.product__types-img-list img')

    wraps.forEach(e => {

        if(e.src == link) {
            e.remove()
        }
    })

}

//add list img
function addListImgDB(idProduct) {
    imgProducts.forEach(e => {
        e.products.id = idProduct
    })
    http.post(`${_URL_MAIN}/ProductImages/adds`, imgProducts)
    .then(data => {
        if(data.status == 200) {
            flash('success', 'Upload Or Edit list img success')
        } else {
            flash('error', data.data)
        }
    })
}

//add type product
function addTypeProductDB(idProduct) {
    setProductType()
    typeProducts.forEach(e => {
        e.products.id = idProduct
    })
    http.post(`${_URL_MAIN}/ProductTypeItem/adds`, typeProducts)
    .then(data => {
        if(data.status == 200) {
            flash('success', 'Upload Or Edit type success')
        } else {
            flash('error', data.data)
        }
    })
}


//load list hình ảnh khác
function loadListImgOthers() {
    let wrap = _$('.wrap__imgs-rv')
    let id = _$('#product_id').value * 1
    let html = ''
    if(wrap) {
        wrap.classList.add('hasImgs')
        http.get(`${_URL_MAIN}/ProductImages/get/productid/${id}`)
        .then(data => {
            data.forEach((e, i) => {
                if(i == 0) {
                    _$('.review__imgs-product img').setAttribute('data-id', e.id)
                }
                if(i > 0) {
                    html += `<div class="col-sm-4 mt-4">
                        <div class="review__imgs-product">
                            <img data-id="${e.id}" src="${e.image}" alt="${e.products.product_name}" class="imgs__product-item">
                            <button onclick="removeProductImgs(this)" type="button" class="btn-close imgs__product-btn--close" ></button>
                        </div>
                    </div>`
                }
            })

            wrap.innerHTML = html + '<button class="button-custom remove" onclick="removeProductImgs()" data-bs-original-title="" title="">Xóa tất cả</button>'
            if(data.length == 1) {
                wrap.innerHTML = `<div class="col-sm-4 mt-4">
                    <div class="review__imgs-product">
                        <img src="https://www.proclinic-products.com/build/static/default-product.30484205.png" alt="IMG" class="imgs__product-item">            
                    </div>
                </div>
                <div class="col-sm-4 mt-4">
                    <div class="review__imgs-product">
                        <img src="https://www.proclinic-products.com/build/static/default-product.30484205.png" alt="IMG" class="imgs__product-item">            
                    </div>
                </div>
                <div class="col-sm-4 mt-4">
                    <div class="review__imgs-product">
                        <img src="https://www.proclinic-products.com/build/static/default-product.30484205.png" alt="IMG" class="imgs__product-item">            
                    </div>
                </div>`
            }
            setProductImgs()
            
        })
    }
}

//load type product
function loadTypeProducts() {
    let wrap = _$('.wrap__products-type')
    let id = _$('#product_id').value * 1
    let child = ``
    if(wrap) {
        http.get(`${_URL_MAIN}/ProductTypeItem/get/productid/${id}`)
        .then(data => {
            let firstName = data[0].productType.name
            let html = /*html */`
            <div class="shop__products-type--item" data-id="${data[0].productType.id}">
                <div class="mb-2 row">
                    <label class="form-label-title col-sm-2 mb-0">
                        <span class="lnr lnr-checkmark-circle"></span>
                        ${firstName}
                    </label>
                    <div class="col-sm-1">
                        <button onclick="addTypeProductItem(this)" class="button-custom green-dark size-width">
                            +
                        </button>
                    </div>
                    <div class="col-sm-1">
                        <button onclick="removeTypeProduct(this)" class="button-custom remove size-width">
                            x
                        </button>
                    </div>`
            data.forEach((e, i) => {
                if(e.productType.name !== firstName) {
                    html += /*html */`
                        ${child}
                        </div>
                    </div>
                    `
                    html += /*html */`
                    <div class="shop__products-type--item" data-id="${e.productType.id}">
                        <div class="mb-2 row">
                            <label class="form-label-title col-sm-2 mb-0">
                                <span class="lnr lnr-checkmark-circle"></span>
                                ${e.productType.name}
                            </label>
                            <div class="col-sm-1">
                                <button onclick="addTypeProductItem(this)" class="button-custom green-dark size-width">
                                    +
                                </button>
                            </div>
                            <div class="col-sm-1">
                                <button onclick="removeTypeProduct(this)" class="button-custom remove size-width">
                                    x
                                </button>
                            </div>`

                    child = /*html */`
                    <div data-id="${e.id}" class="shop_type_item mb-4 row align-items-center">
                        <div class="col-sm-4">
                            <input class="form-control hasEvent" type="text" placeholder="Tên loại" value="${e.name}" data-value="${e.name}">
                        </div>
                        <div class="col-sm-4">
                            <input class="form-control hasEvent" type="text" placeholder="Hình ảnh" value="${e.image}" data-value="${e.image}">
                        </div>
                        <div class="col-sm-3">
                            <button onclick="showlistImgAddToType(this)" class="button-custom">
                                Chọn ảnh hiển thị
                            </button>
                        </div>
                        <div class="col-sm-1">
                            <button onclick="removeTypeProductItem(this)" class="button-custom remove">
                                X
                            </button>
                        </div>
                        <div class="product__types-img-list">
                            ${getProductTypeImgList(JSON.parse(`[${e.list_imgs}]`))}
                        </div>
                    </div>`
                    firstName = e.productType.name
                } else {
                    child += /*html */`
                    <div data-id="${e.id}" class="shop_type_item mb-4 row align-items-center">
                        <div class="col-sm-4">
                            <input class="form-control hasEvent" type="text" placeholder="Tên loại" value="${e.name}" data-value="${e.name}">
                        </div>
                        <div class="col-sm-4">
                            <input class="form-control hasEvent" type="text" placeholder="Hình ảnh" value="${e.image}" data-value="${e.image}">
                        </div>
                        <div class="col-sm-3">
                            <button onclick="showlistImgAddToType(this)" class="button-custom">
                                Chọn ảnh hiển thị
                            </button>
                        </div>
                        <div class="col-sm-1">
                            <button onclick="removeTypeProductItem(this)" class="button-custom remove">
                                X
                            </button>
                        </div>
                        <div class="product__types-img-list">
                            ${getProductTypeImgList(JSON.parse(`[${e.list_imgs}]`))}
                        </div>
                    </div>`
                }
                
            })
            html += /*html */`
                ${child}
                </div>
            </div>
            `
            wrap.innerHTML = html
            handleTypesImgItem()
        })
    }
    
}

// đang trong trạng thái edit 
if(_$('#product_id')) {
    loadListImgOthers()
    loadTypeProducts()
}