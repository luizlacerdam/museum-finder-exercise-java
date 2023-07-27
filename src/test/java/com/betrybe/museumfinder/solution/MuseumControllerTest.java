package com.betrybe.museumfinder.solution;

import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MuseumControllerTest {

  @MockBean
  MuseumService museumService;

  @Autowired
  MockMvc mockMvc;

  @Test
  @DisplayName("Testa o retorno de rota de um museum pelo museum get by id.")
  public void getMuseumByIdTest() throws Exception {
    Museum museum = new Museum();
     museum.setId(51L);
     museum.setName("Museu do Una");
     museum.setDescription("Museu da várzea do una.");

    Mockito.when(
        museumService.getMuseum(any())
    ).thenReturn(
        museum
    );

    String url = "/museums/51";

    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(51L))
        .andExpect(jsonPath("$.name").value("Museu do Una"))
        .andExpect(jsonPath("$.description").value("Museu da várzea do una."));

  }
  @Test
  @DisplayName("Teste procura por um museum inexistente.")
  public void getByIdnotFound() throws Exception {

    Mockito.when(
        museumService.getMuseum(any())
    ).thenThrow(
        MuseumNotFoundException.class
    );

    String url = "/museums/51";

    mockMvc.perform(get(url))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Museu não encontrado!"));
  }

}
