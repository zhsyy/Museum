package servlet;

import dto.LoginUser;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LoginUser loginUser = new LoginUser(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("userType")
        );

        if (loginUser.isEmptyUsername() || loginUser.isEmptyPassword() // has empty field
        || loginUser.isWrongUsernameOrPassword()) {// wrong username or password
            responseFailedLogin(response, loginUser);
        } else {// success
            responseSuccessfulLogin(request, response, loginUser);
        }
    }

    private void responseFailedLogin(HttpServletResponse response, LoginUser loginUser)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Account Login</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Login Form</h1>\n" +
                "<form action=\"/login\">\n" +
                "    <label>\n" +
                "        " + loginUser.getLabelUsername() + "Username:\n" +
                "        <input type=\"text\" name=\"username\" value=\"" + loginUser.getUsername() + "\">" +
                loginUser.getLabelWrongUsernameOrPassword() + "\n" +
                "    </label><br/>\n" +
                "    <label>\n" +
                "        " + loginUser.getLabelPassword() + "Password:\n" +
                "        <input type=\"text\" name=\"password\" value=\"" + loginUser.getPassword() + "\">\n" +
                "    </label><br/>\n" +
                "    <label>\n" +
                "        <input type=\"radio\" name=\"userType\" value=\"Admin\" " + loginUser.getLabelAdmin() + ">\n" +
                "        Admin\n" +
                "    </label>\n" +
                "    <label>\n" +
                "        <input type=\"radio\" name=\"userType\" value=\"NormalUser\" " + loginUser.getLabelNormalUser() + ">\n" +
                "        Normal User\n" +
                "    </label><br/>" +
                "    <input type=\"submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }

    private void responseSuccessfulLogin(HttpServletRequest request, HttpServletResponse response, LoginUser loginUser)
            throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("username", loginUser.getUsername());

        response.sendRedirect("favorite.jsp");
    }
}
