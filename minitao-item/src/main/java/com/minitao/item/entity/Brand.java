package com.minitao.item.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("tao_brand")
public class Brand {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String image;
    private Date createTime;


}
