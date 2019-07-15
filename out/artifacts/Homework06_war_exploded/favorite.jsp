<%@ page import="data.Users" %>
<%@ page import="java.util.List" %>
<%@ page import="com.sun.javafx.runtime.async.AbstractRemoteResource" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Exhibits</title>
</head>
<body>
<%!
    private static void setAttr(HttpServletRequest request, HttpSession session, String username, String attribute) {
        if (request.getParameter(attribute) != null ) {
            session.setAttribute(attribute, request.getParameter(attribute));
//            putAttr(username, request.getAttribute(attribute));
        }
    }

//    private static void putAttr(String username, Object attribute) {
//        List<Object> favorites;
//        if (Users.favorites.containsKey(username)) {
//            favorites = Users.favorites.get(username);
//        } else {
//            favorites = new ArrayList<>();
//        }
//
//        favorites.add(attribute);
//
//        Users.favorites.put(username, favorites);
//    }
%>

<%
    String username = (String)session.getAttribute("username");
    setAttr(request, session, username, "c1");
    setAttr(request, session, username, "c2");
    setAttr(request, session, username, "c3");
%>

Choose your favorite exhibits:<br/>
<form method="post" action="favorite.jsp">
    <label><input type="checkbox" name="c1" value="Exhibit 1">Exhibit 1</label>
    <label><input type="checkbox" name="c2" value="Exhibit 2">Exhibit 2</label>
    <label><input type="checkbox" name="c3" value="Exhibit 3">Exhibit 3</label>
    <br/>
    <label>
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
        <br/>
        <a href="display.jsp">View my Favorite</a>
    </label>
</form>
</body>
</html>
