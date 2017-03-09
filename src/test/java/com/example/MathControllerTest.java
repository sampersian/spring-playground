package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testPi () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/pi");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testAddition () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=add&x=1&y=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("1 + 2 = 3"));
    }

    @Test
    public void testDefault () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?x=1&y=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("1 + 2 = 3"));
    }
    @Test
    public void testSubtraction () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=subtract&x=2&y=1");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("2 - 1 = 1"));
    }
    @Test
    public void testMultiplication () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=multiply&x=3&y=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("3 * 2 = 6"));
    }
    @Test
    public void testDivision () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/math/calculate?operation=divide&x=6&y=2");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("6 / 2 = 3"));
    }
    @Test
    public void testSum () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/math/sum?n=1&n=2&n=888");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("891"));
    }
}
