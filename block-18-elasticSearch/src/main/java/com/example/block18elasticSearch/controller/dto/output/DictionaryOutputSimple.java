package com.example.block18elasticSearch.controller.dto.output;

import com.example.block18elasticSearch.domain.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DictionaryOutputSimple {
    private String nombre;
    private String explicacion;

    public DictionaryOutputSimple(Dictionary dictionary) {
        this.nombre = dictionary.getNombre();
        this.explicacion = dictionary.getExplicacion();
    }

    public static List<DictionaryOutputSimple> fromDictionaryList(List<Dictionary> dictionaries) {
        return dictionaries.stream().map(DictionaryOutputSimple::new).collect(Collectors.toList());
    }
}
