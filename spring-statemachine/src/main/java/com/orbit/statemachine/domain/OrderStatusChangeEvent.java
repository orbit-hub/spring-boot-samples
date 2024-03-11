package com.orbit.statemachine.domain;

/**
 * @description:订单状态转换行为
 */
public enum OrderStatusChangeEvent {
    //支付
    PAYED,
    //发货
    DELIVERY,
    //收货
    RECEIVED;
}

