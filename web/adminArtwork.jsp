
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.ArtworksEntity" %>
<%@ page import="entity.UsersEntity" %><%
    ArtworksEntity artwork = (ArtworksEntity)request.getAttribute("artwork");
    UsersEntity user = (UsersEntity)session.getAttribute("user");
%>
<!doctype html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>Managements</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="adminPageLib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="adminPageLib/font-awesome/css/font-awesome.css">
    <script src="adminPageLib/jquery-1.11.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="adminPageLib/theme.css">
    <link rel="stylesheet" type="text/css" href="adminPageLib/premium.css">
</head>
<body class=" theme-blue">
<style type="text/css">
    .navbar-default .navbar-brand, .navbar-default .navbar-brand:hover {
        color: #fff;
    }
</style>

<script type="text/javascript">
    $(function() {
        var uls = $('.sidebar-nav > ul > *').clone();
        uls.addClass('visible-xs');
        $('#main-menu').append(uls.clone());
    });
</script>
<div class="navbar navbar-default" role="navigation">
    <!--<div class="navbar-header">-->
    <a href="adminIndex.jsp"><span class="navbar-brand"><span class="fa fa-paper-plane"></span> Museum</span></a>

    <div class="navbar-collapse collapse" style="height: 1px;"> <!--这个是导航栏上管理员的信息（右上角那个）-->
        <ul id="main-menu" class="nav navbar-nav navbar-right">
            <li class="dropdown hidden-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="glyphicon glyphicon-user padding-right-small" style="position:relative;top: 3px;"></span> <%=user.getName()%>
                    <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu">

                    <li><a href="user.admin?name=<%=user.getName()%>">My Account</a></li>
                    <li class="divider"></li>
                    <li><a href="index.page">Front Page</a> </li><!--这个好像没啥用，可以做装饰-->

                    <li><a href="usersList.admin">Users</a></li>
                    <li class="divider"></li>
                    <li><a tabindex="-1" href="">Logout</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>


<div class="sidebar-nav">
    <ul>
        <li><a href="#" data-target=".dashboard-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> Personnel management<i class="fa fa-collapse"></i></a></li>
        <li><ul class="dashboard-menu nav nav-list collapse">
            <li><a href="user.admin?name=<%=user.getName()%>"><span class="fa fa-caret-right"></span> My Profile</a></li>
            <li ><a href="usersList.admin"><span class="fa fa-caret-right"></span> User List</a></li>
        </ul></li>

        <li><a href="#" data-target=".legal-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-legal"></i> Exhibits management<i class="fa fa-collapse"></i></a></li>

        <li><ul class="legal-menu nav nav-list collapse in">
            <li ><a href="artwork.admin"><span class="fa fa-caret-right"></span> Upload arts</a></li>
            <li ><a href="artworksList.admin"><span class="fa fa-caret-right"></span> Art list</a></li>
        </ul></li>

    </ul>
</div>

<div class="content">
    <div class="header">
        <h1 class="page-title">Edit Art</h1>
    </div>
    <div class="main-content">
        <div class="container my-3">
            <h5>Release a work</h5>
            <h5 id="alertMessage" style="color: red"> </h5>
            <form id="artworkFormSubmit" enctype="multipart/form-data" method="post" action="<%
                if (artwork!=null)out.print("modify");
                else out.print("add");
                %>Artwork.admin">
                <input class="invisible" name="submitWay" id="submitWay" value="<%
                if (artwork!=null)out.print("modify");
                else out.print("add");
                %>">
                <input class="invisible" name="artworkId" id="artworkId" value="<%
                if (artwork!=null)out.print(artwork.getArtworkId());
                %>">
                <div class="form-row">
                    <div class="form-group col-md-12">
                        <label for="title">Title</label>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Title" value="<%
                        if (artwork!=null)
                            out.print(artwork.getTitle());
                            %>">
                        <!--<span id="alertTitle" class="alert"></span>-->
                    </div>
                </div>
                <div class="form-group col-md-12">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="3" placeholder="Description"><%
                        if (artwork!=null)
                            out.print(artwork.getDescription());
                    %></textarea>

                    <!--<span id="alertDescription" class="alert"></span>-->
                </div>
                <div class="form-row">
                    <div class="form-group col-md-8">
                        <label for="location">Location</label>
                        <input type="text" class="form-control" id="location" name="location" placeholder="Location" value="<%
                        if (artwork!=null)
                            out.print(artwork.getLocation());
                            %>">
                        <!--<span id="alertGenre" class="alert"></span>-->
                    </div>
                    <div class="form-group col-md-4">
                        <label for="yearOfWork">Year of work</label>
                        <input type="number" class="form-control" id="yearOfWork" name="yearOfWork" placeholder="Year of work" value="<%
                        if (artwork!=null)
                            out.print(artwork.getYearOfWork());
                            %>">

                        <!--<span id="alertYearOfWork" class="alert"></span>-->
                    </div>
                </div>
                <div class="form-group">
                    <div class="custom-file col-md-4">
                        <label for="imageFileName">Upload picture</label>
                        <input type="file" id="imageFileName" name="imageFileName" accept="image/*">
                    </div>
                </div>
                <div class="form-group">
                    <div class="custom-file col-md-4">
                        <label for="videoFileName">Upload video</label>
                        <input type="file" id="videoFileName" name="videoFileName" accept="video/*">
                    </div>
                </div>
                    <div class="custom-file col-md-8"></div>

                <div class="form-group">
                    <div class="custom-file col-md-12">
                        <br>
                        <button type="button" id="btSubmit" class="btn btn-success">Submit</button>
                    </div>
                </div>
            </form>
        </div>

        <footer>
            <hr>
            <p class="pull-right">A <a  target="_blank">Free Bootstrap Theme</a> by <a target="_blank">Portnine</a></p>
            <p>© 2019 <a target="_blank">Museum</a></p>
        </footer>
    </div>
</div>

<script src="adminPageLib/bootstrap/js/bootstrap.js"></script>

<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
</script>

<script src="adminPageLib/js/jsForArtwork.js" type="text/javascript"></script>


</body></html>
