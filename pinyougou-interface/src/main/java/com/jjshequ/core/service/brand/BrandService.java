package com.jjshequ.core.service.brand;

import com.jjshequ.core.entity.PageResult;
import com.jjshequ.core.pojo.good.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有品牌
     * @return
     */
    public List<Brand> findAll();

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    public PageResult findByPage(Integer pageNo,Integer pageSize);

    /**
     * 分页条件查询
     * @param pageNo
     * @param pageSize
     * @param brand
     * @return
     */
    public PageResult search(Integer pageNo, Integer pageSize, Brand brand);

    /**
     * 新增品牌
     * @param brand
     */
    public void add(Brand brand);

    /**
     * 根据ID查询品牌
     * @param id
     * @return
     */
    public Brand findOne(Long id);

    /**
     * 修改品牌
     * @param brand
     */
    public void update(Brand brand);

    /**
     * 删除品牌
     * @param ids
     */
    public void delete(Long[] ids);
}
