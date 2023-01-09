<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.api.response.${classNameLowerCase};

import ${basepackage}.common.base.MicroServicePage;
import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>${tableDesc}分页结果</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}PageResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ${tableDesc}分页结果
     */
    @ApiModelProperty(value = "${tableDesc}分页结果")
    private MicroServicePage<${className}VO> ${classNameLower}VOPage;
}
