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
}
