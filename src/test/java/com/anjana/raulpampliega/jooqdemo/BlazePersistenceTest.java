package com.anjana.raulpampliega.jooqdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.anjana.raulpampliega.jooqdemo.model.EntityCte;
import com.anjana.raulpampliega.jooqdemo.repositories.BlazePersistenceRepositoryImpl;
import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;

public class BlazePersistenceTest extends BaseTest {

  private final BlazePersistenceRepositoryImpl repository;

  public BlazePersistenceTest(
      @Qualifier("BlazePersistenceRepository") CustomRepository repository) {
    super(repository);
    this.repository = (BlazePersistenceRepositoryImpl) repository;
  }

  @Test
  public void testGetAllVersionsFromEntity() {
    List<EntityCte> allVersions = repository.getAllVersions(1);
    assertNotNull(allVersions);
    List<Integer> actual = allVersions.stream().map(EntityCte::getId).collect(Collectors.toList());
    List<Integer> expected = Arrays.asList(4, 6);
    assertEquals(expected.size(), actual.size());
    assertTrue(actual.containsAll(expected));
    assertTrue(expected.containsAll(actual));
  }
}
