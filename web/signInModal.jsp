<%!
    private static void successLogin(HttpServletRequest request, HttpSession session, String username) {
        session.setAttribute("username", username);
    }
%>

<!-- Modal -->
<div class="modal fade" id="signInFormModal" tabindex="-1" role="dialog" aria-labelledby="signInFormModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signInFormModalLabel">Login</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="signIn" method="post" name="signIn" action="${pageContext.request.contextPath}/checkLogin">
                    <div class="form-row">
                        <div class="col mb-3">
                            <span id="alertSignIn" class="alert"></span>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col mb-3">
                            <label for="signInUserName">Username: </label>
                            <input type="text" class="form-control" id="signInUserName" name="username" placeholder="Username">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col mb-3">
                            <label for="signInPassword">Password: </label>
                            <input type="password" class="form-control" id="signInPassword" name="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-8 mb-3">
                            <label for="verify">Verify: </label>
                            <input type="text" class="form-control" id="verify" placeholder="Verify code">
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="code">Code: </label>
                            <a id="code" href="javascript:changeVerify()" class="badge badge-light">2144</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Close</button>
                <button type="button" id="signInSubmit" class="btn btn-outline-primary">Submit</button>
            </div>
        </div>
    </div>
</div>