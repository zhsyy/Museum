<%@ page import="java.util.List" %>
<%@ page import="entity.ArtworksEntity" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!--    <link rel="stylesheet" href="css/cssCommon.css">-->
    <title>Search for</title>
</head>
<body>
<%@include file="nav.jsp"%>
<main class="search">
    <div class="row m-2">
        <div class="col-sm-8">

        </div>
        <div class="col-sm-4 text-right">
            Sort by:
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="checkbox" name="filter" id="filterView" value="view" onclick="checkboxOnclick(<%=request.getAttribute("totalPage")%>)" <%
                               if (request.getAttribute("sortBy")!=null&&request.getAttribute("sortBy").equals("view"))
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

            if (artworksEntities == null) {
                response.sendRedirect("error.page?message=NotExist");
            }

            if (artworksEntities.size() > 0){
                int rolNum = ((artworksEntities.size() % 3)==0 && artworksEntities.size()>3) ? artworksEntities.size()/3 : (artworksEntities.size()/3)+1;
                for (int i = 0;i< rolNum; i++){
        %>
        <div class="row">
            <%
                for (int j = i*3; j < artworksEntities.size() && j<(i+1)*3; j++){
            %>
            <div class="card-group text-center col-md-4">
                <div class="card">
                    <img class="card-img-top" src="resource/img/<%=artworksEntities.get(j).getImageFileName()%>" alt="<%=artworksEntities.get(j).getTitle()%>" style="height: 500px">
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
                        <a href="details.page?artworkId=<%=artworksEntities.get(j).getArtworkId()%>" class="btn btn-outline-primary">More Details</a>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <%
                }
        %>
        <nav class="mt-3" aria-label="Page navigation example">
            <ul class="pagination justify-content-center">

                <li class="page-item"><a id="aFirstPage" class="page-link" href="javascript:void(0)"
                                         onclick="ajaxToPage(1,<%=request.getAttribute("totalPage")%>)">First</a></li>
                <li class="page-item"><a id="aPreviousPage" class="page-link" href="javascript:void(0)"
                                         onclick="ajaxToPage(1,<%=request.getAttribute("totalPage")%>)">Previous</a></li>

                <li class="page-item"><a id="aNextPage" class="page-link" href="javascript:void(0)"
                                         onclick="ajaxToPage(2,<%=request.getAttribute("totalPage")%>)" >Next</a></li>
                <li class="page-item"><a id="aLastPage" class="page-link" href="javascript:void(0)"
                                         onclick="ajaxToPage(<%=request.getAttribute("totalPage")%>,<%=request.getAttribute("totalPage")%>)">Last</a></li>

                <li class="page-item">
                    <form class="form-inline">
                        <input id="page" class="form-control" type="number" min="1" max="<%=request.getAttribute("totalPage")%>"
                               name="page" placeholder="1">
                        &nbsp;/&nbsp;<%=request.getAttribute("totalPage")%> Page(s)
                        &nbsp;<a id="aGoToPage" class="page-link" href="javascript:void(0)" onclick="checkPage(<%=request.getAttribute("totalPage")%>)">Go</a>
                    </form>
                </li>
            </ul>
        </nav>
        <%
        }else {
        %>
        <h5 align="center">Sorry, we couldn't find what you searched for, please try again.</h5>
        <br><br><br><br>
        <%
            }
        %>


    </div>


    </div>

</main>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSignIn.js"></script>
<script src="resource/js/jsPagination.js"></script>
<script src="resource/js/jsSearch.js"></script>
</body>
</html>