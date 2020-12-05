package com.rodmccutcheon.web.controller;

import com.rodmccutcheon.web.data.Building;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class BuildingController {

    private final static long[] BUILDING_IDS = new long[] { 1, 7, 11 };

    @GetMapping("/api/v1/building")
    public Collection<Building> getBuildings() {
        return Arrays.stream(BUILDING_IDS).mapToObj(Building::new).collect(Collectors.toSet());
    }
}
