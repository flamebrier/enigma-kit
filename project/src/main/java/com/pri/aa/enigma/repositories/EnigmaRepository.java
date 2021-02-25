package com.pri.aa.enigma.repositories;

import com.pri.aa.enigma.models.Enigma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnigmaRepository extends JpaRepository<Enigma, Long> {
}