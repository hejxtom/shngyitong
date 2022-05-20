package com.atguigu.yygh.hosp.controller;

import com.atguigu.yygh.common.result.MD5;
import com.atguigu.yygh.common.result.Result;
import com.atguigu.yygh.hosp.service.HospitalSetService;
import com.atguigu.yygh.model.hosp.HospitalSet;
import com.atguigu.yygh.vo.hosp.HospitalSetQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @Classname HospitalSetController
 * @Description TODO
 * @Date 23:28 2022/5/9
 * @Created by hejx
 */

@Api(tags = "医院设置管理")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {


    @Autowired
    private HospitalSetService hospitalSetService;


    //查询医院设置表所有信息
    @ApiOperation(value = "获取所有医院设置")
    @GetMapping("findAll")
    public Result findAllHospitalSet() {
        List<HospitalSet> list = hospitalSetService.list();
        return Result.ok(list);
    }

    //逻辑删除医院设置
    @ApiOperation(value = "逻辑删除医院设置")
    @DeleteMapping("{id}")
    public Result removeHospitalSet(@PathVariable("id") Integer id) {
        boolean b = hospitalSetService.removeById(id);

        if (b) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //条件查询带分页
    //注意：@RequestBody与@PostMapping同用
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("findPageHospitalSet/{current}/{limit}")
    public Result findPageHospitalSet(@PathVariable long current,
                                      @PathVariable long limit,
                                      @RequestBody(required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSet> page = new Page<>(current, limit);

        //构建条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        String hoscode = hospitalSetQueryVo.getHoscode();
        String hosname = hospitalSetQueryVo.getHosname();
        if (!StringUtils.isEmpty(hoscode)) {
            queryWrapper.eq("hoscode", hoscode);
        }
        if (!StringUtils.isEmpty(hosname)) {
            queryWrapper.like("hosname", hosname);
        }

        //查询
        Page<HospitalSet> page1 = hospitalSetService.page(page, queryWrapper);
        return Result.ok(page1);
    }

    //    保存医院设置
    @ApiOperation(value = "保存医院")
    @PostMapping("saveHospitalSet")
    public Result saveHospitalSet(@RequestBody(required = true) HospitalSet hospitalSet) {

        //设置是否启用：0不使用  1使用
        hospitalSet.setStatus(1);

        //设置签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));

        //保存
        boolean save = hospitalSetService.save(hospitalSet);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    根据id查询医院设置
    @ApiOperation("根据id查询医院设置")
    @GetMapping("getHospitalSet/{id}")
    public Result getHospitalSet(@PathVariable Integer id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

    //    根据id修改医院设置
    @ApiOperation("根据id修改医院设置")
    @PutMapping("updateHospitalSet")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean result = hospitalSetService.updateById(hospitalSet);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    //    批量删除医院设置
    @ApiOperation("批量删除医院设置")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSet(@RequestBody List<Integer> ids) {
        boolean result = hospitalSetService.removeByIds(ids);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 医院设置的锁定和解锁
    @ApiOperation("医院设置的锁定和解锁")
    @PutMapping("lockHospitalSet/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        UpdateWrapper<HospitalSet> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", status);
        updateWrapper.eq("id", id);
        boolean result = hospitalSetService.update(updateWrapper);
        if (result) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    // 发送签名密钥
    @ApiOperation("发送签名密钥")
    @GetMapping("sendKey/{id}")
    public Result sendSignKey(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
//        TODO 发送短信
        return Result.ok();

    }

}
