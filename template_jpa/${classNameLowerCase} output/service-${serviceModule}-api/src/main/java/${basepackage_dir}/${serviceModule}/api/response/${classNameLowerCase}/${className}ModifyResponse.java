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
 * <p>${tableDesc}修改结果</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}ModifyResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 已修改的${tableDesc}信息
     */
    @ApiModelProperty(value = "已修改的${tableDesc}信息")
    private ${className}VO ${classNameLower}VO;
}
