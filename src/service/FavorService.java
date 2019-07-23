package service;

import entity.FavorEntity;

import java.util.List;

public interface FavorService {
    FavorEntity query(int favorId);

    void insert(FavorEntity favor);

    void delete(int favorId);

    List<FavorEntity> getFavors(int userId);

    void publicize(int favorId);

    void privatize(int favorId);

    List<FavorEntity> getFavorsByArtworkId(int artworkId);
}
