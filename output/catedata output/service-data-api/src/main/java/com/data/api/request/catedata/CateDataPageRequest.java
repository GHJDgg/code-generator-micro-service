package com.data.api.request.catedata;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.common.util.CustomLocalDateTimeSerializer;
import com.common.util.CustomLocalDateTimeDeserializer;
import java.time.LocalDateTime;
import com.common.base.BaseQueryRequest;
import lombok.*;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>系统日志分页查询请求参数</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CateDataPageRequest extends BaseQueryRequest {
	private static final long serialVersionUID = 1L;

	/**
	 * 批量查询-idList
	 */
	@ApiModelProperty(value = "批量查询-idList")
	private List<Long> idList;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 一级分类id
	 */
	@ApiModelProperty(value = "一级分类id")
	private Long cateFirstParentId;

	/**
	 * 一级分类名称
	 */
	@ApiModelProperty(value = "一级分类名称")
	private String cateFirstParentName;

	/**
	 * 二级分类id
	 */
	@ApiModelProperty(value = "二级分类id")
	private Long cateSecondParentId;

	/**
	 * 二级分类名称
	 */
	@ApiModelProperty(value = "二级分类名称")
	private String cateSecondParentName;

	/**
	 * 分类id
	 */
	@ApiModelProperty(value = "分类id")
	private Long cateId;

	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String cateName;

	/**
	 * 销售额
	 */
	@ApiModelProperty(value = "销售额")
	private BigDecimal totalPrice;

	/**
	 * 销量
	 */
	@ApiModelProperty(value = "销量")
	private Long totalCount;

	/**
	 * 订单量
	 */
	@ApiModelProperty(value = "订单量")
	private Long orderCount;

	/**
	 * 购买人数
	 */
	@ApiModelProperty(value = "购买人数")
	private Long buyerCount;

	/**
	 * 搜索条件:创建时间开始
	 */
	@ApiModelProperty(value = "搜索条件:创建时间开始")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createTimeBegin;
	/**
	 * 搜索条件:创建时间截止
	 */
	@ApiModelProperty(value = "搜索条件:创建时间截止")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createTimeEnd;

	/**
	 * 搜索条件:修改时间开始
	 */
	@ApiModelProperty(value = "搜索条件:修改时间开始")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime updateTimeBegin;
	/**
	 * 搜索条件:修改时间截止
	 */
	@ApiModelProperty(value = "搜索条件:修改时间截止")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime updateTimeEnd;

	/**
	 * 搜索条件:日期开始
	 */
	@ApiModelProperty(value = "搜索条件:日期开始")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime dayBegin;
	/**
	 * 搜索条件:日期截止
	 */
	@ApiModelProperty(value = "搜索条件:日期截止")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime dayEnd;

}