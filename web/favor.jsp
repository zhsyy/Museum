<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.ArtworksEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.FavorEntity" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>My Favor</title>
</head>
<body>

<%@include file="nav.jsp"%>

<main class="cart m-4">
    <h5>My Favor</h5>
    <div class="container my-3">
        <div class="row">
            <table class="table table-hover table-sm">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Description</th>
                    <th scope="col">Image</th>
                    <th scope="col">View</th>
                    <th scope="col">Location</th>
                    <th scope="col">FavoriteTime</th>
                    <th scope="col">Option</th>
                </tr>
                </thead>
                <tbody>
                <%
                    @SuppressWarnings("unchecked")
                    List<ArtworksEntity> artworkList = (List<ArtworksEntity>)request.getAttribute("artworkList");
                    @SuppressWarnings("unchecked")
                    List<FavorEntity> favorList = (List<FavorEntity>)request.getAttribute("favorList");
                    for (int i = 0;i<artworkList.size();i++){
                        ArtworksEntity artworksEntity = artworkList.get(i);
                        FavorEntity favorEntity = favorList.get(i);
                %>
                <tr>
                    <th scope="row"><%=(i+1)%></th>
                    <td>
                        <a href="details.html?artworkID=<%=favorEntity.getArtworkId()%>" class="badge badge-light">
                            <%=artworksEntity.getTitle()%>
                        </a>
                    </td>
                    <td style="display:-webkit-box;
                                -webkit-box-orient:vertical;
                                -webkit-line-clamp:2;
                                overflow: hidden;"><%=artworksEntity.getDescription()%></td>
                    <td>
                        <img class="w-100" src="./resource/img/<%=artworksEntity.getType()%>/<%=artworksEntity.getImageFileName()%>" alt="<%=artworksEntity.getTitle()%>">
                    </td>
                    <td><%=artworksEntity.getView()%></td>
                    <td><%=artworksEntity.getLocation()%></td>
                    <td><%=favorEntity.getTime().toString()%></td>
                    <td>
                        <form class="invisible" method="post" action="favor.jsp">
                            <input type="text" name="favorId" value="<%=favorEntity.getFavorId()%>">
                            <button type="submit" class="btn btn-outline-primary visible">Remove</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>

        <!--alert-->
        <div class="row">
            <span id="alertBuy" class="alert"></span>
        </div>
    </div>

</main>


<footer class="footer navbar navbar-dark bg-dark">
    <div class="navbar-text m-auto">Produced and maintained by HNoodles in 2018</div>
</footer>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
</body>
</html>