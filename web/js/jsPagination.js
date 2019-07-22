function checkPage(totalPage) {
    let page = document.getElementById("page").value;
    if ((page > 0) && (page <= totalPage)) {
        ajaxToPage(page, totalPage);
    }
}
function checkboxOnclick(totalPage){
    ajaxToPage(1,totalPage);
}
function ajaxToPage(page,totalPage) {
    const searchText = document.getElementById("searchText").value;
    const ckbTitle = document.getElementById("ckbTitle");
    const ckbIntroduction = document.getElementById("ckbIntroduction");
    const ckbLocation = document.getElementById("ckbLocation");
    var searchByTitle="",searchByDescription="",searchByLocation="",sortByView = "";
    const filterView = document.getElementById("filterView");
    if (ckbTitle.checked) searchByTitle="title";
    if (ckbIntroduction.checked) searchByDescription="description";
    if (ckbLocation.checked) searchByLocation="location";
    if (filterView.checked) sortByView="view";
    $.ajax({
        type: "post",
        url: "pagingInSearch",
        data: {page:page,searchText:searchText,searchByTitle:searchByTitle,searchByDescription:searchByDescription,searchByLocation:searchByLocation,sortByView:sortByView},// data post
        dataType: "json",
        success: function(data) {
            let prePage = (page > 1) ? (page - 1) : 1;
            let nextPage = (totalPage - page > 0) ? (page + 1) : totalPage;
            let searchResult = $('#searchResult');
            // clear div search result
            searchResult.empty();

            // // console out put
            // console.log(data);

            let output = "";

            // compose html with data
            // start of total row
            for (let i = 0; i < data.length;) {
                output += `<div class="row">`;
                for (let j = 0; j < 3; j++) {
                    if (data[i]) {
                        output += `<div class="card-group text-center col-md-4"><div class="card">
                                <img class="card-img-top" src="resource/img/` + data[i]['imageFileName'] + `" alt="` + data[i]['title'] + `"  style="height: 500px">
                                <div class="card-body">
                                <h5 class="card-title">` + data[i]['title'] + `</h5>
                            <p class="card-text" style="display: 
                        -webkit-box;
                        -webkit-box-orient:vertical;  
                        -webkit-line-clamp:3;
                        overflow: hidden;
                        ">` + data[i]['description'] + `</p>
                            </div>
                            <ul class="list-group list-group-flush">
                            <li class="list-group-item">View: <span class="badge badge-primary">` + data[i]['view'] + `</span></li>
                            </ul>
                            <div class="card-footer">
                                <a href="details.page?artworkId=` + data[i]['artworkId'] + `" class="btn btn-outline-primary">More Details</a>
                            </div>
                            </div></div>`;
                        i++;
                    }
                }
                output += `</div>`;

            }// end of for total row

            // start of pagination
            output += `<nav class="mt-3" aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">`;

            if (prePage === page) {
                output += `<li class="page-item disabled"><a id="aFirstPage" class="page-link" href="#">First</a></li>`;
                output += `<li class="page-item disabled"><a id="aPreviousPage" class="page-link" href="#">Previous</a></li>`;
            } else {
                output += `<li class="page-item"><a id="aFirstPage" class="page-link" href="javascript:void(0)" onclick="` + `ajaxToPage(1,`+totalPage+`)` + `">First</a></li>`;
                output += `<li class="page-item"><a id="aPreviousPage" class="page-link" href="javascript:void(0)" onclick="` + `ajaxToPage(`+prePage+`,`+totalPage+`)`+ `">Previous</a></li>`;
            }

            if (nextPage === page) {
                output += `<li class="page-item disabled"><a id="aNextPage" class="page-link" href="#">Next</a></li>`;
                output += `<li class="page-item disabled"><a id="aLastPage" class="page-link" href="#">Last</a></li>`;
            } else {
                output += `<li class="page-item"><a id="aNextPage" class="page-link" href="javascript:void(0)"
                    onclick="` + `ajaxToPage(` + nextPage + `,`+totalPage+`)` + `">Next</a></li>`;
                output += `<li class="page-item"><a id="aLastPage" class="page-link" href="javascript:void(0)"
                    onclick="` + `ajaxToPage(` + totalPage + `,`+totalPage+`)` + `">Last</a></li>`;
            }

            output += `<li class="page-item">
                    <form class="form-inline">
                    <input id="page" class="form-control" type="number" min="1" max="`+ totalPage + `"
                name="page" placeholder="` + page + `">
                    &nbsp;` + `/` + `&nbsp;` + totalPage +  ` Page(s)
                &nbsp;<a id="aGoToPage" class="page-link" href="javascript:void(0)"
                onclick="` + `checkPage(` + totalPage +  `)` + `">Go</a>
                    </form>
                    </li>
                    </ul>
                    </nav>`;
            // out put in search.php
            searchResult.html(output);
        },
        error:function(data){
            console.log(data);
        }
    });
}