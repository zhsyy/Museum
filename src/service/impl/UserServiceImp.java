package service.impl;

import dao.UsersDao;
import dao.impl.UsersDaoImp;
import entity.UsersEntity;
import service.UserService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class UserServiceImp implements UserService {
    private UsersDao usersDao = new UsersDaoImp();

    @Override
    public UsersEntity login(String username, String password) {
        UsersEntity user = usersDao.query(username, password);

        if (user != null) {// found matched user
            // refresh last login time
            user.setTime(new Timestamp(new Date().getTime()));
            update(user);

            user = usersDao.query(username, password);
        }

        return user;
    }

    @Override
    public boolean checkSignUpUsername(String username) {
        return usersDao.query(username) == null;
    }

    @Override
    public void signUp(UsersEntity user) {
        usersDao.insert(user);
    }

    @Override
    public UsersEntity getUserByName(String userName) {
        return usersDao.query(userName);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersDao.queryAll();
    }

    @Override
    public void update(UsersEntity user) {
        usersDao.update(user);
    }
}
