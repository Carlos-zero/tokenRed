package com.example.demo.dao;

import com.example.demo.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 94801
 */
@Mapper
@Component
public interface UserDao {

    /**
     *      用户注册
     * @param user
     * @return
     */
    @Insert("insert into user set user_name=#{userName},password=#{password},email=#{email},telephone=#{telephone}")
    boolean register(User user);

    /**
     *      用户登录
     * @param userName
     * @param password
     * @return
     */
    @Select("select * from user where user_name=#{userName} and password=#{password}")
    User login(@Param("userName") String userName,@Param("password") String password);

    /**
     *      查询用户信息
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User userInfo(String id);

}
