package Proyecto.GestorAlmuerzo.service;

import Proyecto.GestorAlmuerzo.Repository.AppRepository;
import Proyecto.GestorAlmuerzo.exceptions.GestorAlmuerzosAppException;
import Proyecto.GestorAlmuerzo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.lang.reflect.InvocationTargetException;

@Service
public class AppServices {

    @Autowired
    private AppRepository appRepository;
}
