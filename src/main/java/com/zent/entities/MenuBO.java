package com.zent.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.zent.dto.MenuDTO;
import com.zent.dto.StateDTO;


/**
 * The persistent class for the menu database table.
 * 
 */
@Entity
@Table(name="menu")
public class MenuBO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String url;
	private MenuBO parent;
    private List<MenuBO> submenus;
	private List<RoleMenuBO> roleMenus;
	
	public MenuBO() {
	}

	public MenuDTO toDTO(Boolean check) {
		MenuDTO dto = new MenuDTO();
		dto.setId(id.toString());
		dto.setText(name);
		dto.setUrl(url);
		if (parent!=null) {
			dto.setParent(parent.getId().toString());
		}else dto.setParent("#");
		StateDTO stateDTO = new StateDTO();
		stateDTO.setSelected(check);
		stateDTO.setOpened(true);
		dto.setState(stateDTO);
		return dto;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@ManyToOne
	@JoinColumn(name="parent_id")
	public MenuBO getParent() {
		return parent;
	}

	public void setParent(MenuBO parent) {
		this.parent = parent;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="parent")
	public List<MenuBO> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<MenuBO> submenus) {
		this.submenus = submenus;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "menu")
	public List<RoleMenuBO> getRoleMenus() {
		return this.roleMenus;
	}

	public void setRoleMenus(final List<RoleMenuBO> roleMenus) {
		this.roleMenus = roleMenus;
	}
}