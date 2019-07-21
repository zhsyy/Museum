package dao.impl;

import dao.FriendshipDao;
import entity.FriendshipEntity;
import util.DBUtils;

import java.util.List;

public class FriendshipDaoImp implements FriendshipDao {
    @Override
    public List<FriendshipEntity> getFriendships(int userId) {
        String sql = "SELECT * FROM friendship WHERE senderId = ? OR receiverId = ?";

        return DBUtils.getList(FriendshipEntity.class, sql, userId, userId);
    }

    @Override
    public List<FriendshipEntity> getFriendshipRequests(int receiverId) {
        String sql = "SELECT * FROM friendship WHERE receiverId = ? AND status = sent";

        return DBUtils.getList(FriendshipEntity.class, sql, receiverId);
    }
}
