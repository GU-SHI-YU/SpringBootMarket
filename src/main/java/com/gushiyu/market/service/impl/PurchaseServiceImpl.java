package com.gushiyu.market.service.impl;

import com.gushiyu.market.dao.ProductDao;
import com.gushiyu.market.dao.PurchaseRecordDao;
import com.gushiyu.market.pojo.ProductPo;
import com.gushiyu.market.pojo.PurchaseRecordPo;
import com.gushiyu.market.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    final private ProductDao productDao;
    final private PurchaseRecordDao purchaseRecordDao;

    @Autowired
    public PurchaseServiceImpl(ProductDao productDao, PurchaseRecordDao purchaseRecordDao) {
        this.productDao = productDao;
        this.purchaseRecordDao = purchaseRecordDao;
    }

    @Override
    @Transactional
    public boolean purchase(Long userId, Long productId, int quantity) {
        for (int i = 0; i < 3; i++) {
            /* 最多重试三次 */
            /* 获取产品信息 */
            ProductPo product = productDao.getProduct(productId);
            if (product.getStock() < quantity) {
                /* 库存不足 */
                return false;
            }
            /* 获取当前版本号 */
            int version = product.getVersion();
            /* 减少库存，同时比较版本号 */
            int result = productDao.decreaseProduct(productId, quantity, version);
            if (result == 0) {
                /* 更新数据失败， 重新尝试 */
                continue;
            }
            /* 生成购买记录并插入 */
            PurchaseRecordPo pr = this.initPurchaseRecord(userId, product, quantity);
            purchaseRecordDao.insertPurchaseRecord(pr);
            return true;
        }
        return false;
    }

    private PurchaseRecordPo initPurchaseRecord(Long userId, ProductPo product, int quantity) {
        PurchaseRecordPo pr = new PurchaseRecordPo(userId, product.getId(), product.getPrice(), quantity);
        pr.setNote("购买日志，时间：" + System.currentTimeMillis());
        return pr;
    }
}
