package com.chengzhid.servicelottery.service;

import com.chengzhid.entity.Prize;
import com.chengzhid.entity.UserLoginInfo;

import java.util.List;

/**
 * @author chengzhid
 * @date 2023年09月26日 10:33:19
 * @packageName com.chengzhid.servicelottery.service
 * @className LotteryService
 * @describe
 */
public interface LotteryService {

    /**
     * 获取一个奖品号，作为抽的的奖品
     * 用随机数对奖品种类数取模
     * @return
     */
    public long winPrize(UserLoginInfo userLoginInfo);

    /**
     * 获得奖品信息
     * @return
     */
    public Prize getPrize(long prizeId);

    /**
     * 获取所有的奖品信息
     * @return
     */
    public List<Prize> getAllPrize();

    /**
     * 查一查这个用户是否已经参与过抽奖
     * 参与过返回true
     * 没参与过返回false
     * @return
     */
    public boolean checkAlreadyWin(long userId);

    /**
     * 根据用户id在winner_info中查询用户历史记录中的得奖信息
     * 如果存在则返回礼物的prize_id
     * @param userId
     * @return
     */
    public long getHistoryPrize(long userId);

}
