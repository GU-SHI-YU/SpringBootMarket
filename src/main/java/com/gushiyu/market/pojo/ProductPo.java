package com.gushiyu.market.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;

@Alias("product")
@Data
@NoArgsConstructor
public class ProductPo implements Serializable {
    @Serial
    private static final long serialVersionUID = -2284832824879700137L;
    private Long id;
    private String productName;
    private int stock;
    private double price;
    private int version;
    private String note;

    public ProductPo(String productName, int stock, double price)
    {
        this.productName = productName;
        this.stock = stock;
        this.price = price;
    }

}
