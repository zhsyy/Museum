package service;

import entity.FriendshipEntity;

public interface FriendshipService {
    void accept(FriendshipEntity friendship);

    void reject(FriendshipEntity friendship);

    FriendshipEntity get(int userId, int friendId);

    void delete(int userId, int friendId);

    void add(int senderId, int receiverId);

    String getFriendshipStatus(int userId, int friendId);
}
