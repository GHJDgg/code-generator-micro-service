package com.data.catedata.repository;

import com.data.catedata.model.root.CateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * <p>系统日志DAO</p>
 * @author ghj
 * @date 2023-01-04 14:50:19
 */
@Repository
public interface CateDataRepository extends JpaRepository<CateData, Long>,
        JpaSpecificationExecutor<CateData> {

}
