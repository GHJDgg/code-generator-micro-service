<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign delFlag=false>
<#list table.columns as column>
	<#if column.columnNameLower == "delFlag">
		<#assign delFlag=true>
		<#break>
	</#if>
</#list>
package ${basepackage}.${serviceModule}.${classNameLowerCase}.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.repository.${className}Repository;
import ${basepackage}.${serviceModule}.${classNameLowerCase}.model.root.${className};
import ${basepackage}.${serviceModule}.api.request.${classNameLowerCase}.${className}QueryRequest;
import ${basepackage}.${serviceModule}.bean.vo.${className}VO;
import ${basepackage}.common.util.KsBeanUtil;
import java.util.List;

/**
 * <p>${tableDesc}业务逻辑</p>
 * @author ${rapidAuthor}
 * @date <#if now??>${now?string('yyyy-MM-dd HH:mm:ss')}</#if>
 */
@Service("${className}Service")
public class ${className}Service {
	@Autowired
	private ${className}Repository ${classNameLower}Repository;
	
	/** 
	 * 新增${tableDesc}
	 * @author ${rapidAuthor}
	 */
	@Transactional
	public ${className} add(${className} entity) {
		${classNameLower}Repository.save(entity);
		return entity;
	}
	
	/** 
	 * 修改${tableDesc}
	 * @author ${rapidAuthor}
	 */
	@Transactional
	public ${className} modify(${className} entity) {
		${classNameLower}Repository.save(entity);
		return entity;
	}

	/**
	 * 单个删除${tableDesc}
	 * @author ${rapidAuthor}
	 */
	@Transactional
	public void deleteById(${table.idColumn.javaType} id) {
<#if delFlag>
		${classNameLower}Repository.deleteById(id);
<#else>
		${classNameLower}Repository.delete(id);
</#if>
	}
	
	/** 
	 * 批量删除${tableDesc}
	 * @author ${rapidAuthor}
	 */
	@Transactional
	public void deleteByIdList(List<${table.idColumn.javaType}> ids) {
<#if delFlag>
		${classNameLower}Repository.deleteByIdList(ids);
<#else>
		ids.forEach(id -> ${classNameLower}Repository.delete(id));
</#if>
	}
	
	/** 
	 * 单个查询${tableDesc}
	 * @author ${rapidAuthor}
	 */
	public ${className} getById(${table.idColumn.javaType} id){
		return ${classNameLower}Repository.findById(id).orElse(null);
	}
	
	/** 
	 * 分页查询${tableDesc}
	 * @author ${rapidAuthor}
	 */
	public Page<${className}> page(${className}QueryRequest queryReq){
		return ${classNameLower}Repository.findAll(
				${className}WhereCriteriaBuilder.build(queryReq),
				queryReq.getPageRequest());
	}
	
	/** 
	 * 列表查询${tableDesc}
	 * @author ${rapidAuthor}
	 */
	public List<${className}> list(${className}QueryRequest queryReq){
		return ${classNameLower}Repository.findAll(
				${className}WhereCriteriaBuilder.build(queryReq),
				queryReq.getSort());
	}

	/**
	 * 将实体包装成VO
	 * @author ${rapidAuthor}
	 */
	public ${className}VO wrapperVo(${className} ${classNameLower}) {
		if (${classNameLower} != null){
			${className}VO ${classNameLower}VO=new ${className}VO();
			KsBeanUtil.copyPropertiesThird(${classNameLower},${classNameLower}VO);
			return ${classNameLower}VO;
		}
		return null;
	}
}
