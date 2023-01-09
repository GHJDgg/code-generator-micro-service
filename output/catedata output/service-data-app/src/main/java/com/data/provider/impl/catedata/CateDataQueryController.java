package com.data.provider.impl.catedata;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import com.common.base.BaseResponse;
import com.common.base.MicroServicePage;
import com.common.util.KsBeanUtil;
import com.data.api.provider.catedata.CateDataQueryProvider;
import com.data.api.request.catedata.CateDataPageRequest;
import com.data.api.request.catedata.CateDataQueryRequest;
import com.data.api.response.catedata.CateDataPageResponse;
import com.data.api.request.catedata.CateDataListRequest;
import com.data.api.response.catedata.CateDataListResponse;
import com.data.api.request.catedata.CateDataByIdRequest;
import com.data.api.response.catedata.CateDataByIdResponse;
import com.data.bean.vo.CateDataVO;
import com.data.catedata.service.CateDataService;
import com.data.catedata.model.root.CateData;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>系统日志查询服务接口实现</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@RestController
@Validated
public class CateDataQueryController implements CateDataQueryProvider {
	@Autowired
	private CateDataService cateDataService;

	@Override
	public BaseResponse<CateDataPageResponse> page(@RequestBody @Valid CateDataPageRequest cateDataPageReq) {
		CateDataQueryRequest queryReq = new CateDataQueryRequest();
		KsBeanUtil.copyPropertiesThird(cateDataPageReq, queryReq);
		Page<CateData> cateDataPage = cateDataService.page(queryReq);
		Page<CateDataVO> newPage = cateDataPage.map(entity -> cateDataService.wrapperVo(entity));
		MicroServicePage<CateDataVO> microPage = new MicroServicePage<>(newPage, cateDataPageReq.getPageable());
		CateDataPageResponse finalRes = new CateDataPageResponse(microPage);
		return BaseResponse.success(finalRes);
	}

	@Override
	public BaseResponse<CateDataListResponse> list(@RequestBody @Valid CateDataListRequest cateDataListReq) {
		CateDataQueryRequest queryReq = new CateDataQueryRequest();
		KsBeanUtil.copyPropertiesThird(cateDataListReq, queryReq);
		List<CateData> cateDataList = cateDataService.list(queryReq);
		List<CateDataVO> newList = cateDataList.stream().map(entity -> cateDataService.wrapperVo(entity)).collect(Collectors.toList());
		return BaseResponse.success(new CateDataListResponse(newList));
	}

	@Override
	public BaseResponse<CateDataByIdResponse> getById(@RequestBody @Valid CateDataByIdRequest cateDataByIdRequest) {
		CateData cateData = cateDataService.getById(cateDataByIdRequest.getId());
		return BaseResponse.success(new CateDataByIdResponse(cateDataService.wrapperVo(cateData)));
	}

}

