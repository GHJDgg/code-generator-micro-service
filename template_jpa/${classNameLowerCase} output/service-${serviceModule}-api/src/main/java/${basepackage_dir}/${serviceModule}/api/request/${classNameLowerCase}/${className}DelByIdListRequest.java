<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.api.request.${classNameLowerCase};

import ${basepackage}.${serviceModule}.api.request.${serviceModule?cap_first}BaseRequest;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>批量删除${tableDesc}请求参数</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}DelByIdListRequest extends ${serviceModule?cap_first}BaseRequest {
	private static final long serialVersionUID = 1L;

	/**
	 * 批量删除-${table.idColumn.columnAlias}List
	 */
	@ApiModelProperty(value = "批量删除-${table.idColumn.columnAlias}List")
	@NotEmpty
	private List<${table.idColumn.javaType}> ${table.idColumn.columnNameLower}List;
}