package com.afkl.travel.exercise.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private String code;
    private String name;
    private String type;
    private double latitude;
    private double longitude;
    private String description;
    private String parentCode;
    private String parentType;
}
