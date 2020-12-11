package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsersJpaRepository extends JpaRepository<User,String> {
}
