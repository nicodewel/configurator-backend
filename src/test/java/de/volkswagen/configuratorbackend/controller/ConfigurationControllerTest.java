package de.volkswagen.configuratorbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.volkswagen.configuratorbackend.model.CarConfig;
import de.volkswagen.configuratorbackend.model.CarConfigAttribute;
import de.volkswagen.configuratorbackend.model.Type;
import de.volkswagen.configuratorbackend.repository.CarConfigAttributeRepository;
import de.volkswagen.configuratorbackend.repository.ConfigurationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class ConfigurationControllerTest {

    private String stringConfig;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarConfigAttributeRepository carConfigAttributeRepository;

    @BeforeEach
    void setUp() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        CarConfig config = new CarConfig();
        CarConfigAttribute attribute1 = new CarConfigAttribute("a1", Type.color);
        carConfigAttributeRepository.save(attribute1);
        config.addCarConfigAttribute(attribute1);
        this.stringConfig = mapper.writeValueAsString(config);
    }

    @Test
    @Order(1)
    void postConfiguration() throws Exception {
        mockMvc.perform(post("/api/configuration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stringConfig)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.carConfigAttributes[0].title").value("a1"))
        ;
    }

    @Test
    @Order(2)
    void getConfigurationById() throws Exception {
        mockMvc.perform((get("/api/configuration/{id}","1")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.carConfigAttributes",hasSize(1)))
                .andExpect(jsonPath("$.carConfigAttributes[0].title").value("a1"))
        ;
    }

    @Test
    @Order(3)
    void getAllConfigurations() throws Exception {
        mockMvc.perform((get("/api/configurations")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
        ;
    }
}