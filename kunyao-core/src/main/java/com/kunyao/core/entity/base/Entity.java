package com.kunyao.core.entity.base;

import java.util.Date;

public class Entity {
    
    private String id;

    private Boolean isValid;
    
    private Boolean isDelete;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;
    
    public void setValue(String appUserId) {
        this.setCreatedBy(appUserId);
        this.setCreatedDate(new Date());
        this.setIsValid(true);
        this.setIsDelete(false);
        this.setLastModifiedBy(appUserId);
        this.setLastModifiedDate(new Date());
    }
    
    public void setValueAsModify(String appUserId) {
        this.setLastModifiedBy(appUserId);
        this.setLastModifiedDate(new Date());
    }
        
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    
}
