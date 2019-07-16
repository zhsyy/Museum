package dao.impl;

import dao.favorDao;
import entity.ArtworksEntity;
import entity.FavorEntity;
import org.omg.CORBA.ARG_IN;
import util.DBconnect;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class favorDaoImp implements favorDao {
    @Override
    public void addFavor(int UserID, int artworkID) {
        try{
            DBconnect.init();
        }catch (Exception e){
            e.printStackTrace();
        }
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DBconnect.Update("favor",UserID,artworkID, Timestamp.valueOf(dateFormat.format(date)));
        DBconnect.closeConn();
    }

    @Override
    public void deleteFavor(int favorID) {
        try{
            DBconnect.init();
        }catch (Exception e){
            e.printStackTrace();
        }
        DBconnect.Delete("favor",favorID);
        DBconnect.closeConn();
    }

    @Override
    public List<ArtworksEntity> getFavorArtworks(int UserID) {
        List<ArtworksEntity> list = new ArrayList<>();
        try {
            try{
                DBconnect.init();
            }catch (Exception e){
                e.printStackTrace();
            }
            ResultSet resultSet = DBconnect.Query("favor","userID",UserID);
            while (resultSet != null && resultSet.next()) {
                int artworkID = resultSet.getInt("artworkID");
                ResultSet tmp = DBconnect.Query("artworks","artworkID", artworkID);
                while (tmp != null && tmp.next()) {
                    ArtworksEntity artworksEntity = new ArtworksEntity(artworkID,tmp.getString("imageFileName"),tmp.getString("title"),
                            tmp.getString("description"), tmp.getInt("yearOfWork"),
                            tmp.getString("location"),tmp.getInt("view"),tmp.getString("type"));
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
            ResultSet rs = DBconnect.Query("favor","userID",UserID);
            while (rs!=null && rs.next()) {
                int favorID = rs.getInt("favorID");
                int artworkID = rs.getInt("artworkID");
                FavorEntity favorEntity = new FavorEntity(favorID,UserID,artworkID,rs.getTimestamp("time"));
                list.add(favorEntity);
            }
            DBconnect.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
