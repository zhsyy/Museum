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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "AdminPage", value = "*.admin")
public class AdminServlet extends HttpServlet {
    private ArtworkService artworkService = new ArtworkServiceImp();
    private UserService userService = new UserServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.getAndDoMethod(this, req, resp);
    }

    @SuppressWarnings("unused")
    private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tsStr = "2011-05-09 11:49:45";
        UsersEntity usersEntity = new UsersEntity("zhsyy","123@email","123456","admin");
        usersEntity.setTime(Timestamp.valueOf(tsStr));
        usersEntity.setUserId(2);
        HttpSession session = req.getSession();
        session.setAttribute("user",usersEntity);
        req.getRequestDispatcher("adminIndex.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        UsersEntity usersEntity = userService.getUserByName(userName);
        req.setAttribute("user",usersEntity);
        req.getRequestDispatcher("adminUser.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void usersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UsersEntity> list = userService.getAllUsers();
        req.setAttribute("usersList",list);
//        System.out.println(list.size());
        req.getRequestDispatcher("adminUsersList.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    private void artwork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artworkId = req.getParameter("artworkId");
        ArtworksEntity artworksEntity = artworkService.getArtwork(Integer.parseInt(artworkId));
        req.setAttribute("artwork",artworksEntity);
        req.getRequestDispatcher("adminUser.jsp").forward(req, resp);
    }
}