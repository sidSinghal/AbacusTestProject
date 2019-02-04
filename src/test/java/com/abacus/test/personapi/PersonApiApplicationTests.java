package com.abacus.test.personapi;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonApiApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void verifyAllPersonList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/persons").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(6))).andDo(print());
    }

    @Test
    public void verifyPersonById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/persons/p001").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.age").exists())
                .andExpect(jsonPath("$.locale").exists())
                .andExpect(jsonPath("$.id").value("p001"))
                .andExpect(jsonPath("$.name").value("Sid"))
                .andExpect(jsonPath("$.age").value(27))
                .andExpect(jsonPath("$.locale").value("Boston"))
                .andDo(print());

    }

    @Test
    public void ErrorCaseFindPersonNoId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test/persons/x").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Person does not exist"))
                .andDo(print());

    }

    @Test
    public void verifyAddPerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/test/persons/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"p006\", \"name\" : \"Sam\", \"age\" : \"25\", \"locale\" : \"LA\" }")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.age").exists())
                .andExpect(jsonPath("$.locale").exists())
                .andExpect(jsonPath("$.id").value("p006"))
                .andExpect(jsonPath("$.name").value("Sam"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.locale").value("LA"))
                .andExpect(status().isCreated());

    }

    @Test
    public void verifyUpdatePersonById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/test/persons/p006")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"p006\", \"name\" : \"Sam\", \"age\" : \"25\", \"locale\" : \"Boston\" }")
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.age").exists())
                .andExpect(jsonPath("$.locale").exists())
                .andExpect(jsonPath("$.id").value("p006"))
                .andExpect(jsonPath("$.name").value("Sam"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.locale").value("Boston"))
                .andExpect(status().isCreated());
    }

    @Test
    public void verifyDeletePerson() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/test/persons/p006").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200))
                .andDo(print());
    }

    @Test
    public void verifyDeletePersonInvalidId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/test/persons/x").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Person to remove does not exist"))
                .andDo(print());
    }

    @Test
    public void verifyDeletePersonAlreadyDeleted() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/test/persons/p008").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(404))
                .andExpect(jsonPath("$.message").value("Person to remove does not exist"))
                .andDo(print());
    }

}

