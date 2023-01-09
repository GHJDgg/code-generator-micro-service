package com.data.catedata.service;

import com.common.util.StringUtil;
import com.wanmi.sbc.common.util.XssUtils;
import com.data.api.request.catedata.CateDataQueryRequest;
import com.data.catedata.model.root.CateData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>系统日志动态查询条件构建器</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
public class CateDataWhereCriteriaBuilder {
    public static Specification<CateData> build(CateDataQueryRequest queryRequest) {
        return (root, cquery, cbuild) -> {
            List<Predicate> predicates = new ArrayList<>();
            // 批量查询-idList
            if (CollectionUtils.isNotEmpty(queryRequest.getIdList())) {
                predicates.add(root.get("id").in(queryRequest.getIdList()));
            }

            // id
            if (queryRequest.getId() != null) {
                predicates.add(cbuild.equal(root.get("id"), queryRequest.getId()));
            }

            // 一级分类id
            if (queryRequest.getCateFirstParentId() != null) {
                predicates.add(cbuild.equal(root.get("cateFirstParentId"), queryRequest.getCateFirstParentId()));
            }

            // 模糊查询 - 一级分类名称
            if (StringUtils.isNotEmpty(queryRequest.getCateFirstParentName())) {
                predicates.add(cbuild.like(root.get("cateFirstParentName"), StringUtil.SQL_LIKE_CHAR
                        .concat(XssUtils.replaceLikeWildcard(queryRequest.getCateFirstParentName()))
                        .concat(StringUtil.SQL_LIKE_CHAR)));
            }

            // 二级分类id
            if (queryRequest.getCateSecondParentId() != null) {
                predicates.add(cbuild.equal(root.get("cateSecondParentId"), queryRequest.getCateSecondParentId()));
            }

            // 模糊查询 - 二级分类名称
            if (StringUtils.isNotEmpty(queryRequest.getCateSecondParentName())) {
                predicates.add(cbuild.like(root.get("cateSecondParentName"), StringUtil.SQL_LIKE_CHAR
                        .concat(XssUtils.replaceLikeWildcard(queryRequest.getCateSecondParentName()))
                        .concat(StringUtil.SQL_LIKE_CHAR)));
            }

            // 分类id
            if (queryRequest.getCateId() != null) {
                predicates.add(cbuild.equal(root.get("cateId"), queryRequest.getCateId()));
            }

            // 模糊查询 - 分类名称
            if (StringUtils.isNotEmpty(queryRequest.getCateName())) {
                predicates.add(cbuild.like(root.get("cateName"), StringUtil.SQL_LIKE_CHAR
                        .concat(XssUtils.replaceLikeWildcard(queryRequest.getCateName()))
                        .concat(StringUtil.SQL_LIKE_CHAR)));
            }

            // 销售额
            if (queryRequest.getTotalPrice() != null) {
                predicates.add(cbuild.equal(root.get("totalPrice"), queryRequest.getTotalPrice()));
            }

            // 销量
            if (queryRequest.getTotalCount() != null) {
                predicates.add(cbuild.equal(root.get("totalCount"), queryRequest.getTotalCount()));
            }

            // 订单量
            if (queryRequest.getOrderCount() != null) {
                predicates.add(cbuild.equal(root.get("orderCount"), queryRequest.getOrderCount()));
            }

            // 购买人数
            if (queryRequest.getBuyerCount() != null) {
                predicates.add(cbuild.equal(root.get("buyerCount"), queryRequest.getBuyerCount()));
            }

            // 大于或等于 搜索条件:创建时间开始
            if (queryRequest.getCreateTimeBegin() != null) {
                predicates.add(cbuild.greaterThanOrEqualTo(root.get("createTime"),
                        queryRequest.getCreateTimeBegin()));
            }
            // 小于或等于 搜索条件:创建时间截止
            if (queryRequest.getCreateTimeEnd() != null) {
                predicates.add(cbuild.lessThanOrEqualTo(root.get("createTime"),
                        queryRequest.getCreateTimeEnd()));
            }

            // 大于或等于 搜索条件:修改时间开始
            if (queryRequest.getUpdateTimeBegin() != null) {
                predicates.add(cbuild.greaterThanOrEqualTo(root.get("updateTime"),
                        queryRequest.getUpdateTimeBegin()));
            }
            // 小于或等于 搜索条件:修改时间截止
            if (queryRequest.getUpdateTimeEnd() != null) {
                predicates.add(cbuild.lessThanOrEqualTo(root.get("updateTime"),
                        queryRequest.getUpdateTimeEnd()));
            }

            // 大于或等于 搜索条件:日期开始
            if (queryRequest.getDayBegin() != null) {
                predicates.add(cbuild.greaterThanOrEqualTo(root.get("day"),
                        queryRequest.getDayBegin()));
            }
            // 小于或等于 搜索条件:日期截止
            if (queryRequest.getDayEnd() != null) {
                predicates.add(cbuild.lessThanOrEqualTo(root.get("day"),
                        queryRequest.getDayEnd()));
            }

            Predicate[] p = predicates.toArray(new Predicate[predicates.size()]);
            return p.length == 0 ? null : p.length == 1 ? p[0] : cbuild.and(p);
        };
    }
}
