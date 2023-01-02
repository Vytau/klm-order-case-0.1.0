package com.afkl.travel.exercise.controller;

import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.service.TravelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/travel")
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/locations")
    public ResponseEntity getLocations(
            @RequestHeader(name = "Accept-Language", required = false, defaultValue = "English") String acceptedLanguage
    ) {

        final List<Location> allLocations = travelService.getAllLocations();

        return ResponseEntity.ok(allLocations);
    }

    @GetMapping("/locations/{type}/{code}")
    public ResponseEntity getLocation(
            @RequestHeader(name = "Accept-Language", required = false, defaultValue = "English") String acceptedLanguage,
            @PathVariable LocationType type,
            @PathVariable String code
    ) {

        final List<Location> locations = travelService.getLocations(type, code);

        return ResponseEntity.ok(locations);
    }
}
