<%@ page import="java.util.List" %>
<%@ page import="entity.EmailsEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // will have this attr if going to send email to someone
    String receiverName = (String) request.getAttribute("receiverName");
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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/css/cssCommon.css">
</head>
<body>

<%@include file="nav.jsp"%>

<main>
    <div class="container my-4">
        <h2>My Mailbox</h2>
        <div class="card">
            <div class="card-header">
                <ul class="nav nav-tabs">
                    <li class="nav-item">
                        <a class="nav-link active show" href="#sendEmail" data-toggle="tab">Send email</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#receiveEmailBox" data-toggle="tab">Inbox</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#sendEmailBox" data-toggle="tab">Outbox</a>
                    </li>
                </ul>
            </div>

            <div class="card-body">
                <div class="tab-content">
                    <div class="tab-pane active in" id="sendEmail">
                        <span id="alertMessage" class="alert"></span>
                        <form id="emailForm" action="send.email">
                            <div class="form-group">
                                <label for="send">Recipients:</label>
                                <input type="text" class="form-control" id="send" name="send" placeholder="Enter receiver's name" value="<%=receiverName != null ? receiverName : ""%>">
                            </div>
                            <div class="form-group">
                                <label for="title">Title:</label>
                                <input type="text" class="form-control" id="title" name="title" placeholder="Enter title">
                            </div>
                            <div class="form-group">
                                <label for="content">Content:</label>
                                <textarea class="form-control" id="content" name="content" placeholder="Mail content"></textarea>
                            </div>
                            <button type="button" class="btn btn-outline-primary" id="btSend">Send</button>
                        </form>
                    </div>

                    <div class="tab-pane fade" id="receiveEmailBox">
                        <ul class="list-group">

                            <% for (EmailsEntity email : inbox) { %>

                            <li class="list-group-item ">
                                <a class="btn btn-outline-primary" data-toggle="collapse" href="#collapseEmail<%=email.getEmailId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                                    <%=email.getTime() + "_" + email.getTitle()%>
                                </a>
                                <div class="collapse" id="collapseEmail<%=email.getEmailId()%>">
                                    <div class="card">
                                        <h5 class="card-header"><%=email.getTitle()%></h5>
                                        <div class="card-body">
                                            <h5 class="card-title">From: <%=email.getSenderName()%></h5>
                                            <p class="card-text"><%=email.getContent()%></p>
                                            <a class="btn btn-outline-primary" href="delete.email?emailId=<%=email.getEmailId()%>">
                                                Delete
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <% }// end of loop of inbox email %>

                        </ul>
                    </div>

                    <div class="tab-pane fade" id="sendEmailBox">
                        <ul class="list-group">

                            <% for (EmailsEntity email : outbox){ %>

                            <li class="list-group-item ">
                                <a class="btn btn-outline-primary" data-toggle="collapse" href="#collapseEmail<%=email.getEmailId()%>" role="button" aria-expanded="false" aria-controls="collapseExample">
                                    <%=email.getTime() + "_" + email.getTitle()%>
                                </a>
                                <div class="collapse" id="collapseEmail<%=email.getEmailId()%>">
                                    <div class="card">
                                        <h5 class="card-header"><%=email.getTitle()%></h5>
                                        <div class="card-body">
                                            <h5 class="card-title">To: <%=email.getReceiverName()%></h5>
                                            <p class="card-text"><%=email.getContent()%></p>
                                            <a class="btn btn-outline-primary" href="delete.email?emailId=<%=email.getEmailId()%>">
                                                Delete
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <% } %>

                        </ul>
                    </div>
                </div>
            </div>

            <div class="card-footer">My Email</div>
        </div>

    </div>
</main>

<%@include file="footer.jsp"%>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
<script src="resource/js/jsSearch.js"></script>
<script src="resource/js/jsSendEmail.js"></script>
</body>
</html>
