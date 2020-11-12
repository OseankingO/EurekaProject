package com.sean.ProjectServer.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "RESOURCE-SERVER")
public interface ResourceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/msg/1")
    String getPosts();

}
