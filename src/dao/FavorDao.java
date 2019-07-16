package dao;

import entity.FavorEntity;

import java.util.List;

public interface FavorDao {
    void add(int userId, int artworkId);

    void delete(int favorId);

    List<FavorEntity> getFavorEntities(int userId);
}
