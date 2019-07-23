<%@ page import="entity.UsersEntity" %>
<%@ page import="entity.ArtworksEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    UsersEntity friend = (UsersEntity) request.getAttribute("friend");
    @SuppressWarnings("unchecked")
    List<ArtworksEntity> favoriteArtworks = (List<ArtworksEntity>) request.getAttribute("favoriteArtworks");
%>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Profile of Friend <%=friend.getName()%></title>
</head>
<body>

<%@include file="nav.jsp"%>

<main>
    <div class="container my-4">
        <div class="row">
            <div class="col-sm-4">
                <h3>Profile</h3>
                <dl class="row">
                    <dt class="col-sm-3">User: </dt>
                    <dd class="col-sm-9"><%=friend.getName()%></dd>

                    <dt class="col-sm-3">Email: </dt>
                    <dd class="col-sm-9"><%=friend.getEmail()%></dd>

                    <dt class="col-sm-3">Signature: </dt>
                    <dd class="col-sm-9"><%=friend.getSignature()%></dd>
                </dl>
            </div>

            <div class="col-sm-8">

                <% if (favoriteArtworks == null || favoriteArtworks.size() == 0) {// not have favors yet %>

                <h6><%=friend.getName()%> hasn't got any favors yet! Go and tell him/her to favor some now!</h6>

                <% } else {// have favors %>

                <table class="table table-hover table-sm">
                    <caption><%=friend.getName()%>'s Public Favor</caption>
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Image</th>
                        <th scope="col">View</th>
                        <th scope="col">Location</th>
                    </tr>
                    </thead>

                    <tbody>

                    <% for (ArtworksEntity artwork : favoriteArtworks){ %>

                    <tr>
                        <th scope="row" class="align-middle"><%=favoriteArtworks.indexOf(artwork) + 1%></th>
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
                    </tr>

                    <% }// end of loop of favorite artworks %>

                    </tbody>
                </table>

                <% }// end of have favors %>

            </div>
        </div>
    </div>
</main>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSearch.js"></script>
</body>
</html>
