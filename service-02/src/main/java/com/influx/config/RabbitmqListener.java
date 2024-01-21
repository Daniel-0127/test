package com.influx.config;

import com.influx.pojo.po.DemoPOJO;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitmqListener {


    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg){
        log.info("接收到fanout.queue1的消息${}",msg);
    }
    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg){
        log.info("接收到fanout.queue2的消息${}",msg);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"one", "two"}
    ))
    public void listenDirectQueue1(String msg){
        log.info("接收到direct.queue1的消息${}",msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "direct.exchange", type = ExchangeTypes.DIRECT),
            key = {"one", "three"}
    ))
    public void listenDirectQueue2(DemoPOJO pojo){

        log.info("接收到direct.queue2的消息${}",pojo);
        char[] token = "1wHB1UJEdbZ-v9y8K_aFoNm6mxiNwcHP2j7MjK-ua2nL39Zkuma2Bh2cdcvT5UzuyR1yq85UFgRo8wKu1p8qvA==".toCharArray();
        String org = "hyz";
        String bucket = "example_java";
        String url = "http://192.168.177.128:8086/";
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org, bucket);
        WriteApiBlocking writeApiBlocking = influxDBClient.getWriteApiBlocking();
        writeApiBlocking.writeMeasurement(WritePrecision.MS,pojo);
        //关闭
        influxDBClient.close();

    }
}
