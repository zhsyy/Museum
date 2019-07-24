<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.UsersEntity" %>

<%
    UsersEntity editUser = (UsersEntity)request.getAttribute("user");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/css/cssCommon.css">
    <title>Edit User</title>
</head>
<body>

<%@include file="nav.jsp"%>

<div class="container my-3">
    <div class="header">
        <h1 class="page-title">Edit User</h1>
    </div>
    <div class="main-content">
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
                            <input type="hidden" name="userId" value="<%if (editUser!=null)out.print(editUser.getUserId());%>">
                            <div class="form-group">
                                <label for="name">Username</label>
                                <input type="text" id="name" name="name" value="<%
                                                if (editUser!=null) out.print(editUser.getName());
                                                %>" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" id="email" name="email" value="<%
                                                if (editUser!=null) out.print(editUser.getEmail());
                                                %>" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="text" id="password" name="password" value="<%
                                                if (editUser!=null) out.print(editUser.getPassword());
                                                %>" class="form-control">
                            </div>
                            <div class="form-group">
                                <label>Admin</label>
                                <input type="radio" name="type" value="admin" <%
                                                if (editUser == null || editUser.getType().equals("admin")) out.print("checked");
                                                %> class="form-control">
                                <label>Normal</label>
                                <input type="radio" name="type" value="normal" <%
                                                if (editUser!=null && editUser.getType().equals("normal")) out.print("checked");
                                                %> class="form-control">
                            </div>
                        </form>
                        <div class="btn-toolbar list-toolbar">
                            <button class="btn btn-outline-primary" id="btSave"><i class="fa fa-save"></i> Save</button>
                            <a href="#myModal" data-toggle="modal" class="btn btn-outline-danger <%if (editUser==null)out.print("invisible");%>">Delete</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal small fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h3 id="myModalLabel">Delete Confirmation</h3>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <p class="error-text"><i class="fa fa-warning modal-icon"></i>Are you sure you want to delete the user?</p>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-outline-danger" data-dismiss="modal"
                                onclick="<%if (editUser!=null){ out.print("window.location.href='deleteUser.admin?name="+editUser.getName()+"'");}%>">
                            Delete
                        </button>
                        <button class="btn btn-outline-default" data-dismiss="modal" aria-hidden="true">Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSearch.js"></script>
<script src="resource/js/jsForUser.js"></script>
</body>
</html>
