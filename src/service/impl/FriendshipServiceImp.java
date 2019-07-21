package service.impl;

import dao.FriendshipDao;
import dao.impl.FriendshipDaoImp;
import entity.FriendshipEntity;
import service.FriendshipService;

import java.util.List;

public class FriendshipServiceImp implements FriendshipService {
    private FriendshipDao friendshipDao = new FriendshipDaoImp();

    @Override
    public List<FriendshipEntity> getFriendships(int userId) {
        return friendshipDao.getFriendships(userId);
    }

    @Override
    public List<FriendshipEntity> getFriendshipRequests(int receiverId) {
        return friendshipDao.getFriendshipRequests(receiverId);
    }
}
