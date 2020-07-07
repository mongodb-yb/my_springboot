package com.yubo.springboot.controller;

import com.yubo.springboot.modal.AyUser;
import com.yubo.springboot.service.AyUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yubo
 * @version V1.0
 * @description
 * @date 2020/4/26 15:07
 */
@RestController
@RequestMapping(value = "/user")
public class AyUserController {

    @Resource
    private AyUserService ayUserService;

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
//    @RequestMapping(value = "/get/{id}")
    public AyUser getUser(@RequestParam String id) {
        return ayUserService.findById(id);
    }
}
