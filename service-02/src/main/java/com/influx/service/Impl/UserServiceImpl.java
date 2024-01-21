package com.influx.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.influx.mapper.UserMapper;
import com.influx.pojo.po.User;
import com.influx.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
