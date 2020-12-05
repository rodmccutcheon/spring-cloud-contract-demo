package com.rodmccutcheon.clock.client;

import com.rodmccutcheon.clock.data.Building;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@FeignClient("web")
public interface WebClient {

    @GetMapping("/api/v1/building")
    Collection<Building> getBuildings();

}
