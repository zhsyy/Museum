package servlet;

import entity.FavorEntity;
import service.FavorService;
import service.impl.FavorServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FavorServlet", value = "*.favor")
public class FavorServlet extends HttpServlet {
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
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int artworkId = Integer.parseInt(req.getParameter("artworkId"));
        int userId = Integer.parseInt(req.getParameter("userId"));

        FavorEntity favor = new FavorEntity(userId, artworkId, "public");

        favorService.insert(favor);

        // back to previous page, details of artwork
        resp.sendRedirect(req.getHeader("Referer"));
    }

    @SuppressWarnings("unused")
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int favorId = Integer.parseInt(req.getParameter("favorId"));

        favorService.delete(favorId);

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
