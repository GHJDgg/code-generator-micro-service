<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.bean.vo;

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
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>${tableDesc}VO</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@Data
public class ${className}VO implements Serializable {
	private static final long serialVersionUID = 1L;

<#list table.columns as column>
	<#if column.javaType == "LocalDateTime">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalDate">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalTime">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower};

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