package com.zent.json;

import java.util.List;

import com.zent.dto.RoleDTO;
import com.zent.entities.RoleBO;

public class RoleJson {
	Integer iTotalRecords;
	 
    Integer iTotalDisplayRecords;
 
    String sEcho;
 
    String sColumns;
 
    List<RoleDTO> aaData;

	public Integer getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(Integer iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public Integer getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public String getsColumns() {
		return sColumns;
	}

	public void setsColumns(String sColumns) {
		this.sColumns = sColumns;
	}

	public List<RoleDTO> getAaData() {
		return aaData;
	}

	public void setAaData(List<RoleDTO> aaData) {
		this.aaData = aaData;
	}

	
    
    
	
    
    
    
}
