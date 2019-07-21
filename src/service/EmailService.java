package service;

import entity.EmailsEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EmailService {

    EmailsEntity getEmail(int emailId);

    List<EmailsEntity> getInbox(String userName);

    List<EmailsEntity> getOutbox(String userName);

    void sendEmail(HttpServletRequest httpServletRequest);

    void deleteEmail(int emailId);
}
