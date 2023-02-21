package com.fly.dao.product;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fly.common.storage.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author HelloWood
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {

    @Update("update product set stock = stock - #{count} where id = #{productId} and stock >= #{count}")
    int reduce(@Param("productId") Long productId,@Param("count") Integer count);

}
