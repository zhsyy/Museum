<%@ page import="dao.FavorDao" %>
<%@ page import="dao.impl.FavorDaoImp" %>
<%@ page import="dao.ArtworksDao" %>
<%@ page import="dao.impl.ArtworksDaoImp" %>
<%@ page import="entity.ArtworksEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.FavorEntity" %>
<%
    session.setAttribute("userId",1);
    FavorDao favorDaoImp = new FavorDaoImp();
    ArtworksDao artworksDaoImp = new ArtworksDaoImp();
//    if (session.getAttribute("userID") == null){
//        response.sendRedirect("index.jsp");
//    }
    int userID = (int)session.getAttribute("userId");
    if (request.getParameter("favorId")!=null){
        favorDaoImp.delete(Integer.parseInt(request.getParameter("favorId")));
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Cart</title>
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

                                List<ArtworksEntity> artworkList = artworksDaoImp.getFavorArtworks(userID);
                                List<FavorEntity> favorList = favorDaoImp.getFavorEntities(userID);
                                for (int i = 0;i<artworkList.size();i++){
                                    ArtworksEntity artworksEntity = artworkList.get(i);
                                    FavorEntity favorEntity = favorList.get(i);
                                    out.print("<tr>\n" +
                                            "<th scope=\"row\">"+(i+1)+"</th>\n" +
                                            "<td>\n" +
                                            "<a href=\"details.html?artworkID="+favorEntity.getArtworkId()+"\" class=\"badge badge-light\">\n" +
                                            artworksEntity.getTitle()+"\n" +
                                            "</a>\n" +
                                            "</td>\n" +
                                            "<td style=\"display: -webkit-box;\n" +
                                            "  -webkit-box-orient:vertical;\n" +
                                            "  -webkit-line-clamp:2;\n" +
                                            "  overflow: hidden;\">"+artworksEntity.getDescription()+"</td>\n" +
                                            "<td>\n" +
                                            "<img class=\"w-100\" src=\"./resource/img/"+artworksEntity.getType()+"/"+artworksEntity.getImageFileName()+"\" alt=\""+artworksEntity.getTitle()+"\">\n" +
                                            "</td>\n" +
                                            "<td>"+artworksEntity.getView()+"</td>\n" +
                                            "<td>"+artworksEntity.getLocation()+"</td>\n" +
                                            "<td>"+favorEntity.getTime().toString()+"</td>\n" +
                                            "<td>\n" +
                                            "<form class=\"invisible\" method=\"post\" action=\"favor.jsp\">\n" +
                                            "<input type=\"text\" name=\"favorId\" value=\""+favorEntity.getFavorId()+"\">\n" +
                                            "<button type=\"submit\" class=\"btn btn-outline-primary visible\">Remove</button>\n" +
                                            "</form>\n" +
                                            "</td>" +
                                            "</tr>");
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