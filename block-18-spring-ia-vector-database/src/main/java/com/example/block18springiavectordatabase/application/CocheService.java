package com.example.block18springiavectordatabase.application;

import com.example.block18springiavectordatabase.controller.dto.CocheInput;
import com.example.block18springiavectordatabase.domain.Coche;

import java.util.List;

public interface CocheService {
    public void saveCoche();
    public List<Coche> getAllCoches();

}
