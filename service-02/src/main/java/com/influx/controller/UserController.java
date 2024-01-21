package com.influx.controller;

import com.influx.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    public Object test(){
        return userService.query().list();
    }

    @RequestMapping("/test2")
    public Object test2(){
        rabbitTemplate.convertAndSend("fanout.exchange","","test");
        return "ok";
    }
}
