package com.influx.controller;

import com.influx.pojo.po.DemoPOJO;
import com.influx.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;

@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public Object test() {
        return userService.query().list();
    }

    @GetMapping("/send1")
    public Object send1() {
        rabbitTemplate.convertAndSend("fanout.exchange", "", "fanout消息");
        return "ok";
    }

    @GetMapping("/send2")
    public Object send2() {
        rabbitTemplate.convertAndSend("direct.exchange", "one", "direct消息 -one");
        return "ok";
    }

    @GetMapping("/send3")
    public Object send3() {
        rabbitTemplate.convertAndSend("direct.exchange", "two", "direct消息 -two");
        return "ok";
    }


    @GetMapping("/send4")
    public Object send4() {
        DemoPOJO pojo = new DemoPOJO( "east", 26.65, Instant.now());
        rabbitTemplate.convertAndSend("direct.exchange", "three",pojo);
        return "ok";
    }
}
