package com.afkl.travel.exercise.service;

import com.afkl.travel.exercise.entity.Translation;
import com.afkl.travel.exercise.model.Location;
import com.afkl.travel.exercise.model.LocationType;
import com.afkl.travel.exercise.repository.TranslationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TravelService {

    private final TranslationRepository translationRepository;

    public List<Location> getAllLocations() {

        List<Translation> translationList = translationRepository.findAll();

        return translationList
                .stream()
                .map(this::convertTranslationToLocation)
                .collect(Collectors.toList());
    }

    public List<Location> getLocations(LocationType type, String code) {

        List<Translation> translationList = translationRepository.findByLocation_TypeIgnoreCaseAndLocation_CodeIgnoreCase(type.getType(), code);

        return translationList
                .stream()
                .map(this::convertTranslationToLocation)
                .collect(Collectors.toList());
    }

    private Location convertTranslationToLocation(Translation translation) {

        return Location.builder()
                .code(translation.getLocation().getCode())
                .name(translation.getName())
                .type(translation.getLocation().getType())
                .latitude(translation.getLocation().getLatitude() == null
                        ? Double.NaN
                        : translation.getLocation().getLatitude()
                )
                .longitude(translation.getLocation().getLongitude() == null
                        ? Double.NaN
                        : translation.getLocation().getLongitude()
                )
                .description(translation.getDescription())
                .parentCode(translation.getLocation().getParent() == null
                        ? ""
                        : translation.getLocation().getParent().getCode()
                )
                .parentType(translation.getLocation().getParent() == null
                        ? ""
                        : translation.getLocation().getParent().getType()
                )
                .build();
    }
}
