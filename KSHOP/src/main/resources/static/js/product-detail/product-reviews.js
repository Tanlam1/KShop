
const loadComment = (data) => {
    let wrapComment = document.querySelector('.product_rating')
    let html = ``
    data = data.reverse()
    data.forEach(e => {
        html +=  /*html*/`<li class="comment">
        <div class="comment-body">
            <figure class="comment-avatar">
                <img src="${e.customers.avatar == null ? 'https://thumbs.dreamstime.com/b/default-avatar-profile-vector-user-profile-default-avatar-profile-vector-user-profile-profile-179376714.jpg' : e.customers.avatar}" alt="Commenter Avatar" width="90" height="90">
            </figure>
            <div class="comment-content">
                <h4 class="comment-author">
                    <a href="javascript:void(0)">${e.customers.first_name} ${e.customers.last_name}</a>
                    <span class="comment-date">${getDateCurrStr(e.created_at)}</span>
                </h4>
                <div class="ratings-container comment-rating">
                    <div class="ratings-full">
                        <span style=" position: absolute; bottom: 5px; right: -25px; ">(${e.rating})</span>
                        <span class="ratings" id="ratings_star" style="width:${e.rating*20}%;"></span>
                        <span style="display: none;" class="tooltiptext tooltip-top">${e.rating*20}</span>
                    </div>
                </div>
                <p>${e.comment}</p>
                <div class="comment-action">
                    
                    <a href="#" class="btn btn-secondary btn-link btn-underline sm btn-icon-left font-weight-normal text-capitalize">
                    <i class="far fa-thumbs-up"></i>Hữu ích
                    </a>                    
                </div>
            </div>
        </div>
    </li>`
    })
    wrapComment.innerHTML = html
}
http.get(`http://localhost:8080/ProductReviews/get/productid/${ _$('#product_id').value * 1}`)
    .then(data => loadComment(data))
    .catch(err => console.log(err))