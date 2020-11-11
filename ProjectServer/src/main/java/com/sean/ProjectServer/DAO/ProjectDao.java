package com.sean.ProjectServer.DAO;

import com.sean.ProjectServer.Entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectDao extends JpaRepository<ProjectEntity, Integer> {
}
