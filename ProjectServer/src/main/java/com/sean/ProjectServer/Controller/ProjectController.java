package com.sean.ProjectServer.Controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sean.ProjectServer.Entity.ProjectEntity;
import com.sean.ProjectServer.FeignClient.ResourceClient;
import com.sean.ProjectServer.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

//    @Autowired
//    private ResourceClient resourceClient;

//    @Autowired
//    private DiscoveryClient discoveryClient;

//    @GetMapping("/msg")
//    public String message() {
//        return resourceClient.getPosts();
//    }

    @GetMapping("")
    public ResponseEntity<?> getAllProjects() {
        Optional<List<ProjectEntity>> result = projectService.getAllProjects();
        if (!result.isPresent()) {
            return new ResponseEntity<>("No Project!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/server/{id}")
    public String getProjectExistById(@PathVariable int id) {
        Optional<ProjectEntity> result = projectService.getProjectById(id);
        if(result.isPresent()) {
            return "1";
        }
        return "0";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProjectById(@PathVariable int id) {
        Optional<ProjectEntity> result = projectService.getProjectById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/resource/{id}")
    @HystrixCommand(fallbackMethod = "getFallResourcesByProjectId")
    public ResponseEntity<?> getResourcesByProjectId(@PathVariable int id) {
        Optional<List<Integer>> result = projectService.getResourcesByProjectId(id);
        if(result == null) {
            return new ResponseEntity<>("No Resource for Project!", HttpStatus.NOT_FOUND);
        }
        if (!result.isPresent()) {
            return new ResponseEntity<>("No Project!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public ResponseEntity<?> getFallResourcesByProjectId(@PathVariable int id) {
        return new ResponseEntity<>("Can't Get The Resources Right Now", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createProject(@RequestBody ProjectEntity project) {
        Optional<ProjectEntity> result = projectService.addProject(project);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Create Fail", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProject(@RequestBody ProjectEntity project, @PathVariable int id) {
        Optional<ProjectEntity> result = projectService.updateProject(project, id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @HystrixCommand(fallbackMethod = "deleteFallByProjectId")
    public ResponseEntity<?> deleteByProjectId(@PathVariable int id) {
        Optional<ProjectEntity> result = projectService.deleteProjectById(id);
        if(result == null) {
            return new ResponseEntity<>("Fail to Delete Project Relationship!", HttpStatus.OK);
        }
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project Not Found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteFallByProjectId(@PathVariable int id) {
        return new ResponseEntity<>("Can't Delete Project Right Now!", HttpStatus.OK);
    }
}
