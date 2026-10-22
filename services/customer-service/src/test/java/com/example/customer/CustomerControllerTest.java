
package com.example.customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCustomers() throws Exception {
        mockMvc.perform(get("/api/customers"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(101))
            .andExpect(jsonPath("$[0].name").value("Alex Johnson"));
    }

    @Test
    void shouldReturnCustomerById() throws Exception {
        mockMvc.perform(get("/api/customers/102"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Sam Taylor"))
            .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void shouldReturn404ForUnknownCustomer() throws Exception {
        mockMvc.perform(get("/api/customers/999"))
            .andExpect(status().isNotFound());
    }
}
