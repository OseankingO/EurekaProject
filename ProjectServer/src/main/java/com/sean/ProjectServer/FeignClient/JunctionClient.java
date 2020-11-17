package com.sean.ProjectServer.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "JUNCTION-SERVER")
public interface JunctionClient {

    @GetMapping("/project/{id}")
    Integer[] getResourcesByProjectId(@PathVariable int id);

    @DeleteMapping("/project/{id}")
    void deleteProjectById(@PathVariable int id);
}