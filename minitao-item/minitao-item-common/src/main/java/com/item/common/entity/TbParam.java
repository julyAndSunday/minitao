package com.item.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: july
 * @Date: 2020-06-18 16:17
 **/
@TableName("tao_param")
@Data
public class TbParam {
    @TableId(type = IdType.AUTO)
    private Long id;
    //参数名
    private String name;
    //是否作为搜索
    private boolean searching;
    //是否多选
    private boolean isSelect;
    //参数值
    private String value;
    //手动输入
    private boolean isHand;

}
