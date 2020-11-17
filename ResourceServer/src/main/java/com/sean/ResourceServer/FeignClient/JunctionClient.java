package com.sean.ResourceServer.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "JUNCTION-SERVER")
public interface JunctionClient {

    @DeleteMapping("/resource/{id}")
    void deleteResourceById(@PathVariable int id);
}
