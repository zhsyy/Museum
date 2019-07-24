<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.ArtworksEntity" %>

<%
    ArtworksEntity artwork = (ArtworksEntity)request.getAttribute("artwork");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/css/cssCommon.css">
    <title>Upload Artwork</title>
</head>
<body>

<%@include file="nav.jsp"%>

<div class="container my-3">
    <div class="header">
        <h1 class="page-title">Edit Art</h1>
    </div>
    <div class="main-content">
        <div class="container my-3">
            <span id="alertMessage" class="alert"></span>
            <form id="artworkFormSubmit" enctype="multipart/form-data" method="post" action="<%
                            if (artwork!=null)out.print("modify");
                            else out.print("add");
                            %>Artwork.admin">
                <input type="hidden" name="submitWay" id="submitWay" value="<%
                                if (artwork!=null)out.print("modify");
                                else out.print("add");
                                %>">
                <input type="hidden" name="artworkId" id="artworkId" value="<%
                                if (artwork!=null)out.print(artwork.getArtworkId());
                                %>">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Title" value="<%
                                        if (artwork!=null)out.print(artwork.getTitle());
                                        %>">
                        <!--<span id="alertTitle" class="alert"></span>-->
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="description">Description</label>
                        <textarea class="form-control" id="description" name="description" rows="3" placeholder="Description"><%
                            if (artwork!=null) out.print(artwork.getDescription());
                        %></textarea>
                        <!--<span id="alertDescription" class="alert"></span>-->
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="location">Location</label>
                        <input type="text" class="form-control" id="location" name="location" placeholder="Location" value="<%
                                        if (artwork!=null) out.print(artwork.getLocation());
                                        %>">
                        <!--<span id="alertGenre" class="alert"></span>-->
                    </div>
                    <div class="form-group col-md-4">
                        <label for="yearOfWork">Year of work</label>
                        <input type="number" class="form-control" id="yearOfWork" name="yearOfWork" placeholder="Year of work" value="<%
                                        if (artwork!=null) out.print(artwork.getYearOfWork());
                                        %>">
                        <!--<span id="alertYearOfWork" class="alert"></span>-->
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="imageFileName">Upload picture</label>
                        <input type="file" id="imageFileName" name="imageFileName" accept="image/*">
                    </div>
                    <div class="form-group">
                        <label for="videoFileName">Upload video</label>
                        <input type="file" id="videoFileName" name="videoFileName" accept="video/*">
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <button type="button" id="btSubmit" class="btn btn-outline-primary">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSearch.js"></script>
<script src="resource/js/jsForArtwork.js"></script>
</body>
</html>
