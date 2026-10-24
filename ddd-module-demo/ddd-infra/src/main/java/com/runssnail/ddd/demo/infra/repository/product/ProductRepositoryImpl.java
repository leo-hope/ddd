package com.runssnail.ddd.demo.infra.repository.product;

import java.util.List;
import java.util.UUID;

import com.runssnail.ddd.common.exception.ConcurrencyConflicts;
import com.runssnail.ddd.demo.domain.valueobject.product.ProductId;
import com.runssnail.ddd.demo.infra.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.runssnail.ddd.common.result.PagingResult;
import com.runssnail.ddd.demo.infra.dataobject.ProductDO;
import com.runssnail.ddd.demo.domain.model.product.Product;
import com.runssnail.ddd.demo.domain.repository.product.ProductQuery;
import com.runssnail.ddd.demo.domain.repository.product.ProductRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengwei
 * @date 2019-11-05 15:14
 **/
@Repository
@Slf4j
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductConverter productConverter;

    // 测试的
    private ProductMapper productMapper = new ProductMapper() {
        @Override
        public int removeProduct(String productId, String operator, int concurrencyVersion) {
            return 1;
        }
    };

    @Override
    public void save(Product product) {

        ProductDO productDO = productConverter.serialize(product);

        // todo 调用mybatis mapper保存数据对象
        log.info("save domain, id={}", product.getProductId());
    }

    @Override
    public Product selectById(String id) {

        // todo 调用mybatis mapper查询数据对象
        Product product = new Product(id);
        return product.name("test").description("test description");
    }

    @Override
    public void update(Product product) {

        ProductDO productDO = productConverter.serialize(product);

        // todo 调用mybatis mapper修改数据对象
        log.info("update domain, id={}", product.getProductId());
    }

    @Override
    public ProductId nextId() {
        return ProductId.create(UUID.randomUUID().toString());
    }

    @Override
    public void deactivate(Product product) {
        // todo 调用mybatis mapper修改数据对象
        log.info("deactivate domain, id={}", product.getProductId());
    }

    @Override
    public void activate(Product product) {
        log.info("activate domain, id={}", product.getProductId());
    }

    @Override
    public void remove(Product product) {
        log.info("remove domain, id={}", product.getProductId());
        ConcurrencyConflicts.check(() -> productMapper.removeProduct(product.getProductId(), product.getOperator(), product.getConcurrencyVersion()));
    }

    @Override
    public PagingResult<Product> queryProducts(ProductQuery query) {
        log.info("query products, query={}", query);

        Product product = new Product("1", "test", "test description");
        List<Product> products = Lists.newArrayList(product);
        return PagingResult.create(products, 1, 1);
    }
}
