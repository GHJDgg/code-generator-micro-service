<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.api.request.${classNameLowerCase};

import ${basepackage}.${serviceModule}.api.request.${serviceModule?cap_first}BaseRequest;
import lombok.*;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>单个删除${tableDesc}请求参数</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}DelByIdRequest extends ${serviceModule?cap_first}BaseRequest {
	private static final long serialVersionUID = 1L;

	/**
	 * ${table.idColumn.columnAlias}
	 */
	@ApiModelProperty(value = "${table.idColumn.columnAlias}")
	@NotNull
	private ${table.idColumn.javaType} ${table.idColumn.columnNameLower};
}