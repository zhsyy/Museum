package dao.impl;

import dao.FavorDao;
import entity.FavorEntity;
import util.DBUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FavorDaoImp implements FavorDao {
    @Override
    public void add(int userId, int artworkId) {
        String sql = "INSERT INTO favor (userId, artworkId, time) VALUE (?,?,?)";
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        DBUtils.update(sql, userId, artworkId, Timestamp.valueOf(dateFormat.format(new Date())));
    }

    @Override
    public void delete(int favorId) {
        String sql = "DELETE FROM favor WHERE favorId = ?";

        DBUtils.update(sql, favorId);
    }

    @Override
    public List<FavorEntity> getFavorEntities(int userId) {
        String sql = "SELECT * FROM favor WHERE userId = ?";

        return DBUtils.getList(FavorEntity.class, sql, userId);
    }
}
