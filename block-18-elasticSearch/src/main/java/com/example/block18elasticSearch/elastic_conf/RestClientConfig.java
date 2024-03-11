package com.example.block18elasticSearch.elastic_conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.lang.NonNull;

@Configuration
public class RestClientConfig extends ElasticsearchConfiguration {


    @Override
    @NonNull
    public ClientConfiguration clientConfiguration() {
        System.out.println("clientConfiguration");
        return ClientConfiguration.builder()
                .connectedToLocalhost()
                .build();
    }



}
