package com.zent.json;

import java.util.List;

import com.zent.dto.UserDTO;
import com.zent.entities.UserBO;

public class UserJson {
	Integer iTotalRecords;
	 
    Integer iTotalDisplayRecords;
 
    String sEcho;
 
    String sColumns;
 
    List<UserDTO> aaData;

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

	public List<UserDTO> getAaData() {
		return aaData;
	}

	public void setAaData(List<UserDTO> aaData) {
		this.aaData = aaData;
	}

	
    
    
	
    
    
    
}
