package com.pri.aa.enigma.services;

import com.pri.aa.enigma.models.Enigma;
import com.pri.aa.enigma.repositories.EnigmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnigmaService {

    private EnigmaRepository enigmaRepository;

    @Autowired
    public EnigmaService(EnigmaRepository enigmaRepository) {
        this.enigmaRepository = enigmaRepository;
    }

    public void save(Enigma enigma) {
        enigmaRepository.save(enigma);
    }

    public void saveAll(List<Enigma> enigmas) {
        enigmaRepository.saveAll(enigmas);
    }

    public List<Enigma> getAll() {
        return enigmaRepository.findAll();
    }

    public Optional<Enigma> getById(Long id) {
        return enigmaRepository.findById(id);
    }

    public void delete(Long id) {
        enigmaRepository.deleteById(id);
    }
}
