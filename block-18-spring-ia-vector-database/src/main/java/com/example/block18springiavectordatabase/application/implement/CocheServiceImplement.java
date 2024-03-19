package com.example.block18springiavectordatabase.application.implement;

import com.example.block18springiavectordatabase.application.CocheService;
import com.example.block18springiavectordatabase.controller.dto.CocheInput;
import com.example.block18springiavectordatabase.domain.Coche;
import com.example.block18springiavectordatabase.repository.CocheRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CocheServiceImplement implements CocheService {

    @Autowired
    private CocheRepository cocheRepository;

    @Override
    public void saveCoche() {

        Faker faker = new Faker();
        for (int i = 0; i < 2000; i++) {
            CocheInput cocheInput = new CocheInput();
            cocheInput.setMarca(faker.company().name());
            cocheInput.setModelo(faker.funnyName().name());
            cocheInput.setColor(faker.color().name());
            cocheInput.setAño(faker.number().numberBetween(1990, 2022));
            cocheInput.setImporte((float) faker.number().randomDouble(2, 10000, 50000));

            Coche coche = new Coche(cocheInput);
            cocheRepository.save(coche);
        }
    }

    @Override
    public List<Coche> getAllCoches() {
        return cocheRepository.findAll();
    }
}
