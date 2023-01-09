<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${serviceModule}.api.provider.${classNameLowerCase};

import ${basepackage}.common.base.BaseResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}PageRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}PageResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}ListRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}ListResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}ByIdRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}ByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

/**
 * <p>${tableDesc}查询服务Provider</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@FeignClient(value = "<@jspEl 'application.${serviceModule}.name'/>", contextId = "${className}QueryProvider")
public interface ${className}QueryProvider {

	/**
	 * 分页查询${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}PageReq 分页请求参数和筛选对象 {@link ${className}PageRequest}
	 * @return ${tableDesc}分页列表信息 {@link ${className}PageResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/page")
	BaseResponse<${className}PageResponse> page(@RequestBody @Valid ${className}PageRequest ${classNameFirstLower}PageReq);

	/**
	 * 列表查询${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}ListReq 列表请求参数和筛选对象 {@link ${className}ListRequest}
	 * @return ${tableDesc}的列表信息 {@link ${className}ListResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/list")
	BaseResponse<${className}ListResponse> list(@RequestBody @Valid ${className}ListRequest ${classNameFirstLower}ListReq);

	/**
	 * 单个查询${tableDesc}API
	 *
	 * @author ${rapidAuthor}
	 * @param ${classNameFirstLower}ByIdRequest 单个查询${tableDesc}请求参数 {@link ${className}ByIdRequest}
	 * @return ${tableDesc}详情 {@link ${className}ByIdResponse}
	 */
	@PostMapping("/${serviceModule}/<@jspEl 'application.${serviceModule}.version'/>/${classNameLowerCase}/get-by-id")
	BaseResponse<${className}ByIdResponse> getById(@RequestBody @Valid ${className}ByIdRequest ${classNameFirstLower}ByIdRequest);

}

