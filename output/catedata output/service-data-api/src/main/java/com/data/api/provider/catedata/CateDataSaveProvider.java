package com.data.api.provider.catedata;

import com.common.base.BaseResponse;
import com.data.api.request.catedata.CateDataAddRequest;
import com.data.api.response.catedata.CateDataAddResponse;
import com.data.api.request.catedata.CateDataModifyRequest;
import com.data.api.response.catedata.CateDataModifyResponse;
import com.data.api.request.catedata.CateDataDelByIdRequest;
import com.data.api.request.catedata.CateDataDelByIdListRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

/**
 * <p>系统日志保存服务Provider</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@FeignClient(value = "${application.data.name}", contextId = "CateDataSaveProvider")
public interface CateDataSaveProvider {

	/**
	 * 新增系统日志API
	 *
	 * @author ghj
	 * @param cateDataAddRequest 系统日志新增参数结构 {@link CateDataAddRequest}
	 * @return 新增的系统日志信息 {@link CateDataAddResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/add")
	BaseResponse<CateDataAddResponse> add(@RequestBody @Valid CateDataAddRequest cateDataAddRequest);

	/**
	 * 修改系统日志API
	 *
	 * @author ghj
	 * @param cateDataModifyRequest 系统日志修改参数结构 {@link CateDataModifyRequest}
	 * @return 修改的系统日志信息 {@link CateDataModifyResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/modify")
	BaseResponse<CateDataModifyResponse> modify(@RequestBody @Valid CateDataModifyRequest cateDataModifyRequest);

	/**
	 * 单个删除系统日志API
	 *
	 * @author ghj
	 * @param cateDataDelByIdRequest 单个删除参数结构 {@link CateDataDelByIdRequest}
	 * @return 删除结果 {@link BaseResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/delete-by-id")
	BaseResponse deleteById(@RequestBody @Valid CateDataDelByIdRequest cateDataDelByIdRequest);

	/**
	 * 批量删除系统日志API
	 *
	 * @author ghj
	 * @param cateDataDelByIdListRequest 批量删除参数结构 {@link CateDataDelByIdListRequest}
	 * @return 删除结果 {@link BaseResponse}
	 */
	@PostMapping("/data/${application.data.version}/catedata/delete-by-id-list")
	BaseResponse deleteByIdList(@RequestBody @Valid CateDataDelByIdListRequest cateDataDelByIdListRequest);

}

