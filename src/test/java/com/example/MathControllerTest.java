package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        RequestBuilder request = post("/math/sum?n=1&n=2&n=888");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("891"));
    }
    @Test
    public void testVolume1 () throws Exception {
        RequestBuilder request = post("/math/volume/3/4/5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
    }
    @Test
    public void testVolume2 () throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.patch("/math/volume/6/7/8");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }
    @Test
    public void testAreaCircle() throws Exception {

        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("shape", "circle")
                .param("radius", "4");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
    }
    @Test
    public void testAreaRectangle() throws Exception {

        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("shape", "rectangle")
                .param("width", "4")
                .param("height", "7");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }
    @Test
    public void testAreaInvalid() throws Exception {

        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("shape", "rectangle")
                .param("radius", "5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid"));
    }
}
