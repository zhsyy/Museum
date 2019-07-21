package dao;

import entity.FriendshipEntity;

import java.util.List;

public interface FriendshipDao {
    List<FriendshipEntity> getFriendships(int userId);

    List<FriendshipEntity> getFriendshipRequests(int receiverId);
}
