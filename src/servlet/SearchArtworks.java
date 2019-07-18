package servlet;

import dao.impl.ArtworksDaoImp;
import entity.ArtworksEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class SearchArtworks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        String sortBy = req.getParameter("sortBy");
        if (sortBy==null)
            sortBy="";
        String[] searchBy = req.getParameterValues("searchBy");
        ArtworksDaoImp artworksDaoImp = new ArtworksDaoImp();
        List<ArtworksEntity> artworksEntities;
        int totalCount;
        if (searchText==null || searchText.equals("")){
            searchBy = new String[3];
            searchBy[0] = "title";searchBy[1] = "description";searchBy[2] = "location";
            artworksEntities = artworksDaoImp.getPageSearchArtworks("",searchBy,1,sortBy);
            req.setAttribute("searchText","");
            req.setAttribute("currentPage",1);
            totalCount = artworksDaoImp.getAllSearchArtworksCount("",searchBy);
        } else {
            artworksEntities = artworksDaoImp.getPageSearchArtworks(searchText,searchBy,1,sortBy);
            req.setAttribute("searchText",searchText);
            req.setAttribute("currentPage",1);
            totalCount = artworksDaoImp.getAllSearchArtworksCount(searchText,searchBy);
            req.setAttribute("totalCount",totalCount);
        }
        int totalPage = ((totalCount % 9 == 0) ? (totalCount / 9):(totalCount / 9 + 1));
        req.setAttribute("totalPage",totalPage);
        req.setAttribute("searchBy",searchBy);
        req.setAttribute("sortBy",sortBy);
        req.setAttribute("artworksEntities",artworksEntities);
        req.getRequestDispatcher("/search.jsp").forward(req,resp);
    }
}
