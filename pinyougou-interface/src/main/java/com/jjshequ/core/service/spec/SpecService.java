package com.jjshequ.core.service.spec;

import com.jjshequ.core.entity.PageResult;
import com.jjshequ.core.entity.Result;
import com.jjshequ.core.entity.SpecVo;
import com.jjshequ.core.pojo.specification.Specification;

public interface SpecService {


    /**
     * 分页条件查询
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    public PageResult search(Integer page, Integer rows, Specification specification);

    /**
     * 新增规格
     * @param specVo
     * @return
     */
    public void add(SpecVo specVo);


    /**
     * 规格回显
     * @param id
     * @return
     */
    public SpecVo findOne(Long id);

    /**
     * 修改规格
     * @param specVo
     */
    public void update(SpecVo specVo);

    /**
     * 删除规格
     * @param ids
     */
    public void delete(Long[] ids);
}
