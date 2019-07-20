package service;

import entity.ArtworksEntity;

import java.util.List;

public interface ArtworkService {
    List<ArtworksEntity> getFavorArtworks(int userId);

    List<ArtworksEntity> getHottestArtworks();

    List<ArtworksEntity> getNewestArtworks();

    ArtworksEntity getArtwork(int artworkId);

    void updateView(int artworkId, int oldView);

    List<ArtworksEntity> getSearchArtworks(String searchText,String[] searchBy,String sortBy);

    List<ArtworksEntity> getOutputArtworks(List<ArtworksEntity> allArtworks,int page);
}
