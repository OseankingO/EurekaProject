package com.sean.ResourceServer.Service;

import com.sean.ResourceServer.DAO.ResourceDao;
import com.sean.ResourceServer.Entity.ResourceEntity;
import com.sean.ResourceServer.FeignClient.JunctionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JunctionClient junctionClient;

    public Optional<List<ResourceEntity>> getAllResources() {
        List<ResourceEntity> projects = resourceDao.findAll();
        if(projects.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(projects);
    }

    public Optional<ResourceEntity> getResourceById(int id) {
        return resourceDao.findById(id);
    }

    public Optional<ResourceEntity> addResource(ResourceEntity resource) {
        ResourceEntity createdProject =resourceDao.save(resource);
        Optional<ResourceEntity> result = Optional.of(createdProject);
        return result;

    }

    public Optional<ResourceEntity> updateResource(ResourceEntity resource, Integer id) {
        Optional<ResourceEntity> existProject = resourceDao.findById(id);
        if (existProject.isPresent()) {
            resource.setId(id);
            ResourceEntity createdProject = resourceDao.save(resource);
            Optional<ResourceEntity> result = Optional.of(createdProject);
            return result;
        }
        return Optional.empty();
    }

    public Optional<ResourceEntity> deleteResourceById(int id) {
        Optional<ResourceEntity> project = resourceDao.findById(id);
        if(project.isPresent()) {
            try {
                junctionClient.deleteResourceById(id);
                resourceDao.deleteById(id);
                return Optional.of(project.get());
            } catch (Error e) {
                return null;
            }
        }
        return Optional.empty();
    }
}
