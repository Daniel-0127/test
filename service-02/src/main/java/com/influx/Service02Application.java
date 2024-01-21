package com.influx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.influx.mapper")
@SpringBootApplication
public class Service02Application {
    public static void main(String[] args) {
        SpringApplication.run(Service02Application.class,args);
    }
}
