package service.impl;

import dao.FriendshipDao;
import dao.impl.FriendshipDaoImp;
import entity.FriendshipEntity;
import service.FriendshipService;

public class FriendshipServiceImp implements FriendshipService {
    private FriendshipDao friendshipDao = new FriendshipDaoImp();

//    @Override
//    public List<FriendshipEntity> getFriendships(int userId) {
//        return friendshipDao.getFriendships(userId);
//    }
//
//    @Override
//    public List<FriendshipEntity> getFriendshipRequests(int receiverId) {
//        return friendshipDao.getFriendshipRequests(receiverId);
//    }

    @Override
    public void accept(FriendshipEntity friendship) {
        friendship.setStatus("accepted");
        friendshipDao.update(friendship);
    }

    @Override
    public void reject(FriendshipEntity friendship) {
//        friendship.setStatus("rejected");
//        friendshipDao.update(friendship);
        friendshipDao.delete(friendship.getSenderId(), friendship.getReceiverId());
    }

    @Override
    public FriendshipEntity get(int userId, int friendId) {
        return friendshipDao.get(userId, friendId);
    }

    @Override
    public void delete(int userId, int friendId) {
        friendshipDao.delete(userId, friendId);
    }

    @Override
    public void add(int senderId, int receiverId) {
        friendshipDao.insert(new FriendshipEntity(senderId, receiverId));
    }

    @Override
    public String getFriendshipStatus(int userId, int friendId) {
        FriendshipEntity friendship = friendshipDao.get(userId, friendId);

        return friendship == null ? null : friendship.getStatus();
    }
}
