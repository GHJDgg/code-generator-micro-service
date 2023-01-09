package com.data.api.provider.catedata;

import com.common.base.BaseResponse;
import com.data.api.request.catedata.CateDataPageRequest;
import com.data.api.response.catedata.CateDataPageResponse;
import com.data.api.request.catedata.CateDataListRequest;
import com.data.api.response.catedata.CateDataListResponse;
import com.data.api.request.catedata.CateDataByIdRequest;
import com.data.api.response.catedata.CateDataByIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

/**
 * <p>系统日志查询服务Provider</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@FeignClient(value = "${application.data.name}", contextId = "CateDataQueryProvider")
public interface CateDataQueryProvider {

	/**
	 * 分页查询系统日志API
	 *
	 * @author ghj
	 * @param cateDataPageReq 分页请求参数和筛选对象 {@link CateDataPageRequest}
	 * @return 系统日志分页列表信息 {@link CateDataPageResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/page")
	BaseResponse<CateDataPageResponse> page(@RequestBody @Valid CateDataPageRequest cateDataPageReq);

	/**
	 * 列表查询系统日志API
	 *
	 * @author ghj
	 * @param cateDataListReq 列表请求参数和筛选对象 {@link CateDataListRequest}
	 * @return 系统日志的列表信息 {@link CateDataListResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/list")
	BaseResponse<CateDataListResponse> list(@RequestBody @Valid CateDataListRequest cateDataListReq);

	/**
	 * 单个查询系统日志API
	 *
	 * @author ghj
	 * @param cateDataByIdRequest 单个查询系统日志请求参数 {@link CateDataByIdRequest}
	 * @return 系统日志详情 {@link CateDataByIdResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/get-by-id")
	BaseResponse<CateDataByIdResponse> getById(@RequestBody @Valid CateDataByIdRequest cateDataByIdRequest);

}

