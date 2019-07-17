package servlet;

import entity.UsersEntity;
import service.UserService;
import service.impl.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SignUp", value = "/signUp")
public class SignUp extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // generate user bean and sign up
        String username = req.getParameter("signUpUserName");
        String email = req.getParameter("signUpEmail");
        String password = req.getParameter("signUpPassword");

        UsersEntity user = new UsersEntity(-1, username, email, password, "normalUser");

        userService.signUp(user);

        // inform users what is going on
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("Sign up succeed! Returning to web page...");

        // login to get signed up user bean, set session
        user = userService.login(username, password);
        req.getSession().setAttribute("user", user);

        resp.setHeader("refresh", "2;url=index.jsp");
    }
}
