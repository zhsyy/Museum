package dao.impl;

import dao.UsersDao;
import entity.UsersEntity;
import util.DBUtils;

import java.util.List;

public class UsersDaoImp implements UsersDao {
    @Override
    public UsersEntity query(String username, String password) {
        String sql = "SELECT * FROM users WHERE name = ? AND password = ?";

        return DBUtils.get(UsersEntity.class, sql, username, password);
    }

    @Override
    public UsersEntity query(String username) {
        String sql = "SELECT * FROM users WHERE name = ?";

        return DBUtils.get(UsersEntity.class, sql, username);
    }

    @Override
    public void insert(UsersEntity user) {
        String sql = "INSERT INTO users (name, email, password, type) VALUE (?,?,?,?)";

        DBUtils.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getType());
    }

    @Override
    public List<UsersEntity> queryAll(){
        String sql = "SELECT * FROM users";

        return DBUtils.getList(UsersEntity.class, sql);
    }
}
