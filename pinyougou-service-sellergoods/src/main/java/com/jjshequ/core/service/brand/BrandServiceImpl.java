package com.jjshequ.core.service.brand;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jjshequ.core.dao.good.BrandDao;
import com.jjshequ.core.entity.PageResult;
import com.jjshequ.core.pojo.good.Brand;
import com.jjshequ.core.pojo.good.BrandQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandDao brandDao;

    @Override
    public List<Brand> findAll() {
        return brandDao.selectByExample(null);
    }

    @Override
    public PageResult findByPage(Integer pageNo, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(pageNo,pageSize);
        //获取分页结果
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(null);
        //封装结果并返回
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult search(Integer pageNo, Integer pageSize, Brand brand) {
        //设置分页信息
        PageHelper.startPage(pageNo,pageSize);
        //封装查询条件
        BrandQuery brandQuery = new BrandQuery();
        BrandQuery.Criteria criteria = brandQuery.createCriteria(); //封装具体查询条件的对象
        //拼接sql语句
        if (brand.getName() != null && !"".equals(brand.getName().trim())){
            criteria.andNameLike("%"+brand.getName().trim()+"%");
        }
        if (brand.getFirstChar() != null && !"".equals(brand.getFirstChar().trim())){
            criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        //根据id倒序
        brandQuery.setOrderByClause("id desc");
        //进行查询
        Page<Brand> page = (Page<Brand>) brandDao.selectByExample(brandQuery);
        //封装数据并返回
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Transactional
    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public Brand findOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Transactional
    @Override
    public void delete(Long[] ids) {
        /*if (ids != null && ids.length > 0){
            for (Long id : ids) {
                brandDao.deleteByPrimaryKey(id);
            }
        }*/
        if (ids != null && ids.length > 0) {
            brandDao.deleteByPrimaryKeys(ids);
        }
    }
}
