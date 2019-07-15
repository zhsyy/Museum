package servlet;

import dto.SignUpUser;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "signUp", value = "/signUp")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SignUpUser signUpUser = new SignUpUser(
                request.getParameter("username"),
                request.getParameter("password"),
                request.getParameter("userType")
        );

        if (signUpUser.isEmptyUsername() || signUpUser.isEmptyPassword() // has empty field
        || signUpUser.isRepeatedUsername()) {// repeated name
            responseFailedSignUp(response, signUpUser);
        } else {// success
            signUpUser.getUsers().put(signUpUser.getUsername(), signUpUser.getPassword());
            response.sendRedirect("account_login.html");
        }
    }

    private void responseFailedSignUp(HttpServletResponse response, SignUpUser signUpUser)
            throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Account Registration</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Sign Up Form</h1>\n" +
                "<form action=\"/signUp\">\n" +
                "    <label>\n" +
                "        " + signUpUser.getLabelUsername() + "Username:\n" +
                "        <input type=\"text\" name=\"username\" value=\"" + signUpUser.getUsername() + "\"> " +
                signUpUser.getLabelRepeatedUsername() + "\n" +
                "    </label><br/>\n" +
                "    <label>\n" +
                "        " + signUpUser.getLabelPassword() + "Password:\n" +
                "        <input type=\"text\" name=\"password\" value=\"" + signUpUser.getPassword() + "\">\n" +
                "    </label><br/>\n" +
                "    <label>\n" +
                "        <input type=\"radio\" name=\"userType\" value=\"Admin\" " + signUpUser.getLabelAdmin() + ">\n" +
                "        Admin\n" +
                "    </label>\n" +
                "    <label>\n" +
                "        <input type=\"radio\" name=\"userType\" value=\"NormalUser\" " + signUpUser.getLabelNormalUser() + ">\n" +
                "        Normal User\n" +
                "    </label><br/>" +
                "    <input type=\"submit\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
    }
}
