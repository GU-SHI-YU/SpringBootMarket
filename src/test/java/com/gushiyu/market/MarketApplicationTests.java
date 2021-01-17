package com.gushiyu.market;

import com.gushiyu.market.dao.ProductDao;
import com.gushiyu.market.dao.PurchaseRecordDao;
import com.gushiyu.market.pojo.ProductPo;
import com.gushiyu.market.pojo.PurchaseRecordPo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class MarketApplicationTests {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private PurchaseRecordDao purchaseRecordDao;

    @Test
    @Rollback
    void daoTest() {
        productDao.insertProduct(new ProductPo("AAA", 1, 2.5));
        ProductPo p = productDao.getProductByName("AAA");
        ProductPo pp = productDao.getProduct(p.getId());
        Assertions.assertEquals(p, pp);
        purchaseRecordDao.insertPurchaseRecord(new PurchaseRecordPo(0L, p.getId(), 2.5, 1));
        Assertions.assertEquals(0, p.getStock());
    }

}
