package com.solvd.socialnetwork;

import java.sql.Date;

abstract public class Model
{
    private int id;
    private Date creationDate;

    public Model(int id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public Model(){

    }

    public Model(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
