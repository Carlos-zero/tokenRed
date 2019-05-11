package com.example.demo.service;

import com.example.demo.bean.User;
import com.example.demo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 94801
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private User user;
    @Autowired
    private UserDao userDao;
    @Override
    public boolean register(String userName, String password, String email, long telephone) {
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setTelephone(telephone);
        boolean yes=false;
        yes=userDao.register(user);
        return yes;
    }

    @Override
    public User login(String userName, String password) {
        user=userDao.login(userName,password);
        return user;
    }

    @Override
    public Map getInfo(String role, String id) {
        user=userDao.userInfo(id);
        System.out.println(user.getId());
        String userName= user.getUserName();
        String password= user.getPassword();
        String email= user.getEmail();
        String telephone= String.valueOf(user.getTelephone());
        Map<String,String> userInfo=new HashMap<>();
        if ("visitor".equals(role)){
            userInfo.put("userName",userName);
        }else if ("member".equals(role)){
            userInfo.put("userName",userName);
            userInfo.put("email",email);
            userInfo.put("telephone",telephone);
        }else if ("admin".equals(role)){
            userInfo.put("userName",userName);
            userInfo.put("email",email);
            userInfo.put("telephone",telephone);
            userInfo.put("id",id);
            userInfo.put("password",password);
        }else {
            return null;
        }
        return userInfo;
    }
}
