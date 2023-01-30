var primary = localStorage.getItem("primary") || '#7366ff';
var secondary = localStorage.getItem("secondary") || '#f73164';

window.RicaAdminConfig = {
	// Theme Primary Color
	primary: primary,
	// theme secondary color
	secondary: secondary,
};
const _URL_MAIN = 'http://localhost:8080'
const _ADMIN_DIR = '/admin-panel'
const loadingUrl = 'https://img.pikbest.com/png-images/20190918/cartoon-snail-loading-loading-gif-animation_2734139.png'
const defaultImg = 'https://www.proclinic-products.com/build/static/default-product.30484205.png'
const loadingGif = 'https://i.ibb.co/pydNgCD/Poly-RNN-Demo.gif'

function formatVND(value) {
    return new Intl.NumberFormat().format(value) + ' ₫'
}
function getDateCurr() {
    let nowDate = new Date()
    const offset = nowDate.getTimezoneOffset()
    nowDate = new Date(nowDate.getTime() - (offset))
    return nowDate.toISOString()
}

function getDateCurrStr(currentDate = '') {
    let currentdate = currentDate == '' ? new Date() : new Date(currentDate)
    return ((currentdate.getHours() < 10) ? ('0' + currentdate.getHours()) : currentdate.getHours()) + ":"  
        + ((currentdate.getMinutes() < 10) ? ('0' + currentdate.getMinutes()) : currentdate.getMinutes()) + ":" 
        + ((currentdate.getSeconds() < 10) ? ('0' + currentdate.getSeconds()) : currentdate.getSeconds()) + ", " 
        + ((currentdate.getDate() < 10) ? ('0' + currentdate.getDate()) : currentdate.getDate()) + "-"
        + (((currentdate.getMonth()+1) < 10) ? ('0' + (currentdate.getMonth()+1)) : (currentdate.getMonth()+1)) + "-" 
        + ((currentdate.getFullYear() < 10) ? ('0' + currentdate.getFullYear()) : currentdate.getFullYear())
}


const uploadToImgur = (e, output, bloob) => {
	let files = e.target.files
        
    if (files) {
        if (files[0].size > $(this).data('max-size') * 1024) {
            console.log('Vui lòng chọn file có dung lượng nhỏ hơn!')
            return false
        }
        
        console.log('Đang upload hình ảnh lên imgur...')
        
        let apiUrl = 'https://api.imgur.com/3/image'
        let apiKey = '58f2ebf29687a0b'
        let options = {
            async: false,
            crossDomain: true,
            processData: false,
            contentType: false,
            method: 'POST',
            headers: {
                Authorization: 'Client-ID ' + apiKey,
                Accept: 'application/json',
            },
            mimeType: 'multipart/form-data',
        }

        let formData = new FormData()

        formData.append('image', files[0])

        options.body = formData

        fetch(apiUrl, options)
            .then((response) => {
                return response.json()
            })
            .then((response) => {
                console.log(response)

                let obj = response
                let linkRS = obj.data.link
                console.log("Link: " + linkRS)

				if(imgResult)
                	imgResult.value = linkRS
                if(output)
					output.src = URL.createObjectURL(bloob)
            })
    }
}

const uploadToImbb = (e, output = false, outputSrc = false, callback = false) => {
    
    let files = e.target.files
    
    if (files) {
        if(outputSrc && output == '') { // nhiều ảnh
            if(outputSrc.classList.contains('hasImgs')) {
                let wrapHtml = outputSrc.innerHTML
                outputSrc.innerHTML = wrapHtml.substr(0, wrapHtml.lastIndexOf('<button'))
            } else {
                outputSrc.innerHTML = ''
            }
        }
        files.forEach(file => {
            if (file.size > $(this).data('max-size') * 1024) {
                
                return {
                    data : 'Vui lòng chọn file có dung lượng nhỏ hơn!',
                    error : 'Max size upload',
                    status : 19
                }
            }

            if(outputSrc && output) {
                outputSrc.src = loadingUrl
            }
            if(outputSrc && output == '') {
                outputSrc.innerHTML = outputSrc.innerHTML + `<div class="col-sm-4 mt-4">
                        <div class="review__imgs-product">
                            <img src="${loadingUrl}" alt="IMG" class="imgs__product-item loadding">
                            <button onclick="removeProductImgs(this)" type="button" class="btn-close imgs__product-btn--close" ></button>
                        </div>
                    </div>` 
            }
            
            console.log('Đang upload hình ảnh lên imgbb...')
            
            let apiUrl = 'https://cf-kodoku.imgbb.com/json'
            let auth_token = 'fe8b474e191692f1877c6ae57603bb0b0473e296'
            let options = {
                async: false,
                crossDomain: true,
                processData: false,
                contentType: false,
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                },
                mimeType: 'multipart/form-data',
            }
    
            let formData = new FormData()
    
            formData.append('source', file)
            formData.append('type', 'file')
            formData.append('action', 'upload')
            formData.append('timestamp', (+ new Date()) * 1)
            formData.append('auth_token', auth_token)
    
            options.body = formData

            fetch(apiUrl, options)
                .then((response) => {
                    return response.json()
                })
                .then((response) => {                    
    
                    let obj = response
                    let linkRS = obj.image.display_url
                    console.log("Link: " + linkRS)
    
                    if(output) {
                        output.value = linkRS
                    }

                    if(callback != false) {
                        callback(obj)
                    }
                        
                })
        })
    }
}


function renderCity(selector, first = 'Chọn Tỉnh Thành') {
    let selectCity = _$(selector)

    let selectItem = `<option value="-1">${first}</option>`
    let header = {
        token: '68c0df7b-601d-11ed-8008-c673db1cbf27'
    }

    http.get(`https://online-gateway.ghn.vn/shiip/public-api/master-data/province`, header)
    .then(data => {
        data.data.forEach(e => {
            selectItem += `<option data-text="${e.ProvinceName}" value="${e.ProvinceID}">
                ${e.ProvinceName}
            </option>`
        })
        selectCity.innerHTML = selectItem
    })
}

function renderState(selector = '#ship_city', selector1 = '#ship_state') {
    let selectCity = _$(selector)
    if(selectCity.value == -1) return
    let selectState = _$(selector1)

    let selectItem = ``
    let data = {
        "province_id": (selectCity.value) * 1
    }
    let header = {
        token: '68c0df7b-601d-11ed-8008-c673db1cbf27'
    }
    let url = `https://online-gateway.ghn.vn/shiip/public-api/master-data/district`

    http.post(url, data, header)
    .then(data => {
        data.data.forEach(e => {
            selectItem += `<option data-text="${e.DistrictName}" value="${e.DistrictID}">
                ${e.DistrictName}
            </option>`
        })
        selectState.innerHTML = selectItem
    })
}


function renderWard(selector = '#ship_state', selector1 = '#ship_ward') {
    let selectState = _$(selector)
    let selectWard = _$(selector1)

    let selectItem = ``
    let data = {
        "district_id": (selectState.value) * 1
    }
    let header = {
        token: '68c0df7b-601d-11ed-8008-c673db1cbf27'
    }
    let url = `https://online-gateway.ghn.vn/shiip/public-api/master-data/ward`

    http.post(url, data, header)
    .then(data => {
        data.data.forEach(e => {
            selectItem += `<option data-text="${e.WardName}" value="${e.WardCode}">
                ${e.WardName}
            </option>`
        })
        selectWard.innerHTML = selectItem
    })
}