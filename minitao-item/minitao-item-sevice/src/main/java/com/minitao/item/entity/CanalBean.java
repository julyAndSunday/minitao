package com.minitao.item.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Description: 自己构造的binlog映射类 自带的FlatMessage映射的data不好用
 * @Author: July
 * @Date: 2021-10-23 15:38
 **/
@Data
public class CanalBean {
    //数据
    private Map<String,String> data;
    //数据库名称
    private String database;
    private long es;
    //递增，从1开始
    private int id;
    //是否是DDL语句
    private boolean isDdl;
    //UPDATE语句，旧数据
    private String old;
    //主键名称
    private List<String> pkNames;
    //sql语句
    private String sql;
    //表名
    private String table;
    private long ts;
    //(新增)INSERT、(更新)UPDATE、(删除)DELETE、(删除表)ERASE等等
    private String type;
    //getter、setter方法
}
