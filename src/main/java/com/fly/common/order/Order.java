package com.fly.common.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author HelloWoodes
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order")
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long userId;

    private Long productId;

    private Integer count;

    private Integer money;

//    private Date createTime;
//
//    private Date modifyTime;
}
