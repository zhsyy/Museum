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

    @Override
    public List<ArtworksEntity> getPageSearchArtworks(String searchText, String[] searchBy, int page,String sortBy) {
        List<ArtworksEntity> artworksEntities;
        int length = searchBy.length;
        String sql;
        if (searchText.equals("")){//first into search
            if (sortBy.equals("")){
                sql = "SELECT * FROM artworks limit ?,9";
            }else {
                sql = "SELECT * FROM artworks ORDER BY view desc limit ?,9 ";
            }
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,(page-1)*9);
        } else if (length == 1){
            if (sortBy.equals("")){
                sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? limit ?,9";
            }else {
                sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? ORDER BY view desc limit ?,9";
            }
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,"%"+searchText+"%",(page-1)*9);
        }else if (length == 2){
            if (sortBy.equals("")){
                sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? OR "+searchBy[1]+" like ? limit ?,9";
            }else {
                sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? OR "+searchBy[1]+" like ? ORDER BY view desc limit ?,9";
            }
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,"%"+searchText+"%","%"+searchText+"%",(page-1)*9);
        }else{
            if (sortBy.equals("")){
                sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? OR "+searchBy[1]+" like ? OR "+searchBy[2]+" like ? limit ?,9";
            }else {
                sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? OR "+searchBy[1]+" like ? OR "+searchBy[2]+" like ? ORDER BY view desc limit ?,9";
            }
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,"%"+searchText+"%","%"+searchText+"%","%"+searchText+"%",(page-1)*9);
        }
        return artworksEntities;
    }

    @Override
    public int getAllSearchArtworksCount(String searchText, String[] searchBy) {
        List<ArtworksEntity> artworksEntities;
        int length = searchBy.length;
        String sql;
        if (searchText.equals("")){//first into search
            sql = "SELECT * FROM artworks";
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql);
        } else if (length == 1){
            sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ?";
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,"%"+searchText+"%");
        }else if (length == 2){
            sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? OR "+searchBy[1]+" like ?";
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,"%"+searchText+"%","%"+searchText+"%");
        }else{
            sql = "SELECT * FROM artworks WHERE "+searchBy[0]+" like ? OR "+searchBy[1]+" like ? OR "+searchBy[2]+" like ?";
            artworksEntities = DBUtils.getList(ArtworksEntity.class,sql,"%"+searchText+"%","%"+searchText+"%","%"+searchText+"%");
        }
        return artworksEntities.size();
    }
}
