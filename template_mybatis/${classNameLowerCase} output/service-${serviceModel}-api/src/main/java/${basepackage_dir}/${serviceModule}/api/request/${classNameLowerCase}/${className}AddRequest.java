<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.api.request.${classNameLowerCase};

<#-- 根据不同的字段类型,引入对应需要的包,定义一些标识防止重复引入 -->
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
import ${basepackage}.${serviceModule}.api.request.${serviceModule?cap_first}BaseRequest;
import lombok.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>${tableDesc}新增参数</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}AddRequest extends ${serviceModule?cap_first}BaseRequest {
	private static final long serialVersionUID = 1L;

<#list table.notPkColumns as column>
	<#-- 遍历非主键,特殊处理LocalDateTime,LocalDate,LocalTime类型,增加需要的JSON序列化注解(自定义转换方式) -->
	<#if column.javaType == "LocalDateTime">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
		<#-- 根据字段属性,加上多个hibernate校验注解 -->
		<#if column.hibernateValidatorExprssion??  && column.hibernateValidatorExprssion != "">
			<#list column.hibernateValidatorExprssion?split(" ") as exp>
				<#if exp??  && exp != "" && !((exp == "@NotNull" || exp == "@NotBlank") && (column.columnNameLower == "createTime" || column.columnNameLower =="updateTime"))>
	${exp}
				</#if>
			</#list>
		</#if>
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalDate">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
		<#if column.hibernateValidatorExprssion??  && column.hibernateValidatorExprssion != "">
			<#list column.hibernateValidatorExprssion?split(" ") as exp>
				<#if exp??  && exp != "" && !((exp == "@NotNull" || exp == "@NotBlank") && (column.columnNameLower == "createTime" || column.columnNameLower =="updateTime"))>
	${exp}
				</#if>
			</#list>
		</#if>
	@JsonSerialize(using = CustomLocalDateSerializer.class)
	@JsonDeserialize(using = CustomLocalDateDeserializer.class)
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalTime">
	/**
	 * ${column.columnAlias}
	 */
	@ApiModelProperty(value = "${column.columnAlias}")
		<#if column.hibernateValidatorExprssion??  && column.hibernateValidatorExprssion != "">
			<#list column.hibernateValidatorExprssion?split(" ") as exp>
				<#if exp??  && exp != "" && !((exp == "@NotNull" || exp == "@NotBlank") && (column.columnNameLower == "createTime" || column.columnNameLower =="updateTime"))>
	${exp}
				</#if>
			</#list>
		</#if>
	@JsonSerialize(using = CustomLocalTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalTimeDeserializer.class)
	private ${column.javaType} ${column.columnNameLower};

	<#else>
		<#-- 若delFlag字段存在,表示假删除,则使用枚举DeleteFlag -->
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
			<#if column.hibernateValidatorExprssion??  && column.hibernateValidatorExprssion !="" && column.sqlTypeName != "TEXT">
				<#list column.hibernateValidatorExprssion?split(" ") as exp>
					<#if exp??  && exp != "" && !((exp == "@NotNull" || exp == "@NotBlank") && (column.columnNameLower =="createPerson" || column.columnNameLower =="updatePerson"))>
	${exp}
					</#if>
				</#list>
			</#if>
	private ${column.javaType} ${column.columnNameLower};

		</#if>
	</#if>
</#list>
}