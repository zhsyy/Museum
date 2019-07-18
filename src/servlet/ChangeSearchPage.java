package servlet;

import com.alibaba.fastjson.JSONArray;
import dao.impl.ArtworksDaoImp;
import entity.ArtworksEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "changeSearchPage", value = "/changeSearchPage")
public class ChangeSearchPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        String search = req.getParameter("searchBy");
        String sortBy = req.getParameter("sortBy");
        search = search.substring(0,search.length()-1);
        String[] searchBy = search.split(",");
        int page = Integer.parseInt(req.getParameter("page"));
        ArtworksDaoImp artworksDaoImp = new ArtworksDaoImp();
        List<ArtworksEntity> artworksEntities = artworksDaoImp.getPageSearchArtworks(searchText,searchBy,page,sortBy);

        JSONArray jsonStrs = new JSONArray();
        jsonStrs.addAll(artworksEntities);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonStrs.toString());
    }
}
