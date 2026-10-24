package com.runssnail.ddd.demo.infra.acl.stock;

import com.runssnail.ddd.demo.domain.acl.stock.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {

    @Override
    public void lockStock(String orderId, String skuId, Integer skuQuantity) {
        // 调用库存域服务，锁库存
    }
}
