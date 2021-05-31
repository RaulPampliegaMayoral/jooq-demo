package com.anjana.raulpampliega.jooqdemo.services;

import com.anjana.raulpampliega.jooqdemo.model.Entity;
import com.anjana.raulpampliega.jooqdemo.repositories.CustomEntityRepository;
import java.util.List;

public class EntityService {

  private final CustomEntityRepository repository;

  public EntityService(CustomEntityRepository repository) {
    this.repository = repository;
  }

  public List<Entity> getAllEntitiesFiltered(List<Integer> ids) {
    return repository.getByIds(ids);
  }
}
