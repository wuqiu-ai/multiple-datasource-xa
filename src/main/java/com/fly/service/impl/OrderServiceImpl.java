package com.fly.service.impl;

import com.fly.common.order.Order;
import com.fly.dao.order.OrderDao;
import com.fly.service.OrderService;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author HelloWoodes
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Transactional
    @Override
    public int insertOrder(Order order) {
        return orderDao.insertOrder(order);
    }
}
