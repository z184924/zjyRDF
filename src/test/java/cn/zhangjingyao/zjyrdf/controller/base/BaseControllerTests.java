package cn.zhangjingyao.zjyrdf.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseControllerTests {
    @Autowired
    public WebApplicationContext wac;
    @Autowired
    @SuppressWarnings("unchecked")
    private FilterChainProxy springSecurityFilterChain;

    public String accessToken;

    public MockMvc mvc;

    @Before
    public void setupMockMvc() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build(); //初始化MockMvc对象
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/oauth/token")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("grant_type", "password")
                .param("username", "admin")
                .param("password", "admin")
                .param("scope", "all")
                .header("Authorization", "Basic Y2xpZW50OnNlY3JldA==")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        accessToken = getResultValue(result, "access_token").toString();
    }

    @After
    public void logout() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build(); //初始化MockMvc对象
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/oauth/logout")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + accessToken)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        accessToken = "";
    }

    public String getResultString(ResultActions result) throws UnsupportedEncodingException {
        return result.andReturn().getResponse().getContentAsString();
    }

    public Object getResultValue(ResultActions result, String key) throws UnsupportedEncodingException {
        String resultString = this.getResultString(result);
        return JSON.parseObject(resultString).get(key);
    }

    public String getFormToken() throws Exception {
        Map<String, Object> requestData = new HashMap<>();
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/formToken")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + accessToken)
                .content(JSON.toJSONString(requestData)) //传json参数
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        String resultString = this.getResultString(result);
        JSONObject jsonObject = JSON.parseObject(resultString);
        String formToken = jsonObject.getJSONObject("data").getString("formToken");
        return formToken;
    }
}
