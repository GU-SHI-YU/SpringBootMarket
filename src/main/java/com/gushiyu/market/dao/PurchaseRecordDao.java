package com.gushiyu.market.dao;

import com.gushiyu.market.pojo.PurchaseRecordPo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseRecordDao {
    @Insert("INSERT INTO t_purchase_record(user_id, product_id, price, quantity, sum, purchase_date, note)" +
            "VALUES(#{userId}, #{productId}, #{price}, #{quantity}, #{sum}, NOW(), #{note})")
    void insertPurchaseRecord(PurchaseRecordPo pr);

}
