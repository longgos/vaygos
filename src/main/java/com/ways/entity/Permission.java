package com.ways.entity;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private String createdBy;
	
	private Date creatdeDate;
	
	private String updatedBy;
	
	private Date updatedDate;
	
	public Permission() {  
        super();  
    }  

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreatdeDate() {
		return creatdeDate;
	}



	public void setCreatdeDate(Date creatdeDate) {
		this.creatdeDate = creatdeDate;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	// --------------------------------------------------------------------------------------  
	@Override  
    public int hashCode() {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result + ((id == null) ? 0 : id.hashCode());  
        return result;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        Permission other = (Permission) obj;  
        if (id == null) {  
            if (other.id != null)  
                return false;  
        } else if (!id.equals(other.id))  
            return false;  
        return true;  
    }  
	    
	    
	    
}
