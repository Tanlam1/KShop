function getTongBanTrongNam() {

    setTimeout(() => {
        let dataTongBanTrongNam = [21, 22, 10, 28, 16, 21, 13, 30, 12, 20, 30, 12]
        
        let options = {
            series: [{
                data: dataTongBanTrongNam
            }],
            chart: {
                height: 400,
                type: 'bar',
            },
            colors: ['#e22454', '#2483e2', '#3d3d3d'],
            plotOptions: {
                bar: {
                    columnWidth: '45%',
                    distributed: true,
                }
            },
            dataLabels: {
                enabled: false
            },
            legend: {
                show: false
            },
            xaxis: {
                categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
                labels: {
                    style: {
                        colors: ['#e22454', '#2483e2', '#3d3d3d'],
                        fontSize: '12px'
                    }
                }
            }
        };

        const chart = new ApexCharts(_$("#tong_ban_trong_nam"), options);
        const chart1 = new ApexCharts(_$("#tong_sp_trong_nam"), options);
        chart.render();
        chart1.render();
    }, 1000);
}
getTongBanTrongNam()

function getBankingShop() {
    setTimeout(() => {
        let data = [2000000, 220000, 1220000, 800000, 900000, 3220000]
        let options = {
            series: [{
                name: 'Tổng rút',
                data: data
            }],

            colors: ['#e22454'],

            chart: {
                type: 'area',
                // stacked: false,
                height: 350,
            },
            tooltip: {
                y: {
                    formatter: function (val) {
                        return formatVND(val)
                    }
                }
            },
            stroke: {
                width: 3,
                curve: 'smooth'
            },
            xaxis: {
                categories: ['7', '8', '9', '10', '11', '12'],
                labels: {
                    style: {
                        colors: ['#e22454', '#2483e2', '#3d3d3d'],
                        fontSize: '12px'
                    }
                }
            }
        }

        const chart = new ApexCharts(_$("#employ-salary"), options);
        chart.render();
    }, 1000)
}
getBankingShop()


function getStatusOrder() {
    setTimeout(() => {
        let data = [
            {
                name: 'Đơn hủy',
                data: [23, 11, 22, 27, 13, 22, 37, 21, 44, 22, 30]
            }, {
                name: 'Đơn thành công',
                data: [44, 55, 41, 67, 22, 43, 21, 41, 56, 27, 43]
            }
        ]
        //sales purchase return cart
        let options = {
            series: data,
            chart: {
                height: 350,
                type: 'area'
            },
            dataLabels: {
                enabled: false,
            },
            stroke: {
                curve: 'straight',
                width: [3, 3]
            },

            colors: ['#e22454', '#2483e2', '#e2c924'],

            xaxis: {
                type: 'time',
                categories: [01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11, 12],
            },

            legend: {
                show: false,
            },

            tooltip: {
                show: false,
            },
        };

        const chart = new ApexCharts(_$("#sales-purchase-return-cart"), options);
        chart.render();
    }, 1000)
}
getStatusOrder()

//History order
let limitInReport = 10
let currentPageInReport = 1
let pagePrevInReport, pageNextInReport
let htmlpagePrevInReport, htmlpageNextInReport
let totalPageInReport, totalOrderInReport

const loadOrder = (data) => {
    let wrapOrder = _$('.order_list')
    let html = ``
    data.forEach(e => {
        
        html +=  /*html*/`<tr>
            <td>${e.id}</td>

            <td class="order-success">
                <a href="javascript:void(0)">
                    <span>${getDateCurrStr(e.created_at)}</span>
                </a>
            </td>

            <td>
                <a href="javascript:void(0)">
                    ${e.customers.first_name}
                    ${e.customers.last_name}
                </a>
            </td>

            <td>
                ${e.ship_address1}
            </td>

            <td class="order-success">
                <span>${e.order_status}</span>
            </td>

            <td>
                <ul>
                    <li>
                        <a href="${_ADMIN_DIR}/detail-orders/${e.id}">
                            <span class="lnr lnr-eye"></span>
                        </a>
                    </li>
                </ul>
            </td>
        </tr>`
    })
    wrapOrder.innerHTML = html
}

const loadPagesInReport = (totalOrder) => {
    let pagination = _$('.paginationInReport')
    totalOrderInReport = totalOrder

    totalPageInReport = Math.floor(totalOrderInReport / limitInReport) + 1

    let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``

    for (let i = currentPageInReport + 1; i <= currentPageInReport + 3; i++) {
        if (i > totalPageInReport) break
        htmlPagesNext += `<li class="page-item ${i == currentPageInReport ? 'active' : ''}">
            <button class="page-link" onclick="genderOrderInReport(${i})">${i}</button>
        </li>`
    }

    for (let i = currentPageInReport - 1; i >= currentPageInReport - 3; i--) {
        if (i < 1) break
        htmlPagesPrev = `<li class="page-item ${i == currentPageInReport ? 'active' : ''}">
            <button class="page-link" onclick="genderOrderInReport(${i})">${i}</button>
        </li>` + htmlPagesPrev
    }

    htmlPages += `<li class="page-item active">
        <button class="page-link" onclick="genderOrderInReport(${currentPageInReport})">${currentPageInReport}</button>
    </li>`

    pagePrevInReport = (currentPageInReport - 1 < 1) ? totalPageInReport : currentPageInReport - 1
    pageNextInReport = (currentPageInReport + 1 > totalPageInReport) ? 1 : currentPageInReport + 1
    htmlpagePrevInReport = `<li class="page-item">
        <button class="page-link" onclick="genderOrderInReport(${pagePrevInReport})"
            tabindex="-1">Previous</button>
    </li>`
    htmlpageNextInReport = `<li class="page-item">
        <button class="page-link" onclick="genderOrderInReport(${pageNextInReport})"
            tabindex="-1">Next</button>
    </li>`

    pagination.innerHTML = htmlpagePrevInReport + htmlPagesPrev + htmlPages + htmlPagesNext + htmlpageNextInReport
}

const genderOrderInReport = (page) => {
    currentPageInReport = page
    loadPagesInReport(totalOrderInReport)
    window.scrollTo({ top: 0, behavior: 'smooth' })
    http.get(`http://localhost:8080/Orders/get/${currentPageInReport - 1}/${limitInReport}`)
        .then(data => {
            if(data.status == 200) {
                loadOrder(data.data)
            }
        })
        .catch(err => console.log(err))
}
genderOrderInReport(currentPageInReport)

http.get(`http://localhost:8080/Orders/get`)
    .then(data => loadPagesInReport(data.length))
    .catch(err => console.log(err))

    

function filterInReport() {
    let items = _$$('.order_list tr')
    let keyword = _$('#filterInReport-search')
    let xacNhan = _$('#filterInReport-xn') // 0 - 1 - 2
    let regexXN = '.+'

    if(xacNhan.value != 0) {
        regexXN = xacNhan.options[xacNhan.selectedIndex].text
    }
    regexXN = regexXN.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')

    keyword = keyword.value.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
    if(keyword.length == 0 || keyword === '') keyword = '.+'
    items.forEach(e => {
        let xacNhanTable = e.querySelectorAll('.order-success')[1].innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')
        let tableSearch = e.innerText.trim().toLocaleLowerCase().replaceAll(' ', '').replaceAll('\n', '')

        if(xacNhanTable.match(regexXN) && tableSearch.match(keyword)) {
            e.style = ''
        } else {
            e.style = 'display: none;'
        }

    })
}

function sortByDateInReport() {
    let start_date = _$('.reportOrder-sort-date #start_date')
    let end_date = _$('.reportOrder-sort-date #end_date')
    let data = {
        start_date: start_date.value,
        end_date: end_date.value
    }

    http.post(`${_URL_MAIN}/Orders/report`, data)
    .then(data => {
        if(data.status != 200) {
            Swal.fire('Message', data.data, 'info')
        } else {

            if(data.data.result.length == 0) {
                Swal.fire('Message', 'Không tìm thấy kết quả nào.', 'info')
            } else {
    
                loadOrder(data.data.result)
                loadPagesInReport(data.data.result.length)
            }
        }

    })

}
//set cho input date time hiện tại
_$('.reportOrder-sort-date #start_date').valueAsDate = new Date()
_$('.reportOrder-sort-date #end_date').valueAsDate = new Date()