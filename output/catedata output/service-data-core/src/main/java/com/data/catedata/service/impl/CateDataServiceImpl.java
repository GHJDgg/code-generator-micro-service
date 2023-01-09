package com.cdf.data.aggregation.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import com.cdf.data.aggregation.entity.CateData;
import com.cdf.data.aggregation.mapper.CateDataMapper;
import com.cdf.data.aggregation.service.CateDataService;

/**
 * @ClassName CateDataServiceImpl
 * @Description: TODO
 * @Author ghj
 * @Date 10/26/22
 **/
@Service
public class CateDataServiceImpl extends ServiceImpl<CateDataMapper, CateData> implements CateDataService {

    @Override
    public int updateBatch(List<CateData> list) {
        return baseMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<CateData> list) {
        return baseMapper.batchInsert(list);
    }
}



