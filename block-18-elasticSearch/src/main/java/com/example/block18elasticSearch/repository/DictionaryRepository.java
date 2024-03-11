package com.example.block18elasticSearch.repository;

import com.example.block18elasticSearch.domain.Dictionary;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository extends ElasticsearchRepository<Dictionary, String> {
    @Query("{\"bool\": {\"should\": [{\"match\": {\"nombre\": \"?0\"}}, {\"match\": {\"explicacion\": \"?0\"}}]}}")
    Optional<List<Dictionary>> findByNombreOrExplicacion(String text);
}
