package dao;

import entity.FavorEntity;

import java.util.List;

public interface FavorDao {
    void insert(int userId, int artworkId);

    void delete(int favorId);

    List<FavorEntity> getFavorEntities(int userId);
}
