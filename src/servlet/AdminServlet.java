package servlet;
import entity.ArtworksEntity;
import entity.UsersEntity;
import service.ArtworkService;
import service.UserService;
import service.impl.ArtworkServiceImp;
import service.impl.UserServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "AdminPage", value = "*.admin")
public class AdminServlet extends HttpServlet {
    private ArtworkService artworkService = new ArtworkServiceImp();
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
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UsersEntity usersEntity = (UsersEntity) session.getAttribute("user");
        if (usersEntity==null) {
            resp.sendRedirect("signUp.page");return;
        }
//        String tsStr = "2011-05-09 11:49:45";
//        UsersEntity usersEntity = new UsersEntity("zhsyy","123@email","123456","admin","");
//        usersEntity.setTime(Timestamp.valueOf(tsStr));
//        usersEntity.setUserId(2);
//        HttpSession session = req.getSession();
//        session.setAttribute("user",usersEntity);
        req.getRequestDispatcher("adminIndex.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        UsersEntity usersEntity = userService.getUserByName(name);
        req.setAttribute("user",usersEntity);
        req.getRequestDispatcher("adminUser.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void usersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UsersEntity> list = userService.getAllUsers();
        req.setAttribute("usersList",list);
        req.getRequestDispatcher("adminUsersList.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void artwork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artworkId = req.getParameter("artworkId");
        if (artworkId!=null){
            ArtworksEntity artworksEntity = artworkService.getArtwork(Integer.parseInt(artworkId));
            req.setAttribute("artwork",artworksEntity);
        }
        req.getRequestDispatcher("adminArtwork.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void artworksList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ArtworksEntity> list = artworkService.getNewestArtworks();
        req.setAttribute("artworksList",list);
        req.getRequestDispatcher("adminArtworksList.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void addArtwork(HttpServletRequest req, HttpServletResponse resp){
        String path = this.getServletContext().getRealPath("/resource/img");
        artworkService.saveArtworks(req,path);
        resp.setHeader("refresh", "0.1;url=artworksList.admin");
    }

    @SuppressWarnings("unused")
    private void modifyArtwork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = this.getServletContext().getRealPath("/resource/img");
        artworkService.saveArtworks(req,path);
        resp.setHeader("refresh", "0.1;url=artworksList.admin");
    }

    @SuppressWarnings("unused")
    private void deleteArtwork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artworkId = req.getParameter("artworkId");
        artworkService.deleteArtwork(artworkId);
        resp.setHeader("refresh", "0.1;url=artworksList.admin");
    }

    @SuppressWarnings("unused")
    private void addUser(HttpServletRequest req, HttpServletResponse resp){
        String Username = req.getParameter("name");
        String Email = req.getParameter("email");
        String Password = req.getParameter("password");
        String Type = req.getParameter("type");
        UsersEntity usersEntity = new UsersEntity(Username,Email,Password,Type,"");
        userService.addUser(usersEntity);
        resp.setHeader("refresh", "0.1;url=usersList.admin");
    }

    @SuppressWarnings("unused")
    private void modifyUser(HttpServletRequest req, HttpServletResponse resp){
        String userId = req.getParameter("userId");
        userService.deleteUser(Integer.parseInt(userId));
        addUser(req,resp);
        resp.setHeader("refresh", "0.1;url=usersList.admin");
    }

    @SuppressWarnings("unused")
    private void deleteUser(HttpServletRequest req, HttpServletResponse resp){
        String name = req.getParameter("name");
        userService.deleteUser(name);
        resp.setHeader("refresh", "0.1;url=usersList.admin");
    }
}