<%@ page import="java.util.List" %>
<%@ page import="entity.EmailsEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EmailsEntity readEmail = (EmailsEntity)request.getAttribute("email");//first into this page
    String receiverName = (String)request.getAttribute("receiverName");
    @SuppressWarnings("unchecked")
    List<EmailsEntity> inbox = (List<EmailsEntity>)request.getAttribute("inbox");
    @SuppressWarnings("unchecked")
    List<EmailsEntity> outbox = (List<EmailsEntity>)request.getAttribute("outbox");
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Email</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <style>
        .list-group a{color: #69696c;}
        .list-group a:hover{text-decoration: none;}
        .del{float: right;}
        .del:hover{cursor:pointer;font-weight: bold;}
        .boxTitle{margin-left: 5em;}
    </style>
</head>
<body>


<div class="container">
    <h2>我的邮箱</h2>
    <div class="card">
        <div class="card-header">
            <ul class="nav nav-tabs">
                <li class="nav-item active" >
                    <a class="nav-link" href="#sendEmail" data-toggle="tab">Send email</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link" href="#receiveEmailBox" data-toggle="tab">Inbox</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link" href="#sendEmailBox" data-toggle="tab">Outbox</a>
                </li>
                <%--此处需要修改href指向个人界面--%>
                <li class="nav-item">
                    <a class="nav-link" href="">Personal</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.page">Home</a>
                </li>

            </ul>
        </div>

        <div class="card-body">
            <div class="tab-content">
                <div class="tab-pane active in" id="sendEmail">
                    <h5 id="alertMessage" style="color: red"> </h5>
                    <form id="emailForm" action="send.email">
                            <div class="form-group">
                                <label for="send">Recipients:</label><span class="warning send"></span>
                                <input type="text" class="form-control" id="send" name="send" placeholder="Enter receiver' name" value="<%if (receiverName!=null){
                                    out.print(receiverName);
                                }if (readEmail!=null){
                                    out.print(readEmail.getReceiverName());
                                }%>">
                            </div>
                            <div class="form-group">
                                <label for="title">Title:</label><span class="warning title"></span>
                                <input type="text" class="form-control" id="title" name="title" placeholder="Enter title" value="<%
                                if (readEmail!=null){
                                    out.print(readEmail.getTitle());
                                }%>">
                            </div>
                            <div class="form-group">
                                <label for="content">Content:</label><span class="warning content"></span>
                                <textarea class="form-control" id="content" name="content" placeholder="Mail content"><%
                                    if (readEmail!=null){
                                        out.print(readEmail.getContent());
                                    }%></textarea>
                            </div>
                            <button type="button" class="btn btn-primary <%
                                if (readEmail!=null){
                                    out.print("invisible");
                                }%>" id="btSend" >Send</button>
                    </form>
                </div>

                <div class="tab-pane fade" id="receiveEmailBox">
                    <ul class="list-group">
                        <%
                            for (EmailsEntity email:inbox){
                        %>
                        <li class="list-group-item "><a href="read.email?emailId=<%=email.getEmailId()%>"><%=email.getTime()%><span class='boxTitle'><%=email.getTitle()%></span></a><a class='del' onclick="window.location.href='delete.email?emailId=<%=email.getEmailId()%>'">Delete</a></li>
                        <br>
                        <%
                            }
                        %>
                    </ul>
                </div>

                <div class="tab-pane fade" id="sendEmailBox">
                    <ul class="list-group">
                        <%
                            for (EmailsEntity email:outbox){
                        %>
                        <li class="list-group-item "><a href="read.email?emailId=<%=email.getEmailId()%>"><%=email.getTime()%><span class='boxTitle'><%=email.getTitle()%></span></a><a class='del' onclick="window.location.href='delete.email?emailId=<%=email.getEmailId()%>'">Delete</a></li>
                        <br>
                        <%
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>

        <div class="card-footer">My Email</div>
    </div>

</div>

</body>
<script src="js/jsSendEmail.js"></script>
</html>
