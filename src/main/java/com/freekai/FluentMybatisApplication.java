package com.freekai;

import cn.org.atool.fluent.form.annotation.FormServiceScan;
import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan({"com.freekai.mapper","com.freekai.fluent"})
@FormServiceScan("com.freekai.controller")
public class FluentMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(FluentMybatisApplication.class, args);
    }

    @Bean
    public MapperFactory mapperFactory(){
        return new MapperFactory();
    }
}
