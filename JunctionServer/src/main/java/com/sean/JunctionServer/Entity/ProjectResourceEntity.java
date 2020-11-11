package com.sean.JunctionServer.Entity;

import javax.persistence.*;

@Entity
@Table(name = "project_resource")
@IdClass(IdPair.class)
public class ProjectResourceEntity {
    @Id
    @Column(name = "project_id")
    private int projectId;

    @Id
    @Column(name = "resource_id")
    private int resourceId;

    public ProjectResourceEntity() {
    }

    public ProjectResourceEntity(int projectId, int resourceId) {
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
