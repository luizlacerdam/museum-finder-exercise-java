package com.betrybe.museumfinder.solution;

import static org.mockito.ArgumentMatchers.any;

import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Classe de tests.
 */

@SpringBootTest
@AutoConfigureMockMvc
public class CollectionTypeControllerTest {
  @MockBean
  CollectionTypeService collectionTypeService;

  @Autowired
  MockMvc mockMvc;


  @Test
  @DisplayName("Testa rota não encontrada porque resultado foi = a 0")
  public void rotaCollectionNotFound() throws Exception {

    String[] collectionTypes = {"kappa"};
    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(collectionTypes, 0);

    Mockito.when(
        collectionTypeService.countByCollectionTypes(anyString())
    ).thenReturn(
        collectionTypeCount
    );

    String url = "/collections/count/kappa";

    mockMvc.perform(get(url))
        .andExpect(status().isNotFound());

  }


  @Test
  @DisplayName("Testa se rota com parametro hist,imag retorna count 492.")
  public void testRotaCollectionComParams() throws Exception {

    String[] collectionTypes = {"hist", "imag"};

    CollectionTypeCount collectionTypeCount = new CollectionTypeCount(collectionTypes, 492);

    Mockito.when(
        collectionTypeService.countByCollectionTypes(anyString())
    ).thenReturn(
      collectionTypeCount
    );

    String url = "/collections/count/hist,imag";

    mockMvc.perform(get(url))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(492))
        .andExpect(jsonPath("$.collectionTypes[0]").value("hist"))
        .andExpect(jsonPath("$.collectionTypes[1]").value("imag"));

  }

}
