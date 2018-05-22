package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvlCountSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ExpertAirLvlCountSourceMapper;
import xsscd.monitor.air.southwest.modules.job.service.ExpertAirLvlCountSourceService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ExpertAirLvlCountSourceServiceImpl implements ExpertAirLvlCountSourceService {
    @Autowired
    private ExpertAirLvlCountSourceMapper mapper;
    @Override
    @EasymisDataSource(DataSourceType.Slave)
    public List<ExpertAirLvlCountSource> select(SelectPiontVO ExpertAirLvlCountSourceView) {
        return mapper.select(ExpertAirLvlCountSourceView);
    }
}
