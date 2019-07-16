package dao;

import entity.ArtworksEntity;

import java.util.List;

public interface ArtworksDao {
    List<ArtworksEntity> getFavorArtworks(int userId);
}
