package com.chengzhid.serviceuser.controller;

import com.chengzhid.common.Result;
import com.chengzhid.entity.UserLoginInfo;

import com.chengzhid.serviceuser.service.UserManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author chengzhid
 * @date 2023年09月20日 09:48:50
 * @packageName com.chengzhid.serviceuser.controller
 * @className LoginController
 * @describe
 */
@RestController
@Slf4j
@CrossOrigin
public class LoginController {

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Autowired
    public UserManagementService userManagementService;

    /**
     * 登录 并存储token
     * @param account
     * @param pwd
     * @return
     */
    @ResponseBody
    @PostMapping("/userManagement/doLogin")
    public Result doLogin(String account, String pwd){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); System.out.println(sdf.format(System.currentTimeMillis()));
        System.out.println("我是userService2 请求时间为:" + sdf);

//        String account = userLoginInfo1.getUserAccount();
//        String pwd = userLoginInfo1.getUserPassword();
        System.out.println("login request:" + account + " " + pwd);
        System.out.println(account);
        System.out.println(pwd);
        UserLoginInfo userLoginInfo = userManagementService.login(account, pwd);
        if (userLoginInfo != null){ //登录成功

            //token
//            String token = UUID.randomUUID().toString();
            System.out.println("account:" + account);
//            System.out.println("token:" + token);
            System.out.println(" ");
//            存token
//            redisTemplate.opsForValue().set(token, userLoginInfo.toString(), Duration.ofSeconds(7200));
            return Result.success(userLoginInfo);
//            return "登录成功";
        } else {    //登录失败
            Boolean checkAccount = userManagementService.checkBeforeRegister(account);
            if (checkAccount == false){
                return Result.error("密码不匹配");
            } else {    //用户不存在
                return Result.error("用户不存在");
            }
            //return null;
        }


    }

    /**
     * 注册
     */
    @ResponseBody
    @PostMapping("/userManagement/register")
    public Result doRegister(String account, String pwd, String confirmPwd){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); System.out.println(sdf.format(System.currentTimeMillis()));
        System.out.println("我是userService2 请求时间为:" + sdf);
        System.out.println("register request:" + account + " " + pwd + " " + confirmPwd);
        if (account.isEmpty() || pwd.isEmpty() || confirmPwd.isEmpty()){
            return Result.error("输入框不要空着");
        }
        if (!pwd.equals(confirmPwd)){
            return Result.error("两次输入的密码不匹配");
        }
        Boolean flag = userManagementService.checkBeforeRegister(account);
        System.out.println("flag: " + flag);
        if (flag == true){  //不存在用户
            Boolean registerFlag = userManagementService.register(account, pwd);
            if (registerFlag == true){  //注册成功
                return Result.success();
            } else {    //注册失败
                return Result.error("注册失败");
            }
        } else {    //该用户账号已存在 注册失败
            return Result.error("该用户账号已存在，注册失败");
        }

    }

    @GetMapping("/getUser/{userId}")
    public UserLoginInfo getUser(@PathVariable("userId") String userId){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); System.out.println(sdf.format(System.currentTimeMillis()));
        System.out.println("我是userService2 请求时间为:" + sdf);
        return userManagementService.getUserLoginInfo(Long.parseLong(userId));
    }

}
