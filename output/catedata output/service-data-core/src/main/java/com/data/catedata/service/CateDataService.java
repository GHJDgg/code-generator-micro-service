package com.data.catedata.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.data.catedata.repository.CateDataRepository;
import com.data.catedata.model.root.CateData;
import com.data.api.request.catedata.CateDataQueryRequest;
import com.data.bean.vo.CateDataVO;
import com.common.util.KsBeanUtil;
import java.util.List;

/**
 * <p>系统日志业务逻辑</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@Service("CateDataService")
public class CateDataService {
	@Autowired
	private CateDataRepository cateDataRepository;
	
	/** 
	 * 新增系统日志
	 * @author ghj
	 */
	@Transactional
	public CateData add(CateData entity) {
		cateDataRepository.save(entity);
		return entity;
	}
	
	/** 
	 * 修改系统日志
	 * @author ghj
	 */
	@Transactional
	public CateData modify(CateData entity) {
		cateDataRepository.save(entity);
		return entity;
	}

	/**
	 * 单个删除系统日志
	 * @author ghj
	 */
	@Transactional
	public void deleteById(Long id) {
		cateDataRepository.delete(id);
	}
	
	/** 
	 * 批量删除系统日志
	 * @author ghj
	 */
	@Transactional
	public void deleteByIdList(List<Long> ids) {
		ids.forEach(id -> cateDataRepository.delete(id));
	}
	
	/** 
	 * 单个查询系统日志
	 * @author ghj
	 */
	public CateData getById(Long id){
		return cateDataRepository.findById(id).orElse(null);
	}
	
	/** 
	 * 分页查询系统日志
	 * @author ghj
	 */
	public Page<CateData> page(CateDataQueryRequest queryReq){
		return cateDataRepository.findAll(
				CateDataWhereCriteriaBuilder.build(queryReq),
				queryReq.getPageRequest());
	}
	
	/** 
	 * 列表查询系统日志
	 * @author ghj
	 */
	public List<CateData> list(CateDataQueryRequest queryReq){
		return cateDataRepository.findAll(
				CateDataWhereCriteriaBuilder.build(queryReq),
				queryReq.getSort());
	}

	/**
	 * 将实体包装成VO
	 * @author ghj
	 */
	public CateDataVO wrapperVo(CateData cateData) {
		if (cateData != null){
			CateDataVO cateDataVO=new CateDataVO();
			KsBeanUtil.copyPropertiesThird(cateData,cateDataVO);
			return cateDataVO;
		}
		return null;
	}
}
