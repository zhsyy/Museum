package dao;

import entity.UsersEntity;

public interface UsersDao {
    UsersEntity query(String username, String password);

    UsersEntity query(String username);

    void insert(UsersEntity user);
}
