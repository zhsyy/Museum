package dao;

import entity.ArtworksEntity;

import java.util.List;

public interface ArtworksDao {
    List<ArtworksEntity> getHottestArtworks();

    List<ArtworksEntity> getNewestArtworks();

    List<ArtworksEntity> getPageSearchArtworks(String searchText,String[] searchBy,int page,String sortBy);

    int getAllSearchArtworksCount(String searchText, String[] searchBy);

    ArtworksEntity getArtwork(int artworkId);

    void updateView(int artworkId, int view);
}
