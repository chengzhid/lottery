package com.chengzhid.servicelottery.service.impl;

import com.chengzhid.entity.Prize;
import com.chengzhid.entity.UserLoginInfo;
import com.chengzhid.servicelottery.mapper.LotteryMapper;
import com.chengzhid.servicelottery.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author chengzhid
 * @date 2023年09月26日 10:33:33
 * @packageName com.chengzhid.servicelottery.service.impl
 * @className LotteryServiceImpl
 * @describe
 */
@Service
public class LotteryServiceImpl implements LotteryService {

    @Autowired
    LotteryMapper lotteryMapper;

    @Override
    public long winPrize(UserLoginInfo userLoginInfo){
        System.out.println("enter service");
        long totalNumOfPrizeVarieties = lotteryMapper.getTotalNumOfPrizeVarieties();
        Boolean[] flag = new Boolean[10001];
        for (int i = 1; i < 10000; i++){
            flag[i] = false;
            //System.out.println(flag[i]);
        }
        Boolean tempFlag = true;//true表示还有奖品未被尝试过，false表示所有奖品均被尝试
        while (tempFlag == true){
            long timestamp = System.currentTimeMillis();
            Random random = new Random(timestamp);
            long randomNumber = random.nextInt((int) totalNumOfPrizeVarieties);
            randomNumber+=1;
            System.out.println("randomNumber:" + randomNumber);
            Prize prize = lotteryMapper.getPrize(randomNumber);
            if (prize.getPrizeAmount() >= 1){
                //抽奖成功
                System.out.println("prizeId: " + prize.getPrizeId() + " deduct");
                lotteryMapper.deductPrize(randomNumber);                                //根据奖品id将对应奖品数减一
                lotteryMapper.insertUserPrize(userLoginInfo.getUserId(), randomNumber); //添加获奖记录
                return randomNumber;
            }
            //此次抽奖失败 意味着这个奖品已经没有了
            //检查是否所有奖品均被尝试过
            flag[(int) randomNumber] = true;
            tempFlag = false;
            for (int i = 1; i <= totalNumOfPrizeVarieties; i++){
                if (flag[i] == false){
                    //有奖品未被尝试时tempFlag = true;
                    tempFlag = true;
                }
            }
            if (tempFlag == false){
                //所有奖品均被尝试
                return 0;
            } else {
                //有奖品未被尝试
            }
        }
        return 0;
    }

    @Override
    public Prize getPrize(long prizeId) {
        return lotteryMapper.getPrize(prizeId);
    }

    @Override
    public List<Prize> getAllPrize() {
        return lotteryMapper.getAllPrize();
    }

    @Override
    public boolean checkAlreadyWin(long userId) {
        long num = lotteryMapper.getRecordNumByUserId(userId);
        System.out.println("RecordNumByUser: " + num);
        if (num > 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getHistoryPrize(long userId) {
        return lotteryMapper.getHistoryPrize(userId);
    }
}
