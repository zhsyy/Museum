package dao;

import entity.FavorEntity;

import java.util.List;

public interface FavorDao {
    void insert(FavorEntity favor);

    void delete(int favorId);

    List<FavorEntity> getFavors(int userId);

    void update(int favorId, String type);
}
