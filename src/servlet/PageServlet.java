package servlet;

import entity.ArtworksEntity;
import entity.UsersEntity;
import service.ArtworkService;
import service.impl.ArtworkServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Page", value = "*.page")
public class PageServlet extends HttpServlet {
    private ArtworkService artworkService = new ArtworkServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.getAndDoMethod(this, req, resp);
    }

    @SuppressWarnings("unused")
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("hottestArtworks", artworkService.getHottestArtworks());
        req.setAttribute("newestArtworks", artworkService.getNewestArtworks());

        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {// already logged in, shouldn't access sign up
            resp.sendRedirect("error.page?message=LoggedIn");
        } else {// not logged in, normal forward
            req.getRequestDispatcher("signUp.jsp").forward(req, resp);
        }
    }

    @SuppressWarnings("unused")
    private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int artworkId;

        try {
            artworkId = Integer.parseInt(req.getParameter("artworkId"));
        } catch (NullPointerException e) {// artwork id not set
            resp.sendRedirect("error.page?message=NotExist");
            return;
        }

        ArtworksEntity artwork = artworkService.getArtwork(artworkId);

        if (artwork != null) {// found artwork in database

            // check if user has logged in
            UsersEntity user = (UsersEntity) req.getSession().getAttribute("user");
            if (user != null) {// has logged in, check if artwork is in user's favor list
                List<ArtworksEntity> favorArtworks = artworkService.getFavorArtworks(user.getUserId());
                boolean isFavored = favorArtworks.contains(artwork);
                req.setAttribute("isFavored", isFavored);
            }

            // add view time by 1
            artworkService.updateView(artwork.getArtworkId(), artwork.getView());

            // get updated artwork and set attribute
            artwork = artworkService.getArtwork(artworkId);
            req.setAttribute("artwork", artwork);

            req.getRequestDispatcher("details.jsp").forward(req, resp);
        } else {// artwork not found in database
            resp.sendRedirect("error.page?message=NotExist");
        }
    }

    @SuppressWarnings("unused")
    private void error(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("message");
        String info = "Sorry! ";

        switch (message) {
            case "NotExist":
                info += "Page requested doesn't exist!";
                break;
            case "LoggedIn":
                info += "You have already logged in!";
                break;
            default:
                info += "Some unknown error occurred!";
                break;
        }

        req.setAttribute("info", info);
        req.getRequestDispatcher("error.jsp").forward(req, resp);
    }
}
