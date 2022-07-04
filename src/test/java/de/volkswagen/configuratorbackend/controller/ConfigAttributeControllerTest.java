package de.volkswagen.configuratorbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.volkswagen.configuratorbackend.model.CarConfigAttribute;
import de.volkswagen.configuratorbackend.model.Type;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConfigAttributeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String stringAttribute1;
    private String stringAttribute2;
    private String stringAttribute3;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        CarConfigAttribute attribute1 = new CarConfigAttribute("a1", Type.color);
        CarConfigAttribute attribute2 = new CarConfigAttribute("a2", Type.extra);
        CarConfigAttribute attribute3 = new CarConfigAttribute("a3", Type.engine);
        this.stringAttribute1 = mapper.writeValueAsString(attribute1);
        this.stringAttribute2 = mapper.writeValueAsString(attribute2);
        this.stringAttribute3 = mapper.writeValueAsString(attribute3);
    }


    @Test
    @Order(1)
    void saveAttribute() throws Exception {
        mockMvc.perform(post("/api/attribute").contentType(MediaType.APPLICATION_JSON).content(stringAttribute1)
        ).andExpect(status().isCreated());
        mockMvc.perform(post("/api/attribute").contentType(MediaType.APPLICATION_JSON).content(stringAttribute2)
        ).andExpect(status().isCreated());
        mockMvc.perform(post("/api/attribute").contentType(MediaType.APPLICATION_JSON).content(stringAttribute3)
        ).andExpect(status().isCreated());
    }

    @Test
    @Order(2)
    void saveExclusion() throws Exception {
        mockMvc.perform(post("/api/attribute/exclusion")
                        .param("attrId1", "1")
                        .param("attrId2", "2"))
                .andExpect(status().isCreated())
        ;
        mockMvc.perform(post("/api/attribute/exclusion")
                        .param("attrId1", "1")
                        .param("attrId2", "3"))
                .andExpect(status().isCreated())
        ;
    }

    @Test
    @Order(3)
    void getAttributesByType() throws Exception {
        mockMvc.perform(get("/api/attribute/type/{type}", "color"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize((1))))
                .andExpect(jsonPath("$[0].title").value("a1"))
        ;
    }

    @Test
    @Order(4)
    void getAttributeById() throws Exception {
        mockMvc.perform(get("/api/attribute/id/{id}", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("a2"))
        ;
    }

    @Test
    @Order(5)
    void getAttributes() throws Exception {
        mockMvc.perform(get("/api/attributes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[2].id").value("3"))
                .andExpect(jsonPath("$[0].title").value("a1"))
                .andExpect(jsonPath("$[1].title").value("a2"))
                .andExpect(jsonPath("$[2].title").value("a3"))
                .andExpect(jsonPath("$[0].exclusions", hasSize(2)))
                .andExpect(jsonPath("$[1].exclusions", hasSize(1)))
                .andExpect(jsonPath("$[2].exclusions", hasSize(1)))
                .andExpect(jsonPath("$[0].exclusions", containsInAnyOrder(2, 3)))
                .andExpect(jsonPath("$[1].exclusions", containsInAnyOrder(1)))
                .andExpect(jsonPath("$[2].exclusions", containsInAnyOrder(1)))
                .andDo(print())
        ;
    }
}