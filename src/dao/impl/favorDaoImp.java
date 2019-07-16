package dao.impl;

import dao.favorDao;
import entity.ArtworksEntity;
import entity.FavorEntity;
import util.DBconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class favorDaoImp implements favorDao {

    @Override
    public List<ArtworksEntity> getFavorArtworks(int UserID) {
        List<ArtworksEntity> list = new ArrayList<>();
        try {
            try{
                DBconnect.init();
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultSet rs = DBconnect.selectSql("select * from favor where userID = '"+UserID+"'");
            while (rs.next()) {
                int artworkID = rs.getInt("artworkID");
                ResultSet tmp = DBconnect.selectSql("select * from artworks where artworkID = '"+artworkID+"'");
                while (tmp.next()) {
                    String image = tmp.getString("imageFileName");
                    String title = tmp.getString("title");
                    String description = tmp.getString("description");
                    int year = tmp.getInt("yearOfWork");
                    int view = tmp.getInt("view");
                    String location = tmp.getString("location");
                    String type = tmp.getString("type");
                    ArtworksEntity artworksEntity = new ArtworksEntity();
//                    artworksEntity.setArtworkId(artworkID);
                    artworksEntity.setDescription(description);
                    artworksEntity.setImageFileName(image);
                    artworksEntity.setLocation(location);
                    artworksEntity.setType(type);
                    artworksEntity.setView(view);
                    artworksEntity.setYearOfWork(year);
                    artworksEntity.setTitle(title);

                    list.add(artworksEntity);
                }
            }
            DBconnect.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ArrayList<FavorEntity> getFavorList(int UserID) {
        ArrayList<FavorEntity> list = new ArrayList<>();
        try {
            try{
                DBconnect.init();
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultSet rs = DBconnect.selectSql("select * from favor where userID = '"+UserID+"'");
            while (rs.next()) {
                int favorID = rs.getInt("favorID");
                int artworkID = rs.getInt("artworkID");
                FavorEntity favorEntity = new FavorEntity();
                favorEntity.setFavorId(favorID);
                favorEntity.setUserId(UserID);
                favorEntity.setArtworkId(artworkID);
                favorEntity.setTime(rs.getTimestamp("time"));
                list.add(favorEntity);
            }
            DBconnect.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
