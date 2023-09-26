package com.chengzhid.servicelottery.mapper;

import com.chengzhid.entity.Prize;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author chengzhid
 * @date 2023年09月26日 10:28:24
 * @packageName com.chengzhid.servicelottery.mapper
 * @className LotteryMapper
 * @describe
 */
@Mapper
public interface LotteryMapper {

    @Select("select * from prize_info where prize_id = #{prizeId}")
    public Prize getPrize(@Param("prizeId") long prizeId);

    @Update("update prize_info set prize_amount = prize_amount-1 where prize_id = #{prizeId}")
    public void deductPrize(@Param("prizeId") long prizeId);

    @Select("select count(*) as total from prize_info")
    public long getTotalNumOfPrizeVarieties();

    @Select("select * from prize_info")
    public List<Prize> getAllPrize();

    @Insert("insert into winner_info (user_id, prize_id) values(#{userId}, #{prizeId})")
    public void insertUserPrize(@Param("userId") long userId,
                                @Param("prizeId") long prizeId);

    @Select("select count(*) as total from winner_info where user_id = #{userId}")
    public long getRecordNumByUserId(@Param("userId") long userId);

    @Select("select prize_id from winner_info where user_id = #{userId}")
    public long getHistoryPrize(@Param("userId") long userId);

}
