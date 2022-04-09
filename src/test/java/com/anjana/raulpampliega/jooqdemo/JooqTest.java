package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class JooqTest extends Base{

  public JooqTest(@Qualifier("JOOQRepositoryImpl") CustomRepository repository) {
    super(repository);
  }
}
