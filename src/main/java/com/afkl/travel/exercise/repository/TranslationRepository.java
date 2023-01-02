package com.afkl.travel.exercise.repository;

import com.afkl.travel.exercise.entity.Translation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.lang.NonNull;

public interface TranslationRepository extends JpaRepository<Translation, Integer> {


    @Query("select t from Translation t where upper(t.location.type) = upper(:type) and upper(t.location.code) = upper(:code)")
    List<Translation> findByLocation_TypeIgnoreCaseAndLocation_CodeIgnoreCase(@NonNull String type, @NonNull String code);
}