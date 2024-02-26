package com.example.block7jpaconrelacionesyllamadasentremicros.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicProductoConfig {

    @Bean
    public NewTopic generateTopicProducto() {

        Map<String,String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE); //delete (Borra mensaje),compact(Mantiene el mas actual)
        configurations.put(TopicConfig.RETENTION_MS_CONFIG,"172800000"); //2 dias // Tiempo de retencion de mensajes -- Defecto -1, no se borran nunca
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824"); //1 GB // Tamaño maximo de segmento
        configurations.put(TopicConfig.MAX_MESSAGE_BYTES_DOC,"1048576"); //1 MB // Tamaño maximo de mensaje

        return TopicBuilder.name("producto")
                .partitions(2)
                .replicas(2)
                .configs(configurations)
                .build();
    }

}
