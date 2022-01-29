package com.scoful.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scoful.config.Result;
import com.scoful.entity.SysDept;
import com.scoful.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @PostMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        System.out.println(file);


//        EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener(uploadDAO)).sheet().doRead();
        return "success";
    }

    @GetMapping("/download")
    public String download() {

        ExcelWriter writer = ExcelUtil.getWriter("C:\\Users\\scoful\\Desktop\\writeTest.xlsx", "第一个");
        List<String> row1 = CollUtil.newArrayList("父级设备编码", "父级设备名称", "设备编码", "设备名称");
        List<String> row2 = CollUtil.newArrayList("");
        List<String> row3 = CollUtil.newArrayList("测点编码", "测点名称", "单位", "展示方式", "预警上限值", "正常上限值", "预警下限值", "正常下限值");


        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3);


//通过工具类创建writer

//跳过当前行，既第一行，非必须，在此演示用
//        writer.passCurrentRow();

//合并单元格后的标题行，使用默认标题样式
//        writer.merge(row1.size() - 1, "测试标题");
//一次性写出内容，强制输出标题
        writer.write(rows, true);


        writer.setSheet("第二个");

        List<String> row11 = CollUtil.newArrayList("父级设备编码", "父级设备名称", "设备编码", "设备名称");
        List<String> row22 = CollUtil.newArrayList("");
        List<String> row33 = CollUtil.newArrayList("测点编码", "测点名称", "单位", "展示方式", "预警上限值", "正常上限值", "预警下限值", "正常下限值");


        List<List<String>> rows1 = CollUtil.newArrayList(row11, row22, row33);

        writer.write(rows1, true);

//关闭writer，释放内存
        writer.close();

        return "success";
    }

}

