package service.impl;

import dao.EmailDao;
import dao.impl.EmailDaoImp;
import entity.EmailsEntity;
import entity.UsersEntity;
import service.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class EmailServiceImp implements EmailService {
    private EmailDao emailDao = new EmailDaoImp();
    @Override
    public EmailsEntity getEmail(int emailId) {
        return emailDao.query(emailId);
    }

    @Override
    public List<EmailsEntity> getInbox(String userName) {
        return emailDao.queryInbox(userName);
    }

    @Override
    public List<EmailsEntity> getOutbox(String userName) {
        return emailDao.queryOutbox(userName);
    }

    @Override
    public void sendEmail(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        UsersEntity user = (UsersEntity)httpSession.getAttribute("user");
        String receiverName = req.getParameter("send");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String senderName = user.getName();
        EmailsEntity emailsEntity = new EmailsEntity(senderName,receiverName,title,content);
        emailDao.insert(emailsEntity);
    }

    @Override
    public void deleteEmail(int emailId) {
        emailDao.delete(emailId);
    }
}
