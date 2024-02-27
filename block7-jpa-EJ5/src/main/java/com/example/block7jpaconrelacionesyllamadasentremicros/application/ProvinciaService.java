package com.example.block7jpaconrelacionesyllamadasentremicros.application;




import org.example.dto.dtoProvincia.ProvinciaInputDto;
import org.example.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;

import java.util.List;

public interface ProvinciaService {
    ProvinciaOutPutComplete getProvincia(int id);
    ProvinciaOutPutComplete addProvincia(ProvinciaInputDto provincia);
    ProvinciaOutPutComplete updateProvincia(ProvinciaInputDto provincia);
    void deleteProvincia(int id);
    List<ProvinciaOutPutComplete> getAllProvincia();
    List<ProvinciaOutPutComplete> getProvinciaByNombre(String nombre);
}
