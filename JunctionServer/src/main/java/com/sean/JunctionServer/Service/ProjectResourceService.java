package com.sean.JunctionServer.Service;

import com.sean.JunctionServer.DAO.ProjectResourceDao;
import com.sean.JunctionServer.Entity.ProjectResourceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class ProjectResourceService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProjectResourceDao projectResourceDao;

//    public Optional<ProjectEntity> getProjectById(int id) {
//        return projectDao.findById(id);
//    }

    public Optional<ProjectResourceEntity> addProject(ProjectResourceEntity projectResource) {
        boolean projectExist = restTemplate.getForObject("http://PROJECT-SERVER/server/" + Integer.toString(projectResource.getProjectId()), Boolean.class);
        boolean resourceExist = restTemplate.getForObject("http://RESOURCE-SERVER/server/" + Integer.toString(projectResource.getResourceId()), Boolean.class);
        if(projectExist && resourceExist) {
            ProjectResourceEntity createdProject =projectResourceDao.save(projectResource);
            return Optional.of(createdProject);
        }
        return Optional.empty();

    }

//    public Optional<ProjectEntity> updateProject(ProjectEntity project, Integer id) {
//        Optional<ProjectEntity> existProject = projectDao.findById(id);
//        if (existProject.isPresent()) {
//            project.setId(id);
//            ProjectEntity createdProject = projectDao.save(project);
//            Optional<ProjectEntity> result = Optional.of(createdProject);
//            return result;
//        }
//        return Optional.empty();
//    }
//
//    public Optional<ProjectEntity> deleteProjectById(int id) {
//        Optional<ProjectEntity> project = projectDao.findById(id);
//        if(project.isPresent()) {
//            projectDao.deleteById(id);
//            return Optional.of(project.get());
//        }
//        return Optional.empty();
//    }

}
