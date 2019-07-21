package dao.impl;

import dao.EmailDao;
import entity.EmailsEntity;
import util.DBUtils;

import java.util.List;

public class EmailDaoImp implements EmailDao {
    @Override
    public void insert(EmailsEntity emailsEntity) {
        String sql = "INSERT INTO emails (senderName, receiverName, title, content) VALUE (?,?,?,?)";

        DBUtils.update(sql, emailsEntity.getSenderName(),emailsEntity.getReceiverName(),emailsEntity.getTitle(),emailsEntity.getContent());
    }

    @Override
    public EmailsEntity query(int emailId) {
        String sql = "SELECT * FROM emails WHERE emailId = ?";

        return DBUtils.get(EmailsEntity.class, sql, emailId);
    }

    @Override
    public void delete(int emailId) {
        String sql = "DELETE FROM emails WHERE emailId = ?";

        DBUtils.update(sql, emailId);
    }

    @Override
    public List<EmailsEntity> queryInbox(String receiverName) {
        String sql = "SELECT * FROM emails WHERE receiverName = ? ORDER BY time DESC";

        return DBUtils.getList(EmailsEntity.class, sql, receiverName);
    }

    @Override
    public List<EmailsEntity> queryOutbox(String senderName) {
        String sql = "SELECT * FROM emails WHERE senderName = ? ORDER BY time DESC";

        return DBUtils.getList(EmailsEntity.class, sql, senderName);
    }
}
