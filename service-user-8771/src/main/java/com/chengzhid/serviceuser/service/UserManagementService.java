package com.chengzhid.serviceuser.service;

import com.chengzhid.entity.UserLoginInfo;

/**
 * @author chengzhid
 * @date 2023年09月23日 15:42:14
 * @packageName com.chengzhid.serviceuser.service
 * @className UserLoginService
 * @describe
 */
public interface UserManagementService {

    /**
     * 根据账号和密码登录
     * 判断系统中是否存在该用户
     * 匹配成功返回UserLoginInfo对象 表示登录成功
     *
     * @param userAccount
     * @param userPassword
     */
    public UserLoginInfo login(String userAccount, String userPassword);

    /**
     * 输入用户账号，查询数据库中是否已有账号名相同的用户
     * 如果有返回false 没有返回true
     * @param userAccount
     * @return
     */
    public Boolean checkBeforeRegister(String userAccount);

    /**
     * 输入账号和密码注册用户
     * 直接向系统中创建新的用户
     * @param userAccount
     * @param userPassword
     * @return
     */
    public Boolean register(String userAccount, String userPassword);

    /**
     * 根据UserId查询userLoginInfo
     * @param userId
     * @return
     */
    public UserLoginInfo getUserLoginInfo(long userId);

}
