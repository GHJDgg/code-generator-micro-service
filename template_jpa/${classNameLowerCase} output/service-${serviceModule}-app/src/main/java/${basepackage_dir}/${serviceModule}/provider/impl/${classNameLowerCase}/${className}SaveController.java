<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${serviceModule}.provider.impl.${classNameLowerCase};

import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import ${basepackage}.common.base.BaseResponse;
import ${basepackage}.common.util.KsBeanUtil;
import ${basepackage}.${serviceModule}.api.provider.${classNameLowerCase}.${className}SaveProvider;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}AddRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}AddResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}ModifyRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}ModifyResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}DelByIdRequest;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}DelByIdListRequest;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.service.${className}Service;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import javax.validation.Valid;

/**
 * <p>${tableDesc}保存服务接口实现</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@RestController
@Validated
public class ${className}SaveController implements ${className}SaveProvider {
	@Autowired
	private ${className}Service ${classNameFirstLower}Service;

	@Override
	public BaseResponse<${className}AddResponse> add(@RequestBody @Valid ${className}AddRequest ${classNameFirstLower}AddRequest) {
		${className} ${classNameFirstLower} = new ${className}();
		KsBeanUtil.copyPropertiesThird(${classNameFirstLower}AddRequest, ${classNameFirstLower});
		return BaseResponse.success(new ${className}AddResponse(
				${classNameFirstLower}Service.wrapperVo(${classNameFirstLower}Service.add(${classNameFirstLower}))));
	}

	@Override
	public BaseResponse<${className}ModifyResponse> modify(@RequestBody @Valid ${className}ModifyRequest ${classNameFirstLower}ModifyRequest) {
		${className} ${classNameFirstLower} = new ${className}();
		KsBeanUtil.copyPropertiesThird(${classNameFirstLower}ModifyRequest, ${classNameFirstLower});
		return BaseResponse.success(new ${className}ModifyResponse(
				${classNameFirstLower}Service.wrapperVo(${classNameFirstLower}Service.modify(${classNameFirstLower}))));
	}

	@Override
	public BaseResponse deleteById(@RequestBody @Valid ${className}DelByIdRequest ${classNameFirstLower}DelByIdRequest) {
		${classNameFirstLower}Service.deleteById(${classNameFirstLower}DelByIdRequest.get${table.idColumn.columnName}());
		return BaseResponse.SUCCESSFUL();
	}

	@Override
	public BaseResponse deleteByIdList(@RequestBody @Valid ${className}DelByIdListRequest ${classNameFirstLower}DelByIdListRequest) {
		${classNameFirstLower}Service.deleteByIdList(${classNameFirstLower}DelByIdListRequest.get${table.idColumn.columnName}List());
		return BaseResponse.SUCCESSFUL();
	}

}

