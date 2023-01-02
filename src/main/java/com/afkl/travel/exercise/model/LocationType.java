package com.afkl.travel.exercise.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationType {

    AIRPORT("airport"),
    CITY("city"),
    COUNTRY("country");

    private final String type;
}
