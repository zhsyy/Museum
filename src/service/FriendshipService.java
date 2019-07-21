package service;

import entity.FriendshipEntity;

import java.util.List;

public interface FriendshipService {
    List<FriendshipEntity> getFriendships(int userId);

    List<FriendshipEntity> getFriendshipRequests(int receiverId);
}
