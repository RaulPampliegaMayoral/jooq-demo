package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.repositories.CustomEntityRepository;
import com.anjana.raulpampliega.jooqdemo.repositories.JOOQEntityRepositoryImpl;
import com.anjana.raulpampliega.jooqdemo.repositories.JPAStreamerEntityRepositoryImpl;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Named;
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
  @Qualifier("JPAStreamerEntityRepositoryImpl")
  CustomEntityRepository jpaStreamerRepository;

  @Autowired
  @Qualifier("JOOQEntityRepositoryImpl")
  CustomEntityRepository jooqEntityRepository;

  @Test
  void getEntitiesWithJPAStreamer() {
    List<Integer> idsToFind = Arrays.asList(7,8,9);
    List<Entity> entities = jpaStreamerRepository.getByIds(idsToFind);

    Assertions.assertIterableEquals(idsToFind,
        entities.stream().map(Entity::getId).collect(Collectors.toList()));
  }

  @Test
  void getEntitiesWithJOOQ() {
    List<Integer> idsToFind = Arrays.asList(7,8,9);
    List<Entity> entities = jooqEntityRepository.getByIds(idsToFind);

    Assertions.assertIterableEquals(idsToFind,
        entities.stream().map(Entity::getId).collect(Collectors.toList()));
  }

}
