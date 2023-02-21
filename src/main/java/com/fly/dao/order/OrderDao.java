package com.fly.dao.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fly.common.order.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HelloWood
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {

    /**
     * 插入订单
     * @param order
     */
    @Insert({"INSERT INTO `orders`(`product_id`, `sum`) VALUES (#{productId},#{count})"})
    int insertOrder(Order order);



}
