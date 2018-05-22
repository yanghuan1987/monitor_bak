package xsscd.monitor.air.southwest.modules.core.entitys.mybatis.dto;

import java.io.Serializable;
import java.util.List;

public class AreaProvince implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long pid;
	//省编码
	private Integer provinceCode;
	//省名字
	private String provinceName;
	//省简称
	private String provinceJC;
	//排序
	private Integer orderP;
	//直辖市：1，非直辖市：0
	private Integer isMunicipality;
	//省包含城市
	private List<AreaCity> provinceCities;
	//省包含区域
	private List<AreaZone> provinceZones;

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Integer provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceJC() {
		return provinceJC;
	}

	public void setProvinceJC(String provinceJC) {
		this.provinceJC = provinceJC;
	}

	public Integer getOrderP() {
		return orderP;
	}

	public void setOrderP(Integer orderP) {
		this.orderP = orderP;
	}

	public Integer getIsMunicipality() {
		return isMunicipality;
	}

	public void setIsMunicipality(Integer isMunicipality) {
		this.isMunicipality = isMunicipality;
	}

	public List<AreaCity> getProvinceCities() {
		return provinceCities;
	}

	public void setProvinceCities(List<AreaCity> provinceCities) {
		this.provinceCities = provinceCities;
	}

	public List<AreaZone> getProvinceZones() {
		return provinceZones;
	}

	public void setProvinceZones(List<AreaZone> provinceZones) {
		this.provinceZones = provinceZones;
	}
}