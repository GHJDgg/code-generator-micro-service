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

package ${basepackage}.${serviceModule}.${classNameLowerCase}.mapper;


import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName ${tableDesc}Mapper
 * @Description: TODO
 * @Author ${rapidAuthor}
 * @Date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 **/
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {

<#if delFlag>
    /**
     * 逻辑删除${tableDesc}
     * @author ${rapidAuthor}
     */
    void deleteByIdLogical(Long id);
</#if>
}