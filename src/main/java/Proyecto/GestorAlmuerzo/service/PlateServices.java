package Proyecto.GestorAlmuerzo.service;


import Proyecto.GestorAlmuerzo.Repository.PlateRepository;
import Proyecto.GestorAlmuerzo.model.Plate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlateServices {
    @Autowired
    private PlateRepository plateRepository;
    /**
     * Me obtiene todos los platos que ofrece el restaurante.
     *
     * @return La lista de los platos que ofrece el restaurante.
     */
    public List<Plate> getAllPlates() {
        return plateRepository.findAll();
    }
    public Plate addPlate(Plate plate) {
        return plateRepository.save(plate);
    }
}
