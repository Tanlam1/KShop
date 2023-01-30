//bar chart
var options = {
    series: [{
            // name: "High - 2013",
            data: [35, 41, 62, 42, 13, 18, 29, 37, 36, 51, 32, 35]
        },

        {
            // name: "Low - 2013",
            data: [87, 57, 74, 99, 75, 38, 62, 47, 82, 56, 45, 47]
        }
    ],

    chart: {
        toolbar: {
            show: false
        }
    },

    chart: {
        height: 320,
    },

    legend: {
        show: false,
    },

    colors: ['#e22454', '#2483e2'],

    markers: {
        size: 1,
    },

    // grid: {
    //     show: false,
    //     xaxis: {
    //         lines: {
    //             show: false
    //         }
    //     },
    // },

    xaxis: {
        categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'],
        labels: {
            show: false,
        }
    },

    responsive: [{
            breakpoint: 1400,
            options: {
                chart: {
                    height: 300,
                },
            },
        },

        {
            breakpoint: 992,
            options: {
                chart: {
                    height: 210,
                    width: "100%",
                    offsetX: 0,
                },
            },
        },

        {
            breakpoint: 578,
            options: {
                chart: {
                    height: 200,
                    width: "105%",
                    offsetX: -20,
                    offsetY: 10,
                },
            },
        },

        {
            breakpoint: 430,
            options: {
                chart: {
                    width: "108%",
                },
            },
        },

        {
            breakpoint: 330,
            options: {
                chart: {
                    width: "112%",
                },
            },
        },
    ],
};

var chart = new ApexCharts(document.querySelector("#bar-chart-earning"), options);
chart.render();

// expenses cart
var options = {
    series: [{
        name: 'Tổng tiền nhận của shop',

        data: [{
                x: '2011',
                y: 1292,
                // goals: [{
                //     name: 'Expected',
                //     value: 1400,
                //     strokeWidth: 5,
                //     strokeColor: '#775DD0'
                // }]
            },

            {
                x: '2012',
                y: 4432
            },

            {
                x: '2013',
                y: 5423
            },

            {
                x: '2014',
                y: 6653
            },

            {
                x: '2015',
                y: 8133
            },

            {
                x: '2016',
                y: 7132
            },

            {
                x: '2017',
                y: 7332
            },

            {
                x: '2018',
                y: 6553
            }
        ]
    }],

    chart: {
        height: 320,
        type: 'bar'
    },

    plotOptions: {
        bar: {
            columnWidth: '40%'
        }
    },

    colors: ['#e22454'],
    dataLabels: {
        enabled: false
    },

    legend: {
        show: false,
    }
};

var chart = new ApexCharts(document.querySelector("#report-chart"), options);
chart.render();