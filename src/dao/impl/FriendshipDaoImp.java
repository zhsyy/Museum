package dao.impl;

import dao.FriendshipDao;
import entity.FriendshipEntity;
import util.DBUtils;

import java.util.List;

public class FriendshipDaoImp implements FriendshipDao {
    @Override
    public List<FriendshipEntity> getFriendships(int userId) {
        String sql = "SELECT * FROM friendship WHERE status = ? AND (senderId = ? OR receiverId = ?)";

        return DBUtils.getList(FriendshipEntity.class, sql, "accepted", userId, userId);
    }

    @Override
    public List<FriendshipEntity> getFriendshipRequests(int receiverId) {
        String sql = "SELECT * FROM friendship WHERE receiverId = ? AND status = ?";

        return DBUtils.getList(FriendshipEntity.class, sql, receiverId, "sent");
    }

    @Override
    public void update(FriendshipEntity friendship) {
        String sql = "UPDATE friendship SET status = ? WHERE senderId = ? AND receiverId = ?";

        DBUtils.update(sql, friendship.getStatus(), friendship.getSenderId(), friendship.getReceiverId());
    }

    @Override
    public FriendshipEntity get(int userId, int friendId) {
        String sql = "SELECT * FROM friendship WHERE (senderId = ? AND receiverId = ?) OR (senderId = ? AND receiverId = ?)";

        return DBUtils.get(FriendshipEntity.class, sql, userId, friendId, friendId, userId);
    }

    @Override
    public void delete(int userId, int friendId) {
        String sql = "DELETE FROM friendship WHERE (senderId = ? AND receiverId = ?) OR (senderId = ? AND receiverId = ?)";

        DBUtils.update(sql, userId, friendId, friendId, userId);
    }
}
