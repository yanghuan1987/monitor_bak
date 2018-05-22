package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.datasource.DataSourceType;
import xsscd.monitor.air.southwest.datasource.EasymisDataSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataView;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.MediumLongVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForcastDataViewMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForcastDataViewService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForcastDataViewServiceImpl implements ForcastDataViewService {
    @Autowired
    private ForcastDataViewMapper mapper;
    @Override
    public List<ForcastDataView> select(ForcastDataView forcastDataViewView) {
        return mapper.select(forcastDataViewView);
    }

    @Override
	public List<ForcastDataView> selectExport(MediumLongVO mediumLongVO) {
		return mapper.selectExport(mediumLongVO);
	}

	@Override
    public void insert(ForcastDataView forcastDataViewView) {
        mapper.insert(forcastDataViewView);
    }

    @Override
    public void insertSelective(ForcastDataView forcastDataViewView) {
        mapper.insertSelective(forcastDataViewView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public List<ForcastDataView> selectByPrimaryKey(SelectPiontVO forcastDataViewView) {
        return mapper.selectByPrimaryKey(forcastDataViewView);
    }

    @Override
    @EasymisDataSource(DataSourceType.Master)
    public void saveBatch(List<ForcastDataView> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ForcastDataView forcastDataViewView) {
        mapper.updateByPrimaryKeySelective(forcastDataViewView);
    }

    @Override
    public void deleteByPrimaryKey(SelectPiontVO selectPiontVO) {
        mapper.deleteByPrimaryKey(selectPiontVO);
    }
}
