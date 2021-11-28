package com.anjana.raulpampliega.jooqdemo;

import com.anjana.core.customers.enums.State;
import com.anjana.raulpampliega.jooqdemo.model.AttributeValue;
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
public class JooqTest {

  @Autowired
  @Qualifier("JOOQRepositoryImpl")
  CustomRepository repository;

  @Test
  void getEntities() {
    List<Integer> idsToFind = Arrays.asList(7,8,9);
    List<Integer> entities = repository.getEntityByIds(idsToFind).stream()
        .map(Entity::getId).collect(Collectors.toList());

    Assertions.assertTrue(idsToFind.size() == entities.size());
    Assertions.assertTrue(idsToFind.containsAll(entities));
    Assertions.assertTrue(entities.containsAll(idsToFind));
  }

  @Test
  void getAllDSAFromSourceRelationship() {
    List<RelationShip> relations = repository.findRelationByInputObject(256, "DSA");
    Assertions.assertNotNull(relations);
    List<Integer> expected = Arrays.asList(174, 177);
    List<Integer> actual = relations.stream().map(RelationShip::getId).collect(Collectors.toList());
    Assertions.assertTrue(expected.size() == actual.size());
    Assertions.assertTrue(expected.containsAll(actual));
    Assertions.assertTrue(actual.containsAll(expected));
  }

  @Test
  void getRelationsShipDatasetFlags() {
    List<RelationShip> states = repository.getDatasetFlags("daniel.ruiz",251);
    Assertions.assertNotNull(states);
    Assertions.assertIterableEquals(Arrays.asList(State.APPROVED), states.stream().map(RelationShip::getState).collect(Collectors.toList()));
  }

  @Test
  void getAttributeValues() {
    List<AttributeValue> values = repository.getAllAttributeValues();
    Assertions.assertNotNull(values);
    Assertions.assertTrue(values.size() == 1500);
  }
}
