package com.atguigu.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname HospConfig
 * @Description TODO
 * @Date 22:21 2022/5/15
 * @Created by hejx
 */

@MapperScan("com.atguigu.yygh.hosp.mapper")
@Configuration
public class HospConfig {
}
