package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ExpertForcastDataViewMapper;
import xsscd.monitor.air.southwest.modules.job.service.ExpertForcastDataViewService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ExpertForcastDataViewServiceImpl implements ExpertForcastDataViewService {
    @Autowired
    private ExpertForcastDataViewMapper mapper;
    @Override
    public List<ExpertForcastDataView> select(ExpertForcastDataView expertForcastDataViewView) {
        return mapper.select(expertForcastDataViewView);
    }

    @Override
    public void insert(ExpertForcastDataView expertForcastDataViewView) {
        mapper.insert(expertForcastDataViewView);
    }

    @Override
    public void insertSelective(ExpertForcastDataView expertForcastDataViewView) {
        mapper.insertSelective(expertForcastDataViewView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<ExpertForcastDataView> selectByPrimaryKey(SelectPiontVO expertForcastDataViewView) {
        return mapper.selectByPrimaryKey(expertForcastDataViewView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<ExpertForcastDataView> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ExpertForcastDataView expertForcastDataViewView) {
        mapper.updateByPrimaryKeySelective(expertForcastDataViewView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
