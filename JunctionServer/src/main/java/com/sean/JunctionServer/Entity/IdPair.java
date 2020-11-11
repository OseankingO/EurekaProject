package com.sean.JunctionServer.Entity;
import java.io.Serializable;

public class IdPair implements Serializable{
    private int projectId;
    int resourceId;

    public IdPair() {
    }

    public IdPair(int projectId, int resourceId) {
        this.projectId = projectId;
        this.resourceId = resourceId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
