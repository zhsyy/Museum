package service;

import entity.FavorEntity;

public interface FavorService {
    void insert(FavorEntity favor);

    void delete(String favorId);
}
