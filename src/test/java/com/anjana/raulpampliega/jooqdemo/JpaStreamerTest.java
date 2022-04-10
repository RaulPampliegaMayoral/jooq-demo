package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class JpaStreamerTest extends BaseTest {

  public JpaStreamerTest(@Qualifier("JPAStreamerRepository") CustomRepository repository) {
    super(repository);
  }
}
