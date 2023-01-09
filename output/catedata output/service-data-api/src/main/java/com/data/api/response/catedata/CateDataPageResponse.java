package com.data.api.response.catedata;

import com.common.base.MicroServicePage;
import com.data.bean.vo.CateDataVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>系统日志分页结果</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@ApiModel
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CateDataPageResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 系统日志分页结果
     */
    @ApiModelProperty(value = "系统日志分页结果")
    private MicroServicePage<CateDataVO> cateDataVOPage;
}
