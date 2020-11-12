package com.sean.JunctionServer.DAO;

import com.sean.JunctionServer.Entity.ProjectResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectResourceDao extends JpaRepository<ProjectResourceEntity, Integer> {

    Optional<ProjectResourceEntity> findByProjectIdAndResourceId(int projectId, int resourceId);

    List<ProjectResourceEntity> findAllByProjectId(int projectId);

    List<ProjectResourceEntity> deleteAllByProjectId(int projectId);

    List<ProjectResourceEntity> deleteAllByResourceId(int resourceId);
}
