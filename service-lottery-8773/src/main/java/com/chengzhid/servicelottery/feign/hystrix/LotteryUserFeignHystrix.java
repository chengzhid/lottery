package com.chengzhid.servicelottery.feign.hystrix;

import com.chengzhid.entity.UserLoginInfo;
import com.chengzhid.servicelottery.feign.LotteryUserFeign;
import org.springframework.stereotype.Component;

/**
 * @author chengzhid
 * @date 2023年09月26日 18:06:16
 * @packageName com.chengzhid.servicelottery.feign.hystrix
 * @className LotteryUserFeignHystrix
 * @describe
 */
@Component
public class LotteryUserFeignHystrix implements LotteryUserFeign {
    @Override
    public UserLoginInfo getUser(String userId) {
        System.out.println("hystrix熔断！");
        UserLoginInfo userLoginInfo = new UserLoginInfo(-1, "error", "error");
        return userLoginInfo;
    }
}
