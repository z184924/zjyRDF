package cn.zhangjingyao.zjyrdf.service.ebsearch;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;

/**
 * @author zjy
 */
@org.springframework.stereotype.Service
public class EbSearchService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 获取智库专家列表
     * @param source
     * @param dim1
     * @param value
     * @param page
     * @param size
     * @param aggregationField
     * @param sortField
     * @return
     * @throws Exception
     */
    public SearchHits getPersonByEb(String source, String dim1, String value, int page, int size, String aggregationField, String sortField) throws Exception {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        List<QueryBuilder> filter = boolQueryBuilder.filter();
        filter.add(QueryBuilders.termQuery("labels.keyword", "source:" + source + " dim1:" + dim1 + " value:" + value));
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder)
                .addAggregation(AggregationBuilders.terms(aggregationField).field(aggregationField))
                .withSort(SortBuilders.fieldSort(sortField))
                .withPageable(PageRequest.of(page, size));
        NativeSearchQuery query = nativeSearchQueryBuilder.build();
        SearchHits<Id> searchHits = elasticsearchRestTemplate.search(query, Id.class);
        return searchHits;
    }

    @Document(indexName = "person_details_prod")
    private class Id {
        @org.springframework.data.annotation.Id
        @Field(type = FieldType.Auto)
        public String id;
    }
}
