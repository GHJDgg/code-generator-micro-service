<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.api.request.${classNameLowerCase};

<#assign dateColumnFlag=false dateTimeFlag=false dateFlag=false timeFlag=false decimalFlag=false>
<#list table.columns as column>
	<#if column.isDateTimeColumn && !dateColumnFlag>
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
		<#assign dateColumnFlag=true>
	</#if>
	<#if column.javaType == "LocalDateTime" && !dateTimeFlag>
import ${basepackage}.common.util.CustomLocalDateTimeSerializer;
import ${basepackage}.common.util.CustomLocalDateTimeDeserializer;
import java.time.LocalDateTime;
		<#assign dateTimeFlag=true>
	<#elseif column.javaType == "LocalDate" && !dateFlag>
import ${basepackage}.common.util.CustomLocalDateSerializer;
import ${basepackage}.common.util.CustomLocalDateDeserializer;
import java.time.LocalDate;
		<#assign dateFlag=true>
	<#elseif column.javaType == "LocalTime" && !timeFlag>
import ${basepackage}.common.util.CustomLocalTimeSerializer;
import ${basepackage}.common.util.CustomLocalTimeDeserializer;
import java.time.LocalTime;
		<#assign timeFlag=true>
	<#elseif column.javaType == "BigDecimal" && !decimalFlag>
import java.math.BigDecimal;
		<#assign decimalFlag=true>
	</#if>
	<#if column.columnNameLower == "delFlag">
import com.wanmi.sbc.common.enums.DeleteFlag;
	</#if>
</#list>
import ${basepackage}.common.base.BaseQueryRequest;
import lombok.*;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>${tableDesc}分页查询请求参数</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}PageRequest extends BaseQueryRequest {
	private static final long serialVersionUID = 1L;

	/**
	 * 批量查询-${table.idColumn.columnAlias}List
	 */
	@ApiModelProperty(value = "批量查询-${table.idColumn.columnAlias}List")
	private List<${table.idColumn.javaType}> ${table.idColumn.columnNameLower}List;

<#list table.columns as column>
	<#if column.javaType == "LocalDateTime">
	/**
	 * 搜索条件:${column.columnAlias}开始
	 */
	@ApiModelProperty(value = "搜索条件:${column.columnAlias}开始")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower}Begin;
	/**
	 * 搜索条件:${column.columnAlias}截止
	 */
	@ApiModelProperty(value = "搜索条件:${column.columnAlias}截止")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower}End;

	<#elseif column.javaType == "LocalDate">
	/**
	 * 搜索条件:${column.columnAlias}开始
	 */
	@ApiModelProperty(value = "搜索条件:${column.columnAlias}开始")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private ${column.javaType} ${column.columnNameLower}Begin;
	/**
	 * 搜索条件:${column.columnAlias}截止
	 */
	@ApiModelProperty(value = "搜索条件:${column.columnAlias}截止")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private ${column.javaType} ${column.columnNameLower}End;

	<#elseif column.javaType == "LocalTime">
	/**
	 * 搜索条件:${column.columnAlias}开始
	 */
	@ApiModelProperty(value = "搜索条件:${column.columnAlias}开始")
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower}Begin;
	/**
	 * 搜索条件:${column.columnAlias}截止
	 */
	@ApiModelProperty(value = "搜索条件:${column.columnAlias}截止")
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower}End;

	<#else>
		<#if column.columnNameLower == "delFlag">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
	private DeleteFlag delFlag;

		<#else>
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
	private ${column.javaType} ${column.columnNameLower};

		</#if>
	</#if>
</#list>
}