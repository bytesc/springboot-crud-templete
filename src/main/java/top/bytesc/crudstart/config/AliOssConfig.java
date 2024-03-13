package top.bytesc.crudstart.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AliOssConfig {
    // 使用@Value注解读取yml配置的数据
    @Value("${oss.key-id}")
    private String id;
    @Value("${oss.key-secret}")
    private String secret;
    @Value("${oss.bucket-name}")
    private String bucket;
    @Value("${oss.end-point}")
    private String point;

    public static String keyId;
    public static String keySecret;
    public static String bucketName;
    public static String endPoint;


    @PostConstruct
    public void setStatic() {
        keyId =this.id;
        keySecret=this.secret;
        bucketName=this.bucket;
        endPoint=this.point;
    }

}
