package dao;

import criteriaObject.CriteriaSearch;
import entity.ArtworksEntity;

import java.util.List;

public interface ArtworksDao {
    List<ArtworksEntity> getHottestArtworks();

    List<ArtworksEntity> getNewestArtworks();

    List<ArtworksEntity> getSearchArtworks(CriteriaSearch criteriaSearch);

    ArtworksEntity getArtwork(int artworkId);

    void updateView(int artworkId, int view);

    void insert(ArtworksEntity artwork);

    void delete(String artworkId);

    void modify(ArtworksEntity artwork);

    List<ArtworksEntity> getArtworksByType(String type);
}
