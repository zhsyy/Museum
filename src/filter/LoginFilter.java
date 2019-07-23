package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter extends HttpFilter {
    private String sessionKey;
    private String redirectUrl;
    private String uncheckedUrls;

    @Override
    protected void init() {
        ServletContext servletContext = getFilterConfig().getServletContext();

        sessionKey = servletContext.getInitParameter("user");
        redirectUrl = servletContext.getInitParameter("redirectPage");
        uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String servletPath = request.getServletPath();
        List<String> urls = Arrays.asList(uncheckedUrls.split(","));
        if (!servletPath.contains(".") || !servletPath.substring(servletPath.lastIndexOf(".")).equals(".jsp") || urls.contains(servletPath)){
            filterChain.doFilter(request,response);
            return;
        }
        Object user = request.getSession().getAttribute(sessionKey);
        if (user == null){
            response.sendRedirect(request.getContextPath() + redirectUrl);
            return;
        }
        filterChain.doFilter(request,response);
    }
}
