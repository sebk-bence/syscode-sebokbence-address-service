package com.sc.sebokbence.scaddressservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.sc.sebokbence.scaddressservice.model.*;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;
import org.springframework.test.web.servlet.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ScAddressServiceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("integration-test")
class AddressServiceIT {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Autowired
  private MockMvc mvc;

  @Test
  void contextLoads() {
  }

  @Test
  void testGetAddress_givenAuthenticated_whenIdExists_thenSuccess() throws Exception {
    final String id = "eb281107-c753-44c5-a64b-9cbdf5705f4e";

    MvcResult result = mvc
        .perform(
            get("/address/" + id)
                .with(httpBasic("user1", "password1"))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andReturn();

    final String contentString = result.getResponse().getContentAsString();
    final Address content = MAPPER.readValue(contentString, new TypeReference<>() {});

    assertEquals(id, content.getId().toString());
    assertEquals("Budapest, Street 1", content.getAddress());
  }

  @Test
  void testGetAddress_givenAuthenticated_whenIdNotExists_thenFails() throws Exception {
    final String id = "13b859f9-dd9a-4ed4-8374-a2f14ad0500d";

    mvc
        .perform(
            get("/address/" + id)
                .with(httpBasic("user1", "password1"))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isNotFound());
  }

  @Test
  void testGetAddress_givenNotAuthenticated_whenIdExists_thenFails() throws Exception {
    final String id = "eb281107-c753-44c5-a64b-9cbdf5705f4e";

    mvc
        .perform(
            get("/address/" + id)
                .with(httpBasic("invalidUser", "invalidPassword"))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isUnauthorized());
  }

}
