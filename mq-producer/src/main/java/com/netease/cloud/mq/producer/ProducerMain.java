package com.netease.cloud.mq.producer;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class ProducerMain {
    public static void main(String[] args) {
        senMsg();
    }

    private static void senMsg() {
        // 获取消息生产者
        DefaultMQProducer producer = Producer.getDefaultMQProducer();

        try {
            for(int i=0;i<15;i++){
                Message msg = new Message(
                        "TopicTest1",                   // topic
                        "TagA",                         // tag
                        "OrderID00"+i,                  // key
                        ("Hello MetaQ"+i).getBytes());  // body
                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            }
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemotingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MQBrokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        producer.shutdown();
    }

}
