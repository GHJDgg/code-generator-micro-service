<#include "/macro.include"/>

<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root;

		<#assign dateColumnFlag=false dateTimeFlag=false dateFlag=false timeFlag=false decimalFlag=false>
		<#list table.columns as column>
		<#if column.isDateTimeColumn && !dateColumnFlag>
		<#assign dateColumnFlag=true>
				</#if>
				<#if column.javaType == "LocalDateTime" && !dateTimeFlag>
import java.time.LocalDateTime;
import ${basepackage}.${serviceModule}.common.util.CustomLocalDateTimeSerializer;
import ${basepackage}.${serviceModule}.common.util.CustomLocalDateTimeDeserializer;
		<#assign dateTimeFlag=true>
				<#elseif column.javaType == "LocalDate" && !dateFlag>
import java.time.LocalDate;
import ${basepackage}.${serviceModule}.common.util.CustomLocalDateSerializer;
import ${basepackage}.${serviceModule}.common.util.CustomLocalDateDeserializer;
		<#assign dateFlag=true>
				<#elseif column.javaType == "LocalTime" && !timeFlag>
import java.time.LocalTime;
import ${basepackage}.${serviceModule}.common.util.CustomLocalTimeSerializer;
import ${basepackage}.${serviceModule}.common.util.CustomLocalTimeDeserializer;
		<#assign timeFlag=true>
				<#elseif column.javaType == "BigDecimal" && !decimalFlag>
import java.math.BigDecimal;
		<#assign decimalFlag=true>
				</#if>
				<#if column.columnNameLower == "delFlag">
import ${basepackage}.${serviceModule}.common.enums.DeleteFlag;
		</#if>
</#list>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import lombok.Data;
import java.io.Serializable;

/**
 * <p>${tableDesc}实体类</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "${table.sqlName}")
public class ${className} implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ${table.idColumn.columnAlias}
	 */
	<#if table.idColumn.isStringColumn>
	@TableId(value = "${table.idColumn.sqlName}", type = IdType.ASSIGN_UUID)
	<#elseif table.idColumn.isIntegerNumber>
	@TableId(value = "${table.idColumn.sqlName}", type = IdType.AUTO)
	<#else>
	@TableId(value = "${table.idColumn.sqlName}", type = IdType.INPUT)
	</#if>
	private ${table.idColumn.javaType} ${table.idColumn.columnNameLower}; ${table.idColumn.sqlName};

<#list table.notPkColumns as column>
	<#if column.javaType == "LocalDateTime">
	/**
	 * ${column.columnAlias}
	 */
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	@TableField(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalDate">
	/**
	 * ${column.columnAlias}
	 */
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	@TableField(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalTime">
	/**
	 * ${column.columnAlias}
	 */
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	@TableField(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	<#else>
	<#if column.columnNameLower == "delFlag">
	/**
	 * ${column.columnAlias}
	 */
	@TableField(value = "${column.sqlName}")
	private DeleteFlag delFlag;

	<#else>
	/**
	 * ${column.columnAlias}
	 */
	@TableField(value = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	</#if>
	</#if>
</#list>

}