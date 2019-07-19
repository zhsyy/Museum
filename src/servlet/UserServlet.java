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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "UserServlet", value = "*.user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String methodName = servletPath.substring(1, servletPath.indexOf("."));

        try {
            Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsersEntity usersEntity = userService.login(username, password);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (usersEntity != null) {// found matched user, login succeed
            out.println("Login succeed! Returning to web page...");

            req.getSession().setAttribute("user", usersEntity);
        } else {// not found, login failed
            out.println("Login failed! Wrong username or password, please try again.<br/>" +
                    "Returning...");
        }

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
    }

    private void checkSignUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        if (userService.checkSignUpUsername(username)) {// valid username
            out.println("");
        } else {// invalid, username has been used
            out.println("Sorry! This name has been used. Please change another one.");
        }
    }

    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("user", null);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("Logged out.<br/>" +
                "Returning to index...");

        resp.setHeader("refresh", "2;url=index.jsp");
    }
}
