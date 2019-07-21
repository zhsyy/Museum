package servlet;

import entity.FriendshipEntity;
import entity.UsersEntity;
import service.FriendshipService;
import service.UserService;
import service.impl.FriendshipServiceImp;
import service.impl.UserServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FriendServlet", value = "*.friend")
public class FriendServlet extends HttpServlet {
    private FriendshipService friendshipService = new FriendshipServiceImp();
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
    private void accept(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int senderId = Integer.parseInt(req.getParameter("senderId"));
        int receiverId = Integer.parseInt(req.getParameter("receiverId"));

        // accept request
        FriendshipEntity friendship = new FriendshipEntity(senderId, receiverId);
        friendshipService.accept(friendship);

        // response and redirect
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println("Request accepted! Returning to previous page...");

        resp.setHeader("refresh", "2;url=profile.page");
    }

    @SuppressWarnings("unused")
    private void reject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int senderId = Integer.parseInt(req.getParameter("senderId"));
        int receiverId = Integer.parseInt(req.getParameter("receiverId"));

        // reject request
        FriendshipEntity friendship = new FriendshipEntity(senderId, receiverId);
        friendshipService.reject(friendship);

        // response and redirect
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println("Request rejected! Returning to previous page...");

        resp.setHeader("refresh", "2;url=profile.page");
    }

    @SuppressWarnings("unused")
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        int friendId = Integer.parseInt(req.getParameter("friendId"));

        // delete friend
        friendshipService.delete(userId, friendId);

        // response and redirect
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println("Friend deleted! Returning to previous page...");

        resp.setHeader("refresh", "2;url=profile.page");
    }

    @SuppressWarnings("unused")
    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int senderId = Integer.parseInt(req.getParameter("senderId"));
        int receiverId = Integer.parseInt(req.getParameter("receiverId"));

        // add friend request
        friendshipService.add(senderId, receiverId);

        // response and redirect
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        out.println("Friend request sent successfully! Returning to previous page...");

        resp.setHeader("refresh", "2;url=profile.page");
    }

    @SuppressWarnings("unused")
    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        String searchName = req.getParameter("searchName") != null ? req.getParameter("searchName") : "";

        Map<UsersEntity, String> searchedUsers = new HashMap<>();

        // get users with similar username
        List<UsersEntity> users = userService.getUserByNameLike(searchName);
        // check if user is friend of current user
        for (UsersEntity friend : users) {
            if (userId == friend.getUserId())// skip user self
                continue;
            String status = friendshipService.getFriendshipStatus(userId, friend.getUserId());
            searchedUsers.put(friend, status);
        }

        // set attribute and forward
        req.setAttribute("searchedUsers", searchedUsers);
        req.getRequestDispatcher("profile.page").forward(req, resp);
    }
}
