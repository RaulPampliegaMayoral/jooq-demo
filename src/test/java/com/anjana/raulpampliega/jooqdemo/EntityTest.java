package com.anjana.raulpampliega.jooqdemo;

import com.anjana.core.enums.State;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.model.RelationShip;
import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@Log4j2
@NoArgsConstructor
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, ExecutionTimeTestListener.class})
public class EntityTest {

  @Autowired
  @Qualifier("JPAStreamerRepositoryImpl")
  CustomRepository jpaStreamerRepository;

  @Autowired
  @Qualifier("JOOQRepositoryImpl")
  CustomRepository jooqEntityRepository;

  @Test
  void getEntitiesWithJPAStreamer() {
    List<Integer> idsToFind = Arrays.asList(7,8,9);
    List<Entity> entities = jpaStreamerRepository.getEntityByIds(idsToFind);

    Assertions.assertIterableEquals(idsToFind,
        entities.stream().map(Entity::getId).collect(Collectors.toList()));
  }

  @Test
  void getEntitiesWithJOOQ() {
    List<Integer> idsToFind = Arrays.asList(7,8,9);
    List<Entity> entities = jooqEntityRepository.getEntityByIds(idsToFind);

    Assertions.assertIterableEquals(idsToFind,
        entities.stream().map(Entity::getId).collect(Collectors.toList()));
  }

  @Test
  void getAllDSAFromSourceRelationship() {
    List<RelationShip> relations = jooqEntityRepository.findRelationByInputObject(256, "DSA");
    Assertions.assertNotNull(relations);
    Assertions.assertIterableEquals(Arrays.asList(174, 177), relations.stream().map(RelationShip::getId).collect(Collectors.toList()));
  }

  @Test
  void getRelationsShipDatasetFlagsWithJPAStremaer() {
    List<RelationShip> states = jpaStreamerRepository.getDatasetFlags("daniel.ruiz",251);
    Assertions.assertNotNull(states);
    Assertions.assertIterableEquals(Arrays.asList("APPROVED"), states.stream().map(RelationShip::getState).collect(Collectors.toList()));
  }

  @Test
  void getRelationsShipDatasetFlagsWithJOOQ() {
    List<RelationShip> states = jooqEntityRepository.getDatasetFlags("daniel.ruiz",251);
    Assertions.assertNotNull(states);
    Assertions.assertIterableEquals(Arrays.asList(State.APPROVED), states.stream().map(RelationShip::getState).collect(Collectors.toList()));
  }
}
