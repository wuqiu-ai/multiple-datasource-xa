package com.fly.service.impl;

import com.fly.common.OperationResponse;
import com.fly.common.order.Order;
import com.fly.common.order.PlaceOrderRequestVO;
import com.fly.dao.order.OrderDao;
import com.fly.dao.product.ProductDao;
import com.fly.service.JtaService;
import com.fly.service.OrderService;
import com.fly.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: peijiepang
 * @date 2021/4/21
 * @Description:
 */
@Service
@Slf4j
public class JtaServiceImpl implements JtaService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Transactional(value="transactionManager")
    @Override
    public OperationResponse normalPlaceOrder(String type,
        PlaceOrderRequestVO placeOrderRequestVO) throws Exception{
        log.info("=============ORDER=================");
        Integer price = placeOrderRequestVO.getPrice();
        Integer amount = 1;
        // 扣减库存
        boolean operationStorageResult = productDao.reduce(placeOrderRequestVO.getProductId(), amount) > 0 ? true:false;
        if( !operationStorageResult ){
            throw new RuntimeException("下单失败");
        }
        if(operationStorageResult){
            Order order = Order.builder()
                .userId(placeOrderRequestVO.getUserId())
                .productId(placeOrderRequestVO.getProductId())
                .money(price)
                .count(1)
                .build();
            orderDao.insertOrder(order);
            return OperationResponse.builder().success(operationStorageResult).build();
        }
        return OperationResponse.builder().success(false).build();    }
}
