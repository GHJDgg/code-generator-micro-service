<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.${classNameLowerCase}.service.builder;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}QueryRequest;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import ${basepackage}.${serviceModule}.common.enums.SortType;
import ${basepackage}.${serviceModule}.common.util.XssUtils;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>${tableDesc}动态查询条件构建器</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
public class ${className}QueryWrapperBuilder {
    public static LambdaQueryWrapper build(${className}QueryRequest queryRequest) {
        LambdaQueryWrapper<${className}> wrapper = Wrappers.lambdaQuery();

        // 批量查询-idList
        if (CollectionUtils.isNotEmpty(queryRequest.get${table.idColumn.columnName}List())) {
            wrapper.in(${className}::get${table.idColumn.columnName},queryRequest.get${table.idColumn.columnName}List());
        }

        // ${table.idColumn.columnAlias}
<#if table.idColumn.isStringColumn>
        if (StringUtils.isNotEmpty(queryRequest.get${table.idColumn.columnName}())) {
<#else>
        if (queryRequest.get${table.idColumn.columnName}() != null) {
</#if>
            wrapper.eq(${className}::get${table.idColumn.columnName}(),queryRequest.get${table.idColumn.columnName}());
        }


<#list table.notPkColumns as column>
    <#if column.isDateTimeColumn>
        // 大于或等于 搜索条件:${column.columnAlias}开始
        if (queryRequest.get${column.columnName}() != null) {
            wrapper.ge(${className}::get${column.columnName}(),queryRequest.get${column.columnName}());
        }
        // 小于或等于 搜索条件:${column.columnAlias}截止
        if (queryRequest.get${column.columnName}() != null) {
            wrapper.le(${className}::get${column.columnName}(),queryRequest.get${column.columnName}());
        }
    <#elseif column.isStringColumn>
        // 模糊查询 - ${column.columnAlias}
        if (StringUtils.isNotEmpty(queryRequest.get${column.columnName}())) {
        predicates.add(cbuild.like(root.get("${column.columnNameLower}"), StringUtil.SQL_LIKE_CHAR
        .concat(XssUtils.replaceLikeWildcard(queryRequest.get${column.columnName}()))
        .concat(StringUtil.SQL_LIKE_CHAR)));
            wrapper.like(${className}::get${column.columnName}(),queryRequest.get${column.columnName}());
        }
    <#else>
        // ${column.columnAlias}
        if (queryRequest.get${column.columnName}() != null) {
            wrapper.eq(${className}::get${column.columnName}(),queryRequest.get${column.columnName}());
        }
    </#if>
</#list>

        // 排序
        if (StringUtils.isNotBlank(queryRequest.getSortColumn())) {
            // 判断规则 DESC ASC
            getSort(wrapper,queryRequest);
        }
        return wrapper;
    }

    public static void getSort(LambdaQueryWrapper wrapper,${className}QueryRequest queryRequest) {
        // 单个排序
        if (StringUtils.isNotBlank(queryRequest.getSortColumn())) {
            // 判断规则 DESC ASC
            if (SortType.ASC.toValue().equalsIgnoreCase(queryRequest.getSortRole())) {
                wrapper.orderByAsc(queryRequest.getSortColumn());
            } else {
                wrapper.orderByDesc(queryRequest.getSortColumn());
            }
            return;
        }

        // 多重排序
        if (MapUtils.isNotEmpty(queryRequest.getSortMap())) {
            queryRequest.getSortMap().keySet().stream().filter(StringUtils::isNotBlank).forEach(column -> {
                if (SortType.ASC.toValue().equalsIgnoreCase(queryRequest.getSortMap().get(column))) {
                    wrapper.orderByAsc(column);
                } else {
                    wrapper.orderByDesc(column);
                }
            });
        }
    }
}
