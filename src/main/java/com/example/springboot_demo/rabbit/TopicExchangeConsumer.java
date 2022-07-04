package com.example.springboot_demo.rabbit;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicExchangeConsumer {

    @RabbitListener(queues = "fan.test")
    @RabbitHandler
    public void getQueue2Msg(String data, Channel channel, Message message){
        try{
            System.out.println("========"+data);
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
            channel.basicAck(deliveryTag, false);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "boot-queue")
    @RabbitHandler
    public void getQueueMsg(String data, Channel channel, Message message){
        // 因为在消费的过程中可能会出现异常，所以把所有的业务代码都要用try包起来
        try{
            System.out.println("监听者接收到的消息:"+ data);
            Thread.sleep(500);

            //int i = 10 / 0; // 模拟消费过程中出现了异常

            //获取消息的标识
            long deliveryTag = message.getMessageProperties().getDeliveryTag();

            // 把消息消费成功，告诉MQ这个消息我已经消费了
            // 第一个参数是应答消息的标识(10)
            // 第二个参数是否批量应答(如果开启的批量应答，会把小于10的消息全部应答)
            channel.basicAck(deliveryTag, false);
        }catch(Exception e){
            System.out.println("消费异常");
            try{
                long deliveryTag = message.getMessageProperties().getDeliveryTag();
                // 应答MQ，这个消息我消费不了
                // 第一个参数是应答消息的标识(10)
                // 第二个参数是否批量应答(如果开启的批量应答，会把小于10的消息全部应答)
                // 第三个参数:该消息是否重入队列，压倒队列的头部
                //          false:该消息就会丢失
                channel.basicNack(deliveryTag, false, true);
            }catch (Exception e1){
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
    }
}
