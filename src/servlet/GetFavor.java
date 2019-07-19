package servlet;

import dao.impl.ArtworksDaoImp;
import dao.impl.FavorDaoImp;
import entity.ArtworksEntity;
import entity.FavorEntity;
import entity.UsersEntity;
import service.ArtworkService;
import service.impl.ArtworkServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "favor", value = "/favor")
public class GetFavor extends HttpServlet {
    private ArtworkService artworkService = new ArtworkServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UsersEntity user = (UsersEntity)session.getAttribute("user");
        if (user==null){
            resp.sendRedirect("signUp.jsp");
        }else {
            FavorDaoImp favorDaoImp = new FavorDaoImp();
            List<ArtworksEntity> artworksEntityList = artworkService.getFavorArtworks(user.getUserId());
            List<FavorEntity> favorEntityList = favorDaoImp.getFavors(user.getUserId());
            req.setAttribute("artworkList",artworksEntityList);
            req.setAttribute("favorList",favorEntityList);
            req.getRequestDispatcher("/favor.jsp").forward(req,resp);
        }
    }
}
