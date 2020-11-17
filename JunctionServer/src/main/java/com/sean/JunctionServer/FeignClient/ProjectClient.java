package com.sean.JunctionServer.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "PROJECT-SERVER")
public interface ProjectClient {

    @GetMapping("/server/{id}")
    String isProjectExistById(@PathVariable int id);

}
