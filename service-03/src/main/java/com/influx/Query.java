package com.influx;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxColumn;
import com.influxdb.query.FluxTable;

import java.util.List;

public class Query {
    private static char[] token = "1wHB1UJEdbZ-v9y8K_aFoNm6mxiNwcHP2j7MjK-ua2nL39Zkuma2Bh2cdcvT5UzuyR1yq85UFgRo8wKu1p8qvA==".toCharArray();
    private static String org = "hyz";
    private static String bucket = "example_java";
    private static String url = "http://192.168.177.128:8086/";

    public static void main(String[] args) {
        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org);
        QueryApi queryApi = influxDBClient.getQueryApi();

        String flux = """
                from(bucket: "example_java")
                  |> range(start: -1h)
                  |> filter(fn: (r) => r["_measurement"] == "temperature")
                  |> aggregateWindow(every: 10s, fn: mean, createEmpty: false)
                  |> yield(name: "mean")""";

        List<FluxTable> query = queryApi.query(flux);
        for (FluxTable fluxTable : query) {
            List<FluxColumn> columns = fluxTable.getColumns();
            for (int i = 0; i < columns.size(); i++) {
                System.out.println(columns.get(i));
            }

        }
        System.out.println(query.get(0));
    }
}
