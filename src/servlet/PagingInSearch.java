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
    private ArtworkService artworkService = new ArtworkServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONArray jsonArray = new JSONArray();
        List<ArtworksEntity> outputList = artworkService.getOutputArtworks(req);
        jsonArray.addAll(outputList);
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(jsonArray.toString());
    }
}
