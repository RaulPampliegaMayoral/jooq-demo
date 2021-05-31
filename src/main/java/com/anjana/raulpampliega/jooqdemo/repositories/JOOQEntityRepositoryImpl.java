package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooq.model.Tables;
import com.anjana.raulpampliega.jooqdemo.model.Entity;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JOOQEntityRepositoryImpl implements CustomEntityRepository {

  @Autowired
  DSLContext context;

  @Override
  public List<Entity> getByIds(List<Integer> ids) {
    return context.selectFrom(Tables.ENTITY)
        .where(Tables.ENTITY.ID_ENTITY.in(ids))
        .fetchInto(Entity.class);
  }
}
