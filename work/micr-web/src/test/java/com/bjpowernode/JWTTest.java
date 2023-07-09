package com.bjpowernode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTTest {

    //创建jwt
    //95dff70708e84ec8a329842839c11eb4
    @Test
    public void testCreateJwt(){

        String key="95dff70708e84ec8a329842839c11eb4";

        //创建secretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        Date curDate = new Date();
        Map<String, Object> data = new HashMap<>();
        data.put("userId",1001);
        data.put("name","李四");
        data.put("role","经理");
        //创建jwt，使用jwts类
        String jwt = Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(new Date(),10))
                .setIssuedAt(curDate)
                .setId(UUID.randomUUID().toString())
                .addClaims(data).compact();

        System.out.println("jwt=="+jwt);
//        eyJhbGciOiJIUzI1NiJ9
//                .eyJleHAiOjE2ODg4NzQzNTgsImlhdCI6MTY4ODg3Mzc1OCwianRpIjoiZWM5M2I4ZDgtOTgzOS00YmRiLWFjYzUtNTk3OTkwYWRkMzk3Iiwicm9sZSI6Iue7j-eQhiIsIm5hbWUiOiLmnY7lm5siLCJ1c2VySWQiOiIxMDAxIn0
//                .jOibVliWLZtLglshZhsQuQWssdIcK1axe2AYmYkc_5o
    }

    @Test
    public void testReadJwt(){
        String jwt="eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODg4NzUwNzksImlhdCI6MTY4ODg3NDQ3OSwianRpIjoiN2RlNGZiNjItZTZiMi00YjI5LTgxMTQtZWM1ZWE0MzAyZDAyIiwicm9sZSI6Iue7j-eQhiIsIm5hbWUiOiLmnY7lm5siLCJ1c2VySWQiOjEwMDF9.KvcFs2eYZzE34rZvoR7hN8MhyFpeCPVRCKhZMU3B1sk";
        String key = "95dff70708e84ec8a329842839c11eb4";
        //创建SeceretKey
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        //解析jwt ， 没有异常，解析成功
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(jwt);

        //读数据
        Claims body = claims.getBody();
        Integer userId = body.get("userId", Integer.class);
        System.out.println("userId="+userId);
        Object uid = body.get("userId");
        System.out.println("uid="+uid);

        Object name = body.get("name");
        if( name != null ){
            String str= (String)name;
            System.out.println("str = " + str);
        }

        String jwtId = body.getId();
        System.out.println("jwtId="+jwtId);

        Date expiration = body.getExpiration();
        System.out.println("过期时间："+expiration);


    }
}
