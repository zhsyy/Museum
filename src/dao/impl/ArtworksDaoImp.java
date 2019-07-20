package dao.impl;

import criteriaObject.CriteriaSearch;
import dao.ArtworksDao;
import entity.ArtworksEntity;
import util.DBUtils;

import java.util.List;

public class ArtworksDaoImp implements ArtworksDao {
    @Override
    public List<ArtworksEntity> getHottestArtworks() {
        String sql = "SELECT * FROM artworks ORDER BY view DESC";

        return DBUtils.getList(ArtworksEntity.class, sql);
    }

    @Override
    public List<ArtworksEntity> getNewestArtworks() {
        String sql = "SELECT * FROM artworks ORDER BY timeReleased DESC";

        return DBUtils.getList(ArtworksEntity.class, sql);
    }

    @Override
    public List<ArtworksEntity> getSearchArtworks(CriteriaSearch criteriaSearch) {
        String sql;
        if (criteriaSearch.getSortWay().equals("view"))
            sql = "SELECT * FROM artworks WHERE title like ? OR description like ? OR location like ? ORDER BY view desc";
        else sql = "SELECT * FROM artworks WHERE title like ? OR description like ? OR location like ?";
        return DBUtils.getList(ArtworksEntity.class,sql,criteriaSearch.getSearchInTitle(),criteriaSearch.getSearchInDescription(),
                criteriaSearch.getSearchInLocation());
    }

    @Override
    public ArtworksEntity getArtwork(int artworkId) {
        String sql = "SELECT * FROM artworks WHERE artworkId = ?";

        return DBUtils.get(ArtworksEntity.class, sql, artworkId);
    }

    @Override
    public void updateView(int artworkId, int view) {
        String sql = "UPDATE artworks SET view = ? WHERE artworkId = ?";

        DBUtils.update(sql, view, artworkId);
    }
}
