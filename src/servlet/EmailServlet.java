package servlet;

import entity.EmailsEntity;
import entity.UsersEntity;
import service.EmailService;
import service.impl.EmailServiceImp;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmailServlet", value = "*.email")
public class EmailServlet extends HttpServlet {
    private EmailService emailService = new EmailServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.getAndDoMethod(this, req, resp);
    }

    @SuppressWarnings("unused")
    void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        UsersEntity user = (UsersEntity)httpSession.getAttribute("user");
        String receiverName = req.getParameter("toName");//通过传入的发件人名字来获取发件人信息(注意不是收件人Id）
        List<EmailsEntity> outbox = emailService.getOutbox(user.getName());
        List<EmailsEntity> inbox = emailService.getInbox(user.getName());

        req.setAttribute("receiverName",receiverName);
        req.setAttribute("inbox",inbox);
        req.setAttribute("outbox",outbox);
        req.getRequestDispatcher("email.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    void read(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        UsersEntity user = (UsersEntity)httpSession.getAttribute("user");
        String emailId = req.getParameter("emailId");
        EmailsEntity emailsEntity =  emailService.getEmail(Integer.parseInt(emailId));
        req.setAttribute("email",emailsEntity);
        List<EmailsEntity> outbox = emailService.getOutbox(user.getName());
        List<EmailsEntity> inbox = emailService.getInbox(user.getName());
        req.setAttribute("inbox",inbox);
        req.setAttribute("outbox",outbox);
        req.getRequestDispatcher("email.jsp").forward(req, resp);
    }

    @SuppressWarnings("unused")
    void send(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailService.sendEmail(req);
        resp.sendRedirect("index.email");
    }

    @SuppressWarnings("unused")
    void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailId = req.getParameter("emailId");
        emailService.deleteEmail(Integer.parseInt(emailId));
        resp.sendRedirect("index.email");
    }
}
