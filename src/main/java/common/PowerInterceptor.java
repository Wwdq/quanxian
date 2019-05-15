package common;

import dao.PowerMapper;
import model.Power;
import model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import service.PowerService;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerInterceptor implements HandlerInterceptor {
    @Autowired
    private PowerService powerService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        List<String> list=powerService.selectAllUri();


        String url=httpServletRequest.getServletPath();
        System.out.println(url);
        HttpSession session=httpServletRequest.getSession();
        Set<String> set1 = (Set<String>) session.getAttribute("userUrl");
        if(!list.contains(url)){
            return true;
        }else{
            if(set1.contains(url))
                return true;
            else{
                httpServletRequest.getRequestDispatcher("/WEB-INF/views/error/error.jsp").forward(httpServletRequest, httpServletResponse);

                return false;
            }

        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
