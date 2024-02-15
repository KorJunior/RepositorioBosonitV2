package com.example.block7jpaconrelacionesyllamadasentremicros.application.implementation;

import com.example.block7jpaconrelacionesyllamadasentremicros.application.ProvinciaService;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.ProvinciaInputDto;
import com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia.provinciaOutPut.ProvinciaOutPutComplete;
import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Provincia;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ClienteRepository;
import com.example.block7jpaconrelacionesyllamadasentremicros.repository.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;
    @Override
    public ProvinciaOutPutComplete getProvincia(int id) {
        return null;
    }

    @Override
    public ProvinciaOutPutComplete addProvincia(ProvinciaInputDto provincia) {
        Provincia provincia1 = new Provincia(provincia);
        provinciaRepository.save(provincia1);
        return provincia1.toProvinciaOutPut();
    }

    @Override
    public ProvinciaOutPutComplete updateProvincia(ProvinciaInputDto provincia) {
        return null;
    }

    @Override
    public void deleteProvincia(int id) {

    }

    @Override
    public List<ProvinciaOutPutComplete> getAllProvincia() {
        return toListaProvinciasOutPut();

    }

    private List<ProvinciaOutPutComplete>  toListaProvinciasOutPut() {
        return provinciaRepository.findAll().stream()
                .map(Provincia::toProvinciaOutPut)
                .collect(Collectors.toList());
    }

    public List<ProvinciaOutPutComplete> getProvinciaByNombre(String nombre) {
        Optional<List<Provincia>> provincias = provinciaRepository.findByNombre(nombre);
        return provincias.orElseThrow(() ->new NoSuchElementException("No existe el cliente"))
                .stream()
                .map(Provincia::toProvinciaOutPut)
                .collect(Collectors.toList());

    }
}
