
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
package ${basepackage}.${serviceModule}.${classNameLowerCase}.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}QueryRequest;
import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName CateDataService
 * @Description: TODO
 * @Author ghj
 * @Date 10/26/22
 **/
public interface ${className}Service extends IService<${className}> {

    ${className} add(${className} entity);

    ${className} modify(${className} entity);

    ${className} getById(Long id);

    IPage<${className}> page(${className}QueryRequest queryReq);

    List<${className}> list(${className}QueryRequest queryReq);

    ${className}VO wrapperVo(${className} ${classNameLower});

    void deleteById(Long id);

    void deleteByIdList(List<Long> ids);

}



