package com.sean.JunctionServer.DAO;

import com.sean.JunctionServer.Entity.ProjectResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectResourceDao extends JpaRepository<ProjectResourceEntity, Integer> {
}
