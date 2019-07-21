package service.impl;

import dao.FriendshipDao;
import dao.UsersDao;
import dao.impl.FriendshipDaoImp;
import dao.impl.UsersDaoImp;
import entity.FriendshipEntity;
import entity.UsersEntity;
import service.UserService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserServiceImp implements UserService {
    private UsersDao usersDao = new UsersDaoImp();
    private FriendshipDao friendshipDao = new FriendshipDaoImp();

    @Override
    public UsersEntity login(String username, String password) {
        UsersEntity user = usersDao.query(username, password);

        if (user != null) {// found matched user
            // refresh last login time
            user.setTime(new Timestamp(new Date().getTime()));
            update(user);

            user = usersDao.query(username, password);
        }

        return user;
    }

    @Override
    public boolean checkSignUpUsername(String username) {
        return usersDao.query(username) == null;
    }

    @Override
    public void signUp(UsersEntity user) {
        usersDao.insert(user);
    }

    @Override
    public UsersEntity getUserByName(String username) {
        return usersDao.query(username);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersDao.queryAll();
    }

    @Override
    public UsersEntity update(UsersEntity user) {
        usersDao.update(user);
        return usersDao.query(user.getName());
    }

    @Override
    public boolean checkModifyUser(int userId, String password) {
        return usersDao.query(userId, password) != null;
    }

    @Override
    public void addUser(UsersEntity user) {
        usersDao.insert(user);
    }

    @Override
    public void modifyUser(UsersEntity user) {
        usersDao.delete(user.getName());
        usersDao.insert(user);
    }

    @Override
    public void deleteUser(String name) {
        usersDao.delete(name);
    }

    @Override
    public void deleteUser(int userId) {
        usersDao.delete(userId);
    }

    @Override
    public List<UsersEntity> getFriends(int userId) {
        List<UsersEntity> friends = new ArrayList<>();

        // get all friendships of user
        List<FriendshipEntity> friendships = friendshipDao.getFriendships(userId);
        for (FriendshipEntity friendship : friendships) {
            int friendUserId = friendship.getSenderId() == userId ? friendship.getReceiverId() : friendship.getSenderId();
            friends.add(usersDao.query(friendUserId));
        }

        return friends;
    }

    @Override
    public List<UsersEntity> getFriendRequestSenders(int receiverId) {
        List<UsersEntity> senders = new ArrayList<>();

        // get all friendship requests
        List<FriendshipEntity> requests = friendshipDao.getFriendshipRequests(receiverId);
        for (FriendshipEntity request : requests) {
            senders.add(usersDao.query(request.getSenderId()));
        }

        return senders;
    }

    @Override
    public List<UsersEntity> getUserByNameLike(String username) {
        return usersDao.queryLike(username);
    }
}
