package com.smart.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smart.Application;
import com.smart.dao.UserMapper;
import com.smart.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration

public class webTests {
    MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;
    String expectdJson;


    @Before
    public void setUp() throws JsonProcessingException{
        expectdJson="{\"userId\":1,\"userName\":\"admin\",\"password\":\"123456\",\"credits\":305,\"lastIp\":\"0:0:0:0:0:0:0:1\",\"lastVisit\":1517296562000}";

        mvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    protected String obj2Json(Object obj) throws JsonProcessingException{
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }


    @Test
   public void testUserContoller() throws Exception{
        String uri="/cs";
       MvcResult result=mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
       int status=result.getResponse().getStatus();
       String content=result.getResponse().getContentAsString();
       Assert.assertEquals("正确,返回的Http状态与预期的一致",200,status);
       Assert.assertEquals("正确,返回的json值与预期的一致",expectdJson,content);

   }


}
