<#assign className = table.className>
<#assign classNameFirstLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
import { Fetch } from 'qmkit';

/**
 * 查询列表
 */
export function getPage(params) {
  return Fetch<TResult>('/${classNameLowerCase}/page', {
    method: 'POST',
    body: JSON.stringify(params)
  });
}

/**
 * 单个查询
 */
export function getById(id) {
  return Fetch<TResult>(`/${classNameLowerCase}/<@jspEl 'id'/>`, {
    method: 'GET',
  });
}

/**
 * 添加
 */
export function add(info) {
  return Fetch<TResult>('/${classNameLowerCase}/add', {
    method: 'POST',
    body: JSON.stringify(info)
  });
}

/**
 * 修改
 */
export function modify(info) {
  return Fetch<TResult>('/${classNameLowerCase}/modify', {
    method: 'PUT',
    body: JSON.stringify(info)
  });
}

/**
 * 单个删除
 */
export function deleteById(id) {
  return Fetch<TResult>(`/${classNameLowerCase}/<@jspEl 'id'/>`, {
    method: 'DELETE',
  });
}

/**
 * 批量删除
 */
export function deleteByIdList(idList) {
	return Fetch<TResult>('/${classNameLowerCase}/delete-by-id-list', {
		method: 'DELETE',
		body: JSON.stringify({${table.idColumn.columnNameLower}List: idList})
	});
}