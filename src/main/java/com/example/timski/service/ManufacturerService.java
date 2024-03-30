package com.example.timski.service;

import com.example.timski.model.Category;
import com.example.timski.model.Manufacturer;
import com.example.timski.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerService(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }


    public List<Manufacturer> manufacturerList() {
        return manufacturerRepository.findAll();
    }


    public Optional<Manufacturer> findById(Long id) {
        return manufacturerRepository.findById(id);
    }


    public Optional<Manufacturer> save(String address, String name) {
        return Optional.of(manufacturerRepository.save(new Manufacturer(address,name)));
    }

    public void deleteById(Long id) {
        manufacturerRepository.deleteById(id);
    }
}
