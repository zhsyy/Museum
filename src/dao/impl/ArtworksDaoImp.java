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
}
