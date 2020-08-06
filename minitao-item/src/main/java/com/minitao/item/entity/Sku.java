package com.minitao.item.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@TableName("tao_sku")
public class Sku {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long spuId;
    private String title;
    private String images;
    private Long price;
    private String paramJson; //参数键值对
    private Date createTime;// 创建时间
    private Integer stock;// 库存
}