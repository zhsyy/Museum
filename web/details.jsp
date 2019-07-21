<%@ page import="entity.ArtworksEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArtworksEntity artwork = (ArtworksEntity) request.getAttribute("artwork");

    if (artwork == null) {
        response.sendRedirect("error.jsp?message=NotExist");
    }

    assert artwork != null;
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/cssCommon.css">
    <title>Details of <%=artwork.getTitle()%>></title>
</head>
<body>

<%@include file="nav.jsp"%>

<main>
    <div class="row m-3">
        <div class="col-sm-6">
            <img class="rounded w-100" src="resource/img/<%=artwork.getImageFileName()%>" alt="<%=artwork.getTitle()%>">
        </div>
        <div class="col-sm-6">
            <h1><%=artwork.getTitle()%></h1>
            <h3>At <%=artwork.getLocation()%></h3>

            <dl class="row">
                <dt class="col-sm-3">Year of work: </dt>
                <dd class="col-sm-9"><%=artwork.getYearOfWork()%></dd>

                <dt class="col-sm-3">Viewed: </dt>
                <dd class="col-sm-9"><%=artwork.getView()%> Time(s)</dd>

                <dt class="col-sm-3">Description: </dt>
                <dd class="col-sm-9">
                    <p><%=artwork.getDescription()%></p>
                </dd>

<%--                TODO: if have video, show--%>
<%--                <dt class="col-sm-3">Released time: </dt>--%>
<%--                <dd class="col-sm-9">XXXX</dd>--%>
            </dl>

            <% if (user != null) {// logged in %>

            <% boolean isFavored = (boolean) request.getAttribute("isFavored"); %>

            <form class="invisible" method="post" action="add.favor">
                <label><input id="artworkId" name="artworkId" type="text" value="<%=artwork.getArtworkId()%>"></label>
                <label><input id="userId" name="userId" type="text" value="<%=user.getUserId()%>"></label>

                <% if (isFavored) { %>

                <button type="submit" class="btn btn-outline-primary btn-block visible" title="Already Favored" disabled>Add to My Favor</button>

                <% } else { %>

                <button type="submit" class="btn btn-outline-primary btn-block visible">Add to My Favor</button>

                <% } %>

            </form>

            <%      if ("admin".equals(user.getType())) {// admin logged in %>

            <form class="invisible" method="post" action="<% //TODO: set manage page as action%>">
                <button type="submit" class="btn btn-outline-primary btn-block visible">Manage the Artwork</button>
                <label><input name="artworkId" type="text" value="<%=artwork.getArtworkId()%>"></label>
            </form>

            <%      }// end of admin logged in %>

            <% } else {// not logged in %>

            <p class="text-center"><span class="alert">Please login first.</span></p>
            <button type="button" class="btn btn-outline-primary btn-block" title="Please login first." disabled>Add to My Favor</button>

            <% }// end of not logged in %>

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
<script src="js/jsSignIn.js"></script>
<script src="js/jsSearch.js"></script>
</body>
</html>
