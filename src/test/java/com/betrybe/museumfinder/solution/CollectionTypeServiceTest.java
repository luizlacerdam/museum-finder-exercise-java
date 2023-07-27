package com.betrybe.museumfinder.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.dto.CollectionTypeCount;
import com.betrybe.museumfinder.service.CollectionTypeService;
import com.betrybe.museumfinder.service.MuseumService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class service.
 */
@SpringBootTest
public class CollectionTypeServiceTest {
  @Mock
  private MuseumFakeDatabase museumFakeDatabase;

  @InjectMocks
  private CollectionTypeService collectionTypeService;

  @Test
  @DisplayName("Testando camada de serviço com mock.")
  public void testCreateMuseum() {
    when(museumFakeDatabase.countByCollectionType("hist")).thenReturn(500L);

    CollectionTypeCount collectionTypeCount = collectionTypeService.countByCollectionTypes("hist");

    assertEquals(500L, collectionTypeCount.count());
    assertEquals(1, collectionTypeCount.collectionTypes().length);
    assertEquals("hist", collectionTypeCount.collectionTypes()[0]);


  }
}
