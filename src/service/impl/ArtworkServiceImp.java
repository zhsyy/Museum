package service.impl;

import dao.ArtworksDao;
import dao.impl.ArtworksDaoImp;
import entity.ArtworksEntity;
import service.ArtworkService;

import java.util.List;

public class ArtworkServiceImp implements ArtworkService {
    private ArtworksDao artworksDao = new ArtworksDaoImp();

    @Override
    public List<ArtworksEntity> getHottestArtworks() {
        return artworksDao.getHottestArtworks();
    }

    @Override
    public List<ArtworksEntity> getNewestArtworks() {
        return artworksDao.getNewestArtworks();
    }
}
