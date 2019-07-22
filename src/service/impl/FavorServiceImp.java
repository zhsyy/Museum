package service.impl;

import dao.FavorDao;
import dao.impl.FavorDaoImp;
import entity.FavorEntity;
import service.FavorService;

import java.util.List;

public class FavorServiceImp implements FavorService {
    private FavorDao favorDao = new FavorDaoImp();

    @Override
    public void insert(FavorEntity favor) {
        favorDao.insert(favor);
    }

    @Override
    public void delete(int favorId) {
        favorDao.delete(favorId);
    }

    @Override
    public List<FavorEntity> getFavors(int userId) {
        return favorDao.getFavors(userId);
    }

    @Override
    public void publicize(int favorId) {
        favorDao.update(favorId, "public");
    }

    @Override
    public void privatize(int favorId) {
        favorDao.update(favorId, "private");
    }
}
