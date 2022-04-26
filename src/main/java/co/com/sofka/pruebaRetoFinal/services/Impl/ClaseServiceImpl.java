package co.com.sofka.pruebaRetoFinal.services.Impl;

import co.com.sofka.pruebaRetoFinal.repositories.ClaseRepository;
import co.com.sofka.pruebaRetoFinal.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaseServiceImpl implements ClaseService {

    @Autowired
    ClaseRepository claseRepository;
}
