package dao;

import entity.UsersEntity;

import java.util.List;

public interface UsersDao {
    UsersEntity query(String username, String password);

    UsersEntity query(String username);

    UsersEntity query(int userId, String password);

    UsersEntity query(int userId);

    void insert(UsersEntity user);

    List<UsersEntity> queryAll();

    void update(UsersEntity user);

    void delete(String name);

    void delete(int userId);

    List<UsersEntity> queryLike(String username);

    void updateInAdmin(UsersEntity user);
}
