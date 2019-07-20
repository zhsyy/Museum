package servlet;

import com.alibaba.fastjson.JSONArray;
import entity.ArtworksEntity;
import service.ArtworkService;
import service.impl.ArtworkServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "pagingInSearch", value = "/pagingInSearch")
public class PagingInSearch extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArtworkService artworkService = new ArtworkServiceImp();
        int page = Integer.parseInt(req.getParameter("page"));
        String sortBy = req.getParameter("sortByView");
        String searchText = req.getParameter("searchText");
        String searchByTitle = req.getParameter("searchByTitle");
        String searchByDescription = req.getParameter("searchByDescription");
        String searchByLocation = req.getParameter("searchByLocation");

        String[] searchBy = new String[]{searchByTitle,searchByDescription,searchByLocation};

        JSONArray jsonArray = new JSONArray();
        List<ArtworksEntity> outputList = artworkService.getOutputArtworks(artworkService.getSearchArtworks(searchText,searchBy,sortBy),page);
        jsonArray.addAll(outputList);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonArray.toString());
    }
}
