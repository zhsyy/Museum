package dao;

import entity.ArtworksEntity;

import java.util.List;

public interface ArtworksDao {
    List<ArtworksEntity> getFavorArtworks(int userId);

    List<ArtworksEntity> getHottestArtworks();

    List<ArtworksEntity> getNewestArtworks();

    List<ArtworksEntity> getPageSearchArtworks(String searchText,String[] searchBy,int page,String sortBy);

    int getAllSearchArtworksCount(String searchText, String[] searchBy);
}
