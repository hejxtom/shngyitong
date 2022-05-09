package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.hosp.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname HospitalSetController
 * @Description TODO
 * @Date 23:28 2022/5/9
 * @Created by hejx
 */

@RestController
@RequestMapping
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

}
