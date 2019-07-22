<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    if (session.getAttribute("user") == null) {// not logged in
        response.sendRedirect("error.page?message=NotLoggedIn");
    }
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="resource/css/cssCommon.css">
    <title>Modify</title>
</head>
<body>

<%@include file="nav.jsp"%>

<main>
    <div class="container my-3">
        <form id="signUp" method="post" action="${pageContext.request.contextPath}/modify.user">
            <h3 class="mb-3">Modify</h3>
            <input id="oldUsername" type="hidden" value="<%=user.getName()%>">
            <div class="form-row">
                <div class="col mb-3">
                    <label for="signUpUserName">User name: </label>
                    <input type="text" class="form-control" id="signUpUserName" name="signUpUserName" value="<%=user.getName()%>" aria-describedby="userNameHelpBlock" placeholder="User name" onblur="checkSignUpUserName(this.value.trim())">
                    <small id="userNameHelpBlock" class="form-text text-muted">
                        Your user name should have length from 4 to 15. Like 'HNoodles1' is ok.
                    </small>
                    <span id="alertUserName" class="alert"></span>
                </div>
            </div>
            <div class="form-row">
                <div class="col mb-3">
                    <label for="signUpEmail">Email: </label>
                    <input type="text" class="form-control" id="signUpEmail" name="signUpEmail" value="<%=user.getEmail()%>" aria-describedby="emailHelpBlock" placeholder="Email" onblur="checkSignUpEmail(this.value.trim())">
                    <small id="emailHelpBlock" class="form-text text-muted">
                        Your email should be like 'name@example.com'.
                    </small>
                    <span id="alertEmail" class="alert"></span>
                </div>
            </div>
            <div class="form-row">
                <div class="col mb-3">
                    <label for="signUpPassword">Password: </label>
                    <input type="password" class="form-control" id="signUpPassword" name="signUpPassword" value="" aria-describedby="passwordHelpBlock" placeholder="Password" readonly>
                    <small id="passwordHelpBlock" class="form-text text-muted">
                        Sorry, you cannot modify your password.
                    </small>
                    <span id="alertPassword" class="alert"></span>
                </div>
            </div>
            <div class="form-row">
                <div class="col mb-3">
                    <label for="signUpSignature">Signature: </label>
                    <input type="text" class="form-control" id="signUpSignature" name="signUpSignature" value="<%=user.getSignature()%>" aria-describedby="signatureHelpBlock" placeholder="Signature" onblur="checkSignUpSignature(this.value.trim())">
                    <small id="signatureHelpBlock" class="form-text text-muted">
                        Leave some comments of yours here. MAX LENGTH: 120.
                    </small>
                    <span id="alertSignature" class="alert"></span>
                </div>
            </div>
            <button type="button" id="signUpSubmit" class="btn btn-outline-primary" data-toggle="modal" data-target="#alertModal">Submit</button>
        </form>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="alertModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="alertModalLabel">Modify Info</h5>
                </div>
                <div id="signUpInfo" class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" id="signUpCommit" class="btn btn-secondary" data-dismiss="modal">OK</button>
                </div>
            </div>
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
<script src="resource/js/jsModify.js"></script>
<script src="resource/js/jsSearch.js"></script>
</body>
</html>
