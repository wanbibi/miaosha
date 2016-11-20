package org.dao;

import org.apache.ibatis.annotations.Param;
import org.entity.Seckill;
import org.entity.SuccessKilled;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 16.11.16.
 */
@Repository
public interface SuccessKilledDAO {
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    List<SuccessKilled> queryByIdWithSeckill(long seckillId);
}
