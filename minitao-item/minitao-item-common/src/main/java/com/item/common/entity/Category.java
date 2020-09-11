package com.item.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("tao_category")
public class Category {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String name;
  private Long parentId;
  private int level;


}
