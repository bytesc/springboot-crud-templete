package top.bytesc.crudstart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest  //先初始化spring容器
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        ValueOperations<String,String> operations =stringRedisTemplate.opsForValue();
        operations.set("user","zhangsan");

        System.out.println(operations.get("user"));

        operations.set("id","1",15, TimeUnit.SECONDS);
        //15秒过期
    }
}
