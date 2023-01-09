package com.cdf.data.aggregation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdf.data.aggregation.entity.CateData;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName CateDataMapper
 * @Description: TODO
 * @Author ghj
 * @Date 10/28/22
 **/
@Mapper
public interface CateDataMapper extends BaseMapper<CateData> {
    int updateBatch(List<CateData> list);

    int batchInsert(@Param("list") List<CateData> list);
}