package com.influx.pojo.po;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.io.Serializable;
import java.time.Instant;

@Measurement(name = "temperature")
public class DemoPOJO  implements Serializable {

    @Column(tag = true)
    String location;
    @Column
    Double value;
    @Column(timestamp = true)
    Instant timestamp;

    public DemoPOJO(String location, Double value, Instant timestamp) {
        this.location = location;
        this.value = value;
        this.timestamp = timestamp;
    }
}
