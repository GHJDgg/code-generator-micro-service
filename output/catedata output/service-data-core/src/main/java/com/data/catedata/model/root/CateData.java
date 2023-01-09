		package com.data.catedata.model.root;

import java.math.BigDecimal;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import java.time.LocalDateTime;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>系统日志实体类</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@Data
@Entity
@Table(name = "cate_data")
public class CateData implements Serializable {
private static final long serialVersionUID = 1L;

/**
 * id
 */
@Id
@GeneratedValue
@Column(name = "id")
private Long id;

/**
 * 一级分类id
 */
@Column(name = "cate_first_parent_id")
private Long cateFirstParentId;

/**
 * 一级分类名称
 */
@Column(name = "cate_first_parent_name")
private String cateFirstParentName;

/**
 * 二级分类id
 */
@Column(name = "cate_second_parent_id")
private Long cateSecondParentId;

/**
 * 二级分类名称
 */
@Column(name = "cate_second_parent_name")
private String cateSecondParentName;

/**
 * 分类id
 */
@Column(name = "cate_id")
private Long cateId;

/**
 * 分类名称
 */
@Column(name = "cate_name")
private String cateName;

/**
 * 销售额
 */
@Column(name = "total_price")
private BigDecimal totalPrice;

/**
 * 销量
 */
@Column(name = "total_count")
private Long totalCount;

/**
 * 订单量
 */
@Column(name = "order_count")
private Long orderCount;

/**
 * 购买人数
 */
@Column(name = "buyer_count")
private Long buyerCount;

/**
 * 创建时间
 */
@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
@Column(name = "create_time")
private LocalDateTime createTime;

/**
 * 修改时间
 */
@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
@Column(name = "update_time")
private LocalDateTime updateTime;

/**
 * 日期
 */
@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
@Column(name = "day")
private LocalDateTime day;

		}