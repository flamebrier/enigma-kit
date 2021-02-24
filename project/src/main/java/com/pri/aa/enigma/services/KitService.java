package com.pri.aa.enigma.services;

import com.pri.aa.enigma.models.Kit;
import com.pri.aa.enigma.repositories.KitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KitService {

    private KitRepository kitRepository;

    @Autowired
    public KitService(KitRepository kitRepository) {
        this.kitRepository = kitRepository;
    }

    public void save(Kit kit) {
        kitRepository.save(kit);
    }

    public List<Kit> getAll() {
        return kitRepository.findAll();
    }

    public Optional<Kit> getById(Long id) {
        return kitRepository.findById(id);
    }

    public void delete(Long id) {
        kitRepository.deleteById(id);
    }
}
