package com.chengzhid.serviceuser.service.impl;

import com.chengzhid.entity.UserLoginInfo;
import com.chengzhid.serviceuser.mapper.UserLoginInfoMapper;
import com.chengzhid.serviceuser.service.UserManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chengzhid
 * @date 2023年09月23日 15:43:02
 * @packageName com.chengzhid.serviceuser.service.impl
 * @className UserLoginServiceImpl
 * @describe
 */
@Slf4j
@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserLoginInfoMapper userLoginInfoMapper;

    @Override
    public UserLoginInfo login(String userAccount, String userPassword) {
        UserLoginInfo userLoginInfo = userLoginInfoMapper.getUserLoginInfoByAccountAndPassword(userAccount, userPassword);
        if (userLoginInfo != null){
            return userLoginInfo;
        }else {
            return null;
        }
    }

    @Override
    public Boolean checkBeforeRegister(String userAccount) {
        UserLoginInfo userLoginInfo = userLoginInfoMapper.getUserLoginInfoByAccount(userAccount);
        if (userLoginInfo == null){
            return true;    //如果没有 返回true
        } else{
            return false;   //如果有 返回false
        }
    }

    @Override
    public Boolean register(String userAccount, String userPassword) {
        System.out.println("serviceImpl:");
        System.out.println("account:" + userAccount);
        System.out.println("password:" + userPassword);


        userLoginInfoMapper.insertUserLoginInfo(userAccount, userPassword);
        UserLoginInfo userLoginInfo = userLoginInfoMapper.getUserLoginInfoByAccount(userAccount);
        if (userLoginInfo != null){
            userLoginInfoMapper.insertUserBasicInfo(userLoginInfo.getUserId());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserLoginInfo getUserLoginInfo(long userId) {
        return userLoginInfoMapper.getUserLoginInfoByUserId(userId);
    }
}
