package com.example.block7jpaconrelacionesyllamadasentremicros.application;


import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.ProvinciaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;

import java.util.List;

public interface ProvinciaService {
    ProvinciaOutPutComplete getProvincia(int id);
    ProvinciaOutPutComplete addProvincia(ProvinciaInputDto provincia);
    ProvinciaOutPutComplete updateProvincia(ProvinciaInputDto provincia);
    void deleteProvincia(int id);
    List<ProvinciaOutPutComplete> getAllProvincia();
}
