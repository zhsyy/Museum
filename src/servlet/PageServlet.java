package servlet;

import com.alibaba.fastjson.JSONArray;
import dao.impl.FavorDaoImp;
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
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Page", value = "*.page")
public class PageServlet extends HttpServlet {
    private ArtworkService artworkService = new ArtworkServiceImp();
    private UserService userService = new UserServiceImp();
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
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        String sortBy = req.getParameter("sortBy");
        String[] searchBy = req.getParameterValues("searchBy");
        List<ArtworksEntity> allArtworks = artworkService.getSearchArtworks(searchText,searchBy,sortBy);


        int totalCount = allArtworks.size();
        int totalPage = ((totalCount % 9 == 0) ? (totalCount / 9):(totalCount / 9 + 1));
        for (String u: searchBy) {
            if (u.equals("title")) req.setAttribute("searchByTitle",u);
            if (u.equals("description")) req.setAttribute("searchByDescription",u);
            if (u.equals("location")) req.setAttribute("searchByLocation",u);
        }
        req.setAttribute("searchText",searchText);
        req.setAttribute("totalCount",totalCount);
        req.setAttribute("totalPage",totalPage);
        req.setAttribute("sortBy",sortBy);
        req.setAttribute("artworksEntities",artworkService.getOutputArtworks(allArtworks,1));
        req.getRequestDispatcher("search.jsp").forward(req, resp);
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
    private void profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersEntity user = (UsersEntity) req.getSession().getAttribute("user");

        if (user == null) {// user not logged in, error
            resp.sendRedirect("error.page?message=NotLoggedIn");
        } else {// user logged in , normal
            List<UsersEntity> friends = userService.getFriends(user.getUserId());
            List<UsersEntity> requestSenders = userService.getFriendRequestSenders(user.getUserId());

            req.setAttribute("friends", friends);
            req.setAttribute("requestSenders", requestSenders);

            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        }
    }

    @SuppressWarnings("unused")
    private void modify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") == null) {// user not logged in, error
            resp.sendRedirect("error.page?message=NotLoggedIn");
        } else if (req.getAttribute("checkedPassword") == null) {// password not checked
            resp.sendRedirect("error.page?message=NotAuthorized");
        } else {// user logged in , normal
            req.getRequestDispatcher("modify.jsp").forward(req, resp);
        }
    }

    @SuppressWarnings("unused")
    private void favor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersEntity user = (UsersEntity) req.getSession().getAttribute("user");

        if (user == null) {// not logged in, error
            resp.sendRedirect("error.page?message=NotLoggedIn");
        } else {// logged in, normal
            List<FavorEntity> favors = favorService.getFavors(user.getUserId());
            List<ArtworksEntity> artworks = artworkService.getFavorArtworks(user.getUserId());

            // generate map of favor and artwork
            Map<FavorEntity, ArtworksEntity> favoriteArtworks = new Hashtable<>();
            for (FavorEntity favor : favors) {
                for (ArtworksEntity artwork : artworks) {
                    if (favor.getArtworkId() == artwork.getArtworkId()) {
                        favoriteArtworks.put(favor, artwork);
                        break;
                    }
                }
            }

            req.setAttribute("favoriteArtworks", favoriteArtworks);
            req.getRequestDispatcher("favor.jsp").forward(req, resp);
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
            case "NotLoggedIn":
                info += "You haven't logged in! Please login first!";
                break;
            case "NotAuthorized":
                info += "You are not allowed to access this page!";
                break;
            default:
                info += "Some unknown error occurred!";
                break;
        }
        req.setAttribute("info", info);
        req.getRequestDispatcher("error.jsp").forward(req, resp);
    }
}
