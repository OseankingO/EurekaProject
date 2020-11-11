package com.sean.ResourceServer.Controller;

import com.sean.ResourceServer.Entity.ResourceEntity;
import com.sean.ResourceServer.Service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("")
public class ResourceController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResourceService resourceService;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @GetMapping("")
    public ResponseEntity<?> getAllResources() {
        Optional<List<ResourceEntity>> result = resourceService.getAllResources();
        if (!result.isPresent()) {
            return new ResponseEntity<>("No Resource!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/server/{id}")
    public boolean getResourceExistById(@PathVariable int id) {
        Optional<ResourceEntity> result = resourceService.getResourceById(id);
        return result.isPresent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getResourceById(@PathVariable int id) {
        Optional<ResourceEntity> result = resourceService.getResourceById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<?> createResource(@RequestBody ResourceEntity project) {
        Optional<ResourceEntity> result = resourceService.addResource(project);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Create Fail", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResource(@RequestBody ResourceEntity project, @PathVariable int id) {
        Optional<ResourceEntity> result = resourceService.updateResource(project, id);
        if(result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable int id) {
        Optional<ResourceEntity> result = resourceService.deleteResourceById(id);
        if(result.isPresent()) {
            return new ResponseEntity<>("Deleted Resource!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Resource Not Found!", HttpStatus.NOT_FOUND);
    }
}

