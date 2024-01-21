package com.influx;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;

import java.time.Instant;

public class Main {
    private static char[] token = "1wHB1UJEdbZ-v9y8K_aFoNm6mxiNwcHP2j7MjK-ua2nL39Zkuma2Bh2cdcvT5UzuyR1yq85UFgRo8wKu1p8qvA==".toCharArray();
    private static String org = "hyz";
    private static String bucket = "example_java";
    private static String url = "http://192.168.177.128:8086/";
    public static void main(String[] args) {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org, bucket);
        WriteApiBlocking writeApiBlocking = influxDBClient.getWriteApiBlocking();

        //写数据

        //方法一 行协议
//        writeApiBlocking.writeRecord(WritePrecision.MS,"temperature,location=north value=50");


        //方法二 point
//        Point point = Point.measurement("temperature")
//                .addTag("location", "west")
//                .addField("value", 38.8)
//                .time(Instant.now(), WritePrecision.MS);
//        writeApiBlocking.writePoint(point);

        //方法三 pojo类
        DemoPOJO pojo = new DemoPOJO( "east", 26.65, Instant.now());
        writeApiBlocking.writeMeasurement(WritePrecision.MS,pojo);

        //关闭
        influxDBClient.close();
    }
}
