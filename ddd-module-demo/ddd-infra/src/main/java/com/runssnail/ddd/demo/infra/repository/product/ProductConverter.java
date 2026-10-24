package com.runssnail.ddd.demo.infra.repository.product;

import com.runssnail.ddd.converter.Converter;
import com.runssnail.ddd.demo.infra.dataobject.ProductDO;
import com.runssnail.ddd.demo.domain.model.product.Product;

import org.springframework.stereotype.Component;

/**
 * @author zhengwei
 * @date 2019-11-05 15:18
 **/
@Component
public class ProductConverter implements Converter<Product, ProductDO> {

    @Override
    public ProductDO serialize(Product product) {

        // todo 领域对象转换成数据对象
        ProductDO productDO = new ProductDO();
        productDO.setId(product.getProductId());
        return productDO;
    }

    @Override
    public Product deserialize(ProductDO productDO) {

        // todo 数据对象转换成领域对象
        return new Product(productDO.getId());
    }
}
