package dao;

import entity.EmailsEntity;

import java.util.List;

public interface EmailDao {

    EmailsEntity query(int emailId);

    void delete(int emailId);

    void insert(EmailsEntity emailsEntity);

    List<EmailsEntity> queryInbox(String userName);

    List<EmailsEntity> queryOutbox(String userName);
}
