package xsscd.monitor.air.southwest.modules.job.entitys.mybatis.mapper;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.ExpertAirLvlCountSource;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.ModleRealSql;
import xsscd.monitor.air.southwest.modules.job.entitys.mybatis.dto.vo.SelectPiontVO;

import java.util.List;

@Repository
public interface ExpertAirLvlCountSourceMapper {


	@SelectProvider(type= ModleRealSql.class,method = "getExpertAirLvlCountSourceSql")
	@ResultMap("BaseResultMap")
	public List<ExpertAirLvlCountSource> select(SelectPiontVO expertAirLvlCountView);

}