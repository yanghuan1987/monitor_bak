package xsscd.monitor.air.southwest.modules.system.entitys.mybatis.dto;
import java.io.Serializable;
import java.util.List;

/**
 * @description 菜单信息实体
 * @author Nicky
 * @date 2017年3月17日
 */
/*@Table(name="sys_menu")
@Entity*/
public class Menu implements Serializable {

	/** 菜单Id**/
	private int menuId;
	
	/** 上级Id**/
	private int parentId;
	
	/** 菜单名称**/
	private String menuName;
	
	/** 菜单图标**/
	private String menuIcon;
	
	/** 菜单URL**/
	private String menuUrl;
	
	/** 菜单类型**/
	private String menuType;
	
	/** 菜单排序**/
	private String menuOrder;

	/**菜单状态**/
	private String menuStatus;

	private List<Menu> subMenu;

	private String target;

	private boolean hasSubMenu = false;

	public Menu() {
		super();
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public List<Menu> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Menu> subMenu) {
		this.subMenu = subMenu;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public boolean isHasSubMenu() {
		return hasSubMenu;
	}

	public void setHasSubMenu(boolean hasSubMenu) {
		this.hasSubMenu = hasSubMenu;
	}   
	


}
