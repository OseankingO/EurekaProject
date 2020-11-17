package com.sean.JunctionServer.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "RESOURCE-SERVER")
public interface ResourceClient {

    @GetMapping("/server/{id}")
    String isResourceExistById(@PathVariable int id);

}
