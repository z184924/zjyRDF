package cn.zhangjingyao.controller.system;

import cn.zhangjingyao.controller.base.BaseControllerTests;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTests extends BaseControllerTests {

    @Test
    public void testFormToken() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/formToken")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + accessToken)
                .content(JSON.toJSONString(requestData)) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println("result:" + getResultString(result));
        System.out.println("formToken:" + getResultValue(result, "data"));
    }

}
