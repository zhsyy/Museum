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

    void deleteInUsers(String name);
    void deleteInViewHistory(int userId);
    void deleteInDeleteHistory(int userId);
    void deleteInEmail(String name);
    void deleteInFavor(int userId);
    void deleteInfriendship(int userId);
    void deleteInUsers(int userId);

    List<UsersEntity> queryLike(String username);

    void updateInAdmin(UsersEntity user);
}
