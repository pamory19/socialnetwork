package com.solvd.socialnetwork;

import java.sql.Date;

abstract public class Model
{
    private Long id;
    private Date creationDate;

    public Model(Long id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public Model(){

    }

    public Model(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
