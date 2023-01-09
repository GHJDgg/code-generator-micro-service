<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.${classNameLowerCase}.service;

import ${basepackage}.common.util.StringUtil;
import com.wanmi.sbc.common.util.XssUtils;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}QueryRequest;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
<#list table.notPkColumns as column>
    <#if table.idColumn.isStringColumn>
import org.apache.commons.lang3.StringUtils;
        <#break>
    </#if>
</#list>
<#list table.notPkColumns as column>
    <#if table.idColumn.isStringColumn>
import ${basepackage}.common.util.XssUtils;
        <#break>
    </#if>
</#list>
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>${tableDesc}动态查询条件构建器</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
public class ${className}WhereCriteriaBuilder {
    public static Specification<${className}> build(${className}QueryRequest queryRequest) {
        return (root, cquery, cbuild) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 批量查询-${table.idColumn.columnAlias}List
            if (CollectionUtils.isNotEmpty(queryRequest.get${table.idColumn.columnName}List())) {
                predicates.add(root.get("${table.idColumn.columnNameLower}").in(queryRequest.get${table.idColumn.columnName}List()));
            }

            // ${table.idColumn.columnAlias}
<#if table.idColumn.isStringColumn>
            if (StringUtils.isNotEmpty(queryRequest.get${table.idColumn.columnName}())) {
<#else>
            if (queryRequest.get${table.idColumn.columnName}() != null) {
</#if>
                predicates.add(cbuild.equal(root.get("${table.idColumn.columnNameLower}"), queryRequest.get${table.idColumn.columnName}()));
            }

<#list table.notPkColumns as column>
    <#if column.isDateTimeColumn>
            // 大于或等于 搜索条件:${column.columnAlias}开始
            if (queryRequest.get${column.columnName}Begin() != null) {
                predicates.add(cbuild.greaterThanOrEqualTo(root.get("${column.columnNameLower}"),
                        queryRequest.get${column.columnName}Begin()));
            }
            // 小于或等于 搜索条件:${column.columnAlias}截止
            if (queryRequest.get${column.columnName}End() != null) {
                predicates.add(cbuild.lessThanOrEqualTo(root.get("${column.columnNameLower}"),
                        queryRequest.get${column.columnName}End()));
            }

    <#elseif column.isStringColumn>
            // 模糊查询 - ${column.columnAlias}
            if (StringUtils.isNotEmpty(queryRequest.get${column.columnName}())) {
                predicates.add(cbuild.like(root.get("${column.columnNameLower}"), StringUtil.SQL_LIKE_CHAR
                        .concat(XssUtils.replaceLikeWildcard(queryRequest.get${column.columnName}()))
                        .concat(StringUtil.SQL_LIKE_CHAR)));
            }

    <#else>
            // ${column.columnAlias}
            if (queryRequest.get${column.columnName}() != null) {
                predicates.add(cbuild.equal(root.get("${column.columnNameLower}"), queryRequest.get${column.columnName}()));
            }

    </#if>
</#list>
            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : cbuild.and(p);
        };
    }
}
