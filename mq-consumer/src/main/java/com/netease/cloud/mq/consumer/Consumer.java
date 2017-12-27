package com.netease.cloud.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;

public class Consumer {
    private static  String ADDRESS="10.240.193.90:9876";
    /*
     * Constructs a client instance with your account for accessing DefaultMQConsumer
     */
    private static DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private static int initialState = 0;

    private Consumer() {

    }

    public static DefaultMQPushConsumer getDefaultMQPushConsumer(){
        if(consumer == null){
            consumer = new DefaultMQPushConsumer("ConsumerGroupName");
        }

        if(initialState == 0){
            consumer.setNamesrvAddr(ADDRESS);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            initialState = 1;
        }

        return consumer;
    }

}
