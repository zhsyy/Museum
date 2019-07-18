<%@ page import="entity.ArtworksEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="service.ArtworkService" %>
<%@ page import="service.impl.ArtworkServiceImp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    private ArtworkService artworkService = new ArtworkServiceImp();
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/cssCommon.css">
    <title>Museum</title>
</head>
<body>

<%@include file="nav.jsp"%>

<main class="index">
    <div id="carouselGallery" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselGallery" data-slide-to="0" class="active"></li>
            <li data-target="#carouselGallery" data-slide-to="1"></li>
            <li data-target="#carouselGallery" data-slide-to="2"></li>
        </ol>

        <%
            List<ArtworksEntity> hottestArtworks = artworkService.getHottestArtworks();
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
                    <h4><%=artwork.getDescription()%></h4>
                    <a class="btn btn-outline-light" href="details.html?artworkID='.$rowView['artworkID'].'" role="button">Learn more</a>
                </div>
            </div>

            <% } %>

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

        <!--        <?php-->
        <!--        // get result order by released time-->
        <!--        $sqlTimeReleased = 'SELECT * FROM artworks WHERE orderID IS NULL ORDER BY timeReleased DESC';-->
        <!--        $resultTImeReleased = $connection->query($sqlTimeReleased);-->

        <!--        for ($i = 0; $i < 3; $i++) {-->
        <!--            $rowTimeReleased = $resultTImeReleased->fetch_array();-->
        <!--            echo '<div class="card">-->
        <!--            <img class="card-img-top" src="resources/img/'.$rowTimeReleased['imageFileName'].'" alt="'.$rowTimeReleased['title'].'">-->
        <!--            <div class="card-body">-->
        <!--                <h3 class="card-title">'.$rowTimeReleased['title'].'</h3>-->
        <!--                <p class="card-text">'.$rowTimeReleased['description'].'</p>-->
        <!--            </div>-->
        <!--            <div class="card-footer">-->
        <!--                <a href="details.html?artworkID='.$rowTimeReleased['artworkID'].'" class="btn btn-outline-primary">More Details</a>-->
        <!--            </div>-->
        <!--        </div>';-->
        <!--        }-->

        <!--        $resultTImeReleased->close();-->

        <!--        $connection->close();-->
        <!--        ?>-->
        <div class="card">
            <img class="card-img-top" src="SOME_IMAGE" alt="Title">
            <div class="card-body">
                <h3 class="card-title">SOME_IMAGE</h3>
                <p class="card-text">Description</p>
            </div>
            <div class="card-footer">
                <a href="details.html" class="btn btn-outline-primary">More Details</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="SOME_IMAGE" alt="Title">
            <div class="card-body">
                <h3 class="card-title">SOME_IMAGE</h3>
                <p class="card-text">Description</p>
            </div>
            <div class="card-footer">
                <a href="details.html" class="btn btn-outline-primary">More Details</a>
            </div>
        </div>
        <div class="card">
            <img class="card-img-top" src="SOME_IMAGE" alt="Title">
            <div class="card-body">
                <h3 class="card-title">SOME_IMAGE</h3>
                <p class="card-text">Description</p>
            </div>
            <div class="card-footer">
                <a href="details.html" class="btn btn-outline-primary">More Details</a>
            </div>
        </div>

    </div>
</main>

<footer class="footer navbar navbar-dark bg-dark">
    <div class="navbar-text m-auto">Produced and maintained by HNoodles in 2019</div>
</footer>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="js/jsSignIn.js"></script>
<!--<script src="js/jsSearch.js"></script>-->
</body>
</html>

