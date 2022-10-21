package com.anjana.raulpampliega.jooqdemo.repositories;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import java.util.Collection;
import java.util.List;
import org.hibernate.stat.Statistics;

public interface CustomEntityRepository {

  Statistics getSessionStatistics();

  void saveAll(Collection<Entity> entities, int batchSize);

  List<Entity> findWithKeySetPagination(int last, int size);

  List<Entity> findWithKeySetPagination(int size);
}
