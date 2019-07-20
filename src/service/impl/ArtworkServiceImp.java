package service.impl;

import criteriaObject.CriteriaSearch;
import dao.ArtworksDao;
import dao.FavorDao;
import dao.impl.ArtworksDaoImp;
import dao.impl.FavorDaoImp;
import entity.ArtworksEntity;
import entity.FavorEntity;
import service.ArtworkService;

import java.util.ArrayList;
import java.util.List;

public class ArtworkServiceImp implements ArtworkService {
    private ArtworksDao artworksDao = new ArtworksDaoImp();
    private FavorDao favorDao = new FavorDaoImp();

    @Override
    public List<ArtworksEntity> getOutputArtworks(List<ArtworksEntity> allArtworks,int page) {
        List<ArtworksEntity> outputList = new ArrayList<>();
        for (int i = (page - 1) * 9;i < (page - 1) * 9 + 9; i++){
            if(i<allArtworks.size())
                outputList.add(allArtworks.get(i));
        }
        return outputList;
    }

    @Override
    public List<ArtworksEntity> getSearchArtworks(String searchText,String[] searchBy,String sortBy) {
        CriteriaSearch criteriaSearch = new CriteriaSearch(searchText,searchBy,sortBy);
        return artworksDao.getSearchArtworks(criteriaSearch);
    }

    @Override
    public List<ArtworksEntity> getFavorArtworks(int userId) {
        List<ArtworksEntity> artworksEntities = new ArrayList<>();

        // get favor entities of user
        List<FavorEntity> favorEntities = favorDao.getFavors(userId);
        for (FavorEntity favorEntity : favorEntities) {
            artworksEntities.add(
                    // query artwork entity of artwork ID
                    getArtwork(favorEntity.getArtworkId())
            );
        }

        return artworksEntities;
    }

    @Override
    public List<ArtworksEntity> getHottestArtworks() {
        return artworksDao.getHottestArtworks();
    }

    @Override
    public List<ArtworksEntity> getNewestArtworks() {
        return artworksDao.getNewestArtworks();
    }

    @Override
    public ArtworksEntity getArtwork(int artworkId) {
        return artworksDao.getArtwork(artworkId);
    }

    @Override
    public void updateView(int artworkId, int oldView) {
        artworksDao.updateView(artworkId, oldView + 1);
    }
}
