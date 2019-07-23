<%@ page import="entity.ArtworksEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    @SuppressWarnings("unchecked")
    List<ArtworksEntity> hottestArtworks = (List<ArtworksEntity>) request.getAttribute("hottestArtworks");
    @SuppressWarnings("unchecked")
    List<ArtworksEntity> newestArtworks = (List<ArtworksEntity>) request.getAttribute("newestArtworks");

    if (hottestArtworks == null || newestArtworks == null || hottestArtworks.size() == 0 || newestArtworks.size() == 0) {// incorrect visit
        response.sendRedirect("error.page?message=NotExist");
    }
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/css/cssCommon.css">
    <title>Museum</title>
</head>
<body>

<%@include file="nav.jsp"%>

<main>
    <div id="carouselGallery" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselGallery" data-slide-to="0" class="active"></li>
            <li data-target="#carouselGallery" data-slide-to="1"></li>
            <li data-target="#carouselGallery" data-slide-to="2"></li>
        </ol>

        <%

        %>

        <div class="carousel-inner">

            <%
                for (int i = 0; i < 3; i++) {
                    ArtworksEntity artwork = hottestArtworks.get(i);
            %>

            <div class="carousel-item <%=i == 0 ? "active" : ""%>">
                <img class="d-block w-100" src="resource/img/<%=artwork.getImageFileName()%>" alt="<%=artwork.getTitle()%>">
                <div class="carousel-caption d-none d-md-block">
                    <h1 class="display-4"><%=artwork.getTitle()%></h1>
                    <h4 style="display: -webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:6;overflow: hidden;"><%=artwork.getDescription()%></h4>
                    <a class="btn btn-outline-light" href="details.page?artworkId=<%=artwork.getArtworkId()%>" role="button">Learn more</a>
                </div>
            </div>

            <%
                }
            %>

            <a class="carousel-control-prev" href="#carouselGallery" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselGallery" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

    <div class="card-group text-center">

        <%
            for (int i = 0; i < 3; i++) {
                ArtworksEntity artwork = newestArtworks.get(i);
        %>

        <div class="card">
            <img class="card-img-top" src="resource/img/<%=artwork.getImageFileName()%>" alt="<%=artwork.getTitle()%>" style="height: 500px">
            <div class="card-body">
                <h3 class="card-title"><%=artwork.getTitle()%></h3>
                <p class="card-text" style="display: -webkit-box;-webkit-box-orient:vertical;-webkit-line-clamp:3;overflow: hidden;"><%=artwork.getDescription()%></p>
            </div>
            <div class="card-footer">
                <a href="details.page?artworkId=<%=artwork.getArtworkId()%>" class="btn btn-outline-primary">More Details</a>
            </div>
        </div>

        <%
            }
        %>

    </div>
</main>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSignIn.js"></script>
<script src="resource/js/jsSearch.js"></script>
</body>
</html>

