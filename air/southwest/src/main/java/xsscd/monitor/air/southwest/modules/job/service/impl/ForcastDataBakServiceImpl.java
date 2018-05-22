package xsscd.monitor.air.southwest.modules.job.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ForcastDataBak;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper.ForcastDataBakMapper;
import xsscd.monitor.air.southwest.modules.job.service.ForcastDataBakService;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */
@Service
public class ForcastDataBakServiceImpl implements ForcastDataBakService {
    @Autowired
    private ForcastDataBakMapper mapper;
    @Override
    public List<ForcastDataBak> select(ForcastDataBak forcastDataBakView) {
        return mapper.select(forcastDataBakView);
    }

    @Override
    public void insert(ForcastDataBak forcastDataBakView) {
        mapper.insert(forcastDataBakView);
    }

    @Override
    public void insertSelective(ForcastDataBak forcastDataBakView) {
        mapper.insertSelective(forcastDataBakView);
    }

    @Override
    public List<ForcastDataBak> selectByPrimaryKey(ForcastDataBak forcastDataBakView) {
        return mapper.selectByPrimaryKey(forcastDataBakView);
    }

    @Override
    public void saveBatch(List<ForcastDataBak> beans) {
        mapper.saveBatch(beans);
    }

    @Override
    public void updateByPrimaryKeySelective(ForcastDataBak forcastDataBakView) {
        mapper.updateByPrimaryKeySelective(forcastDataBakView);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }
}
