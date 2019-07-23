
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="entity.UsersEntity" %>

<%
    UsersEntity editUser = (UsersEntity)request.getAttribute("user");
    UsersEntity user = (UsersEntity)session.getAttribute("user");
%>

<!doctype html>
<html lang="en"><head>
    <meta charset="utf-8">
    <title>Managements Admin</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

    <script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>

    <script src="lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function() {
            $(".knob").knob();
        });
    </script>


    <link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
    <link rel="stylesheet" type="text/css" href="stylesheets/premium.css">

</head>
<body class=" theme-blue">

<!-- Demo page code -->

<script type="text/javascript">
    $(function() {
        var match = document.cookie.match(new RegExp('color=([^;]+)'));
        if(match) var color = match[1];
        if(color) {
            $('body').removeClass(function (index, css) {
                return (css.match (/\btheme-\S+/g) || []).join(' ')
            })
            $('body').addClass('theme-' + color);
        }

        $('[data-popover="true"]').popover({html: true});

    });
</script>
<style type="text/css">
    #line-chart {
        height:300px;
        width:800px;
        margin: 0px auto;
        margin-top: 1em;
    }
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

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">


<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->

<!--<![endif]-->

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
        <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> Personnel management<i class="fa fa-collapse"></i></a></li>
        <li><ul class="dashboard-menu nav nav-list collapse in">
            <li><a href="user.admin?name=<%=user.getName()%>"><span class="fa fa-caret-right"></span> My Profile</a></li>
            <li ><a href="usersList.admin"><span class="fa fa-caret-right"></span> User List</a></li>
        </ul></li>

        <li><a href="#" data-target=".legal-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-legal"></i> Exhibits management<i class="fa fa-collapse"></i></a></li>
        <li><ul class="legal-menu nav nav-list collapse">
            <li ><a href="artwork.admin"><span class="fa fa-caret-right"></span> Upload arts</a></li>
            <li ><a href="artworksList.admin"><span class="fa fa-caret-right"></span> Art list</a></li>
        </ul></li>

    </ul>
</div>

<div class="content">
    <div class="header">
        <h1 class="page-title">Edit User</h1>
    </div>
    <div class="main-content">

        <ul class="nav nav-tabs">
            <li class="active"><a href="#home" data-toggle="tab">Profile</a></li>
        </ul>

        <div class="row">
            <div class="col-md-4">
                <br>
                <div id="myTabContent" class="tab-content">

                    <div class="tab-pane active in" id="home">
                        <h5 id="alertMessage" style="color: red"> </h5>
                        <form action="<%
                if (editUser!=null)out.print("modify");
                else out.print("add");
                %>User.admin" id="userFormSubmit">
                            <input class="invisible" name="userId" value="<%if (editUser!=null)out.print(editUser.getUserId());%>">
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" id="name" name="name" value="<%
                        if (editUser!=null)
                            out.print(editUser.getName());
                            %>" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" id="email" name="email" value="<%
                        if (editUser!=null)
                            out.print(editUser.getEmail());
                            %>" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="text" id="password" name="password" value="<%
                        if (editUser!=null)
                            out.print(editUser.getPassword());
                            %>" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Admin</label>
                                <input type="radio" name="type" value="admin" <%
                        if (editUser == null || editUser.getType().equals("admin"))
                            out.print("checked");
                            %> class="form-control">
                                <label>Normal</label>
                                <input type="radio" name="type" value="normal"
                                    <% if (editUser!=null && editUser.getType().equals("normal"))
                                        out.print("checked");%> class="form-control">
                            </div>
                        </form>
                        <div class="btn-toolbar list-toolbar">
                        <button class="btn btn-primary" id="btSave"><i class="fa fa-save"></i> Save</button>
                        <a href="#myModal" data-toggle="modal" class="btn btn-danger <%if (editUser==null)out.print("invisible");%>">Delete</a>
                    </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Delete Confirmation</h3>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
                        <button class="btn btn-danger" data-dismiss="modal"
                                onclick="<%if (editUser!=null){
                                    out.print("window.location.href='deleteUser.admin?name="+editUser.getName()+"'");
                                }%>">Delete</button>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <hr>
            <p class="pull-right">A <a href="./" target="_blank">Free Bootstrap Theme</a> by <a href="./" target="_blank">Portnine</a></p>
            <p>© 2014 <a href="http://www.portnine.com" target="_blank">Portnine</a></p>
        </footer>
    </div>
</div>

<script src="lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function() {
        $('.demo-cancel-click').click(function(){return false;});
    });
</script>
<script src="resource/js/jsForUser.js"></script>

</body></html>
