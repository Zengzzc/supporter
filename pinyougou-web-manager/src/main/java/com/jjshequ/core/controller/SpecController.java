package com.jjshequ.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jjshequ.core.entity.PageResult;
import com.jjshequ.core.entity.Result;
import com.jjshequ.core.entity.SpecVo;
import com.jjshequ.core.pojo.specification.Specification;
import com.jjshequ.core.service.spec.SpecService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specification")
public class SpecController {

    @Reference
    private SpecService specService;

    /**
     * 分页条件查询
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    @RequestMapping("/search.do")
    public PageResult search(Integer page, Integer rows,@RequestBody Specification specification){
        return specService.search(page,rows,specification);
    }

    /**
     * 新增规格
     * @param specVo
     * @return
     */
    @RequestMapping("/add.do")
    public Result add(@RequestBody SpecVo specVo){
        try{
            specService.add(specVo);
            return new Result(true,"保存成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"保存失败");
        }
    }

    /**
     * 回显规格
     * @param id
     * @return
     */
    @RequestMapping("/findOne.do")
    public SpecVo findOne(Long id){
        return specService.findOne(id);
    }

    @RequestMapping("/update.do")
    public Result update(@RequestBody SpecVo specVo){
        try{
            specService.update(specVo);
            return new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    @RequestMapping("/delete.do")
    public Result delete(Long[] ids){
        try{
            specService.delete(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }
}
