package com.example.block18elasticSearch.application.implement;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import com.example.block18elasticSearch.application.DictionaryService;
import com.example.block18elasticSearch.controller.dto.input.DictionaryInput;
import com.example.block18elasticSearch.controller.dto.output.DictionaryOutputSimple;
import com.example.block18elasticSearch.domain.Dictionary;
import com.example.block18elasticSearch.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DictionaryImpl implements DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    @Autowired
    ElasticsearchOperations operations;

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
    public List<DictionaryOutputSimple> searchDictionaryv2(String word) {
        List<DictionaryOutputSimple> dictionaryList = new ArrayList<>();

        Pageable pageable = Pageable.unpaged();
        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> b
                                .should(s -> s
                                        .match(m -> m
                                                .field("nombre")
                                                .query(word)
                                        )
                                )
                                .should(s -> s
                                        .match(m -> m
                                                .field("explicacion")
                                                .query(word)
                                        )
                                )
                        )
                )
                .withPageable(pageable)
                .build();

        SearchHits<Dictionary> searchHits = operations.search(query, Dictionary.class);

        if (searchHits.hasSearchHits()) {
            searchHits.getSearchHits().forEach(searchHit -> {
                Dictionary dictionary = searchHit.getContent();
                dictionaryList.add(new DictionaryOutputSimple(dictionary));
            });
        }else{
            return null;
        }

        return dictionaryList;
    }


    @Override
    public void updateDictionary() {

    }

    @Override
    public void deleteDictionary(String id) {
        dictionaryRepository.deleteById(id);
    }
}
