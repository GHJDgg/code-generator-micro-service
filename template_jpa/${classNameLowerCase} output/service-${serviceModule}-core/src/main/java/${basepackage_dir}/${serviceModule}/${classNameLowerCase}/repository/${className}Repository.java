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
package ${basepackage}.${serviceModule}.${classNameLowerCase}.repository;

import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
<#if delFlag>
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
</#if>
import org.springframework.stereotype.Repository;

<#if delFlag>
import java.util.List;
</#if>

/**
 * <p>${tableDesc}DAO</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Repository
public interface ${className}Repository extends JpaRepository<${className}, ${table.idColumn.javaType}>,
        JpaSpecificationExecutor<${className}> {

<#if delFlag>
    /**
     * 单个删除${tableDesc}
     * @author ${rapidAuthor}
     */
    @Modifying
    @Query("update ${className} set delFlag = 1 where ${table.idColumn.columnNameLower} = ?1")
    void deleteById(${table.idColumn.javaType} ${table.idColumn.columnNameLower});

    /**
     * 批量删除${tableDesc}
     * @author ${rapidAuthor}
     */
    @Modifying
    @Query("update ${className} set delFlag = 1 where ${table.idColumn.columnNameLower} in ?1")
    int deleteByIdList(List<${table.idColumn.javaType}> ${table.idColumn.columnNameLower}List);

</#if>
}
