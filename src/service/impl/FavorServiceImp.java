package service.impl;

import dao.FavorDao;
import dao.impl.FavorDaoImp;
import entity.FavorEntity;
import service.FavorService;

public class FavorServiceImp implements FavorService {
    private FavorDao favorDao = new FavorDaoImp();

    @Override
    public void insert(FavorEntity favor) {
        favorDao.insert(favor);
    }
}
