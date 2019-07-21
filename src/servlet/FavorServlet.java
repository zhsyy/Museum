package servlet;

import dao.impl.ArtworksDaoImp;
import dao.impl.FavorDaoImp;
import entity.ArtworksEntity;
import entity.FavorEntity;
import entity.UsersEntity;
import service.ArtworkService;
import service.FavorService;
import service.impl.ArtworkServiceImp;
import service.impl.FavorServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "favor", value = "*.favor")
public class FavorServlet extends HttpServlet {
    private ArtworkService artworkService = new ArtworkServiceImp();
    private FavorService favorService = new FavorServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.getAndDoMethod(this, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    @SuppressWarnings("unused")
    void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int artworkId = Integer.parseInt(req.getParameter("artworkId"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        FavorEntity favor = new FavorEntity(userId, artworkId);

        favorService.insert(favor);

        // back to previous page, details of artwork
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @SuppressWarnings("unused")
    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String favorId = req.getParameter("favorId");
        favorService.delete(favorId);
        resp.sendRedirect(req.getHeader("Referer"));
    }


}
