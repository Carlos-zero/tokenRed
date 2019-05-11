package com.example.demo.controller;
import com.example.demo.bean.User;
import com.example.demo.jwt.CreateCode;
import com.example.demo.jwt.ParseToken;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 94801
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    User user;
    @Autowired
    CreateCode createCode;
    @Autowired
    ParseToken parseToken;
    @PostMapping(value = "/register")
    public String register(String userName,String password,String email,long telephone){
        if (userService.register(userName,password,email,telephone)){
            return "注册成功！";
        }else {
            return "注册失败！";
        }
    }

    @PostMapping(value = "/login")
    public Map login(String userName, String password, HttpServletRequest request){
        Map<String,Object> codeAndUri=new HashMap<>();
        user=userService.login(userName,password);
        if (user!=null){
//            System.out.println("------login---controller"+user.getId());
            String token=createCode.createCode(String.valueOf(user.getId()));
            String redirectUri="?????";
            codeAndUri.put("token",token);
            codeAndUri.put("redirectUri",redirectUri);
            return codeAndUri;
        }else {
            codeAndUri.put("error","loginFailure");
            return codeAndUri;
        }
    }

    //通过code拉取用户信息
    @PostMapping(value = "/redirect_uri")
    public Map<String,String> getInfo(String code){
        Map<String, String> codeMap=parseToken.parse(code);
//        System.out.println(codeMap.get("id")+"---getInfo----controller----"+codeMap.get("role"));
        Map<String,String> userInfoMap=userService.getInfo(codeMap.get("role"),codeMap.get("id"));
        return userInfoMap;
    }
}
