<#include "/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
package ${basepackage}.${serviceModule}.api.response.${classNameLowerCase};

import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>根据id查询任意（包含已删除）${tableDesc}信息response</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}ByIdResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ${tableDesc}信息
     */
    @ApiModelProperty(value = "${tableDesc}信息")
    private ${className}VO ${classNameLower}VO;
}
