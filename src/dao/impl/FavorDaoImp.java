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
    public void insert(FavorEntity favor) {
        String sql = "INSERT INTO favor (userId, artworkId, type, time) VALUE (?, ?, ?, ?)";
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        DBUtils.update(sql, favor.getUserId(), favor.getArtworkId(), favor.getType(), Timestamp.valueOf(dateFormat.format(new Date())));
    }

    @Override
    public void delete(int favorId) {
        String sql = "DELETE FROM favor WHERE favorId = ?";

        DBUtils.update(sql, favorId);
    }

    @Override
    public List<FavorEntity> getFavors(int userId) {
        String sql = "SELECT * FROM favor WHERE userId = ?";

        return DBUtils.getList(FavorEntity.class, sql, userId);
    }

    @Override
    public void update(int favorId, String type) {
        String sql = "UPDATE favor SET type = ? WHERE favorId = ?";

        DBUtils.update(sql, type, favorId);
    }
}
