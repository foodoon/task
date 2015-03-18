package guda.task.web.filter;

import guda.task.biz.vo.AjaxResponce;
import guda.task.common.security.AppContexHolder;
import guda.task.common.security.AppContext;
import guda.task.web.constans.SessionConstants;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by foodoon on 2014/12/27.
 */
public class SecurityCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            AppContext app = AppContexHolder.getContext();
            String requestURI = req.getRequestURI();
            if (requestURI.endsWith("json") || requestURI.endsWith("htm")) {
                if (requestURI.startsWith("/admin") && !app.isAdmin()) {
                    returnError(req,resp);
                    return;
                }
                if (requestURI.startsWith("/buyer") && !app.isBuyer()) {
                    returnError(req,resp);
                    return;
                }
                if (requestURI.startsWith("/seller") && !app.isSeller()) {

                    returnError(req,resp);
                    return;
                }
            }
        }

        chain.doFilter(request, response);

    }

    private void returnError(HttpServletRequest req ,HttpServletResponse resp){
        try {
            String requestURI = req.getRequestURI();
            if(requestURI.endsWith("json")){
                resp.setStatus(401);
                ajaxReturnObj(new AjaxResponce(false), resp);
            }else {
                resp.sendRedirect(getBasePath(req) + "/500.htm");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void ajaxReturnObj(Object obj, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            response.setContentType("text/json;charset=UTF-8");
            out = response.getWriter();
            out.print(obj);
            out.flush();
        } catch (IOException e) {

        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://"
                + request.getServerName() + ":" + request.getServerPort()
                + path;
        return basePath;
    }

    @Override
    public void destroy() {

    }
}
