<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String info = (String) request.getAttribute("info");

    if (info == null)
        info = "Sorry! Some unknown error occurred!";
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Oops!</title>
</head>
<body>
<main>
    <%=info%><br/>
    <a href="index.page">Return to Index</a>
</main>
</body>
</html>
