package cn.zhangjingyao.zjyrdf.service.demo;

import cn.zhangjingyao.zjyrdf.entity.PageData;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServiceTests {
    @Autowired
    private DemoService demoService;

    @Test
    public void listPageTest() throws Exception {
        PageData pd = new PageData();
        pd.put("pageNum", 1);
        pd.put("pageSize", 10);
        PageInfo<PageData> pageInfo = demoService.listPage(pd);
        System.out.println(JSON.toJSON(pageInfo));
    }
}
