package dao;

import entity.ArtworksEntity;
import entity.FavorEntity;

import java.util.ArrayList;
import java.util.List;

public interface favorDao {
    List<ArtworksEntity> getFavorArtworks(int UserID);
    ArrayList<FavorEntity> getFavorList(int UserID);
}
