package top.bytesc.crudstart;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    private final String key = "asdfgh";//这个密钥千万不能露
    @Test
    public void testGen(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","jack");
        String token = JWT.create().withClaim("user",claims)
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60))
                .sign(Algorithm.HMAC256(key));
        System.out.println(token);
        //eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImphY2sifSwiZXhwIjoxNzA4OTIzMDY5fQ.D0dC8dy5irrqn8ZNXNKNKnKOvezhBe0MNmUsysgIm64
    }

    @Test
    public void testParse(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImphY2sifSwiZXhwIjoxNzA4OTIzMDY5fQ.D0dC8dy5irrqn8ZNXNKNKnKOvezhBe0MNmUsysgIm64";
        JWTVerifier jwtVerifier =JWT.require(Algorithm.HMAC256(key)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token); //com.auth0.jwt.exceptions.JWTDecodeException
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));

//        try {
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(key)).build();
//            DecodedJWT decodedJWT = jwtVerifier.verify(token);
//            Map<String, Claim> claims = decodedJWT.getClaims();
//            System.out.println(claims.get("user"));
//        } catch (JWTDecodeException e) {
//            // 处理JWTDecodeException异常
//            e.printStackTrace();
//        }
    }
}
