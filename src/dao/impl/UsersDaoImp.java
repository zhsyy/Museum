package dao.impl;

import dao.UsersDao;
import entity.UsersEntity;
import util.DBUtils;

public class UsersDaoImp implements UsersDao {
    @Override
    public UsersEntity query(String username, String password) {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

        return DBUtils.get(UsersEntity.class, sql, username, password);
    }
}
