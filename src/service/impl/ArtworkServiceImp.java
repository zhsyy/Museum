package service.impl;

import criteriaObject.CriteriaSearch;
import dao.ArtworksDao;
import dao.FavorDao;
import dao.impl.ArtworksDaoImp;
import dao.impl.FavorDaoImp;
import entity.ArtworksEntity;
import entity.FavorEntity;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ArtworkService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArtworkServiceImp implements ArtworkService {
    private ArtworksDao artworksDao = new ArtworksDaoImp();
    private FavorDao favorDao = new FavorDaoImp();

    @Override
    public List<ArtworksEntity> getOutputArtworks(HttpServletRequest req) {
        int page = Integer.parseInt(req.getParameter("page"));
        String sortBy = req.getParameter("sortByView");
        String searchText = req.getParameter("searchText");
        String searchByTitle = req.getParameter("searchByTitle");
        String searchByDescription = req.getParameter("searchByDescription");
        String searchByLocation = req.getParameter("searchByLocation");

        String[] searchBy = new String[]{searchByTitle,searchByDescription,searchByLocation};
        return getOutputArtworks(getSearchArtworks(searchText,searchBy,sortBy),page);
    }

    @Override
    public void deleteArtwork(String artworkId) {
        artworksDao.delete(artworkId);
    }

    @Override
    public void updateArtwork(ArtworksEntity artwork) {
        artworksDao.modify(artwork);
    }

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

    @Override
    public void saveArtworks(HttpServletRequest req,String filePath){
        DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
        String[] p = filePath.split("\\\\");
        StringBuilder realPath = new StringBuilder();
        for (int i = 0;i<p.length-5;i++){
            realPath.append(p[i]).append("\\");
        }
        realPath.append("web\\resource\\img");
        sf.setRepository(new File(filePath));//设置文件存放目录
        sf.setSizeThreshold(1024*1024);//设置文件上传小于1M放在内存中
        String rename = "";//文件新生成的文件名
        String fileName = "";//文件原名称

        String title = "";//普通field字段
        String description = "";
        String location = "";
        String yearOfWork = "";
        String imageFileName = "";
        String artworkId = "";
        ServletFileUpload sfu = new ServletFileUpload(sf);

        try {
            @SuppressWarnings("unchecked")
            List<FileItem> lst = sfu.parseRequest(req);//得到request中所有的元素
            for (FileItem fileItem : lst) {
                if(fileItem.isFormField()){
                    if("title".equals(fileItem.getFieldName())){
                        title = fileItem.getString("UTF-8");
                    }else if ("description".equals(fileItem.getFieldName())){
                        description = fileItem.getString("UTF-8");
                    }else if ("location".equals(fileItem.getFieldName())){
                        location = fileItem.getString("UTF-8");
                    }else if ("yearOfWork".equals(fileItem.getFieldName())){
                        yearOfWork = fileItem.getString("UTF-8");
                    }else if ("artworkId".equals(fileItem.getFieldName())){
                        artworkId = fileItem.getString("UTF-8");
                    }
                }else{
                    //获得文件名称
                    fileName = fileItem.getName();
                    if (!fileName.equals("")) {
                        fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                        String suffix = fileName.substring(fileName.lastIndexOf("."));
                        rename = UUID.randomUUID() + "";
                        imageFileName = rename.substring(0, 8) + suffix;
                        System.out.println(imageFileName);
                        fileItem.write(new File(realPath.toString(), imageFileName));
                    }else {
                        imageFileName = artworksDao.getArtwork(Integer.parseInt(artworkId)).getImageFileName();
                    }
                }
            }
            ArtworksEntity artworksEntity = new ArtworksEntity(imageFileName,title,description,Integer.parseInt(yearOfWork),location,0,"other",new Timestamp(System.currentTimeMillis()));
            ArtworksDao artworkService = new ArtworksDaoImp();
            if (!artworkId.equals("")){
                artworksEntity.setArtworkId(Integer.parseInt(artworkId));
                artworkService.modify(artworksEntity);
            }else {
                artworkService.insert(artworksEntity);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtworksEntity> getFriendArtworks(int friendId) {
        List<ArtworksEntity> friendArtworks = new ArrayList<>();

        // get favors of friend
        List<FavorEntity> allFavors = favorDao.getFavors(friendId);
        for (FavorEntity favor : allFavors) {
            if ("public".equals(favor.getType())) {// get public favors
                friendArtworks.add(
                        // query artwork entity of artwork ID
                        getArtwork(favor.getArtworkId())
                );
            }
        }

        return friendArtworks;
    }
}
