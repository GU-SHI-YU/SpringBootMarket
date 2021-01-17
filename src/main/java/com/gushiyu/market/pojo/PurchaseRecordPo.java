package com.gushiyu.market.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Alias("purchaseRecord")
public class PurchaseRecordPo implements Serializable {
    @Serial
    private static final long serialVersionUID = -6072430715597881034L;
    private Long id;
    private Long userId;
    private Long productId;
    private double price;
    private int quantity;
    private double sum;
    private Timestamp purchaseTime;
    private String note;

    public PurchaseRecordPo(Long userId, Long productId, double price, int quantity)
    {
        this.userId = userId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.sum = this.price * this.quantity;

    }

}
