package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class JpaStreamerTest extends Base {

  public JpaStreamerTest(@Qualifier("JPAStreamerRepositoryImpl") CustomRepository repository) {
    super(repository);
  }
}
