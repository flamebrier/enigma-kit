package com.pri.aa.enigma.repositories;

import com.pri.aa.enigma.models.Kit;
import com.pri.aa.enigma.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {
    List<Kit> findAllByUser(User user);
}