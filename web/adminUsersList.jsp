<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.UsersEntity" %>
<%@ page import="java.util.List" %>

<%
    @SuppressWarnings("unchecked")
    List<UsersEntity> usersList = (List<UsersEntity>)request.getAttribute("usersList");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/css/cssCommon.css">
    <title>User List</title>
</head>
<body class=" theme-blue">

<%@include file="nav.jsp"%>

<div class="container my-3">
    <div class="header">
        <h1 class="page-title">Users List</h1>
        <a class="btn btn-outline-primary" href="user.admin">New User</a>
    </div>
    <div class="main-content">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Recent</th>
                <th style="width: 3.5em;"></th>
            </tr>
            </thead>
            <tbody>

            <% for (UsersEntity usersEntity : usersList) { %>

            <tr>
                <td><%=usersList.indexOf(usersEntity) + 1%></td>
                <td><%=usersEntity.getName()%></td>
                <td><%=usersEntity.getEmail()%></td>
                <td><%=usersEntity.getTime()%></td>
                <td>
                    <a class="btn btn-outline-primary" href="user.admin?name=<%=usersEntity.getName()%>">Modify</a><!--修改-->
                    <a class="btn btn-outline-danger" href="deleteUser.admin?name=<%=usersEntity.getName()%>">Delete</a><!--删除-->
                </td>
            </tr>

            <% }// end of loop of user list %>

            </tbody>
        </table>
    </div>
</div>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSearch.js"></script>
</body>
</html>

