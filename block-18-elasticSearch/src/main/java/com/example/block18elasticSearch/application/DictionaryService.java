package com.example.block18elasticSearch.application;

import com.example.block18elasticSearch.controller.dto.input.DictionaryInput;
import com.example.block18elasticSearch.controller.dto.output.DictionaryOutputSimple;

import java.util.List;

public interface DictionaryService {
    DictionaryOutputSimple saveDictionary(DictionaryInput dictionaryInput);
    List<DictionaryOutputSimple> searchDictionary(String word);
    List<DictionaryOutputSimple> searchDictionaryv2(String word);
    void updateDictionary();

    void deleteDictionary(String id);
}
