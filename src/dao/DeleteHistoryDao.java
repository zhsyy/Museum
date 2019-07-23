package dao;

import entity.DeletehistoryEntity;

import java.util.List;

public interface DeleteHistoryDao {
    boolean query(int userId,int artworkId);
    void add(int userId,int artworkId);
    void delete(int userId,int artworkId);
    List<DeletehistoryEntity> query(int userId);
}
