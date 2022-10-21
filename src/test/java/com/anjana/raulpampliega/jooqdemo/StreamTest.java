package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.repositories.EntityRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@TestExecutionListeners(value = {ExecutionTimeTestListener.class},
    mergeMode = MergeMode.MERGE_WITH_DEFAULTS)
@TestInstance(Lifecycle.PER_CLASS)
public class StreamTest {

  private static int NUMBER_OF_ENTITIES = 100000;

  @Autowired
  EntityRepository entityRepository;

  @BeforeAll
  public void initializeData() {
    entityRepository.saveAll(IntStream.range(0, NUMBER_OF_ENTITIES)
        .mapToObj(i -> {
          Entity e = new Entity();
          e.setId(11 + i);
          e.setName("Entity_" + i);
          return e;
        })
        .collect(Collectors.toList()), 1000);
  }

  @Test
  @Transactional
  void testStream() {
    try (Stream<Entity> data = entityRepository.findAllWithStream()) {
      Assertions.assertEquals(NUMBER_OF_ENTITIES, data.mapToInt(e -> 1).sum());
    }

    Assertions.assertEquals(1, entityRepository.getSessionStatistics().getQueryExecutionCount());
  }

  @Test
  @Transactional
  void testPaginationWithKeySet() {
    List<Entity> data = entityRepository.findWithKeySetPagination(1000);
    int cont = data.size();
    while (CollectionUtils.isNotEmpty(data)) {
      data = entityRepository.findWithKeySetPagination(data.get(data.size() - 1).getId(), 1000);
      cont += data.size();
    }

    Assertions.assertEquals(NUMBER_OF_ENTITIES, cont);
  }
}
