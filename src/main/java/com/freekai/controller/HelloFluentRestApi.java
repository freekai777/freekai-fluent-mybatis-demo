package com.freekai.controller;

import cn.org.atool.fluent.form.annotation.Entry;
import cn.org.atool.fluent.form.annotation.FormService;
import com.freekai.annotation.FreekaiAnnotation;
import com.freekai.fluent.entity.UserTestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FormService(table = "user_test")
public interface HelloFluentRestApi {

    @GetMapping("/findByUserName")
    @FreekaiAnnotation
    UserTestEntity findByUserName(@RequestParam("userName") @Entry(value = "userName") String userName);
}
