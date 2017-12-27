package com.netease.cloud.mq.consumer;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

public class ConsumerMain {
    public static void main(String[] args) {
        receiveMsg();
    }

    private static void receiveMsg() {
        // 获取消息生产者
        DefaultMQPushConsumer consumer = Consumer.getDefaultMQPushConsumer();
consumer.setConsumeMessageBatchMaxSize(15);
        // 订阅主体
        try {
            consumer.subscribe("TopicTest1", "*");

            consumer.registerMessageListener(new MessageListenerConcurrently() {

                /**
                 * * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息
                 */
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

//                    System.out.println("currentThreadName:{}"+Thread.currentThread().getName()+" and Receive New Messages:{}"+msgs);
                    MessageExt msg = msgs.get(0);
                    if (msg.getTopic().equals("TopicTest1")) {
                        // 执行TopicTest1的消费逻辑
                        if (msg.getTags() != null && msg.getTags().equals("TagA")) {
                            // 执行TagA的消费
                            System.out.println(new String(msg.getBody()));
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagC")) {
                            // 执行TagC的消费
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagD")) {
                            // 执行TagD的消费
                        }
                    } else if (msg.getTopic().equals("TopicTest2")) {
                        // 执行TopicTest2的消费逻辑
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /**
             * Consumer对象在使用之前必须要调用start初始化，初始化一次即可<br>
             */
            consumer.start();

            System.out.println("Consumer Started.");
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
