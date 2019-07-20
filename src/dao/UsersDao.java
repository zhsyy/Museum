package dao;

import entity.UsersEntity;

import java.util.List;

public interface UsersDao {
    UsersEntity query(String username, String password);

    UsersEntity query(String username);

    void insert(UsersEntity user);

    List<UsersEntity> queryAll();

    void delete(String name);

    void delete(int userId);
}
