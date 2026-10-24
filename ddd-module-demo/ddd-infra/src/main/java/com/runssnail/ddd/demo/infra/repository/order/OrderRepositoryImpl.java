package com.runssnail.ddd.demo.infra.repository.order;

import com.runssnail.ddd.demo.domain.model.order.Order;
import com.runssnail.ddd.demo.domain.repository.order.OrderRepository;
import com.runssnail.ddd.demo.domain.valueobject.order.OrderId;
import com.runssnail.ddd.demo.infra.dataobject.OrderDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order getOrder(String orderId) {
        OrderDO orderDO = new OrderDO();
        orderDO.setId("idtest");
        orderDO.setTitle("test");
        orderDO.setSkuId("skuid");
        orderDO.setQuantity(1);

        Order order = new Order(new OrderId(orderDO.getId()));

        BeanUtils.copyProperties(orderDO, order);
        return order;
    }

    @Override
    public void createOrder(Order order) {

        // todo 1 将order转换成orderDO
        OrderDO orderDO = new OrderDO();

        // 2 保存到DB
    }

    @Override
    public void orderPay(Order order) {

    }
}
