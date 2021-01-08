package com.zka.lyceena.dao;

import com.zka.lyceena.entities.material.LevelMaterialNumberOfHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LevelMaterialNumberOfHoursJpaRepository extends JpaRepository<LevelMaterialNumberOfHours,Integer> {

    @Query("Select s from LevelMaterialNumberOfHours s " +
            "where s.classLevelRef.id = :levelId " +
            "and s.material.id = :materialId")
    Optional<LevelMaterialNumberOfHours> findFirstByLevelIdAndMaterialId(@Param("levelId") Integer levelId,@Param("materialId") String materialId);

    @Query("Select s from LevelMaterialNumberOfHours s " +
            "where s.classLevelRef.id = :levelId ")
    List<LevelMaterialNumberOfHours> findByLevelId(@Param("levelId") Integer levelId);
}
