package dao.impl;

import dao.ArtworksDao;
import entity.ArtworksEntity;
import entity.FavorEntity;
import util.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class ArtworksDaoImp implements ArtworksDao {
    private FavorDaoImp favorDaoImp = new FavorDaoImp();

    @Override
    public List<ArtworksEntity> getFavorArtworks(int userId) {
        List<ArtworksEntity> artworksEntities = new ArrayList<>();

        String sql = "SELECT * FROM artworks WHERE artworkID = ?";

        // get favor entities of user
        List<FavorEntity> favorEntities = favorDaoImp.getFavorEntities(userId);
        for (FavorEntity favorEntity : favorEntities) {
            artworksEntities.add(
                    // query artwork entity of artwork ID
                    DBUtils.get(ArtworksEntity.class, sql, favorEntity.getArtworkId())
            );
        }

        return artworksEntities;
    }

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
}
