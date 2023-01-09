<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${serviceModule}.api.provider.${classNameLowerCase};


import ${basepackage}.${serviceModule}.common.base.BaseResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}AddRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}AddResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}ModifyRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}ModifyResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}DelByIdRequest;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}DelByIdListRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

/**
 * <p>${tableDesc}保存服务Provider</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@FeignClient(value = "<@jspEl 'application.${serviceModule}.name'/>", contextId = "${className}SaveProvider")
public interface ${className}SaveProvider {

	/**
	 * 新增${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}AddRequest ${tableDesc}新增参数结构 {@link ${className}AddRequest}
	 * @return 新增的${tableDesc}信息 {@link ${className}AddResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/add")
	BaseResponse<${className}AddResponse> add(@RequestBody @Valid ${className}AddRequest ${classNameFirstLower}AddRequest);

	/**
	 * 修改${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}ModifyRequest ${tableDesc}修改参数结构 {@link ${className}ModifyRequest}
	 * @return 修改的${tableDesc}信息 {@link ${className}ModifyResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/modify")
	BaseResponse<${className}ModifyResponse> modify(@RequestBody @Valid ${className}ModifyRequest ${classNameFirstLower}ModifyRequest);

	/**
	 * 单个删除${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}DelByIdRequest 单个删除参数结构 {@link ${className}DelByIdRequest}
	 * @return 删除结果 {@link BaseResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/delete-by-id")
	BaseResponse deleteById(@RequestBody @Valid ${className}DelByIdRequest ${classNameFirstLower}DelByIdRequest);

	/**
	 * 批量删除${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}DelByIdListRequest 批量删除参数结构 {@link ${className}DelByIdListRequest}
	 * @return 删除结果 {@link BaseResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/delete-by-id-list")
	BaseResponse deleteByIdList(@RequestBody @Valid ${className}DelByIdListRequest ${classNameFirstLower}DelByIdListRequest);

}

