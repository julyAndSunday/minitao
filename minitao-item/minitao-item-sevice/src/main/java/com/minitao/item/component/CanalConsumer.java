package com.minitao.item.component;

import com.alibaba.fastjson.JSONObject;
import com.minitao.common.utils.RedisClient;
import com.minitao.item.entity.CanalBean;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CanalConsumer {
    //日志记录
    private static Logger log = LoggerFactory.getLogger(CanalConsumer.class);
    //redis操作工具类
    @Autowired
    private RedisClient redisClient;

    @KafkaListener(topicPartitions = {@TopicPartition(topic = "canaltopic", partitions = {"0"})}, groupId = "house-consumer-group")
    public void partition1(ConsumerRecord<?, ?> record) {
        receive(record);
    }

    @KafkaListener(topicPartitions = {@TopicPartition(topic = "canaltopic", partitions = {"1"})}, groupId = "house-consumer-group")
    public void partition2(ConsumerRecord<?, ?> record) {
        receive(record);
    }

    @KafkaListener(topicPartitions = {@TopicPartition(topic = "canaltopic", partitions = {"2"})}, groupId = "house-consumer-group")
    public void partition3(ConsumerRecord<?, ?> record) {
        receive(record);
    }

    private void receive(ConsumerRecord<?, ?> record) {
        final String value = (String) record.value();
        System.out.println(value);
        CanalBean canalBean = JSONObject.parseObject(value, CanalBean.class);
        Map<String, String> data = canalBean.getData();
        String id = data.get("id");
        String jsonString = JSONObject.toJSONString(data);
        log.info("分区：" + record.partition() + "接收到操作类别：" + canalBean.getType());
        redisClient.setString(id, jsonString);
    }
}
