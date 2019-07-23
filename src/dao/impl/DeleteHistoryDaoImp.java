package dao.impl;

import dao.DeleteHistoryDao;
import entity.DeletehistoryEntity;
import util.DBUtils;

import java.util.List;

public class DeleteHistoryDaoImp implements DeleteHistoryDao {
    @Override
    public boolean query(int userId, int artworkId) {
        String sql = "SELECT * FROM deletehistory WHERE userId = ? AND artworkId = ?";
        DeletehistoryEntity deletehistoryEntity = DBUtils.get(DeletehistoryEntity.class, sql, userId,artworkId);
        return (deletehistoryEntity!=null);
    }

    @Override
    public void add(int userId, int artworkId) {
        String sql = "INSERT INTO deletehistory (userId, artworkId) VALUE (?, ?)";

        DBUtils.update(sql, userId,artworkId);
    }

    @Override
    public void delete(int userId, int artworkId) {
        String sql = "DELETE FROM deletehistory WHERE userId = ? AND artworkId = ?";

        DBUtils.update(sql, userId,artworkId);
    }

    @Override
    public List<DeletehistoryEntity> query(int userId) {
        String sql = "SELECT * FROM deletehistory WHERE userId = ?";

        return DBUtils.getList(DeletehistoryEntity.class, sql, userId);
    }
}
