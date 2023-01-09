<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root;

<#assign dateColumnFlag=false dateTimeFlag=false dateFlag=false timeFlag=false decimalFlag=false>
<#list table.columns as column>
	<#if column.isDateTimeColumn && !dateColumnFlag>
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
		<#assign dateColumnFlag=true>
	</#if>
	<#if column.javaType == "LocalDateTime" && !dateTimeFlag>
import java.time.LocalDateTime;
		<#assign dateTimeFlag=true>
	<#elseif column.javaType == "LocalDate" && !dateFlag>
import java.time.LocalDate;
		<#assign dateFlag=true>
	<#elseif column.javaType == "LocalTime" && !timeFlag>
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
import javax.persistence.*;
<#if table.idColumn.isStringColumn>
import org.hibernate.annotations.GenericGenerator;
</#if>
import java.io.Serializable;

/**
 * <p>${tableDesc}实体类</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Data
@Entity
@Table(name = "${table.sqlName}")
public class ${className} implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ${table.idColumn.columnAlias}
	 */
	@Id
	<#if table.idColumn.isStringColumn>@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")<#else>@GeneratedValue</#if>
	@Column(name = "${table.idColumn.sqlName}")
	private ${table.idColumn.javaType} ${table.idColumn.columnNameLower};

<#list table.notPkColumns as column>
	<#if column.javaType == "LocalDateTime">
	/**
	 * ${column.columnAlias}
	 */
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalDate">
	/**
	 * ${column.columnAlias}
	 */
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	<#elseif column.javaType == "LocalTime">
	/**
	 * ${column.columnAlias}
	 */
	@Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

	<#else>
		<#if column.columnNameLower == "delFlag">
	/**
	 * ${column.columnAlias}
	 */
	@Column(name = "${column.sqlName}")
	@Enumerated
	private DeleteFlag delFlag;

		<#else>
	/**
	 * ${column.columnAlias}
	 */
	@Column(name = "${column.sqlName}")
	private ${column.javaType} ${column.columnNameLower};

		</#if>
	</#if>
</#list>
}