package dao;

import entity.UsersEntity;

public interface UsersDao {
    UsersEntity query(String username, String password);
}
