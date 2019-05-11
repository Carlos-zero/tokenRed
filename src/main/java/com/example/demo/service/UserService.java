package com.example.demo.service;

import com.example.demo.bean.User;

import java.util.Map;

/**
 * @author 94801
 **/
public interface UserService {
    /**
     *      用户注册
     * @param userName
     * @param password
     * @param email
     * @param telephone
     * @return
     **/

    boolean register(String userName,String password,String email,long telephone);

   /**
     *      用户登录
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);

    /**
     *      获取用户信息
     * @param role
     * @param id
     * @return
     */
    Map getInfo(String role,String id);

}
