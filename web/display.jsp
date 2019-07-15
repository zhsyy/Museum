<%@ page import="java.io.IOException" %>
<%@ page import="data.Users" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>My Favorites</title>
</head>
<body>
    <%!
        private static void getAttr(HttpSession session, JspWriter out, String attribute) throws IOException {
            if (session.getAttribute(attribute) != null) {
                out.println(session.getAttribute(attribute) + "<br>");
            }
        }
//        private static List<String> getAttrs(String username) throws IOException {
//            List<String> attrs = new ArrayList<>();
//            for (Object attribute : Users.favorites.get(username)) {
//                attrs.add(
//                        new String(
//                                ((String) attribute).getBytes(StandardCharsets.ISO_8859_1)
//                        )
//                );
//            }
//            return attrs;
//        }
    %>

    <%
        getAttr(session, out, "c1");
        getAttr(session, out, "c2");
        getAttr(session, out, "c3");
//        String username = (String)session.getAttribute("username");
//        List<String> attrs = getAttrs(username);
//
//        for (String attr : attrs)
//            out.println(attr + "<br/>");
    %>
</body>
</html>
