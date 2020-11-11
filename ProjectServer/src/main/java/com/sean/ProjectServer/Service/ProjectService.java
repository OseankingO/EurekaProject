package com.sean.ProjectServer.Service;

import com.sean.ProjectServer.DAO.ProjectDao;
import com.sean.ProjectServer.Entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

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
            projectDao.deleteById(id);
            return Optional.of(project.get());
        }
        return Optional.empty();
    }
}
