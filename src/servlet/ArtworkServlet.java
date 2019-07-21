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

@WebServlet(name = "ArtworkServlet", value = "*.artwork")
public class ArtworkServlet extends HttpServlet {
    private FavorService favorService = new FavorServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.getAndDoMethod(this, req, resp);
    }
}
