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

import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTests extends BaseControllerTests {
    String insertId;

//    @Test
    public void saveTest() throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/demo/save")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .header("Authorization", "Bearer " + accessToken)
                .param("formToken",this.getFormToken())
                .param("demoText1", "mvcTest-mvc测试")
                .param("demoText2", "mvcTest-mvc测试")
                .param("demoNumber1", "1")
                .param("demoNumber2", "1")
                .param("demoBoolean1", "false")
                .param("demoBoolean2", "false")
                .param("demoTime1", simpleDateFormat.format(new Date()))
                .param("demoTime2", simpleDateFormat.format(new Date()))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println("result:" + getResultString(result));
    }

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
