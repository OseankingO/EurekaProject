package com.sean.ProjectServer.Service;

import com.sean.ProjectServer.DAO.ProjectDao;
import com.sean.ProjectServer.Entity.ProjectEntity;
import com.sean.ProjectServer.FeignClient.JunctionClient;
import com.sean.ProjectServer.FeignClient.ResourceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private ResourceClient resourceClient;

    @Autowired
    private JunctionClient junctionClient;

    public Optional<List<ProjectEntity>> getAllProjects() {
        List<ProjectEntity> projects = projectDao.findAll();
        if(projects.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(projects);
    }

    public Optional<ProjectEntity> getProjectById(int id) {
        return projectDao.findById(id);
    }

    public Optional<List<Integer>> getResourcesByProjectId(int id) {
        Optional<ProjectEntity> existProject = projectDao.findById(id);
        if (existProject.isPresent()) {
            Integer[] result = junctionClient.getResourcesByProjectId(id);
            if(result.length != 0) {
                return Optional.of(Arrays.asList(result));
            }
            return null;
        }
        return Optional.empty();
    }

    public Optional<ProjectEntity> addProject(ProjectEntity project) {
        ProjectEntity createdProject =projectDao.save(project);
        return Optional.of(createdProject);

    }

    public Optional<ProjectEntity> updateProject(ProjectEntity project, Integer id) {
        Optional<ProjectEntity> existProject = projectDao.findById(id);
        if (existProject.isPresent()) {
            project.setId(id);
            ProjectEntity createdProject = projectDao.save(project);
            Optional<ProjectEntity> result = Optional.of(createdProject);
            return result;
        }
        return Optional.empty();
    }

    public Optional<ProjectEntity> deleteProjectById(int id) {
        Optional<ProjectEntity> project = projectDao.findById(id);
        if(project.isPresent()) {
            try {
                junctionClient.deleteProjectById(id);
                projectDao.deleteById(id);
                return project;
            } catch (Error e) {
                return null;
            }
        }
        return Optional.empty();
    }
}
