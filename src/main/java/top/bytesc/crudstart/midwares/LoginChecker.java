package top.bytesc.crudstart.midwares;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.bytesc.crudstart.utils.JwtUtil;
import top.bytesc.crudstart.utils.ThreadLocalUtil;

import java.util.Map;

@Component
public class LoginChecker implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        String token = request.getHeader("token");

        try{
            //解析token
            Map<String,Object> claims = JwtUtil.parseToken(token);
            //查redis
            ValueOperations<String,String> operations =stringRedisTemplate.opsForValue();
            String redisToken =operations.get(token);
            if (redisToken==null) throw new RuntimeException();

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
