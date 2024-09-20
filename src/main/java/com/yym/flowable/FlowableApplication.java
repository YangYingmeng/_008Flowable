package com.yym.flowable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FlowableApplication启动类
 * @Description: FlowableApplication
 * @Author: Yym
 * @Version: 1.0
 * @Date: 2023/10/31 13:48
 */
@SpringBootApplication
@MapperScan("com.yym.flowable.mapper")
public class FlowableApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlowableApplication.class, args);
    }
}
