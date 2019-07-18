<%@ page import="java.util.List" %>
<%@ page import="entity.ArtworksEntity" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!--    <link rel="stylesheet" href="css/cssCommon.css">-->
    <title>Search for</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="../../../../html/index.html"><strong>Art Store</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <span class="navbar-text mx-2">Where you find <strong>genius</strong> and <strong>extraordinary</strong></span>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="../../../../html/index.html">Front Page<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="javascript:void(0)" data-toggle="modal" data-target="#signInFormModal" onclick="changeVerify()">Sign in</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../../../../html/signUp.html">Sign up</a>
            </li>
        </ul>
        <form id="search" class="form-inline my-2 my-lg-0" method="post" action="/search">
            <input id="searchText" class="form-control mr-sm-2" type="search" name="searchText" placeholder="Search here" aria-label="Search" value="<%=request.getAttribute("searchText")%>">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Search By...
                </button>
                <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ckbTitle" name="searchBy" value="title"
                            <%
                            String[] searchBy = (String[])request.getAttribute("searchBy");
                            for (int i =0;i<searchBy.length;i++){
                               if (searchBy[i].equals("title")){
                                    out.print("checked");
                                }
                            }
                            %>
                        >
                        <label class="form-check-label" for="ckbTitle">Title</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ckbIntroduction" name="searchBy" value="description"
                            <%
                            for (int i =0;i<searchBy.length;i++){
                               if (searchBy[i].equals("description")){
                                    out.print("checked");
                                }
                            }
                            %>
                        >
                        <label class="form-check-label" for="ckbIntroduction">Description</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ckbLocation" name="searchBy" value="location"
                            <%
                            for (int i =0;i<searchBy.length;i++){
                               if (searchBy[i].equals("location")){
                                    out.print("checked");
                                }
                            }
                            %>
                        >
                        <label class="form-check-label" for="ckbLocation">Location</label>
                    </div>
                    <input class="invisible" id="sortBy" name="sortBy" value="<%=request.getAttribute("sortBy")%>">
                    <span id="searchAlert" class="invisible alert"></span><br/>
                    <button id="btSearch" class="btn btn-outline-primary my-2 my-sm-0" type="button">Search</button>
                </div>
            </div>
        </form>
    </div>
</nav>

<main class="search">
    <div class="row m-2">
        <div class="col-sm-8">

        </div>
        <div class="col-sm-4 text-right">
            Sort by:
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" name="filter" id="filterView" value="view" <%
                               if (request.getAttribute("sortBy").equals("view"))
                                   out.print("checked");
                            %>>
                <label class="form-check-label" for="filterView">View</label>
            </div>
        </div>
    </div>

    <div id="searchResult">
        <%
            @SuppressWarnings("unchecked")
            List<ArtworksEntity> artworksEntities = (List<ArtworksEntity>)request.getAttribute("artworksEntities");
            if (artworksEntities.size() > 0){
                int rolNum = ((artworksEntities.size() % 3)==0 && artworksEntities.size()>3) ? artworksEntities.size()/3 : (artworksEntities.size()/3)+1;
                for (int i = 0;i<rolNum;i++){
        %>
        <div class="row">
            <%
                for (int j = i*3; j < artworksEntities.size() && j<(i+1)*3; j++){
            %>
            <div class="card-group text-center col-md-4">
                <div class="card">
                    <img class="card-img-top" src="/resource/img/<%=artworksEntities.get(j).getImageFileName()%>" alt="<%=artworksEntities.get(j).getTitle()%>">
                    <div class="card-body">
                        <h5 class="card-title"><%=artworksEntities.get(j).getTitle()%></h5>
                        <p class="card-text" style="display:
                        -webkit-box;
                        -webkit-box-orient:vertical;
                        -webkit-line-clamp:3;
                        overflow: hidden;
                        "><%=artworksEntities.get(j).getDescription()%></p>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">View: <span class="badge badge-primary"><%=artworksEntities.get(j).getView()%></span></li>
                    </ul>
                    <div class="card-footer">
                        <a href="details.jsp?artworkId=<%=artworksEntities.get(j).getArtworkId()%>" class="btn btn-outline-primary">More Details</a>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <%
                }
            }else {
        %>
        <h5 align="center">Sorry, we couldn't find what you searched for, please try again.</h5>
        <br><br><br><br>
        <%
            }
        %>
        <nav class="mt-3" aria-label="Page navigation example">
            <ul class="pagination justify-content-center">

                <li class="page-item"><a id="aFirstPage" class="page-link" href="javascript:void(0)"
                                         onclick="clickToPage(1,'<%=request.getAttribute("searchText")%>',
                                             <%=request.getAttribute("totalPage")%>,'<%=request.getAttribute("sortBy")%>')">First</a></li>
                <li class="page-item"><a id="aPreviousPage" class="page-link" href="javascript:void(0)"
                                         onclick="clickToPage(1,'<%=request.getAttribute("searchText")%>',
                                             <%=request.getAttribute("totalPage")%>,'<%=request.getAttribute("sortBy")%>')">Previous</a></li>

                <li class="page-item"><a id="aNextPage" class="page-link" href="javascript:void(0)"
                                         onclick="clickToPage(2,'<%=request.getAttribute("searchText")%>',
                                             <%=request.getAttribute("totalPage")%>,'<%=request.getAttribute("sortBy")%>')" >Next</a></li>
                <li class="page-item"><a id="aLastPage" class="page-link" href="javascript:void(0)"
                                         onclick="clickToPage(<%=request.getAttribute("totalPage")%>,'<%=request.getAttribute("searchText")%>',
                                             <%=request.getAttribute("totalPage")%>,'<%=request.getAttribute("sortBy")%>')">Last</a></li>

                <li class="page-item">
                    <form class="form-inline">
                        <input id="page" class="form-control" type="number" min="1" max="<%=request.getAttribute("totalPage")%>"
                               name="page" placeholder="<%=request.getAttribute("currentPage")%>">
                        &nbsp;/&nbsp;<div id="totalPage"><%=request.getAttribute("totalPage")%></div> Page(s)
                        &nbsp;<a id="aGoToPage" class="page-link" href="javascript:void(0)"
                                 onclick="inputToPage('<%=request.getAttribute("searchText")%>',<%=request.getAttribute("totalPage")%>,'<%=request.getAttribute("sortBy")%>')">Go</a>
                    </form>
                </li>
            </ul>
        </nav>
    </div>




</main>
<footer class="footer navbar navbar-dark bg-dark">
    <div class="navbar-text m-auto">Produced and maintained by HNoodles in 2018</div>
</footer>
<script src="js/jsSearch.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="js/jsPagination.js"></script>

<script>
    function clickToPage(page,searchText,totalPage,sortBy) {
        var searchBy = "";
        <% for (String u : searchBy) { %>
        searchBy += "<%=u%>,";
        <% } %>
        ajaxToPage(page,searchText,searchBy,totalPage,sortBy);

    }
    function inputToPage(searchText,totalPage,sortBy) {
        var searchBy = "";
        <% for (String u : searchBy) { %>
        searchBy += "<%=u%>,";
        <% } %>
        checkPage(searchText,searchBy,totalPage,sortBy)
    }
</script>