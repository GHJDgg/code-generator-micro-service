package com.data.bean.vo;

import java.math.BigDecimal;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.common.util.CustomLocalDateTimeSerializer;
import com.common.util.CustomLocalDateTimeDeserializer;
import java.time.LocalDateTime;
import lombok.Data;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>系统日志VO</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@ApiModel
@Data
public class CateDataVO implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime updateTime;

	/**
	 * 日期
	 */
	@ApiModelProperty(value = "日期")
	@JsonSerialize(using = CustomLocalDateTimeSerializer.class)
	@JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
	private LocalDateTime day;

}