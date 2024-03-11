package com.example.block18elasticSearch.application.implement;

import com.example.block18elasticSearch.application.DictionaryService;
import com.example.block18elasticSearch.controller.dto.input.DictionaryInput;
import com.example.block18elasticSearch.controller.dto.output.DictionaryOutputSimple;
import com.example.block18elasticSearch.domain.Dictionary;
import com.example.block18elasticSearch.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DictionaryImpl implements DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Override
    public DictionaryOutputSimple saveDictionary(DictionaryInput dictionaryInput) {
        Dictionary dictionary = new Dictionary(dictionaryInput);
        dictionaryRepository.save(dictionary);

        return new DictionaryOutputSimple(dictionary);
    }

    @Override
    public List<DictionaryOutputSimple> searchDictionary(String word) {
        Optional<List<Dictionary>> dictionaryList = dictionaryRepository.findByNombreOrExplicacion(word);
        return dictionaryList.map(DictionaryOutputSimple::fromDictionaryList).orElse(null);
    }

    @Override
    public void updateDictionary() {

    }

    @Override
    public void deleteDictionary(String id) {
        dictionaryRepository.deleteById(id);
    }
}
