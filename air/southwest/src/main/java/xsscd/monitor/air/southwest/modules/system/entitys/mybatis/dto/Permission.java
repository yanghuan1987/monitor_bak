package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;

import java.util.HashSet;
import java.util.Set;

public class Permission {
	private int id;
	private String pdesc;
	private String name;
	private static final long serialVersionUID = 1L;

	private Menu menu;

	private Set<Operation> operations = new HashSet<Operation>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
