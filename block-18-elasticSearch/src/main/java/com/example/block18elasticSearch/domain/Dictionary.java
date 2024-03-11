package com.example.block18elasticSearch.domain;

import com.example.block18elasticSearch.controller.dto.input.DictionaryInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Data
@Document(indexName = "dictionary")
public class Dictionary {

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String nombre;

    @Field(type = FieldType.Text)
    private String explicacion;

    public Dictionary(DictionaryInput dictionaryInput) {
        this.nombre = dictionaryInput.getNombre();
        this.explicacion = dictionaryInput.getExplicacion();
    }
}
