<%@ page import="entity.UsersEntity" %>
<%
    String navFront, navNotLoggedIn, navLoggedIn, navAdmin;
    navFront = navNotLoggedIn = navLoggedIn = navAdmin = "";

    final String ACTIVE = "active";

    switch (request.getServletPath()) {
        case "":
        case "/index.jsp":
            navFront = ACTIVE;
            break;
        case "/signUp.jsp":
            navNotLoggedIn = ACTIVE;
            break;
        case "/favor.jsp":
        case "/profile.jsp":
        case "/email.jsp":
            navLoggedIn = ACTIVE;
            break;
        case "/adminIndex.jsp":
        case "/adminArtwork.jsp":
        case "/adminArtworkList.jsp":
        case "/adminUser.jsp":
        case "adminUsersList.jsp":
            navAdmin = ACTIVE;
            break;
    }
%>

<%
    String checkTitle, checkDescription, checkLocation;

    final String CHECKED = "checked";
    final String UNCHECKED = "";

    checkTitle = request.getAttribute("searchByTitle") != null ? CHECKED : UNCHECKED;
    checkDescription = request.getAttribute("searchByDescription") != null ? CHECKED : UNCHECKED;
    checkLocation = request.getAttribute("searchByLocation") != null ? CHECKED : UNCHECKED;

    // if all unchecked, let title be checked
    if (checkTitle.equals(UNCHECKED)
    && checkDescription.equals(UNCHECKED)
    && checkLocation.equals(UNCHECKED))
        checkTitle = CHECKED;
%>

<%
    UsersEntity user = (UsersEntity) session.getAttribute("user");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="index.page"><strong>Museum</strong></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <span class="navbar-text mx-2">Where you find <strong>genius</strong> and <strong>extraordinary</strong></span>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item <%=navFront%>">
                <a class="nav-link" href="index.page">Front Page<span class="sr-only">(current)</span></a>
            </li>

            <% if (user == null) {// not logged in %>

            <li class="nav-item dropdown <%=navNotLoggedIn%>">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownNotLoggedIn" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Account
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownNotLoggedIn">
                    <a class="dropdown-item" href="javascript:void(0)" data-toggle="modal" data-target="#signInFormModal" onclick="changeVerify()">Login</a>
                    <a class="dropdown-item" href="signUp.page">Sign up</a>
                </div>
            </li>

            <% } else {// logged in %>

            <li class="nav-item dropdown <%=navLoggedIn%>">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownLoggedIn" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <%= user.getName() %>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownLoggedIn">
                    <a class="dropdown-item" href="profile.page">Profile</a>
                    <a class="dropdown-item" href="favor.page">Favor</a>
                    <a class="dropdown-item" href="email.page">Email</a>
                    <a class="dropdown-item" href="logOut.user">Log out</a>
                </div>
            </li>

            <% if (user.getType().equals("admin")) {// user is admin %>

            <li class="nav-item dropdown <%=navAdmin%>">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAdmin" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Management
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownAdmin">
                    <a class="dropdown-item" href="adminIndex.page">Admin Index</a>
                    <a class="dropdown-item" href="usersList.admin">User List</a>
                    <a class="dropdown-item" href="artworksList.admin">Artworks List</a>
                </div>
            </li>

            <% } %>

            <% }// end of logged in %>

        </ul>
        <form id="search" class="form-inline my-2 my-lg-0" method="get" action="search.page">
            <input id="searchText" class="form-control mr-sm-2" type="search" name="searchText" placeholder="Search here" aria-label="Search" value="<%=request.getAttribute("searchText") != null ? request.getAttribute("searchText") : ""%>">
            <div class="dropdown">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Search By...
                </button>
                <div class="dropdown-menu text-center" aria-labelledby="dropdownMenuButton">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ckbTitle" name="searchBy" value="title" <%=checkTitle%>>
                        <label class="form-check-label" for="ckbTitle">Title</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ckbIntroduction" name="searchBy" value="description" <%=checkDescription%>>
                        <label class="form-check-label" for="ckbIntroduction">Description</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="ckbLocation" name="searchBy" value="location" <%=checkLocation%>>
                        <label class="form-check-label" for="ckbLocation">Location</label>
                    </div>
                    <label for="sortBy"></label><input class="invisible" id="sortBy" name="sortBy" value="">
                    <span id="searchAlert" class="invisible alert"></span><br/>
                    <button id="btSearch" class="btn btn-outline-primary my-2 my-sm-0" type="button">Search</button>
                </div>
            </div>
        </form>
    </div>
</nav>

<% if (user == null) { %>

<%@include file="signInModal.jsp"%>

<% } %>