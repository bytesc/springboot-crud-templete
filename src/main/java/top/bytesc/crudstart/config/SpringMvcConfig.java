package top.bytesc.crudstart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.bytesc.crudstart.midwares.LoginChecker;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginChecker loginChecker;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginChecker).
                excludePathPatterns("/user/login","/user/add");
    }


}
