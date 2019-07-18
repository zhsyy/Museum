function checkPage(searchText,searchBy, totalPage,sortBy) {
    let page = document.getElementById("page").value;
    if ((page > 0) && (page <= totalPage)) {
        ajaxToPage(page, searchText, searchBy, totalPage,sortBy);
    }
}

function ajaxToPage(page,searchText,searchBy,totalPage,sortBy) {
    $.ajax({
        type: "post",
        url: "changeSearchPage",
        data: {page:page ,searchText:searchText, searchBy:searchBy, totalPage:totalPage,sortBy:sortBy},// data post
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
            if (searchText===""){
                searchText="\'\'";
            }else {
                searchText="\'"+searchText+"\'";
            }
            if (sortBy===""){
                sortBy="\'\'";
            }else {
                sortBy="\'"+sortBy+"\'";
            }
            var flag = false;
            for (let i = 0; i < data.length;) {
                output += `<div class="row">`;

                for (let j = 0; j < 3; j++) {
                    if (data[i]) {
                        output += `<div class="card-group text-center col-md-4"><div class="card">
                                <img class="card-img-top" src="/resource/img/` + data[i]['imageFileName'] + `" alt="` + data[i]['title'] + `">
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
                                <a href="details.jsp?artworkId=` + data[i]['artworkId'] + `" class="btn btn-outline-primary">More Details</a>
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
                output += `<li class="page-item"><a id="aFirstPage" class="page-link" href="javascript:void(0)" onclick="` + `clickToPage(1,`+ searchText + `,`  + totalPage + `,`+ sortBy + `)` + `">First</a></li>`;
                output += `<li class="page-item"><a id="aPreviousPage" class="page-link" href="javascript:void(0)" onclick="` + `clickToPage(` + prePage + `,` + searchText + `,` + totalPage + `,`+ sortBy + `)`+ `">Previous</a></li>`;
            }

            if (nextPage === page) {
                output += `<li class="page-item disabled"><a id="aNextPage" class="page-link" href="#">Next</a></li>`;
                output += `<li class="page-item disabled"><a id="aLastPage" class="page-link" href="#">Last</a></li>`;
            } else {
                output += `<li class="page-item"><a id="aNextPage" class="page-link" href="javascript:void(0)"
                    onclick="` + `clickToPage(` + nextPage + `,` + searchText + `,`  + totalPage +  `,`+ sortBy + `)` + `">Next</a></li>`;
                output += `<li class="page-item"><a id="aLastPage" class="page-link" href="javascript:void(0)"
                    onclick="` + `clickToPage(` + totalPage + `,` + searchText + `,` + totalPage +  `,`+ sortBy + `)` + `">Last</a></li>`;
            }

            output += `<li class="page-item">
                    <form class="form-inline">
                    <input id="page" class="form-control" type="number" min="1" max="`+ totalPage + `"
                name="page" placeholder="` + page + `">
                    &nbsp;` + `/` + `&nbsp;` + '<div id="totalPage">'+totalPage+ '</div>' + ` Page(s)
                &nbsp;<a id="aGoToPage" class="page-link" href="javascript:void(0)"
                onclick="` + `inputToPage(` + searchText + `,` + totalPage +  `,`+ sortBy + `)` + `">Go</a>
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