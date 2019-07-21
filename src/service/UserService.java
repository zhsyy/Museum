package service;

import entity.UsersEntity;

import java.util.List;

public interface UserService {
    UsersEntity login(String username, String password);

    boolean checkSignUpUsername(String username);

    void signUp(UsersEntity user);

    UsersEntity getUserByName(String userName);

    List<UsersEntity> getAllUsers();

    UsersEntity update(UsersEntity user);

    boolean checkModifyUser(int userId, String password);

    void addUser(UsersEntity users);

    void modifyUser(UsersEntity users);

    void deleteUser(String name);

    void deleteUser(int userId);

    List<UsersEntity> getFriends(int userId);

    List<UsersEntity> getFriendRequestSenders(int receiverId);
}
