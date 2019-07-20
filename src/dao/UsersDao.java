package dao;

import entity.UsersEntity;

import java.util.List;

public interface UsersDao {
    UsersEntity query(String username, String password);

    UsersEntity query(String username);

    UsersEntity query(int userId, String password);

    void insert(UsersEntity user);

    List<UsersEntity> queryAll();

    void update(UsersEntity user);
}
