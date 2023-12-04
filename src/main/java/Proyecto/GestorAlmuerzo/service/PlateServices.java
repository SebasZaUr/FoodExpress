package Proyecto.GestorAlmuerzo.service;


import Proyecto.GestorAlmuerzo.Repository.PlateRepository;
import Proyecto.GestorAlmuerzo.model.Plate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlateServices {

    @Autowired
    private PlateRepository plateRepository;

    public List<Plate> getAllPlates() {
        return plateRepository.findAll();
    }

    public Optional<Plate> getPlateById(int id) {
        return plateRepository.findById(id);
    }

    public Plate addPlate(Plate plate) {
        return plateRepository.save(plate);
    }

    @Transactional
    public void updatePlate(Plate plate) {
        plateRepository.save(plate);
    }

    public void deletePlate(int id) {
        plateRepository.deleteById(id);
    }

    public List<Plate> getAllPlatesOrderedByName() {
        return plateRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<Plate> getAllPlatesOrderedByPrice() {
        return plateRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));

    }

    public List<Plate> findByCategoriesId(Long categoryId) {
        return plateRepository.findByCategoriesId(categoryId);
    }
}

