package com.gushiyu.market.dao;

import com.gushiyu.market.pojo.ProductPo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductDao {
    @Select("SELECT id, product_name, stock, price, version, note FROM t_product WHERE id=#{id}")
    ProductPo getProduct(@Param("id") Long id);

    @Select("SELECT id, product_name, stock, price, version, note FROM t_product WHERE product_name=#{productName}")
    ProductPo getProductByName(@Param("productName") String productName);

    @Update("UPDATE t_product SET stock = stock - #{quantity}, version = version + 1" +
            "WHERE id = #{id} AND version = #{version}")
    int decreaseProduct(@Param("id") Long id, @Param("quantity") int quantity, @Param("version") int version);

    @Insert("INSERT INTO t_product(product_name, stock, price)" +
            "VALUES(#{productName}, #{stock}, #{price})")
    void insertProduct(ProductPo p);

}
