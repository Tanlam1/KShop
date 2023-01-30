let limit = 5
let currentPage = 1
let pagePrev, pageNext
let htmlPagePrev, htmlPageNext
let total_page, total_review

const loadReview = (data) => {
    let wrapReview = document.querySelector('.product-reviews')
    let html = ``
    data.forEach(e => {
        html +=  /*html*/`<tr>
        <td>${e.id}</td>
        <td>${e.products.id}</td>
        <td>${e.customers.id}</td>
        <td>${e.rating}</td>
        <td>${e.comment}</td>
        <td>${e.created_at}</td>
        <td>${e.updated_at}</td>
        <td class="td-check">
            <span class="lnr lnr-checkmark-circle"></span>
        </td>
    </tr>`
    })
    wrapReview.innerHTML = html
}
http.get("http://localhost:8080/ProductReviews/get")
    .then(data => loadReview(data))
    .catch(err => console.log(err))

    // const loadPages = (totalReview) => {
    //     let pagination = _$('.pagination')
    //     total_review = totalReview
    
    //     total_page = Math.floor(total_review / limit) + 1
    
    //     let htmlPages = ``, htmlPagesPrev = ``, htmlPagesNext = ``
    
    //     for (let i = currentPage + 1; i <= currentPage + 3; i++) {
    //         if (i > total_page) break
    //         htmlPagesNext += `<li class="page-item ${i == currentPage ? 'active' : ''}">
    //             <button class="page-link" onclick="genderReview(${i})">${i}</button>
    //         </li>`
    //     }
    
    //     for (let i = currentPage - 1; i >= currentPage - 3; i--) {
    //         if (i < 1) break
    //         htmlPagesPrev = `<li class="page-item ${i == currentPage ? 'active' : ''}">
    //             <button class="page-link" onclick="genderReview(${i})">${i}</button>
    //         </li>` + htmlPagesPrev
    //     }
    
    //     htmlPages += `<li class="page-item active">
    //         <button class="page-link" onclick="genderReview(${currentPage})">${currentPage}</button>
    //     </li>`
    
    //     pagePrev = (currentPage - 1 < 1) ? total_page : currentPage - 1
    //     pageNext = (currentPage + 1 > total_page) ? 1 : currentPage + 1
    //     htmlPagePrev = `<li class="page-item">
    //         <button class="page-link" onclick="genderReview(${pagePrev})"
    //             tabindex="-1">Previous</button>
    //     </li>`
    //     htmlPageNext = `<li class="page-item">
    //         <button class="page-link" onclick="genderReview(${pageNext})"
    //             tabindex="-1">Next</button>
    //     </li>`
    
    //     pagination.innerHTML = htmlPagePrev + htmlPagesPrev + htmlPages + htmlPagesNext + htmlPageNext
    // }
    
    // const genderReview = (page) => {
    //     currentPage = page
    //     loadPages(total_coupon)
    //     window.scrollTo({ top: 0, behavior: 'smooth' })
    //     http.get(`http://localhost:8080/ProductReviews/get/${currentPage - 1}/${limit}`)
    //         .then(data => loadReview(data))
    //         .catch(err => console.log(err))
    // }
    // genderReview(currentPage)