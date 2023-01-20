package com.solvd.socialnetwork;

import java.sql.Timestamp;

abstract public class Model
{
    private int id;
    private Timestamp creationDate;

    public Model(int id, Timestamp creationDate) {
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
