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
    public UsersEntity query(int userId, String password) {
        String sql = "SELECT * FROM users WHERE userId = ? AND password = ?";

        return DBUtils.get(UsersEntity.class, sql, userId, password);
    }

    @Override
    public UsersEntity query(int userId) {
        String sql = "SELECT * FROM users WHERE userId = ?";

        return DBUtils.get(UsersEntity.class, sql, userId);
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

    @Override
    public void update(UsersEntity user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, type = ?, time = ?, signature = ? WHERE userId = ?";

        DBUtils.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getType(), user.getTime(), user.getSignature(), user.getUserId());
    }
  
    public void delete(String name) {
        String sql = "DELETE FROM users WHERE name = ?";

        DBUtils.update(sql, name);
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM users WHERE userId = ?";

        DBUtils.update(sql, userId);
    }

    @Override
    public List<UsersEntity> queryLike(String username) {
        String sql = "SELECT * FROM users WHERE name LIKE ?";

        return DBUtils.getList(UsersEntity.class, sql, "%" + username + "%");
    }
}
