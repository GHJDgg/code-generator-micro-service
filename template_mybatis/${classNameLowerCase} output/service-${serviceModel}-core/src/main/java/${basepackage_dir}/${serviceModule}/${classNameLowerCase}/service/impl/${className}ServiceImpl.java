<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign delFlag=false>
<#list table.columns as column>
    <#if column.columnNameLower == "delFlag">
        <#assign delFlag=true>
        <#break>
    </#if>
</#list>
package ${basepackage}.${serviceModule}.${classNameLowerCase}.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}QueryRequest;
import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import ${basepackage}.${serviceModule}.${classNameLowerCase}.repository.${className}Mapper;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.service.${className}Service;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.builder.${className}QueryWrapperBuilder;
import ${basepackage}.${serviceModule}.common.util.KsBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>${tableDesc}业务逻辑</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Service("${className}Service")
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service {

    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;
    /**
     * 新增系统日志
     * @author ghj
     */
    @Override
    @Transactional
    public ${className} add(${className} entity) {
        this.save(entity);
        return entity;
    }

    /**
     * 修改系统日志
     * @author ghj
     */
    @Override
    @Transactional
    public ${className} modify(${className} entity) {
        this.updateById(entity);
        return entity;
    }

    /**
     * 单个删除系统日志
     * @author ghj
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
<#if delFlag>
        // 逻辑删除
        ${classNameLower}Mapper.deleteByIdLogical(id);
<#else>
        // 物理删除
        ${classNameLower}Mapper.deleteById(id);
</#if>
    }

    /**
     * 批量删除系统日志
     * @author ghj
     */
    @Override
    @Transactional
    public void deleteByIdList(List<Long> ids) {
<#if delFlag>
        // 逻辑删除
        ids.forEach(id -> ${classNameLower}Mapper.deleteByIdLogical(id));
<#else>
        // 物理删除
        ids.forEach(id -> ${classNameLower}Mapper.deleteById(id));
</#if>
    }

    /**
     * 单个查询系统日志
     * @author ghj
     */
    @Override
    public ${className} getById(Long id){
        return ${classNameLower}Mapper.selectById(id);
    }

    /**
     * 分页查询系统日志
     * @author ghj
     */
    @Override
    public IPage<${className}> page(${className}QueryRequest queryReq){
        return ${classNameLower}Mapper.selectPage(
                queryReq.getPageRequest(),${className}QueryWrapperBuilder.build(queryReq));
    }

    /**
     * 列表查询系统日志
     * @author ghj
     */
    @Override
    public List<${className}> list(${className}QueryRequest queryReq){
        return ${classNameLower}Mapper.selectList(${className}QueryWrapperBuilder.build(queryReq));
    }

    /**
     * 将实体包装成VO
     * @author ghj
     */
    @Override
    public ${className}VO wrapperVo(${className} ${classNameLower}) {
        if (${classNameLower} != null){
        ${className}VO ${classNameLower}VO=new ${className}VO();
        KsBeanUtil.copyPropertiesThird(${classNameLower},${classNameLower}VO);
        return ${classNameLower}VO;
        }
        return null;
    }
}



