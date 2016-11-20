package org.dao;

import org.apache.ibatis.annotations.Param;
import org.entity.Seckill;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 16.11.16.
 */
@Repository
public interface SeckillDAO {
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    Seckill queryById(long seckillId);

    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);

}
