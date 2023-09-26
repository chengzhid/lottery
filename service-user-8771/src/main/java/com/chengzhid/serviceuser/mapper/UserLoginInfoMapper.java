package com.chengzhid.serviceuser.mapper;

import com.chengzhid.entity.UserLoginInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author chengzhid
 * @date 2023年09月23日 15:40:12
 * @packageName com.chengzhid.serviceuser.mapper
 * @className UserLoginInfoController
 * @describe
 */
@Mapper
//@Repository
public interface UserLoginInfoMapper {

    /**
     * 通过账号和密码查询用户登录信息
     * 为登录服务提供用户查询功能
     * @return
     */
    @Select("select * from user_login_info where user_account = #{account} and user_password = #{password}")
    public UserLoginInfo getUserLoginInfoByAccountAndPassword(@Param("account") String account, @Param("password") String password);


    /**
     * 通过账号查询用户
     * 为注册提供注册前用户名是否已存在的查询
     * @param account
     * @return
     */
    @Select("select * from user_login_info where user_account = #{account}")
    public UserLoginInfo getUserLoginInfoByAccount(@Param("account") String account);

    /**
     * 插入用户登录信息，创建新用户
     * @param account
     * @param password
     */
    @Insert("insert into user_login_info(user_account, user_password) values(#{account}, #{password})")
    public void insertUserLoginInfo(@Param("account") String account,@Param("password") String password);

    /**
     * 在注册用户的同时在用户基本信息表中创建对应的用户基本信息
     * @param userId
     */
    @Insert("insert into user_basic_info(user_id) values(#{userId})")
    public void insertUserBasicInfo(@Param("userId") long userId);

    @Select("select * from user_login_info where user_id = #{userId}")
    public UserLoginInfo getUserLoginInfoByUserId(@Param("userId") long userId);

}
