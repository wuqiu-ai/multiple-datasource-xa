package com.fly.service;

import com.fly.common.OperationResponse;
import com.fly.common.order.PlaceOrderRequestVO;

/**
 * @author: peijiepang
 * @date 2021/4/21
 * @Description:
 */
public interface JtaService {

     OperationResponse normalPlaceOrder(String type, PlaceOrderRequestVO placeOrderRequestVO) throws Exception;
}
