package com.sean.ResourceServer.DAO;

import com.sean.ResourceServer.Entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceDao extends JpaRepository<ResourceEntity, Integer> {
}
