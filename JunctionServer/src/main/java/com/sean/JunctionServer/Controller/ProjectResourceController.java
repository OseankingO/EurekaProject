package com.sean.JunctionServer.Controller;

import com.sean.JunctionServer.Entity.ProjectResourceEntity;
import com.sean.JunctionServer.Service.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ProjectResourceController {

    @Autowired
    private ProjectResourceService projectResourceService;

    @GetMapping("/project/{id}")
    public Integer[] get(@PathVariable int id) {
        Optional<List<Integer>> result = projectResourceService.getResourcesByProjectId(id);
        if(result.isPresent()) {
            return result.get().toArray(new Integer[result.get().size()]);
        }
        return new Integer[0];
    }

    @PostMapping("")
    public ResponseEntity<?> createProject(@RequestBody ProjectResourceEntity projectResource) {
        Optional<ProjectResourceEntity> result = projectResourceService.addProject(projectResource);
        if(result == null) {
            return new ResponseEntity<>("The Relationship Exist", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project or Resource Not Exist", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<?> deleteByProjectId(@PathVariable int id) {
        Optional<List<ProjectResourceEntity>> result = projectResourceService.deleteByProjectId(id);
        if(result == null) {
            return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Relationship For this Project", HttpStatus.OK);
    }

    @DeleteMapping("/resource/{id}")
    public ResponseEntity<?> deleteByResourceId(@PathVariable int id) {
        Optional<List<ProjectResourceEntity>> result = projectResourceService.deleteByResourceId(id);
        if(result == null) {
            return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("No Relationship For this Resource", HttpStatus.OK);
    }
}
