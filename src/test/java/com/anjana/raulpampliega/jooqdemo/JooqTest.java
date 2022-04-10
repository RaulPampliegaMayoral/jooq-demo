package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class JooqTest extends BaseTest {

  public JooqTest(@Qualifier("JOOQRepository") CustomRepository repository) {
    super(repository);
  }
}
