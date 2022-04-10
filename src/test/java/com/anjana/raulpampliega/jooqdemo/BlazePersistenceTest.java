package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class BlazePersistenceTest extends BaseTest {

  public BlazePersistenceTest(
      @Qualifier("BlazePersistenceRepository") CustomRepository repository) {
    super(repository);
  }
}
