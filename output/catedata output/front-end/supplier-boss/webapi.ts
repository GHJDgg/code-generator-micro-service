import { Fetch } from 'qmkit';

/**
 * 查询列表
 */
export function getPage(params) {
  return Fetch<TResult>('/catedata/page', {
    method: 'POST',
    body: JSON.stringify(params)
  });
}

/**
 * 单个查询
 */
export function getById(id) {
  return Fetch<TResult>(`/catedata/${id}`, {
    method: 'GET',
  });
}

/**
 * 添加
 */
export function add(info) {
  return Fetch<TResult>('/catedata/add', {
    method: 'POST',
    body: JSON.stringify(info)
  });
}

/**
 * 修改
 */
export function modify(info) {
  return Fetch<TResult>('/catedata/modify', {
    method: 'PUT',
    body: JSON.stringify(info)
  });
}

/**
 * 单个删除
 */
export function deleteById(id) {
  return Fetch<TResult>(`/catedata/${id}`, {
    method: 'DELETE',
  });
}

/**
 * 批量删除
 */
export function deleteByIdList(idList) {
	return Fetch<TResult>('/catedata/delete-by-id-list', {
		method: 'DELETE',
		body: JSON.stringify({idList: idList})
	});
}