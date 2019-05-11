package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 94801
 */
@Component
public class ParseToken {
    //校验jwt合法性的方法

    //需要被校验的有两点：1.jwt是否过期 2.jwt的权限是否足够高||是否满足对应逻辑的要求
    //1.一旦jwt过期，将会在 JWTVerifier verifier = JWT.require(algorithm)
    //                    .withIssuer("SERVICE")
    //                    .build()
    //的build()方法中抛com.auth0.jwt.exceptions.TokenExpiredException
    //异常信息示例：
    //com.auth0.jwt.exceptions.TokenExpiredException: The Token has expired on Sat Apr 20 20:29:37 CST 2019.
    //捕捉这个异常即可
    public Map parse(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC256("code");
            JWTVerifier verifier= JWT.require(algorithm)
                    .build();

            //将token转化为jwt
            DecodedJWT jwt = verifier.verify(token);
            String subject=jwt.getSubject();
            //获得JWT中的参数
            Map<String, Claim> claimMap=jwt.getClaims();
            Claim claimRole= claimMap.get("role");
//            System.out.println("--------"+claimRole.asString());
            String role=claimRole.asString();
            Claim claimId= claimMap.get("id");
            String id=claimId.asString();
//            System.out.println("-------------------id"+id);
//            System.out.println("-------------------role"+role);
            Map<String,String> codeMap=new HashMap<>();
            codeMap.put("role",role);
            codeMap.put("id",id);
            return codeMap;
        }catch (JWTVerificationException e){
            return null;
        }
    }
}
