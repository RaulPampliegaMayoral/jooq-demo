package com.anjana.raulpampliega.jooqdemo;

import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Profile;

@Profile("test")
public class ImprovedH2Dialect extends H2Dialect {

  @Override
  public boolean dropConstraints() {
    return true;
  }

  @Override
  public boolean supportsIfExistsAfterAlterTable() {
    return true;
  }


}