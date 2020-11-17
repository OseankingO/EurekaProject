package com.sean.JunctionServer.Service;

import com.sean.JunctionServer.DAO.ProjectResourceDao;
import com.sean.JunctionServer.Entity.ProjectResourceEntity;
import com.sean.JunctionServer.FeignClient.ProjectClient;
import com.sean.JunctionServer.FeignClient.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectResourceService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProjectResourceDao projectResourceDao;

    @Autowired
    private ResourceClient resourceClient;

    @Autowired
    private ProjectClient projectClient;

    public Optional<List<Integer>> getResourcesByProjectId(int id) {
        String projectExist = projectClient.isProjectExistById(id);
        if(projectExist.equals("1")) {
            List<ProjectResourceEntity> entityList = projectResourceDao.findAllByProjectId(id);
            List<Integer> res = new ArrayList<>();
            for(ProjectResourceEntity item : entityList) {
                res.add(item.getResourceId());
            }
            return Optional.of(res);
        }
        return Optional.empty();
    }

    public Optional<ProjectResourceEntity> addProject(ProjectResourceEntity projectResource) {
        String projectExist = projectClient.isProjectExistById(projectResource.getProjectId());
        String resourceExist = resourceClient.isResourceExistById(projectResource.getResourceId());
        if(projectExist.equals("1") && resourceExist.equals("1")) {
            Optional<ProjectResourceEntity> existProject =projectResourceDao.findByProjectIdAndResourceId(projectResource.getProjectId(), projectResource.getResourceId());
            if(existProject.isPresent()) {
                return null;
            }
            ProjectResourceEntity createdProject =projectResourceDao.save(projectResource);
            return Optional.of(createdProject);
        }
        return Optional.empty();

    }

    @Transactional
    public Optional<List<ProjectResourceEntity>> deleteByProjectId(int id) {
        String projectExist = projectClient.isProjectExistById(id);
        if(!projectExist.equals("1")) {
            return null;
        }
        List<ProjectResourceEntity> result = projectResourceDao.deleteAllByProjectId(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    @Transactional
    public Optional<List<ProjectResourceEntity>> deleteByResourceId(int id) {
        String resourceExist = resourceClient.isResourceExistById(id);
        if(!resourceExist.equals("1")) {
            return null;
        }
        List<ProjectResourceEntity> result = projectResourceDao.deleteAllByResourceId(id);
        if(result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result);
    }
}
