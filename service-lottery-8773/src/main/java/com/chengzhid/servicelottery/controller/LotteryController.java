package com.chengzhid.servicelottery.controller;

import com.chengzhid.common.Result;
import com.chengzhid.entity.Prize;
import com.chengzhid.entity.UserLoginInfo;
import com.chengzhid.servicelottery.feign.LotteryUserFeign;
import com.chengzhid.servicelottery.service.LotteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chengzhid
 * @date 2023年09月26日 10:16:40
 * @packageName com.chengzhid.servicelottery.controller
 * @className LotteryController
 * @describe
 */
@RestController
@Slf4j
@CrossOrigin
public class LotteryController {

    @Resource
    LotteryUserFeign lotteryUserFeign;

    @Autowired
    LotteryService lotteryService;

//    @GetMapping("/getLottery")
    @PostMapping("/getLottery")
    public Result work(String userId){
        System.out.println("enter controller");
//        long userIdNum = Long.parseLong(userId);
//        System.out.println("userIdNum: " + userIdNum);
        UserLoginInfo userLoginInfo = lotteryUserFeign.getUser(userId);
        if (userLoginInfo.getUserId() == -1){
            return Result.error("服务熔断，请稍后再来");
        }
        System.out.println("!!!userLoginInfo: " + userLoginInfo);

        //先查一查这个用户有没有抽过奖
        boolean flagAlready = lotteryService.checkAlreadyWin(userLoginInfo.getUserId());
        if (flagAlready == true){
            //参与过抽奖
            return Result.error("您已经参与过抽奖，请不要重复参与");
        } else {
            //未参与过抽奖
            long winPrize = lotteryService.winPrize(userLoginInfo);
            System.out.println("winPrize: " + winPrize + " !!!");
            if (winPrize == 0){
                //没有奖被抽到    所有奖品均被抽光
                return Result.error("所有奖品均被抽光，请下次再来吧");
            } else {
                //抽到奖
                Prize prize = lotteryService.getPrize(winPrize);
                return Result.success("恭喜您抽到" + prize.getPrizeGrade() + "等奖:" + prize.getPrizeName() + "!");
            }
        }

    }

    @PostMapping("/getMyHistoryPrize")
    public Result getMyHistoryPrize(String userId){
        long historyPrize = lotteryService.getHistoryPrize(Long.parseLong(userId));
        System.out.println("historyPrizeNum: " + historyPrize);
        if (historyPrize == 0){
            return Result.success("无");
        } else {
            Prize prize = lotteryService.getPrize(historyPrize);
            return Result.success("曾获" + prize.getPrizeGrade() + "等奖品：" + prize.getPrizeName());
        }
    }

    @GetMapping("/getAllPrizeInfo")
    public Result getAllPrizeInfo(){
        List<Prize> allPrize = lotteryService.getAllPrize();
        System.out.println(allPrize);
        return Result.success(allPrize);
    }

}
