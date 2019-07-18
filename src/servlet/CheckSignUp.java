package servlet;

import service.UserService;
import service.impl.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CheckSignUp", value = "/checkSignUp")
public class CheckSignUp extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if (userService.checkSignUpUsername(username)) {// valid username
            out.println("");
        } else {// invalid, username has been used
            out.println("Sorry! This name has been used. Please change another one.");
        }
    }
}
