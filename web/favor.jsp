<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.ArtworksEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.FavorEntity" %>
<%@ page import="java.util.Map" %>

<%
    @SuppressWarnings("unchecked")
    Map<FavorEntity, ArtworksEntity> favoriteArtworks = (Map<FavorEntity, ArtworksEntity>) request.getAttribute("favoriteArtworks");
%>

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

    <% if (favoriteArtworks == null || favoriteArtworks.size() == 0) {// not have favors yet %>

    <h6>You haven't got any favors yet! Go and find some now!</h6>

    <% } else {// have favors %>

    <div class="container my-3">
        <div class="row">
            <table class="table table-hover table-sm">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col">Image</th>
                    <th scope="col">View</th>
                    <th scope="col">Location</th>
                    <th scope="col">FavoriteTime</th>
                    <th scope="col">Option</th>
                </tr>
                </thead>

                <tbody>

                <%
                    int index = 0;
                    for (Map.Entry<FavorEntity, ArtworksEntity> entry : favoriteArtworks.entrySet()){
                        FavorEntity favor = entry.getKey();
                        ArtworksEntity artwork = entry.getValue();
                %>

                <tr>
                    <th scope="row" class="align-middle"><%=++index%></th>
                    <td class="align-middle">
                        <a class="badge badge-light" href="details.page?artworkId=<%=artwork.getArtworkId()%>">
                            <%=artwork.getTitle()%>
                        </a>
                    </td>
                    <td class="align-middle">
                        <img style="width: 100px; height: 100px" src="resource/img/<%=artwork.getImageFileName()%>" alt="<%=artwork.getTitle()%>">
                    </td>
                    <td class="align-middle">
                        <%=artwork.getView()%>
                    </td>
                    <td class="align-middle">
                        <%=artwork.getLocation()%>
                    </td>
                    <td class="align-middle">
                        <%=favor.getTime().toString()%>
                    </td>
                    <td class="align-middle">
                        <div class="row">

                            <% if ("private".equals(favor.getType())) {// can be publicized %>

                            <div class="col-sm-6">
                                <form method="post" action="publicize.favor">
                                    <input type="hidden" name="favorId" value="<%=favor.getFavorId()%>">
                                    <button type="submit" class="btn btn-outline-primary">Publicize</button>
                                </form>
                            </div>

                            <% } else {// can be privatized %>

                            <div class="col-sm-6">
                                <form method="post" action="privatize.favor">
                                    <input type="hidden" name="favorId" value="<%=favor.getFavorId()%>">
                                    <button type="submit" class="btn btn-outline-primary">Privatize</button>
                                </form>
                            </div>

                            <% }// end of can be privatized %>

                            <div class="col-sm-6">
                                <form method="post" action="delete.favor">
                                    <input type="hidden" name="favorId" value="<%=favor.getFavorId()%>">
                                    <button type="submit" class="btn btn-outline-primary">Delete</button>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>

                <%
                    }// end of loop of favorite artworks
                %>

                </tbody>
            </table>
        </div>
    </div>

    <% }// end of have favors %>

</main>


<footer class="footer navbar navbar-dark bg-dark">
    <div class="navbar-text m-auto">Produced and maintained by HNoodles in 2018</div>
</footer>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="js/jsSearch.js"></script>
</body>
</html>