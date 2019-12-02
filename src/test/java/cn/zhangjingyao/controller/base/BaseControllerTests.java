package cn.zhangjingyao.controller.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class BaseControllerTests {
    @Autowired
    public WebApplicationContext wac;
    @Autowired
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

    public Object getResultValue(ResultActions result, String key) throws UnsupportedEncodingException {
        String resultString = result.andReturn().getResponse().getContentAsString();
        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get(key);
    }

    public String getResultString(ResultActions result) throws UnsupportedEncodingException {
        return result.andReturn().getResponse().getContentAsString();
    }
}
