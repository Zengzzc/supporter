package com.jjshequ.core.entity;

import com.jjshequ.core.pojo.specification.Specification;
import com.jjshequ.core.pojo.specification.SpecificationOption;

import java.io.Serializable;
import java.util.List;

public class SpecVo implements Serializable {
    private Specification specification;
    private List<SpecificationOption> specificationOptionList;

    public SpecVo() {
    }

    public SpecVo(Specification specification, List<SpecificationOption> specificationOptionList) {
        this.specification = specification;
        this.specificationOptionList = specificationOptionList;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public List<SpecificationOption> getSpecificationOptionList() {
        return specificationOptionList;
    }

    public void setSpecificationOptionList(List<SpecificationOption> specificationOptionList) {
        this.specificationOptionList = specificationOptionList;
    }
}
