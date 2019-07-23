package dao.impl;

import dao.ViewHistoryDao;
import entity.ViewhistoryEntity;
import util.DBUtils;

import java.util.List;

public class ViewHistoryDaoImp implements ViewHistoryDao {
    @Override
    public ViewhistoryEntity query(int userId, int artworkId) {
        String sql = "SELECT * FROM viewhistory WHERE userId = ? AND artworkId = ?";

        return DBUtils.get(ViewhistoryEntity.class,sql,userId,artworkId);
    }

    @Override
    public void update(int userId, int artworkId,int viewTime) {
        String sql = "UPDATE viewhistory SET viewTime = ? WHERE userId = ? AND artworkId = ?";

        DBUtils.update(sql, viewTime,userId,artworkId);
    }

    @Override
    public void add(int userId, int artworkId) {
        String sql = "INSERT INTO viewhistory (userId, artworkId) VALUE (?, ?)";

        DBUtils.update(sql, userId,artworkId);
    }

    @Override
    public List<ViewhistoryEntity> query(int userId) {
        String sql = "SELECT * FROM viewhistory WHERE userId = ?";

        return DBUtils.getList(ViewhistoryEntity.class,sql,userId);
    }
}
