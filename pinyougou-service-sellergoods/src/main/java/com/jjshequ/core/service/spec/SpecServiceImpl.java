package com.jjshequ.core.service.spec;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jjshequ.core.dao.specification.SpecificationDao;
import com.jjshequ.core.dao.specification.SpecificationOptionDao;
import com.jjshequ.core.entity.PageResult;
import com.jjshequ.core.entity.SpecVo;
import com.jjshequ.core.pojo.specification.Specification;
import com.jjshequ.core.pojo.specification.SpecificationOption;
import com.jjshequ.core.pojo.specification.SpecificationOptionQuery;
import com.jjshequ.core.pojo.specification.SpecificationQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
public class SpecServiceImpl implements SpecService{

    @Resource
    private SpecificationDao specificationDao;
    @Resource
    private SpecificationOptionDao specificationOptionDao;


    /**
     * 分页条件查询
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        //设置分页信息
        PageHelper.startPage(page,rows);
        //设置查询条件
        SpecificationQuery specificationQuery = new SpecificationQuery();
        //获取设置条件封装对象
        if (specification.getSpecName() != null && !"".equals(specification.getSpecName().trim())){
            specificationQuery.createCriteria().andSpecNameLike("%"+specification.getSpecName().trim()+"%");
        }
        //设置倒序查询
        specificationQuery.setOrderByClause("id desc");
        //进行查询
        Page<Specification> Page = (Page<Specification>) specificationDao.selectByExample(specificationQuery);
        //封装查询结果
        return new PageResult(Page.getTotal(),Page.getResult());
    }

    /**
     * 新增规格
     * @param specVo
     * @return
     */
    @Transactional
    @Override
    public void add(SpecVo specVo) {
        //获取specification
        Specification specification = specVo.getSpecification();
        //获取规格集合
        List<SpecificationOption> specificationOptionList = specVo.getSpecificationOptionList();
        //储存specification，获取返回的主键ID
        if (specification.getSpecName() != null && !"".equals(specification.getSpecName().trim())){
            specificationDao.insertSelective(specification);
        }
        //校验规格集合
        if (specificationOptionList != null && specificationOptionList.size() > 0){
            //遍历集合
            for (SpecificationOption specificationOption : specificationOptionList) {
                //设置外键SpecId
                specificationOption.setSpecId(specification.getId());
                specificationOptionDao.insertSelective(specificationOption);
            }
        }
    }

    /**
     * 回显规格
     * @param id
     * @return
     */
    @Override
    public SpecVo findOne(Long id) {
        //查询规格
        Specification specification = specificationDao.selectByPrimaryKey(id);
        //新建规格选项查询条件
        SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
        //封装查询条件
        specificationOptionQuery.createCriteria().andSpecIdEqualTo(id);
        //查询规格选项
        List<SpecificationOption> specificationOptionList = specificationOptionDao.selectByExample(specificationOptionQuery);
        //封装查询数据
        return new SpecVo(specification,specificationOptionList);
    }

    /**
     * 修改规格
     * @param specVo
     */
    @Transactional
    @Override
    public void update(SpecVo specVo) {
        //获取specification
        Specification specification = specVo.getSpecification();
        //修改规格名称
        specificationDao.updateByPrimaryKeySelective(specification);
        //修改规格选项
        //先删除所有选项
        SpecificationOptionQuery specificationQuery = new SpecificationOptionQuery();
        //设置删除条件
        specificationQuery.createCriteria().andSpecIdEqualTo(specification.getId());
        //删除所有规格选项
        specificationOptionDao.deleteByExample(specificationQuery);

        //获取规格选项
        List<SpecificationOption> optionList = specVo.getSpecificationOptionList();
        if (optionList != null && optionList.size() > 0 ){
            for (SpecificationOption specificationOption : optionList) {
                specificationOption.setSpecId(specification.getId());
            }
        }
        //批量增加规格选项
        specificationOptionDao.insertSelectives(optionList);
    }

    /**
     * 删除规格
     * @param ids
     */
    @Transactional
    @Override
    public void delete(Long[] ids) {
        //校验ids
        if (ids != null && ids.length > 0){
            for (Long id : ids) {
                //先删除所有选项
                SpecificationOptionQuery specificationQuery = new SpecificationOptionQuery();
                //设置删除条件
                specificationQuery.createCriteria().andSpecIdEqualTo(id);
                //删除规格选项
                specificationOptionDao.deleteByExample(specificationQuery);
                //删除规格
                specificationDao.deleteByPrimaryKey(id);
            }
        }
    }
}
