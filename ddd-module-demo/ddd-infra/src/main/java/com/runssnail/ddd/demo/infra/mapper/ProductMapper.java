package com.runssnail.ddd.demo.infra.mapper;

public interface ProductMapper {

    int removeProduct(String productId, String operator, int concurrencyVersion);
}
