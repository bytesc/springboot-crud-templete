package top.bytesc.crudstart.midwares;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.bytesc.crudstart.utils.JwtUtil;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

import java.util.Map;

@Component
public class LoginChecker implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        String token = request.getHeader("token");

        try{
            Map<String,Object> claims = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claims); //存入当前线程
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }

        return true; //放行
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        ThreadLocalUtil.remove(); //防止内存泄露
    }
}
