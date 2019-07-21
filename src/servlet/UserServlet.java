package servlet;

import entity.UsersEntity;
import service.UserService;
import service.impl.UserServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@WebServlet(name = "UserServlet", value = "*.user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.getAndDoMethod(this, req, resp);
    }

    @SuppressWarnings("unused")
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsersEntity usersEntity = userService.login(username, password);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (usersEntity != null) {// found matched user, login succeed
            out.println("Login succeed! Returning to previous page...");

            req.getSession().setAttribute("user", usersEntity);
        } else {// not found, login failed
            out.println("Login failed! Wrong username or password, please try again.<br/>" +
                    "Returning...");
        }

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
    }

    @SuppressWarnings("unused")
    private void checkSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if (userService.checkSignUpUsername(username)) {// valid username
            out.println("");
        } else {// invalid, username has been used
            out.println("Sorry! This name has been used. Please try another one.");
        }
    }

    @SuppressWarnings("unused")
    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // generate user bean and sign up
        String username = req.getParameter("signUpUserName");
        String email = req.getParameter("signUpEmail");
        String password = req.getParameter("signUpPassword");

        UsersEntity user = new UsersEntity(username, email, password, "normal", null);

        userService.signUp(user);

        // inform users what is going on
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("Sign up succeed! Returning to index...");

        // login to get signed up user bean, set session
        user = userService.login(username, password);
        req.getSession().setAttribute("user", user);

        resp.setHeader("refresh", "2;url=index.page");
    }

    @SuppressWarnings("unused")
    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user", null);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("Logged out.<br/>" +
                "Returning to index...");

        resp.setHeader("refresh", "2;url=index.page");
    }

    @SuppressWarnings("unused")
    private void checkPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String password = req.getParameter("password");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if (userService.checkModifyUser(userId, password)) {// valid
            out.println("");
        } else {// invalid
            out.println("Incorrect password! Please try again!");
        }
    }

    @SuppressWarnings("unused")
    private void checkedPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("checkedPassword", true);
        req.getRequestDispatcher("modify.page").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // generate user bean and update
        String username = req.getParameter("signUpUserName").trim();
        String email = req.getParameter("signUpEmail").trim();
        String signature = req.getParameter("signUpSignature").trim();

        UsersEntity user = (UsersEntity) req.getSession().getAttribute("user");
        user.setName(username);
        user.setEmail(email);
        user.setSignature(signature);

        user = userService.update(user);

        // inform users what is going on
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("Modify succeed! Returning to profile page...");

        // update session
        req.getSession().setAttribute("user", user);

        resp.setHeader("refresh", "2;url=profile.page");
    }
}
