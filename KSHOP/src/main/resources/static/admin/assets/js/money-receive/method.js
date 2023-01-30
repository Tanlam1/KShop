let listBank = _$$('.bank-item')

if(listBank) {
    listBank.forEach(e => {
        e.addEventListener('click', (event) => {
            event.preventDefault()
            let bankSelected = _$('.bank-item.selected')

            if(bankSelected) {
                bankSelected.classList.remove('selected')
                
            }            
            if(bankSelected == e) {

                e.classList.remove('selected')
            } else {

                e.classList.add('selected')
            }
        })  
    })
}

function setMethod() {
    let methods = _$('#method')

    switch (methods.value) {
        case 'PAYPAL': {
            let ck_wrap = _$$('.method-ck')
            let nbi_wrap = _$('.method-number-info')
            ck_wrap.forEach(e => {
                e.style.display = 'none'
            })

            nbi_wrap.querySelector('label').innerHTML = 'Email người nhận'
            nbi_wrap.querySelector('input').setAttribute('placeholder', 'Email người nhận..')

            let bankSelected = _$('.bank-item.selected')

            if(bankSelected) {
                bankSelected.classList.remove('selected')
            }
        } break
        
        case 'CK': {
            let ck_wrap = _$$('.method-ck')
            let nbi_wrap = _$('.method-number-info')
            ck_wrap.forEach(e => {
                e.style.display = 'flex'
            })

            nbi_wrap.querySelector('label').innerHTML = 'Số tài khoản'
            nbi_wrap.querySelector('input').setAttribute('placeholder', 'Số tài khoản..')

        } break
        
        case 'VT_MONEY': {
            let ck_wrap = _$$('.method-ck')
            let nbi_wrap = _$('.method-number-info')
            ck_wrap.forEach(e => {
                e.style.display = 'none'
            })

            nbi_wrap.querySelector('label').innerHTML = 'Số điện thoại / STK'
            nbi_wrap.querySelector('input').setAttribute('placeholder', 'Số điện thoại hoặc số tài khoản của ví..')

            let bankSelected = _$('.bank-item.selected')

            if(bankSelected) {
                bankSelected.classList.remove('selected')
            }

        } break
        
        case 'MOMO': {
            let ck_wrap = _$$('.method-ck')
            let nbi_wrap = _$('.method-number-info')
            ck_wrap.forEach(e => {
                e.style.display = 'none'
            })

            nbi_wrap.querySelector('label').innerHTML = 'Số điện thoại / STK'
            nbi_wrap.querySelector('input').setAttribute('placeholder', 'Số điện thoại hoặc số tài khoản của ví..')
            
            let bankSelected = _$('.bank-item.selected')

            if(bankSelected) {
                bankSelected.classList.remove('selected')
            }

        } break
        
    }
}

function applyMethod() {
    let bankSelected = _$('.bank-item.selected')
    let nbi = _$('#nbi_method')
    let method = _$('#method')
    let fullname = _$('#fullname')
    let sodu_ngoai = _$('#sodu_ngoai')

    if(!bankSelected && method && method.value == 'CK') {
        Swal.fire('Message', 'Vui lòng chọn ngân hàng', 'info')
        return
    }

    if(!nbi || nbi.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập thông tin nhận tiền của bạn', 'info')
        return
    }

    if(bankSelected && (!fullname || fullname.value.replaceAll(' ', '') == '')) {
        Swal.fire('Message', 'Vui lòng nhập họ tên trên thẻ của bạn', 'info')
        return
    }

    if(!sodu_ngoai || sodu_ngoai.value.replaceAll(' ', '') == '') {
        Swal.fire('Message', 'Vui lòng nhập số dư ngoài của shop', 'info')
        return
    }

    let bankReceive = bankSelected ? bankSelected.innerText.trim() : ''
    let numberBankReceive = nbi ? nbi.value : ''
    let methodReceive = method ? method.value : ''
    let fullnameReceive = fullname ? fullname.value : ''

    let data = `${fullnameReceive}|${methodReceive}|${numberBankReceive}|${bankReceive}`
    let body = {
        data,
        sodu_ngoai: sodu_ngoai.value * 1
    }

    http.put(`${_URL_MAIN}/Users/updateMoneyType`, body)
    .then(data => {
        if(data.status == 200) {
            Swal.fire('Success', data.data, 'success')
            .then(() => window.location.reload())
        } else {
            Swal.fire('Message', data.data, 'info')
            .then(() => window.location.reload())
        }
    })

}

function loadReceive() {
    
    if(info_receive_money.value) {
        let infos = info_receive_money.value.split('|')
        console.log(infos); 
        fullname.value = infos[0]
        _$(`option[value="${infos[1]}"]`).selected = true
        nbi_method.value = infos[2]
        
        if(infos[1] == 'CK') {
            _$$('.bank-item').forEach(e => {
                if(e.innerText.trim() == infos[3]) {
                    e.classList.add('selected')
                }
            })
        }
        setMethod()
    }
}loadReceive()