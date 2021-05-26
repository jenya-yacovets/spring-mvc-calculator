package by.sample.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if(request.getSession().getAttribute("user") != null) {
            return true;
        } else {
            response.sendRedirect(String.format("/login?%s=%s", "message", "Authorization required"));
            return false;
        }
    }
}
