<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

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

<!-- Modal -->
<%--<div class="modal fade" id="deleteWork<?php echo $rowMyArtWorks['artworkID'] ?>Modal" tabindex="-1"--%>
<%--     role="dialog" aria-labelledby="deleteWork<?php echo $rowMyArtWorks['artworkID'] ?>ModalLabel" aria-hidden="true">--%>
<%--    <div class="modal-dialog modal-dialog-centered" role="document">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <h5 class="modal-title" id="deleteWork<?php echo $rowMyArtWorks['artworkID'] ?>ModalLabel">Delete Art Work</h5>--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
<%--                    <span aria-hidden="true">&times;</span>--%>
<%--                </button>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <form id="delete<?php echo $rowMyArtWorks['artworkID'] ?>" name="delete<?php echo $rowMyArtWorks['artworkID'] ?>" method="get" action="deleteWork.php">--%>
<%--                    <div class="form-row">--%>
<%--                        <div class="col mb-3">--%>
<%--                            <span>You are attempting to DELETE <?php echo $rowMyArtWorks['title'] ?>!</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <input type="hidden" name="artworkID" value="<?php echo $rowMyArtWorks['artworkID'] ?>">--%>
<%--                    <input type="hidden" name="imageFileName" value="<?php echo $rowMyArtWorks['imageFileName'] ?>">--%>
<%--                </form>--%>
<%--            </div>--%>
<%--            <div class="modal-footer">--%>
<%--                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">No</button>--%>
<%--                <button type="submit" class="btn btn-outline-primary" onclick="document.getElementById('delete<?php echo $rowMyArtWorks['artworkID'] ?>').submit()">Yes</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

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

                <!--my artworks-->
                <div class="row">
                    <table class="table table-hover table-sm">
                        <caption>My artworks</caption>
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Release Time</th>
                            <th scope="col">Options</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!--                        <?php-->

                        <!--                        // get my art works-->
                        <!--                        $resultMyArtWorks = $connection->query($sqlMyArtWorks);-->

                        <!--                        // output rows of art works-->
                        <!--                        $i = 1;-->
                        <!--                        while ($rowMyArtWorks = $resultMyArtWorks->fetch_array()) {-->
                        <!--                            echo '<tr>-->
                        <!--                                <th scope="row">'.$i.'</th>-->
                        <!--                                <td>-->
                        <!--                                    <a href="details.php?artworkID='.$rowMyArtWorks['artworkID'].'" class="badge badge-light">-->
                        <!--                                    '.$rowMyArtWorks['title'].'-->
                        <!--                                    </a>-->
                        <!--                                </td>-->
                        <!--                                <td>'.$rowMyArtWorks['timeReleased'].'</td>';-->

                        <!--                            if ($rowMyArtWorks['orderID']) {// sold out-->
                        <!--                                echo '<td>-->
                        <!--                                        <a href="release.html" class="btn btn-outline-primary disabled">Revise</a> &nbsp;-->
                        <!--                                        <button type="button" class="btn btn-outline-primary" disabled>Delete</button>-->
                        <!--                                </td>-->
                        <!--                            </tr>';-->
                        <!--                            } else {// not sold yet-->
                        <!--                                echo '<td>-->
                        <!--                                        <a href="release.html?artworkID='.$rowMyArtWorks['artworkID'].'" class="btn btn-outline-primary">Revise</a> &nbsp;-->
                        <!--                                        <button type="button" class="btn btn-outline-primary" -->
                        <!--                                data-toggle="modal" data-target="#deleteWork'.$rowMyArtWorks['artworkID'].'Modal">Delete</button>-->
                        <!--                                </td>-->
                        <!--                            </tr>';-->
                        <!--                            }-->

                        <!--                            $i++;-->
                        <!--                        }-->

                        <!--                        // release result-->
                        <!--                        $resultMyArtWorks->close();-->

                        <!--                        ?>-->

                        </tbody>
                    </table>
                </div>

                <!--my orders-->
                <div class="row">
                    <table class="table table-hover table-sm">
                        <caption>My orders</caption>
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Order ID</th>
                            <th scope="col">Title</th>
                            <th scope="col">Order Time</th>
                            <th scope="col">Sum</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!--                        <?php-->

                        <!--                        // get my orders-->
                        <!--                        $sqlMyOrders = "SELECT * FROM orders WHERE ownerID = ".$user['userID'];-->
                        <!--                        $resultMyOrders = $connection->query($sqlMyOrders);-->

                        <!--                        // output rows of orders-->
                        <!--                        $i = 1;-->
                        <!--                        while ($rowMyOrders = $resultMyOrders->fetch_array()) {-->

                        <!--                            // output works of every order-->
                        <!--                            echo '<tr>-->
                        <!--                                <th scope="row">'.$i.'</th>-->
                        <!--                                <td>'.$rowMyOrders['orderID'].'</td>-->
                        <!--                                <td>';-->

                        <!--                            // get works of every order-->
                        <!--                            $sqlMyOrderWorks = "SELECT * FROM artworks";-->
                        <!--                            $resultMyOrderWorks = $connection->query($sqlMyOrderWorks);-->
                        <!--                            while ($rowMyOrderWorks = $resultMyOrderWorks->fetch_array()) {-->
                        <!--                                if ($rowMyOrderWorks['orderID'] == $rowMyOrders['orderID']){-->
                        <!--                                    echo '<p>-->
                        <!--                                    <a href="details.php?artworkID='.$rowMyOrderWorks['artworkID'].'" class="badge badge-light">-->
                        <!--                                    '.$rowMyOrderWorks['title'].'-->
                        <!--                                    </a>-->
                        <!--                                </p>';-->
                        <!--                                }-->
                        <!--                            }-->

                        <!--                            echo '</td>-->
                        <!--                                    <td>'.$rowMyOrders['timeCreated'].'</td>-->
                        <!--                                    <td>$'.$rowMyOrders['sum'].'</td>-->
                        <!--                                </tr>';-->

                        <!--                            // release result-->
                        <!--                            $resultMyOrderWorks->close();-->

                        <!--                            $i++;-->
                        <!--                        }-->

                        <!--                        // release result-->
                        <!--                        $resultMyOrders->close();-->

                        <!--                        ?>-->

                        </tbody>
                    </table>
                </div>

                <!--my sold works-->
                <div class="row">
                    <table class="table table-hover table-sm">
                        <caption>My sold works</caption>
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Title</th>
                            <th scope="col">Sold Time</th>
                            <th scope="col">Price</th>
                            <th scope="col">Buyer</th>
                        </tr>
                        </thead>
                        <tbody>

                        <!--                        <?php-->

                        <!--                        // get my sold works-->
                        <!--                        $sqlMySoldWorks = "SELECT * FROM artworks WHERE ownerID = ".$user['userID']." AND orderID IS NOT NULL";-->
                        <!--                        $resultMySoldWorks = $connection->query($sqlMySoldWorks);-->

                        <!--                        // output rows of sold outs-->
                        <!--                        $i = 1;-->
                        <!--                        while ($rowMySoldWorks = $resultMySoldWorks->fetch_array()) {-->

                        <!--                            // get order of every sold out-->
                        <!--                            $sqlMySoldOrder = "SELECT * FROM orders WHERE orderID = ".$rowMySoldWorks['orderID'];-->
                        <!--                            $resultMySoldOrder = $connection->query($sqlMySoldOrder);-->
                        <!--                            $rowMySoldOrder = $resultMySoldOrder->fetch_array();-->

                        <!--                            // get buyer of every order-->
                        <!--                            $sqlMySoldBuyer = "SELECT * FROM users";-->
                        <!--                            $resultMySoldBuyer = $connection->query($sqlMySoldBuyer);-->
                        <!--                            while ($rowMySoldBuyer = $resultMySoldBuyer->fetch_array()) {-->
                        <!--                                if ($rowMySoldOrder['ownerID'] == $rowMySoldBuyer['userID']) {-->
                        <!--                                    break;-->
                        <!--                                }-->
                        <!--                            }-->

                        <!--                            // release results-->
                        <!--                            $resultMySoldOrder->close();-->
                        <!--                            $resultMySoldBuyer->close();-->

                        <!--                            echo '<tr>-->
                        <!--                                <th scope="row">'.$i.'</th>-->
                        <!--                                <td>-->
                        <!--                                    <a href="details.php?artworkID='.$rowMySoldWorks['artworkID'].'" class="badge badge-light">-->
                        <!--                                    '.$rowMySoldWorks['title'].'-->
                        <!--                                    </a>-->
                        <!--                                </td>-->
                        <!--                                <td>'.$rowMySoldOrder['timeCreated'].'</td>-->
                        <!--                                <td>$'.$rowMySoldWorks['price'].'</td>-->
                        <!--                                <td>-->
                        <!--                                    <p>User name: '.$rowMySoldBuyer['name'].'</p>-->
                        <!--                                    <p>Email: '.$rowMySoldBuyer['email'].'</p>-->
                        <!--                                    <p>Tel: '.$rowMySoldBuyer['tel'].'</p>-->
                        <!--                                    <p>Address: '.$rowMySoldBuyer['address'].'</p>-->
                        <!--                                </td>-->
                        <!--                                </tr>';-->

                        <!--                            $i++;-->
                        <!--                        }-->

                        <!--                        // release result-->
                        <!--                        $resultMySoldWorks->close();-->

                        <!--                        ?>-->

                        </tbody>
                    </table>
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
<script src="js/jsSearch.js"></script>
<script src="js/jsModifyModal.js"></script>
</body>
</html>
