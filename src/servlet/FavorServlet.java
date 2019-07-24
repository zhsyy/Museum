package servlet;

import entity.ArtworksEntity;
import entity.FavorEntity;
import entity.UsersEntity;
import service.ArtworkService;
import service.FavorService;
import service.UserService;
import service.impl.ArtworkServiceImp;
import service.impl.FavorServiceImp;
import service.impl.UserServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "FavorServlet", value = "*.favor")
public class FavorServlet extends HttpServlet {
    private FavorService favorService = new FavorServiceImp();
    private UserService userService = new UserServiceImp();
    private ArtworkService artworkService = new ArtworkServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user")==null){resp.sendRedirect("signUp.page");return;}
        ServletUtils.getAndDoMethod(this, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @SuppressWarnings("unused")
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int artworkId = Integer.parseInt(req.getParameter("artworkId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        userService.updateDeleteHistory(userId,artworkId);
        FavorEntity favor = new FavorEntity(userId, artworkId, "public");

        favorService.insert(favor);

        // back to previous page, details of artwork
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @SuppressWarnings("unused")
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        UsersEntity user = (UsersEntity)httpSession.getAttribute("user");
        int favorId = Integer.parseInt(req.getParameter("favorId"));
        FavorEntity favorEntity = favorService.query(favorId);
        ArtworksEntity artworksEntity = artworkService.getArtwork(favorEntity.getArtworkId());
        favorService.delete(favorId);
        userService.updateDeleteHistory(user.getUserId(),artworksEntity.getArtworkId());
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @SuppressWarnings("unused")
    private void publicize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int favorId = Integer.parseInt(req.getParameter("favorId"));

        favorService.publicize(favorId);

        resp.sendRedirect(req.getHeader("Referer"));
    }

    @SuppressWarnings("unused")
    private void privatize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int favorId = Integer.parseInt(req.getParameter("favorId"));

        favorService.privatize(favorId);

        resp.sendRedirect(req.getHeader("Referer"));
    }
}
