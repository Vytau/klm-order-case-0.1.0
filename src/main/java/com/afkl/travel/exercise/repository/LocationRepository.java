package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}