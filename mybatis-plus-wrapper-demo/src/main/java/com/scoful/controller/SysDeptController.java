package com.scoful.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoful.config.Result;
import com.scoful.entity.SysDept;
import com.scoful.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author scoful
 * @since 2020-06-06
 */
@RestController
@RequestMapping("/sys")
@Api()
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表查询
     *
     * @param page
     * @param dept
     * @return
     * @author scoful
     * @since 2020-06-08
     */
    @ApiOperation(value = "列表查询")
    @GetMapping("/")
    public Result<List<SysDept>> query(Page page, SysDept dept) {
        List<SysDept> list = sysDeptService.list();
        return new Result<>(0, list);
    }


    /**
     * 新增
     *
     * @param dept
     * @return
     * @author scoful
     * @since 2020-06-08
     */
    @ApiOperation(value = "新增")
    @PostMapping("/")
    public Result<?> add(@RequestBody SysDept dept) {
//        dept.setId(IdMaker.get());
        sysDeptService.save(dept);

        return Result.SUCCESS;
    }

    /**
     * 修改
     *
     * @param deptId
     * @param dept
     * @return
     * @author scoful
     * @since 2020-06-08
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{deptId}")
    public Result<?> update(@PathVariable("deptId") Integer deptId, @RequestBody SysDept dept) {
        dept.setId(deptId);
        sysDeptService.updateById(dept);

        return Result.SUCCESS;
    }

    /**
     * 删除
     *
     * @param deptId
     * @return
     * @author scoful
     * @since 2020-06-08
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{deptId}")
    public Result<?> delete(@PathVariable("deptId") String deptId) {
        sysDeptService.removeById(deptId);

        return Result.SUCCESS;
    }


    /**
     * 单记录查询
     *
     * @param sysDeptId
     * @return
     * @author scoful
     * @since 2020-06-06
     */
    @ApiOperation(value = "单记录查询")
    @GetMapping(value = {"/dept"})
    public Result<SysDept> get(@RequestParam("sysDeptId") String sysDeptId) {
        System.out.println(sysDeptId);
        return new Result<>(sysDeptService.getById(sysDeptId));
    }

    /**
     * 单记录查询
     *
     * @param sysDeptId
     * @return
     * @author scoful
     * @since 2020-06-06
     */
    @GetMapping(value = {"/dept2/{sysDeptId}"})
    public Result<SysDept> get2(@PathVariable("sysDeptId") String sysDeptId) {
        System.out.println(sysDeptId);
        return new Result<>(sysDeptService.getById(sysDeptId));
    }

    @GetMapping("/selectPage")
    public Result selectPage(Page<SysDept> ipage, String state) {
        System.out.println(ipage);
//        Page<SysDept> ipage = new Page<>();
//        ipage.setSize(2);
//        ipage.setCurrent(1);
        IPage<SysDept> sysDeptIPage = sysDeptService.selectPage(ipage, state);
        return new Result(0, sysDeptIPage);
    }


}

