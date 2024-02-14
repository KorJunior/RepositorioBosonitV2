package com.example.block7jpaconrelacionesyllamadasentremicros.controller.dto.dtoProvincia;

import com.example.block7jpaconrelacionesyllamadasentremicros.domain.Cliente;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvinciaInputDto {
    private int codProvincia;
    private String nombre;

}
