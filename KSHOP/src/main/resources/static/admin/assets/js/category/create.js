const loadShopGrid1 = (data) => {
    console.log(data);
    let wrapCate0 = _$('.category-0-1-js')
    let html = ``
    data.forEach(e => {
        html += `<option value="${e.id}" id="parentId">${e.id}-${e.category_name}</option>`
    })
    wrapCate0.innerHTML = html
}

function renderCategory() {
    let level = _$('.category-0-js')
    if (level.value == 0) {
        _$('.category-0-1-js').style.display = 'none'
    } else {
        _$('.category-0-1-js').style.display = 'block'
    }
    http.get(`http://localhost:8080/ShopCategories/get/level/${(level.value - 1)}`)
        .then(data => loadShopGrid1(data))
        .catch(err => console.log(err))
}

_$('.category-0-js').addEventListener('change', () => {
    renderCategory()
})
renderCategory()

// Create 
const addCate = () => {

    let newCategories = categoryInvalid()

    if (!newCategories) {
        return false
    }

    http.post(`${_URL_MAIN}/ShopCategories/add`, newCategories)
        .then(data => {
            Swal.fire('Success', 'Thêm category thành công', 'success')
                .then(rs => window.location.href = `${_ADMIN_DIR}/categories`)
        })
}

//edit
const editCate = () => {

    let cateEdit = categoryInvalid(1)

    if (!cateEdit) {
        return false
    }

    http.put(`${_URL_MAIN}/ShopCategories/update`, cateEdit)
        .then(data => {
            Swal.fire('Success', 'Chỉnh sửa category thành công', 'success')
                .then(rs => window.location.reload())
        })
}

function categoryInvalid(type = 0) {
    var optionLV = document.getElementById('catelevel')
    var level = optionLV.options[optionLV.selectedIndex]

    var selectID = document.getElementById('parentID')
    var parentId = selectID.options[selectID.selectedIndex]

    let category_code = _$('#category_code')
    let category_name = _$('#category_name')
    let description = _$('.ck-content')
    let image = _$('#image')

    if (!category_code || category_code.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập category code', 'info')
        return false
    }

    if (!category_name || category_name.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập tên category', 'info')
        return false
    }


    let result = {
        level: level.value,
        parentId: parentId.value,
        category_code: category_code.value,
        category_name: category_name.value,
        description: description.innerHTML,
        image: image.value
    }

    if (type == 1) { // edit
        result.id = _$('#category_id').value * 1
    }

    return result
}