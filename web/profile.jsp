<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    @SuppressWarnings("unchecked")
    List<UsersEntity> friends = (List<UsersEntity>) request.getAttribute("friends");
    @SuppressWarnings("unchecked")
    List<UsersEntity> requestSenders = (List<UsersEntity>) request.getAttribute("requestSenders");
    @SuppressWarnings("unchecked")
    Map<UsersEntity, String> searchedUsers = (Map<UsersEntity, String>) request.getAttribute("searchedUsers");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/cssCommon.css">
    <title>Profile of <%=((UsersEntity) session.getAttribute("user")).getName()%></title>
</head>
<body>

<%@include file="nav.jsp"%>

<div class="modal fade" id="modifyFormModal" tabindex="-1" role="dialog" aria-labelledby="modifyFormModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyFormModalLabel">Modify</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="modify" name="modify" method="post" action="checkedPassword.user">
                    <div class="form-row">
                        <div class="col mb-3">
                            <span id="alertModify" class="alert"></span>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col mb-3">
                            <label for="modifyPassword">Please enter your password: </label>
                            <input type="password" class="form-control" id="modifyPassword" name="modifyPassword" placeholder="Password">
                        </div>
                    </div>
                    <label for="userId"></label><input type="text" class="invisible" id="userId" name="userId" value="<%=user.getUserId()%>">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
                <button type="button" id="modifySubmit" class="btn btn-outline-primary">Submit</button>
            </div>
        </div>
    </div>
</div>

<main class="profile">

    <div class="container my-4">
        <div class="row">
            <div class="col-sm-4">
                <h3>Profile</h3>
                <dl class="row">
                    <dt class="col-sm-3">User: </dt>
                    <dd class="col-sm-9"><%=user.getName()%></dd>

                    <dt class="col-sm-3">Email: </dt>
                    <dd class="col-sm-9"><%=user.getEmail()%></dd>

                    <dt class="col-sm-3">Signature: </dt>
                    <dd class="col-sm-9"><%=user.getSignature()%></dd>
                </dl>
                <button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#modifyFormModal">Modify</button>
            </div>

            <div class="col-sm-8">

                <div class="row">
                    <table class="table table-hover table-sm">
                        <caption>New Friends</caption>
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Signature</th>
                            <th scope="col">Options</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% for (UsersEntity sender : requestSenders) { %>

                        <tr>
                            <th scope="row" class="align-middle"><%=requestSenders.indexOf(sender) + 1%></th>
                            <td class="align-middle">
                                <a href="friend.page?friendId=<%=sender.getUserId()%>" class="badge badge-light"><%=sender.getName()%></a>
                            </td>
                            <td class="align-middle">
                                <%=sender.getEmail()%>
                            </td>
                            <td class="align-middle">
                                <%=sender.getSignature()%>
                            </td>
                            <td class="align-middle">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <form method="post" action="accept.friend">
                                            <input type="hidden" name="senderId" value="<%=sender.getUserId()%>">
                                            <input type="hidden" name="receiverId" value="<%=user.getUserId()%>">
                                            <button type="submit" class="btn btn-outline-primary">Accept</button>
                                        </form>
                                    </div>
                                    <div class="col-sm-4">
                                        <form method="post" action="reject.friend">
                                            <input type="hidden" name="senderId" value="<%=sender.getUserId()%>">
                                            <input type="hidden" name="receiverId" value="<%=user.getUserId()%>">
                                            <button type="submit" class="btn btn-outline-primary">Reject</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <% } // end of loop of friend requests  %>

                        </tbody>
                    </table>
                </div>

                <div class="row">
                    <table class="table table-hover table-sm">
                        <caption>My Friends</caption>
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Signature</th>
                            <th scope="col">Options</th>
                        </tr>
                        </thead>
                        <tbody>

                        <% for (UsersEntity friend : friends) { %>

                        <tr>
                            <th scope="row" class="align-middle"><%=friends.indexOf(friend) + 1%></th>
                            <td class="align-middle">
                                <a href="friend.page?friendId=<%=friend.getUserId()%>" class="badge badge-light"><%=friend.getName()%></a>
                            </td>
                            <td class="align-middle">
                                <%=friend.getEmail()%>
                            </td>
                            <td class="align-middle">
                                <%=friend.getSignature()%>
                            </td>
                            <td class="align-middle">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <form method="post" action="<%// TODO: email page%>">
                                            <input type="hidden" name="toName" value="<%=friend.getName()%>">
                                            <button type="submit" class="btn btn-outline-primary">Send Message</button>
                                        </form>
                                    </div>
                                    <div class="col-sm-6">
                                        <form method="post" action="delete.friend">
                                            <input type="hidden" name="friendId" value="<%=friend.getUserId()%>">
                                            <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                                            <button type="submit" class="btn btn-outline-primary">Delete</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>

                        <% } // end of loop of friends %>

                        </tbody>
                    </table>
                </div>

                <form class="form-inline" method="post" action="search.friend">
                    <div class="form-group">
                        <label for="inputUsername" class="col-sm-6 col-form-label">Input username to search: </label>
                        <div class="col-sm-4">
                            <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                            <input type="text" class="form-control" id="inputUsername" name="searchName" placeholder="Username">
                        </div>
                    </div>
                    <button type="submit" class="col-sm-2 btn btn-outline-primary">Search</button>
                </form>

                <% if (searchedUsers != null) {// have searched results %>

                <div class="row">
                    <table class="table table-hover table-sm">
                        <caption>Users Found</caption>
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Signature</th>
                            <th scope="col">Options</th>
                        </tr>
                        </thead>
                        <tbody>

                        <%
                            int index = 0;
                            for (Map.Entry<UsersEntity, String> entry : searchedUsers.entrySet()) {
                                UsersEntity result = entry.getKey();
                        %>

                        <tr>
                            <th scope="row" class="align-middle"><%=++index%></th>
                            <td class="align-middle">
                                <a href="friend.page?friendId=<%=result.getUserId()%>" class="badge badge-light"><%=result.getName()%></a>
                            </td>
                            <td class="align-middle">
                                <%=result.getEmail()%>
                            </td>
                            <td class="align-middle">
                                <%=result.getSignature()%>
                            </td>
                            <td class="align-middle">

                                <% if ("accepted".equals(entry.getValue())) {// is friend %>

                                <form method="post" action="delete.friend">
                                    <input type="hidden" name="friendId" value="<%=result.getUserId()%>">
                                    <input type="hidden" name="userId" value="<%=user.getUserId()%>">
                                    <button type="submit" class="btn btn-outline-primary">Delete</button>
                                </form>

                                <% } else if ("sent".equals(entry.getValue())) {// has sent request %>

                                <form method="post" action="add.friend">
                                    <input type="hidden" name="receiverId" value="<%=result.getUserId()%>">
                                    <input type="hidden" name="senderId" value="<%=user.getUserId()%>">
                                    <button type="submit" class="btn btn-outline-primary" title="Friend request already sent." disabled>Send Friend Request</button>
                                </form>

                                <% } else {// not friend %>

                                <form method="post" action="add.friend">
                                    <input type="hidden" name="receiverId" value="<%=result.getUserId()%>">
                                    <input type="hidden" name="senderId" value="<%=user.getUserId()%>">
                                    <button type="submit" class="btn btn-outline-primary">Send Friend Request</button>
                                </form>

                                <% }// end of not friend %>

                            </td>
                        </tr>

                        <%
                            } // end of loop of user search results
                        %>

                        </tbody>
                    </table>
                </div>

                <% }// end of have searched results %>

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
<script src="js/jsSearch.js"></script>
<script src="js/jsModifyModal.js"></script>
</body>
</html>
