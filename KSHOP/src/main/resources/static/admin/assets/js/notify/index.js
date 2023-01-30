
'use strict';
const flash = (type, message, title = 'Message', timeout = 3, interactive = true) => {
    timeout = timeout * 1000
    message = `<i class="fas fa-bell"></i><b>${title}</b> ${message}`

    switch(type) {
        case 'success': 
            
            $.notify(message, {
                type: 'success',
                allow_dismiss: true,
                delay: timeout,
                showProgressbar: true,
                timer: 300,
                animate: {
                    enter: 'animated fadeInDown',
                    exit: 'animated fadeOutUp'
                }
            });
            break
        case 'warning': 
            $.notify(message, {
                type: 'theme',
                allow_dismiss: true,
                delay: timeout,
                showProgressbar: true,
                timer: 300,
                animate: {
                    enter: 'animated fadeInDown',
                    exit: 'animated fadeOutUp'
                }
            });
            break
        case 'error': 
            $.notify(message, {
                type: 'theme',
                allow_dismiss: true,
                delay: timeout,
                showProgressbar: true,
                timer: 300,
                animate: {
                    enter: 'animated fadeInDown',
                    exit: 'animated fadeOutUp'
                }
            });
            break
        case 'info': 
            $.notify(message, {
                type: 'theme',
                allow_dismiss: true,
                delay: timeout,
                showProgressbar: true,
                timer: 300,
                animate: {
                    enter: 'animated fadeInDown',
                    exit: 'animated fadeOutUp'
                }
            });
            break
    }
}