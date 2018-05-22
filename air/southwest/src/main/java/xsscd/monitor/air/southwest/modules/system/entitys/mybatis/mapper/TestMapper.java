package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.mapper;

import org.apache.ibatis.annotations.Update;

public interface TestMapper {

	Integer queryCount();
	
	@Update("update admin set permission = permission - 1")
	Integer updateAdminByName();

}
