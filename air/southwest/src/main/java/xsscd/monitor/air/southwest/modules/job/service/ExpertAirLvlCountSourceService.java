package xsscd.monitor.air.southwest.modules.job.service;

import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvlCountSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

public interface ExpertAirLvlCountSourceService {
	public List<ExpertAirLvlCountSource> select(SelectPiontVO ExpertAirLvlCountSourceView);

}