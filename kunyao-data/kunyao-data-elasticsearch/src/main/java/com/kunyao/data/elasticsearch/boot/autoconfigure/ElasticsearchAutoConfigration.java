package com.kunyao.data.elasticsearch.boot.autoconfigure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.kunyao.repository.es"})
public class ElasticsearchAutoConfigration {

    @Bean
    public ElasticsearchOperations elasticsearchTemplate(ElasticsearchTemplate elasticsearchTemplate) {
        return elasticsearchTemplate;
    }
}
