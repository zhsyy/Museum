package service;

import entity.UsersEntity;

public interface UserService {
    UsersEntity login(String username, String password);

    boolean checkSignUpUsername(String username);

    void signUp(UsersEntity user);
}
