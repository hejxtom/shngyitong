package com.atguigu.yygh.hosp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname ServiceHospApplication
 * @Description TODO
 * @Date 22:05 2022/5/9
 * @Created by hejx
 */

@SpringBootApplication
@MapperScan("com.atguigu.yygh.hosp.mapper")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}

