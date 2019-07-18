package service;

import entity.ArtworksEntity;

import java.util.List;

public interface ArtworkService {
    List<ArtworksEntity> getHottestArtworks();

    List<ArtworksEntity> getNewestArtworks();
}
