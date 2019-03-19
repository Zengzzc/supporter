package com.jjshequ.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jjshequ.core.entity.PageResult;
import com.jjshequ.core.entity.Result;
import com.jjshequ.core.pojo.good.Brand;
import com.jjshequ.core.service.brand.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return
     */
    @RequestMapping("/findAll.do")
    public List<Brand> findAll(){
        return brandService.findAll();
    }

    /**
     * 查询分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/findByPage.do")
    public PageResult findByPage(Integer pageNo,Integer pageSize){
        PageResult byPage = brandService.findByPage(pageNo, pageSize);
        return byPage;
    }

    /**
     * 条件查询
     * @param pageNo
     * @param pageSize
     * @param brand
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(Integer pageNo,Integer pageSize,@RequestBody Brand brand){

        //进行查询
        return brandService.search(pageNo,pageSize,brand);
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody Brand brand){
        try{
            brandService.add(brand);
            return new Result(true,"保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"保存失败");
        }
    }

    /**
     * 回显查询
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    public Brand findOne(Long id){
        return brandService.findOne(id);
    }

    /**
     * 修改品牌
     * @param brand
     * @return
     */
    @RequestMapping("/update.do")
    public Result update(@RequestBody Brand brand){
        try{
            brandService.update(brand);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    @RequestMapping("/delete.do")
    public Result delete(Long[] ids){
        try{
            brandService.delete(ids);
            return new Result(true,"删除※成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

}
