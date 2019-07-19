package service;

import entity.ArtworksEntity;

import java.util.List;

public interface ArtworkService {
    List<ArtworksEntity> getFavorArtworks(int userId);

    List<ArtworksEntity> getHottestArtworks();

    List<ArtworksEntity> getNewestArtworks();

    ArtworksEntity getArtwork(int artworkId);

    void updateView(int artworkId, int oldView);
}
