package servlet;

import entity.FriendshipEntity;
import service.FriendshipService;
import service.impl.FriendshipServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FriendServlet", value = "*.friend")
public class FriendServlet extends HttpServlet {
    private FriendshipService friendshipService = new FriendshipServiceImp();

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

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
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

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
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

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
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

        resp.setHeader("refresh", "2;url=" + req.getHeader("Referer"));
    }
}
