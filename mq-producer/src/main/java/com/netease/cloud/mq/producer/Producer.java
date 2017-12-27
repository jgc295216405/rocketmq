package com.netease.cloud.mq.producer;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;

public class Producer {
    private static  String ADDRESS="10.240.193.90:9876";
    /*
    * Constructs a client instance with your account for accessing DefaultMQProducer
    */
    private static DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
    private static int initialState = 0;

    private Producer() {

    }

    public static DefaultMQProducer getDefaultMQProducer(){
        if(producer == null){
            producer = new DefaultMQProducer("ProducerGroupName");
        }

        if(initialState == 0){
            producer.setNamesrvAddr(ADDRESS);
            try {
                producer.start();
            } catch (MQClientException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            initialState = 1;
        }
        return producer;
    }
}
