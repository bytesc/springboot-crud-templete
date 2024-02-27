package top.bytesc.crudstart.midwares;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.bytesc.crudstart.models.Result;
import top.bytesc.crudstart.utils.JwtUtil;

import java.util.Map;

@Component
public class LoginChecker implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("token");

        try{
            Map<String,Object> claims = JwtUtil.parseToken(token);
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }

        return true; //放行
    }
}
