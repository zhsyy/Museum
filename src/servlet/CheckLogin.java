package servlet;

import dao.UsersDao;
import dao.impl.UsersDaoImp;
import entity.UsersEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "checkLogin", value = "/checkLogin")
public class CheckLogin extends HttpServlet {
    private UsersDao usersDao = new UsersDaoImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UsersEntity usersEntity = usersDao.query(username, password);

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        if (usersEntity != null) {// found matched user, login succeed
            out.println("Login succeed! Returning to web page...");

            req.getSession().setAttribute("username", usersEntity.getName());
        } else {// not found, login failed
            out.println("Login failed! Wrong username or password, please try again.<br/>" +
                    "Returning...");
        }

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
    }
}
