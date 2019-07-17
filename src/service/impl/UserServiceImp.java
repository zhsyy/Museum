package service.impl;

import dao.UsersDao;
import dao.impl.UsersDaoImp;
import entity.UsersEntity;
import service.UserService;

public class UserServiceImp implements UserService {
    private UsersDao usersDao = new UsersDaoImp();

    @Override
    public UsersEntity login(String username, String password) {
        return usersDao.query(username, password);
    }

    @Override
    public boolean checkSignUpUsername(String username) {
        return usersDao.query(username) == null;
    }

    @Override
    public void signUp(UsersEntity user) {
        usersDao.insert(user);
    }
}
