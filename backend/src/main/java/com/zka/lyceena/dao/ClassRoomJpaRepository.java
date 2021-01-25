package com.zka.lyceena.dao;

import com.zka.lyceena.entities.rooms.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ClassRoomJpaRepository extends JpaRepository<ClassRoom,Integer> {

    @Query("select c from ClassRoom c " +
            "where c.id not in :ids")
    List<ClassRoom> findByIdNot(@Param("ids") Set<Integer> ids);
}
