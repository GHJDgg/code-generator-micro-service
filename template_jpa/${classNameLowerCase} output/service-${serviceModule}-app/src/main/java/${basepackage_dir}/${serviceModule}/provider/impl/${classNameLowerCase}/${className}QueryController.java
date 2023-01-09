<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign pkJavaType = table.idColumn.javaType>
package ${basepackage}.${serviceModule}.provider.impl.${classNameLowerCase};

import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import ${basepackage}.common.base.BaseResponse;
import ${basepackage}.common.base.MicroServicePage;
import ${basepackage}.common.util.KsBeanUtil;
import ${basepackage}.${serviceModule}.api.provider.${classNameLowerCase}.${className}QueryProvider;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}PageRequest;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}QueryRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}PageResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}ListRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}ListResponse;
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}ByIdRequest;
import ${basepackage}.${serviceModule}.api.response.${classNameLowerCase}.${className}ByIdResponse;
import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.service.${className}Service;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>${tableDesc}查询服务接口实现</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@RestController
@Validated
public class ${className}QueryController implements ${className}QueryProvider {
	@Autowired
	private ${className}Service ${classNameFirstLower}Service;

	@Override
	public BaseResponse<${className}PageResponse> page(@RequestBody @Valid ${className}PageRequest ${classNameFirstLower}PageReq) {
		${className}QueryRequest queryReq = new ${className}QueryRequest();
		KsBeanUtil.copyPropertiesThird(${classNameFirstLower}PageReq, queryReq);
		Page<${className}> ${classNameFirstLower}Page = ${classNameFirstLower}Service.page(queryReq);
		Page<${className}VO> newPage = ${classNameFirstLower}Page.map(entity -> ${classNameFirstLower}Service.wrapperVo(entity));
		MicroServicePage<${className}VO> microPage = new MicroServicePage<>(newPage, ${classNameFirstLower}PageReq.getPageable());
		${className}PageResponse finalRes = new ${className}PageResponse(microPage);
		return BaseResponse.success(finalRes);
	}

	@Override
	public BaseResponse<${className}ListResponse> list(@RequestBody @Valid ${className}ListRequest ${classNameFirstLower}ListReq) {
		${className}QueryRequest queryReq = new ${className}QueryRequest();
		KsBeanUtil.copyPropertiesThird(${classNameFirstLower}ListReq, queryReq);
		List<${className}> ${classNameFirstLower}List = ${classNameFirstLower}Service.list(queryReq);
		List<${className}VO> newList = ${classNameFirstLower}List.stream().map(entity -> ${classNameFirstLower}Service.wrapperVo(entity)).collect(Collectors.toList());
		return BaseResponse.success(new ${className}ListResponse(newList));
	}

	@Override
	public BaseResponse<${className}ByIdResponse> getById(@RequestBody @Valid ${className}ByIdRequest ${classNameFirstLower}ByIdRequest) {
		${className} ${classNameFirstLower} = ${classNameFirstLower}Service.getById(${classNameFirstLower}ByIdRequest.get${table.idColumn.columnName}());
		return BaseResponse.success(new ${className}ByIdResponse(${classNameFirstLower}Service.wrapperVo(${classNameFirstLower})));
	}

}

