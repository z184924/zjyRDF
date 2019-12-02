package cn.zhangjingyao.controller.demo;

import cn.zhangjingyao.controller.base.BaseControllerTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTests extends BaseControllerTests {

    @Test
    public void listPageTest() throws Exception {
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/demo/listPage")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + accessToken)
                .param("pageNum", "1")
                .param("pageSize", "10")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println("result:" + getResultString(result));
    }

}
