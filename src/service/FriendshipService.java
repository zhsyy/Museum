package service;

import entity.FriendshipEntity;

import java.util.List;

public interface FriendshipService {
//    List<FriendshipEntity> getFriendships(int userId);
//
//    List<FriendshipEntity> getFriendshipRequests(int receiverId);

    void accept(FriendshipEntity friendship);

    void reject(FriendshipEntity friendship);

    FriendshipEntity get(int userId, int friendId);

    void delete(int userId, int friendId);
}
