package cn.zhangjingyao.zjyrdf.controller.demo;

import cn.zhangjingyao.zjyrdf.annotation.SystemLog;
import cn.zhangjingyao.zjyrdf.controller.base.BaseController;
import cn.zhangjingyao.zjyrdf.entity.PageData;
import cn.zhangjingyao.zjyrdf.service.ebsearch.EbSearchService;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名称:DemoController
 * 创建时间:2019-12-03
 *
 * @author CodeGenerator
 */
@Controller
@RequestMapping(value = "/ebSearch")
public class EbSearchController extends BaseController {
    @Resource(name = "ebSearchService")
    private EbSearchService ebSearchService;

    @SystemLog("根据查询智库")
    @ResponseBody
    @RequestMapping(value = "/findIdList", produces = "application/json;charset=UTF-8")
    public Object findById() throws Exception {
        SearchHits searchHits = ebSearchService.getPersonByEb("eb", "eb", "5e7c71ada7058c6e35113e14", 1, 20, "gender", "h_index");
        List<String> idList = new ArrayList<>();
        List<SearchHit> searchHitsList = searchHits.getSearchHits();
        for (SearchHit searchHit : searchHitsList) {
            idList.add(searchHit.getId());
        }
        Map<String, Aggregation> stringAggregationMap = searchHits.getAggregations().asMap();
        ParsedStringTerms gender = (ParsedStringTerms) stringAggregationMap.get("gender");
        List<? extends Terms.Bucket> buckets = gender.getBuckets();
        List<Map<String, Long>> aggregationResult = new ArrayList<>();
        for (Terms.Bucket bucket : buckets) {
            Map<String, Long> result = new HashMap<>(1);
            result.put(bucket.getKeyAsString(),bucket.getDocCount());
            aggregationResult.add(result);
        }
        PageData pageData = this.getPageData();
        pageData.put("personId", idList);
        pageData.put("aggregationResult", aggregationResult);
        return pageData;
    }
}
