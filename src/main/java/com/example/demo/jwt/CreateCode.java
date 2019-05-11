package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.utils.TimeUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author 94801
 */
@Component
public class CreateCode {
    public String createCode(String id){
        //token密钥,自定义的内容
        String secret="code";
        Algorithm algorithm=Algorithm.HMAC256(secret);
        Map<String,Object> headerMap=new HashMap<>();
        headerMap.put("alg","HS256");
        headerMap.put("type","JWT");

        Date nowDate = new Date();
        Date expireDate = TimeUtils.getAfterDate(nowDate,0,0,0,2,0,0);
        String token= JWT.create()
                .withHeader(headerMap)
                .withClaim("type","oauth")
                .withClaim("role","admin")
                .withClaim("id",id)
                .withSubject("code")
                .withIssuedAt(nowDate)
                .withExpiresAt(expireDate)
                //签名
                .sign(algorithm);
        return token;
    }
}
