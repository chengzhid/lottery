package com.chengzhid.servicelottery.feign;

import com.chengzhid.entity.UserLoginInfo;
import com.chengzhid.servicelottery.feign.hystrix.LotteryUserFeignHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author chengzhid
 * @date 2023年09月26日 15:09:06
 * @packageName com.chengzhid.servicelottery.feign
 * @className UserGetFeign
 * @describe
 */
@FeignClient(value = "user-management", fallback = LotteryUserFeignHystrix.class)
public interface LotteryUserFeign {

    @GetMapping("/getUser/{userId}")
    public UserLoginInfo getUser(@PathVariable("userId") String userId);

}
