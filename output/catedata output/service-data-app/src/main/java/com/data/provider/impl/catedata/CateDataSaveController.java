package com.data.provider.impl.catedata;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.common.base.BaseResponse;
import com.common.util.KsBeanUtil;
import com.data.api.provider.catedata.CateDataSaveProvider;
import com.data.api.request.catedata.CateDataAddRequest;
import com.data.api.response.catedata.CateDataAddResponse;
import com.data.api.request.catedata.CateDataModifyRequest;
import com.data.api.response.catedata.CateDataModifyResponse;
import com.data.api.request.catedata.CateDataDelByIdRequest;
import com.data.api.request.catedata.CateDataDelByIdListRequest;
import com.data.catedata.service.CateDataService;
import com.data.catedata.model.root.CateData;
import javax.validation.Valid;

/**
 * <p>系统日志保存服务接口实现</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@RestController
@Validated
public class CateDataSaveController implements CateDataSaveProvider {
	@Autowired
	private CateDataService cateDataService;

	@Override
	public BaseResponse<CateDataAddResponse> add(@RequestBody @Valid CateDataAddRequest cateDataAddRequest) {
		CateData cateData = new CateData();
		KsBeanUtil.copyPropertiesThird(cateDataAddRequest, cateData);
		return BaseResponse.success(new CateDataAddResponse(
				cateDataService.wrapperVo(cateDataService.add(cateData))));
	}

	@Override
	public BaseResponse<CateDataModifyResponse> modify(@RequestBody @Valid CateDataModifyRequest cateDataModifyRequest) {
		CateData cateData = new CateData();
		KsBeanUtil.copyPropertiesThird(cateDataModifyRequest, cateData);
		return BaseResponse.success(new CateDataModifyResponse(
				cateDataService.wrapperVo(cateDataService.modify(cateData))));
	}

	@Override
	public BaseResponse deleteById(@RequestBody @Valid CateDataDelByIdRequest cateDataDelByIdRequest) {
		cateDataService.deleteById(cateDataDelByIdRequest.getId());
		return BaseResponse.SUCCESSFUL();
	}

	@Override
	public BaseResponse deleteByIdList(@RequestBody @Valid CateDataDelByIdListRequest cateDataDelByIdListRequest) {
		cateDataService.deleteByIdList(cateDataDelByIdListRequest.getIdList());
		return BaseResponse.SUCCESSFUL();
	}

}

