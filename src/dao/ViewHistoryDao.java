package dao;

import entity.ViewhistoryEntity;

import java.util.List;

public interface ViewHistoryDao {
    ViewhistoryEntity query(int userId, int artworkId);
    void update(int userId,int artworkId,int viewTime);
    void add(int userId,int artworkId);
    List<ViewhistoryEntity> query(int userId);
}
