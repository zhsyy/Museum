package dao;

import entity.FriendshipEntity;

import java.util.List;

public interface FriendshipDao {
    List<FriendshipEntity> getFriendships(int userId);

    List<FriendshipEntity> getFriendshipRequests(int receiverId);

    void update(FriendshipEntity friendship);

    FriendshipEntity get(int userId, int friendId);

    void delete(int userId, int friendId);
}
