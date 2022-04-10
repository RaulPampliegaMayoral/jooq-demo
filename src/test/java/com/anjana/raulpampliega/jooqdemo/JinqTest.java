package com.anjana.raulpampliega.jooqdemo;

import com.anjana.raulpampliega.jooqdemo.repositories.CustomRepository;
import org.springframework.beans.factory.annotation.Qualifier;

public class JinqTest extends BaseTest {

  public JinqTest(@Qualifier("JINQRepository") CustomRepository repository) {
    super(repository);
  }
}
