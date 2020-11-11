package com.sean.JunctionServer.Controller;

import com.sean.JunctionServer.Entity.ProjectResourceEntity;
import com.sean.JunctionServer.Service.ProjectResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("")
public class ProjectResourceController {

    @Autowired
    private ProjectResourceService projectResourceService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("")
    public boolean get() {
        boolean response = restTemplate.getForObject("http://project-server/server/1", Boolean.class);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<?> createProject(@RequestBody ProjectResourceEntity projectResource) {
        Optional<ProjectResourceEntity> result = projectResourceService.addProject(projectResource);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Project or Resource Not Exist", HttpStatus.NOT_FOUND);
    }
}
